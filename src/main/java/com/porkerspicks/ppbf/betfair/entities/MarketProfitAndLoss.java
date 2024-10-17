package com.porkerspicks.ppbf.betfair.entities;

import lombok.Data;

import java.util.List;

@Data
public class MarketProfitAndLoss {
    private String marketId;
    private double commissionApplied;
    private List<RunnerProfitAndLoss> profitAndLoss;
}
