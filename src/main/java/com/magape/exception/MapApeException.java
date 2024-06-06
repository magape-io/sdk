package com.magape.exception;

public class MapApeException extends RuntimeException {

    private String message;

    public MapApeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
