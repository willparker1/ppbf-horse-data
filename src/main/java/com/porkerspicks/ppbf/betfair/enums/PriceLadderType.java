package com.porkerspicks.ppbf.betfair.enums;

/*
CLASSIC
Price ladder increments traditionally used for Odds Markets.

FINEST
Price ladder with the finest available increment, traditionally used for
Asian Handicap markets.

LINE_RANGE
Price ladder used for LINE markets. Refer to MarketLineRangeInfo for more details.
 */
public enum PriceLadderType {

    CLASSIC,
    FINEST,
    LINE_RANGE;
}
