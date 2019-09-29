package Model;

import Model.Builders.OutcomeOddBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OutcomeOdd {
    BigDecimal value; //winning*
    LocalDateTime validFrom; //this value valid from
    LocalDateTime validUntil; //this value valid until
    Outcome outcome;

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

    @Override
    public String toString() {
        return "Actual odd: " + value + ", Valid between: " + validFrom.toString() + ", Valid until: " + validUntil.toString() + "\n";
    }
}
