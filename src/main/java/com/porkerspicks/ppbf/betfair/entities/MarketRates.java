package com.porkerspicks.ppbf.betfair.entities;

import lombok.Data;

@Data
public class MarketRates {

    private double marketBaseRate;
    private boolean discountAllowed;
}
