package com.porkerspicks.ppbf.betfair.entities;

import com.porkerspicks.ppbf.betfair.enums.OrderType;
import com.porkerspicks.ppbf.betfair.enums.Side;
import lombok.Data;

@Data
public class PlaceInstruction {

	private OrderType orderType;
	private long selectionId;
	private double handicap;
	private Side side;
	private LimitOrder limitOrder;
	private LimitOnCloseOrder limitOnCloseOrder;
	private MarketOnCloseOrder marketOnCloseOrder;
	private String customerOrderRef;
}
