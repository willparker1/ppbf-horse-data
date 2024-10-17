package com.porkerspicks.ppbf.betfair.entities;

import com.porkerspicks.ppbf.betfair.enums.InstructionReportErrorCode;
import com.porkerspicks.ppbf.betfair.enums.InstructionReportStatus;
import lombok.Data;

import java.util.Date;

@Data
public class PlaceInstructionReport {
	private InstructionReportStatus status;
	private InstructionReportErrorCode errorCode;
	private PlaceInstruction instruction;
	private String betId;
	private Date placedDate;
	private double averagePriceMatched; // This has custom type on Reference docs although they don't actually exist
	private double sizeMatched; // This has custom type on Reference docs although they don't actually exist
}
