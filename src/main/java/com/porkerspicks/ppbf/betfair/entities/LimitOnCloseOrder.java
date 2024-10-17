package com.porkerspicks.ppbf.betfair.entities;

import lombok.Data;

@Data
public class LimitOnCloseOrder {
	private double liability;
	private double price;
}
