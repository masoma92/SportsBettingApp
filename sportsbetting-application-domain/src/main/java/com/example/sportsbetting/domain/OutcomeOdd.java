package com.example.sportsbetting.domain;

import com.example.sportsbetting.domain.builders.OutcomeOddBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OutcomeOdd {
    private BigDecimal value; //winning*
    private LocalDateTime validFrom; //this value valid from
    private LocalDateTime validUntil; //this value valid until
    private Outcome outcome;

    public OutcomeOdd(BigDecimal value, LocalDateTime validFrom, LocalDateTime validUntil) {
        this.value = value;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }

    public OutcomeOdd(OutcomeOddBuilder oob) {
        this.value = oob.getValue();
        this.validFrom = oob.getValidFrom();
        this.validUntil = oob.getValidUntil();
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    @Override
    public String toString() {
        return "Actual odd: " + value + ", Valid between: " + validFrom.toString() + ", Valid until: " + validUntil.toString() + "\n";
    }
}
