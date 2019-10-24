package domain.builders;

import domain.Outcome;
import domain.OutcomeOdd;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OutcomeOddBuilder {
    private BigDecimal value; //winning*
    private LocalDateTime validFrom; //this value valid from
    private LocalDateTime validUntil; //this value valid until
    private Outcome outcome;

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
