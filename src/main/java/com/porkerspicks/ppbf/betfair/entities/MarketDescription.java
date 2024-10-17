package com.porkerspicks.ppbf.betfair.entities;


import lombok.Data;

import java.util.Date;

@Data
public class MarketDescription {

	private Boolean persistenceEnabled;
	private Boolean bspMarket;
	private Date marketTime;
	private Date suspendTime;
	private Date settleTime;
	private String bettingType;
	private Boolean turnInPlayEnabled;
	private String marketType;
	private String regulator;
	private Double marketBaseRate;
	private Boolean discountAllowed;
	private String wallet;
	private String rules;
	private Boolean rulesHasDate;
	private String clarifications;
	private double eachWayDivisor;
	private MarketLineRangeInfo lineRangeInfo;
	private String raceType;
	private PriceLadderDescription priceLadderDescription;
}
