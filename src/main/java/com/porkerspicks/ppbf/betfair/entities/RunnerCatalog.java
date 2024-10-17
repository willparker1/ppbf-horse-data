package com.porkerspicks.ppbf.betfair.entities;

import lombok.Data;

import java.util.Map;

@Data
public class RunnerCatalog {

	private Long selectionId;
	private String runnerName;
	private Double handicap;
	private int sortPriority;
	private Map<String,String> metadata;
}
