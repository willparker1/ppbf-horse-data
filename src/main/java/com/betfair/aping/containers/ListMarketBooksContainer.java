package com.betfair.aping.containers;

import com.porkerspicks.ppbf.betfair.entities.MarketBook;

import java.util.List;

public class ListMarketBooksContainer extends Container{
	
	private List<MarketBook> result;
		
	public List<MarketBook> getResult() {
		return result;
	}
	public void setResult(List<MarketBook> result) {
		this.result = result;
	}
}
