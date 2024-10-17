package com.porkerspicks.ppbf.betfair.enums;

/*
SUCCESS
Order processed successfully

FAILURE
Order failed.

PROCESSED_WITH_ERRORS
The order itself has been accepted, but at least one (possibly all) actions have generated errors. This error only occurs for replaceOrders, cancelOrders and updateOrders operations.
In normal circumstances the placeOrders operation will not return PROCESSED_WITH_ERRORS status as it is an atomic operation.  PLEASE NOTE: if the 'Best Execution' features is switched off, placeOrders can return ‘PROCESSED_WITH_ERRORS’ meaning that some bets can be rejected and other placed when submitted in the same PlaceInstruction

TIMEOUT
The order timed out & the status of the bet is unknown.  If a TIMEOUT error occurs on a placeOrders/replaceOrders request, you should check listCurrentOrders to verify the status of your bets before placing further orders. Please Note: Timeouts will occur after 5 seconds of attempting to process the bet but please allow up to 15 seconds for a timed out order to appear. After this time any unprocessed bets will automatically be Lapsed and no longer be available on the Exchange.
 */
public enum ExecutionReportStatus {
	SUCCESS, FAILURE, PROCESSED_WITH_ERRORS, TIMEOUT;
}
