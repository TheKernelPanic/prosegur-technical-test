package com.prosegur.technicaltest.technicaltest.exception;

public class CustomerNotFound extends Exception {
    public CustomerNotFound() {
        super("Client requested not found");
    }
}
