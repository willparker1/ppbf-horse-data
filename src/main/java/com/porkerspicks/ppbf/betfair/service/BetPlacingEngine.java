package com.porkerspicks.ppbf.betfair.service;

import com.porkerspicks.ppbf.auth.Authenticator;
import com.porkerspicks.ppbf.betfair.BetfairConfig;
import com.porkerspicks.ppbf.betfair.entities.MarketCatalogue;
import com.porkerspicks.ppbf.betfair.entities.MarketFilter;
import com.porkerspicks.ppbf.betfair.entities.TimeRange;
import com.porkerspicks.ppbf.betfair.enums.CountryCode;
import com.porkerspicks.ppbf.betfair.enums.MarketProjection;
import com.porkerspicks.ppbf.betfair.enums.MarketSort;
import com.porkerspicks.ppbf.file.FileStorage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This is a demonstration class to show a quick demo of the new Betfair API-NG.
 * When you execute the class will: <li>find a market (next horse race in the
 * UK)</li> <li>get prices and runners on this market</li> <li>place a bet on 1
 * runner</li> <li>handle the error</li>
 */
@Slf4j
@Component
public class BetPlacingEngine {

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
    public void placeBets() {

        MarketFilter marketFilter = new MarketFilter();
        Set<String> eventTypeIds = new HashSet<String>();
        eventTypeIds.add(EVENT_TYPE_HORSE_RACING);
        marketFilter.setEventTypeIds(eventTypeIds);

        Date now = new Date();
        log.debug("Time: " + now);
        TimeRange time = new TimeRange();
        Date startOfDay = DateUtils.setHours(now, 10);
        Date endOfDay = DateUtils.setHours(now, 23);
        time.setFrom(startOfDay);
        time.setTo(endOfDay);

        marketFilter.setMarketStartTime(time);
        Set<String> countries = new HashSet<String>();
        countries.add(CountryCode.GB.name());
        countries.add(CountryCode.IE.name());

        Set<String> typesCode = new HashSet<String>();
        typesCode.add("WIN");
        marketFilter.setMarketCountries(countries);
        marketFilter.setMarketTypeCodes(typesCode);

        Set<MarketProjection> marketProjection = new HashSet<MarketProjection>();
        marketProjection.add(MarketProjection.RUNNER_DESCRIPTION);

        List<MarketCatalogue> marketCatalogues = betfairService.listMarketCatalogue(marketFilter, marketProjection, MarketSort.FIRST_TO_START);

        fileStorage.saveList(marketCatalogues);
    }
}