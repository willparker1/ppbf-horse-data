package com.porkerspicks.ppbf.betfair.entities;

import lombok.Data;

import java.util.Date;

@Data
public class Order {

	private String betId;
	private String orderType;
	private String status;
	private String persistenceType;
	private String side;
	private Double price;
	private Double size;
	private Double bspLiability;
	private Date placedDate;
	private Double avgPriceMatched;
	private Double sizeMatched;
	private Double sizeRemaining;
	private Double sizeLapsed;
	private Double sizeCancelled;
	private Double sizeVoided;
	private String customerOrderRef; // In API docs
	private String customerStrategyRef; // In API docs
}
