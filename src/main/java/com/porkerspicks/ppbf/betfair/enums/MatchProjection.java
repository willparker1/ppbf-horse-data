package com.porkerspicks.ppbf.betfair.enums;

/*
NO_ROLLUP
No rollup, return raw fragments

ROLLED_UP_BY_PRICE
Rollup matched amounts by distinct matched prices per side.

ROLLED_UP_BY_AVG_PRICE
Rollup matched amounts by average matched price per side
 */
public enum MatchProjection {
	NO_ROLLUP, ROLLED_UP_BY_PRICE, ROLLED_UP_BY_AVG_PRICE;
}
