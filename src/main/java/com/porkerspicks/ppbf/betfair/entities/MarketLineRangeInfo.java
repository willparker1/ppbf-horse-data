package com.porkerspicks.ppbf.betfair.entities;

import lombok.Data;

/*
This class added from new API docs.

maxUnitValue
maxPrice - Maximum value for the outcome, in market units for this market (eg 100 runs)

minUnitValue
minPrice - Minimum value for the outcome, in market units for this market (eg 0 runs)

interval
interval - The odds ladder on this market will be between the range of minUnitValue and maxUnitValue, in increments of the inverval value.e.g. If minUnitValue=10 runs, maxUnitValue=20 runs, interval=0.5 runs, then valid odds include 10, 10.5, 11, 11.5 up to 20 runs.

marketUnit
unit - The type of unit the lines are incremented in by the interval (e.g: runs, goals or seconds.
 */
@Data
public class MarketLineRangeInfo {

    private double maxUnitValue;
    private double minUnitValue;
    private double interval;
    private String marketUnit;
}
