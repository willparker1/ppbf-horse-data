package com.porkerspicks.ppbf.betfair.entities;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MarketBook {
	private String marketId;
	private Boolean isMarketDataDelayed;
	private String status;
	private int betDelay;
	private Boolean bspReconciled;
	private Boolean complete;
	private Boolean inplay;
	private int numberOfWinners;
	private int numberOfRunners;
	private int numberOfActiveRunners;
	private Date lastMatchTime;
	private Double totalMatched;
	private Double totalAvailable;
	private Boolean crossMatching;
	private Boolean runnersVoidable;
	private Long version;
	private List<Runner> runners;
	private String keyLineDescription; // Added as new field in API docs, has custom type but probably not used. See API
}
