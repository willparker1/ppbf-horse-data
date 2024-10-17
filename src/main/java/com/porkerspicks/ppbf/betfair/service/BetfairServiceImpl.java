package com.porkerspicks.ppbf.betfair.service;

import com.porkerspicks.ppbf.betfair.BetfairConfig;
import com.porkerspicks.ppbf.betfair.entities.*;
import com.porkerspicks.ppbf.betfair.enums.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.porkerspicks.ppbf.util.HttpUtil;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BetfairServiceImpl implements BetfairService {

	private final HttpUtil httpUtil;

	private BetfairServiceImpl(BetfairConfig betfairConfig, HttpUtil httpUtil, ObjectMapper objectMapper) {
		this.httpUtil = httpUtil;
	}

	@Override
	public List<EventTypeResult> listEventTypes(MarketFilter filter ) {
		  Map<String, Object> params = new HashMap<>();
		  params.put(FILTER, filter);

		  return Arrays.asList( httpUtil.sendPostRequest(params, ApiNgOperation.LISTEVENTTYPES ));
	}

	@Override
	public List<MarketCatalogue> listMarketCatalogue(MarketFilter filter, Set<MarketProjection> marketProjection,
													 MarketSort sort) {
		Map<String, Object> params = new HashMap<>();
		params.put(FILTER, filter);
		params.put(SORT, sort);
		params.put(MAX_RESULT, 999);
		params.put(MARKET_PROJECTION, marketProjection);

		return Arrays.asList( httpUtil.sendPostRequest(params, ApiNgOperation.LISTMARKETCATALOGUE ));
	}

	@Override
	public List<MarketBook> listMarketBook(List<String> marketIds, PriceProjection priceProjection,
										   OrderProjection orderProjection, MatchProjection matchProjection) {
		Map<String, Object> params = new HashMap<>();
		params.put(MARKET_IDS, marketIds);
		params.put(PRICE_PROJECTION, priceProjection);
		params.put(ORDER_PROJECTION, orderProjection);
		params.put(MATCH_PROJECTION, matchProjection);

		return Arrays.asList( httpUtil.sendPostRequest(params, ApiNgOperation.LISTMARKETBOOK ));
	}

	@Override
	public List<MarketBook> listRunnerBook(String marketId, long selectionId, PriceProjection priceProjection,
										   OrderProjection orderProjection, MatchProjection matchProjection) {
		Map<String, Object> params = new HashMap<>();
		params.put(MARKET_ID, marketId);
		params.put(SELECTION_ID, selectionId);
		params.put(PRICE_PROJECTION, priceProjection);
		params.put(ORDER_PROJECTION, orderProjection);
		params.put(MATCH_PROJECTION, matchProjection);

		return Arrays.asList( httpUtil.sendPostRequest(params, ApiNgOperation.LISTRUNNERBOOK ));
	}
}
