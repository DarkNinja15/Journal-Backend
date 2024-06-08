package com.devwizards.journalApp.common;

public class ErrorResponse {
    private String error;

    public ErrorResponse(String message) {
        this.error = message;
    }

    public String getMessage() {
        return error;
    }

    public void setMessage(String message) {
        this.error = message;
    }
}