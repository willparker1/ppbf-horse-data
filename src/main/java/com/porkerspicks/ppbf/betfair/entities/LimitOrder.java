package com.porkerspicks.ppbf.betfair.entities;


import com.porkerspicks.ppbf.betfair.enums.BetTargetType;
import com.porkerspicks.ppbf.betfair.enums.PersistenceType;
import com.porkerspicks.ppbf.betfair.enums.TimeInForce;
import lombok.Data;

@Data
public class LimitOrder {

	private double size;
	private double price;
	private PersistenceType persistenceType;
	private TimeInForce timeInForce;
	private Double minFillSize;
	private BetTargetType betTargetType;
	private Double betTargetSize;
}
