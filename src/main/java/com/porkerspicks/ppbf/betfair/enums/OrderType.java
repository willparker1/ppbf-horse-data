package com.porkerspicks.ppbf.betfair.enums;

/*
LIMIT
A normal exchange limit order for immediate execution

LIMIT_ON_CLOSE
Limit order for the auction (SP)

MARKET_ON_CLOSE
Market order for the auction (SP)
 */
public enum OrderType {
	LIMIT, LIMIT_ON_CLOSE, MARKET_ON_CLOSE;
}
