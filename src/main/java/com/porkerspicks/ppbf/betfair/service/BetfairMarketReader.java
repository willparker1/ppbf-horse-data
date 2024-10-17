package com.porkerspicks.ppbf.betfair.service;

import com.porkerspicks.ppbf.auth.Authenticator;
import com.porkerspicks.ppbf.betfair.BetfairConfig;
import com.porkerspicks.ppbf.betfair.entities.*;
import com.porkerspicks.ppbf.betfair.enums.*;
import com.porkerspicks.ppbf.file.FileStorage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This is a demonstration class to show a quick demo of the new Betfair API-NG.
 * When you execute the class will: <li>find a market (next horse race in the
 * UK)</li> <li>get prices and runners on this market</li> <li>place a bet on 1
 * runner</li> <li>handle the error</li>
 * 
 */
@Slf4j
@Component
public class BetfairMarketReader {

	private static String EVENT_TYPE_HORSE_RACING = "7";

	@Autowired
	private BetfairService betfairService;

	@Autowired
	private BetfairConfig betfairConfig;

	@Autowired
	private Authenticator authenticator;

	@Autowired
	private FileStorage fileStorage;


//	@Scheduled(fixedDelay = 30000)
    public List<MarketCatalogue> getTodaysMarketCatalogues() {

		MarketFilter marketFilter = new MarketFilter();
		marketFilter.setEventTypeIds( createEventTypeIds() );
		marketFilter.setMarketStartTime( createTimeRange() );
		marketFilter.setMarketCountries( createCountrySet() );
		marketFilter.setMarketTypeCodes( createTypesCodeSet() );

		Set<MarketProjection> marketProjection = new HashSet<>();
		marketProjection.add(MarketProjection.RUNNER_DESCRIPTION);
		marketProjection.add(MarketProjection.EVENT);
		marketProjection.add(MarketProjection.MARKET_START_TIME);
		marketProjection.add(MarketProjection.MARKET_DESCRIPTION);
		marketProjection.add(MarketProjection.COMPETITION);
		marketProjection.add(MarketProjection.RUNNER_METADATA);

		List<MarketCatalogue> marketCatalogues = betfairService.listMarketCatalogue(marketFilter, marketProjection, MarketSort.FIRST_TO_START);

		fileStorage.saveList(marketCatalogues);

		return marketCatalogues;
    }

	private TimeRange createTimeRange() {
		Date now = new Date();
		log.debug("Time: " + now);
		TimeRange time = new TimeRange();
		Date startOfDay = DateUtils.setHours(now, 10);
		Date endOfDay = DateUtils.setHours(now, 23);
		time.setFrom(startOfDay);
		time.setTo(endOfDay);
		return time;
	}

	private Set<String> createCountrySet() {
		Set<String> countries = new HashSet<>();
		countries.add(CountryCode.GB.name());
		countries.add(CountryCode.IE.name());
		return countries;
	}

	private Set<String> createTypesCodeSet() {
		Set<String> typesCode = new HashSet<>();
		typesCode.add("WIN");
		return typesCode;
	}

	private Set<String> createEventTypeIds() {
		Set<String> eventTypeIds = new HashSet<String>();
		eventTypeIds.add(EVENT_TYPE_HORSE_RACING);
		return eventTypeIds;
	}

	//	@Scheduled(fixedDelay = 30000)
	public void getTodaysMarketBooks() {

		List<String> marketIds = getTodaysMarketIds();

		PriceProjection priceProjection = new PriceProjection();
		OrderProjection orderProjection = OrderProjection.ALL;
		MatchProjection matchProjection = MatchProjection.NO_ROLLUP;

		List<MarketBook> marketBooks = betfairService.listMarketBook( marketIds, priceProjection, orderProjection,
				matchProjection);

		fileStorage.saveList(marketBooks);
	}

	public void getRunnerBooks(List<String> marketIds) {

		PriceProjection priceProjection = new PriceProjection();
		Set<PriceData> priceDataList = new HashSet<PriceData>();
		priceDataList.add(PriceData.EX_ALL_OFFERS);
		//priceDataList.add(PriceData.EX_BEST_OFFERS);
		priceDataList.add(PriceData.EX_TRADED);
		priceProjection.setPriceData(priceDataList);
		priceProjection.setVirtualise(true);
//		ExBestOfferOverRides exBestOfferOverRides = new ExBestOfferOverRides();
//		exBestOfferOverRides.setBestPricesDepth(5);
//		priceProjection.setExBestOfferOverRides(exBestOfferOverRides);
		OrderProjection orderProjection = OrderProjection.ALL;
		MatchProjection matchProjection = MatchProjection.ROLLED_UP_BY_AVG_PRICE;


		List<MarketBook> runnerBooks = null;

		List<MarketCatalogue> marketCatalogues = fileStorage.readTodaysMarketCatalogues();

		Map<String, MarketCatalogue> marketCatalogueMap = marketCatalogues.stream()
		 		.collect(Collectors.toMap(MarketCatalogue::getMarketId, marketCatalogue -> marketCatalogue));

		for (String marketId : marketIds) {
			MarketCatalogue marketCatalogue = marketCatalogueMap.get(marketId);
			for (RunnerCatalog runnerCatalog : marketCatalogue.getRunners()) {
				Long selectionId = runnerCatalog.getSelectionId();
				runnerBooks = betfairService.listRunnerBook( marketId, selectionId, priceProjection, orderProjection,
						matchProjection);
				fileStorage.saveList(String.valueOf(selectionId), runnerBooks);
			}
		}

	}

	@Cacheable("marketIds")
	public List<String> getTodaysMarketIds() {

		List<MarketCatalogue> marketCatalogues = fileStorage.readTodaysMarketCatalogues();
		if ( marketCatalogues == null ) {
			marketCatalogues = getTodaysMarketCatalogues();
		}
		// Get list of market ids from list of market catalogues
		List<String> marketIds = marketCatalogues.stream()
				.map(MarketCatalogue::getMarketId)
				.collect(Collectors.toList());
		return marketIds;
	}
}