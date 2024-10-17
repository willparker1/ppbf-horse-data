package com.porkerspicks.ppbf.betfair.entities;

import lombok.Data;

import java.util.List;

@Data
public class StartingPrices {
	private Double nearPrice;
	private Double farPrice;
	private List<PriceSize> backStakeTaken = null;
	private List<PriceSize> layLiabilityTaken = null;
	private Double actualSP;
}
