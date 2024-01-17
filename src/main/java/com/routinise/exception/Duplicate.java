package com.routinise.exception;

public class Duplicate extends GlobalException{
    public Duplicate(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 409;
    }
}
