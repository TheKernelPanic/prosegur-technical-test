package com.prosegur.technicaltest.technicaltest.exception;

public class OriEntityHasNoCustomers extends Exception {
    public OriEntityHasNoCustomers() {
        super("Ori entity requested has no customers");
    }
}
