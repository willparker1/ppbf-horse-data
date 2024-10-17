package com.porkerspicks.ppbf.betfair.entities;

import com.porkerspicks.ppbf.betfair.enums.RollupModel;
import lombok.Data;

@Data
public class ExBestOfferOverRides {

	private int bestPricesDepth;
	private RollupModel rollupModel;
	private int rollupLimit;
	private double rollupLiabilityThreshold;
	private int rollupLiabilityFactor;
}
