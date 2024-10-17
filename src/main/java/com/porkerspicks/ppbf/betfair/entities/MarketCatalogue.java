package com.porkerspicks.ppbf.betfair.entities;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MarketCatalogue {

	private String marketId;
	private String marketName;
	private Date marketStartTime;
	private MarketDescription description;
	private Double totalMatched;
	private List<RunnerCatalog> runners = null;
	private EventType eventType;
	private Competition competition;
	private Event event;
}
