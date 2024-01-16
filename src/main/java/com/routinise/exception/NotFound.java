package com.routinise.exception;

public class NotFound extends GlobalException{
    public NotFound(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
