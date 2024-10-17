package com.porkerspicks.ppbf.betfair.entities;

import com.porkerspicks.ppbf.betfair.enums.ExecutionReportErrorCode;
import com.porkerspicks.ppbf.betfair.enums.ExecutionReportStatus;
import lombok.Data;

import java.util.List;

@Data
public class PlaceExecutionReport {

	private String customerRef;
	private ExecutionReportStatus status;
	private ExecutionReportErrorCode errorCode;
	private String marketId;
	private List<PlaceInstructionReport> instructionReports;
}
