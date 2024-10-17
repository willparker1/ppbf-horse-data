package com.porkerspicks.ppbf.betfair.entities;

import com.porkerspicks.ppbf.betfair.enums.LoginErrorCode;
import com.porkerspicks.ppbf.betfair.enums.LoginStatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

	private String sessionToken;
	private String product;
	private LoginStatusCode loginStatus;
	private LoginErrorCode error;

	public boolean isSuccessful() {
		return loginStatus == LoginStatusCode.SUCCESS;
	}

	public boolean isNotSuccessful() {
		return loginStatus != LoginStatusCode.SUCCESS;
	}
}
