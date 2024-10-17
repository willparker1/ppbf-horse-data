package com.porkerspicks.ppbf.betfair.enums;

/*
COMPETITION
If not selected then the competition will not be returned with marketCatalogue

EVENT
If not selected then the event will not be returned with marketCatalogue

EVENT_TYPE
If not selected then the eventType will not be returned with marketCatalogue

MARKET_START_TIME
If not selected then the start time will not be returned with marketCatalogue

MARKET_DESCRIPTION
If not selected then the description will not be returned with marketCatalogue

RUNNER_DESCRIPTION
If not selected then the runners will not be returned with marketCatalogue

RUNNER_METADATA
If not selected then the runner metadata will not be returned with marketCatalogue. If selected then RUNNER_DESCRIPTION will also be returned regardless of whether it is included as a market projection.
 */
public enum MarketProjection {
	COMPETITION, EVENT, EVENT_TYPE, MARKET_START_TIME, MARKET_DESCRIPTION, RUNNER_DESCRIPTION, RUNNER_METADATA;
}
