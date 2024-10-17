package com.porkerspicks.ppbf.betfair.entities;

import lombok.Data;

import java.util.List;

@Data
public class ClearedOrderSummaryReport {

	private List<ClearedOrderSummary> clearedOrders;
	private boolean moreAvailable;
}
