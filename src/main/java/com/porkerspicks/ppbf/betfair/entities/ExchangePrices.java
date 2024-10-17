package com.porkerspicks.ppbf.betfair.entities;

import lombok.Data;

import java.util.List;

@Data
public class ExchangePrices {

	private List<PriceSize> availableToBack;
	private List<PriceSize> availableToLay;
	private List<PriceSize> tradedVolume;
}
