package com.porkerspicks.ppbf.betfair.entities;

import lombok.Data;

import java.util.Date;

@Data
public class ItemDescription {

	private String eventTypeDesc;
	private String eventDesc;
	private String marketDesc;
	private String marketType;	
	private Date marketStartTime;
	private String runnerDesc;
	private int numberOfWinners;
	private double eachWayDivisor;
}
