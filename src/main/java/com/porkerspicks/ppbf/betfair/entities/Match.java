package com.porkerspicks.ppbf.betfair.entities;

import lombok.Data;

import java.util.Date;

@Data
public class Match {

	private String betId;
	private String matchId;
	private String side;
	private Double price;
	private Double Size;
	private Date matchDate;
}
