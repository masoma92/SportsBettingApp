package Model;

import java.util.ArrayList;
import java.util.List;

public final class Result {

    List<Outcome> winnerOutcomes; //1 event all outcomes

    public Result(){
        this.winnerOutcomes = new ArrayList<Outcome>();
    }

    public List<Outcome> getWinnerOutcomes() {
        return winnerOutcomes;
    }
}
