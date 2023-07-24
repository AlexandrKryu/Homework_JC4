package org.example.exception;

public class NoSuchCustomerException extends Exception {

    public NoSuchCustomerException(String customerName) {
        super(String.format("Указан незарегистрированный покупатель \"%s\"", customerName));
    }

    public NoSuchCustomerException() {
        super("Указан незарегистрированный покупатель.");
    }
}