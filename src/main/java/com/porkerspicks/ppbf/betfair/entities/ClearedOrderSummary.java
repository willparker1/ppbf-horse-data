package com.porkerspicks.ppbf.betfair.entities;

import com.porkerspicks.ppbf.betfair.enums.OrderType;
import com.porkerspicks.ppbf.betfair.enums.PersistenceType;
import com.porkerspicks.ppbf.betfair.enums.Side;
import lombok.Data;

import java.util.Date;

@Data
public class ClearedOrderSummary {
	
	private String eventTypeId;
	private String eventId;
	private String marketId;
	private long selectionId;
	private double handicap;
	private String betId;
	private Date placedDate;
	private PersistenceType persistenceType;
	private OrderType orderType;
	private Side side;
	private ItemDescription itemDescription;
	private String betOutcome;
	private double priceRequested;
	private Date settledDate;
	private Date lastMatchedDate;
	private int betCount;
	private double commission;
	private double priceMatched;
	private boolean priceReduced;
	private double sizeSettled;
	private double profit;
	private double sizeCancelled;
	private String customerOrderRef;
	private String customerStrategyRef;
}
