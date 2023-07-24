package org.example.exception;

public class IncompleteOrderException extends Exception {

    public IncompleteOrderException() {
        super("Нельзя завершить комплектование пустого заказа, необходима хотя бы одна позиция в заказе.");
    }
}