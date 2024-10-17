package com.porkerspicks.ppbf.betfair.entities;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class Runner {
	private Long selectionId;
	private Double handicap;
	private String status;
	private Double adjustmentFactor;
	private Double lastPriceTraded;
	private Double totalMatched;
	private Date removalDate;
	private StartingPrices sp;
	private ExchangePrices ex;
	private List<Order> orders;
	private List<Match> matches;
	private Map<String,String> matchesByStrategy; // Added as new field in API docs. Custom type in docs but probably not used. See API
}
