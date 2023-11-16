package com.karan.CarRentalService.Error;

public class CarTakenException extends Exception
{
    public CarTakenException() {
        super();
    }

    public CarTakenException(String message) {
        super(message);
    }

    public CarTakenException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarTakenException(Throwable cause) {
        super(cause);
    }

    protected CarTakenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
