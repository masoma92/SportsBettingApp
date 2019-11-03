package com.example.sportsbetting.domain;

public class OutcomeOddException extends Exception {
    @Override
    public String getMessage() {
        return this.a + " includes " + this.b + " valid interval.";
    }

    OutcomeOdd a;
    OutcomeOdd b;

    public OutcomeOddException(OutcomeOdd a, OutcomeOdd b) {
        this.a = a;
        this.b = b;
    }
}
