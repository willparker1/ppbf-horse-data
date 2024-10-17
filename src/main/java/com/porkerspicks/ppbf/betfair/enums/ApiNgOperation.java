package com.porkerspicks.ppbf.betfair.enums;

import com.porkerspicks.ppbf.betfair.entities.*;

import java.util.List;

public enum ApiNgOperation {
	LISTEVENTTYPES("listEventTypes", EventTypeResult[].class),
	LISTCOMPETITIONS("listCompetitions", CompetitionResult[].class),
	LISTTIMERANGES("listTimeRanges", TimeRangeResult[].class),
	LISTEVENTS("listEvents", EventResult[].class),
	LISTMARKETTYPES("listMarketTypes", MarketTypeResult[].class),
	LISTCOUNTRIES("listCountries", CountryCodeResult[].class),
	LISTVENUES("listVenues", VenueResult[].class),
	LISTMARKETCATALOGUE("listMarketCatalogue", MarketCatalogue[].class),
	LISTMARKETBOOK("listMarketBook", MarketBook[].class),
	LISTRUNNERBOOK("listRunnerBook", MarketBook[].class),
	LISTCLEAREDORDERS("listClearedOrders", ClearedOrderSummaryReport.class),
	PLACORDERS("placeOrders", PlaceExecutionReport.class);
	
	private String operationName;
	private Class<?> responseClass;
	
	private ApiNgOperation(String operationName, Class<?> response) {
		this.operationName = operationName;
		this.responseClass = response;
	}

	public String getOperationName() {
		return operationName;
	}

	public Class<?> getResponseClass() {
		return responseClass;
	}
}
