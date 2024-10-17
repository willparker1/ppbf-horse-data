package com.porkerspicks.ppbf.betfair.exceptions;

import com.porkerspicks.ppbf.betfair.enums.ApiNgErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class APINGException extends Throwable {

	private String errorDetails;
	private ApiNgErrorCode errorCode;
	private String requestUUID;

	public boolean invalidSession() {
		return errorCode == ApiNgErrorCode.INVALID_SESSION_INFORMATION;
	}
}