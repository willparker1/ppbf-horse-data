package com.porkerspicks.ppbf.betfair.entities;

import lombok.Data;

import java.util.Date;

@Data
public class Event {

	private String id;
	private String name;
	private String countryCode;
	private String timezone;
	private String venue;
	private Date openDate;
}
