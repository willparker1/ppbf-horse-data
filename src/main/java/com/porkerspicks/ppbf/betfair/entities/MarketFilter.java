package com.porkerspicks.ppbf.betfair.entities;

import com.porkerspicks.ppbf.betfair.enums.MarketBettingType;
import com.porkerspicks.ppbf.betfair.enums.OrderStatus;
import lombok.Data;

import java.util.Set;

@Data
public class MarketFilter {

	private String textQuery;
	private Set<String> exchangeIds;
	private Set<String> eventTypeIds;
	private Set<String> marketIds;
	private Boolean inPlayOnly;
	private Set<String> eventIds;
	private Set<String> competitionIds;
	private Set<String> venues;
	private Boolean bspOnly;
	private Boolean turnInPlayEnabled;
	private Set<MarketBettingType> marketBettingTypes;
	private Set<String> marketCountries;
	private Set<String> marketTypeCodes;
	private TimeRange marketStartTime;
	private Set<OrderStatus> withOrders;
	private Set<String> raceTypes;  // Added as new field in API docs
}