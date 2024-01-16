package com.routinise.exception;

public class InvalidRequest extends GlobalException{
    public InvalidRequest(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}