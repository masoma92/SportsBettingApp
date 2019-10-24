package view;

import domain.OutcomeOdd;
import domain.Player;
import domain.SportEvent;
import domain.Wager;

import java.math.BigDecimal;
import java.util.List;

public interface IView {
    Player readPlayerData();
    void printWelcomeMessage(Player p);
    void printBalance(Player p);
    void printOutcomeOdds(List<SportEvent> se);
    OutcomeOdd selectOutcomeOdd(List<SportEvent> se);
    BigDecimal readWagerAmount();
    void printWagerSaved(Wager w);
    void printNotEnoughBalance(Player p);
    void printResults(Player p, List<Wager> w);
}
