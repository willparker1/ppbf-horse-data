package com.porkerspicks.ppbf.betfair.entities;

import com.porkerspicks.ppbf.betfair.enums.PriceData;
import lombok.Data;

import java.util.Set;

@Data
public class PriceProjection {
	private Set<PriceData> priceData;
	private ExBestOfferOverRides exBestOfferOverRides;
	private boolean virtualise;
	private boolean rolloverStakes;
}
