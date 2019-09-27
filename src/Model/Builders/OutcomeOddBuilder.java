package Model.Builders;

import Model.Outcome;
import Model.OutcomeOdd;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OutcomeOddBuilder {
    BigDecimal value; //winning*
    LocalDateTime validFrom; //this value valid from
    LocalDateTime validUntil; //this value valid until
    Outcome outcome;

    public OutcomeOddBuilder() {
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public OutcomeOddBuilder setValue(BigDecimal value){
        this.value = value;
        return this;
    }

    public OutcomeOddBuilder setValidFrom(LocalDateTime validFrom){
        this.validFrom = validFrom;
        return this;
    }

    public OutcomeOddBuilder setValidUntil(LocalDateTime validUntil){
        this.validUntil = validUntil;
        return this;
    }

    public void setOutcome(Outcome outcome){
        this.outcome = outcome;
    }

    public OutcomeOdd getOutcomeOdd(){
        return new OutcomeOdd(this);
    }
}
