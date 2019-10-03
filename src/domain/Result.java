package domain;

import java.util.List;

public final class Result {

    List<Outcome> winnerOutcomes; //1 event all outcomes

    public Result(List<Outcome> winnerOutcomes){
        this.winnerOutcomes = winnerOutcomes;
    }

    public List<Outcome> getWinnerOutcomes() {
        return winnerOutcomes;
    }
}
