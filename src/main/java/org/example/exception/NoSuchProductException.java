package org.example.exception;

public class NoSuchProductException extends Exception {

    public NoSuchProductException(String productName) {
        super(String.format("Указан несуществующий товар \"%s\"", productName));
    }

    public NoSuchProductException() {
        super("Указан несуществующий товар.");
    }
}