package com.porkerspicks.ppbf.betfair.entities;

import lombok.Data;

@Data
public class RunnerProfitAndLoss {
    private long selectionId;
    private double ifWin;
    private double ifLose;
    private double ifPlace;
}
