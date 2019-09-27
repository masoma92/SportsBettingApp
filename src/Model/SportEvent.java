package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class SportEvent {

    String title;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Result result;
    List<Bet> bets;

    public SportEvent(String title, LocalDateTime startDate, LocalDateTime endDate) {
        this.title = title; //sport event name
        this.startDate = startDate; //sport event start date
        this.endDate = endDate; //sport event end date
        this.bets = new ArrayList<>(); //list of the different type of bets
        for(Bet b : bets){
            b.setEvent(this);
        }
        result = new Result(); //sport event result
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public Result getResult() {
        return result;
    }

    public void addBet(Bet bet){
        this.bets.add(bet);
    }

    @Override
    public String toString() {
        String output = "";
        int i = 0;

        for (Bet b : bets){
            for (Outcome o : b.getOutcomes()){
                for (OutcomeOdd od : o.getOutcomeOdds()){
                    output += ++i + ": Sport event: " + this.title + ", " + b.toString() + ", " + o.toString() + od.toString();
                }
            }
        }

        return output;
    }
}
