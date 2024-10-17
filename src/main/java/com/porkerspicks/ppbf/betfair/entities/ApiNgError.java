package com.porkerspicks.ppbf.betfair.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.porkerspicks.ppbf.betfair.exceptions.APINGException;
import lombok.Data;

@Data
public class ApiNgError {

    private String faultCode;

    private String faultString;

    private ApiNgDetail detail;

    @Data
    private class ApiNgDetail {
        private APINGException APINGException;
    }

    public APINGException getApingException() {
        return detail.getAPINGException();
    }

    public void setApingException(APINGException apingException) {
        if (detail == null) {
            detail = new ApiNgDetail();
        }
        detail.setAPINGException(apingException);
    }
}