package com.porkerspicks.ppbf.betfair.enums;

/*
MINIMUM_TRADED
Minimum traded volume

MAXIMUM_TRADED
Maximum traded volume

MINIMUM_AVAILABLE
Minimum available to match

MAXIMUM_AVAILABLE
Maximum available to match

FIRST_TO_START
The closest markets based on their expected start time

LAST_TO_START
The most distant markets based on their expected start time
 */
public enum MarketSort {
	MINIMUM_TRADED, MAXIMUM_TRADED, MINIMUM_AVAILABLE, MAXIMUM_AVAILABLE, FIRST_TO_START, LAST_TO_START;
}
