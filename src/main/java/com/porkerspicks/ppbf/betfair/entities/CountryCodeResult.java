package com.porkerspicks.ppbf.betfair.entities;

import com.porkerspicks.ppbf.betfair.enums.CountryCode;
import lombok.Data;

@Data
public class CountryCodeResult {
	private CountryCode countryCode;
	private int marketCount;
}
