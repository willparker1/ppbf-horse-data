package com.porkerspicks.ppbf.betfair.entities;

import lombok.Data;

@Data
public class MarketLicense {
    private String wallet;
    private String rules;
    private boolean rulesHasDate;
    private String clarifications;
}
