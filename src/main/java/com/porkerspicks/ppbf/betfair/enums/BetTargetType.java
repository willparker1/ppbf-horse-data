package com.porkerspicks.ppbf.betfair.enums;

/*
BACKERS_PROFIT
The payout requested minus the calculated size at which this LimitOrder is to be placed. BetTargetType bets are invalid for LINE markets

PAYOUT
The total payout requested on a LimitOrder
 */
public enum BetTargetType {
    BACKERS_PROFIT,
    PAYOUT
}
