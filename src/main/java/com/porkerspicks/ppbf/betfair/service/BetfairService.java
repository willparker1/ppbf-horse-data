package com.porkerspicks.ppbf.betfair.service;

import com.porkerspicks.ppbf.betfair.entities.*;
import com.porkerspicks.ppbf.betfair.enums.MarketProjection;
import com.porkerspicks.ppbf.betfair.enums.MarketSort;
import com.porkerspicks.ppbf.betfair.enums.MatchProjection;
import com.porkerspicks.ppbf.betfair.enums.OrderProjection;

import java.util.List;
import java.util.Set;

public interface BetfairService {

    String FILTER = "filter";
    String LOCALE = "locale";
    String SORT = "sort";
    String MAX_RESULT = "maxResults";
    String MARKET_IDS = "marketIds";
    String MARKET_ID = "marketId";
    String SELECTION_ID = "selectionId";
    String MATCHED_SINCE = "matchedSince";
    String BET_IDS = "betIds";
    String INSTRUCTIONS = "instructions";
    String CUSTOMER_REF = "customerRef";
    String MARKET_PROJECTION = "marketProjection";
    String PRICE_PROJECTION = "priceProjection";
    String MATCH_PROJECTION = "matchProjection";
    String ORDER_PROJECTION = "orderProjection";
    String BET_STATUS = "betStatus";
    String EVENT_TYPE_IDS = "eventTypeIds";
    String SETTLED_DATE_RANGE = "settledDateRange";
    String CUSTOMER_ORDER_REFS = "customerOrderRefs";
    String FROM_RECORD = "fromRecord";
    String RECORD_COUNT = "recordCount";
    String ITEM_DATA_RANGE = "itemDateRange";
    String INCLUDE_ITEM = "includeItem";
    String WALLET = "wallet";


    List<EventTypeResult> listEventTypes(MarketFilter filter);

    List<MarketBook> listMarketBook(List<String> marketIds, PriceProjection priceProjection,
                                    OrderProjection orderProjection, MatchProjection matchProjection);

    List<MarketBook> listRunnerBook(String marketId, long selectionId, PriceProjection priceProjection,
                                    OrderProjection orderProjection, MatchProjection matchProjection);

    List<MarketCatalogue> listMarketCatalogue(MarketFilter filter, Set<MarketProjection> marketProjection,
                                              MarketSort sort);
}
