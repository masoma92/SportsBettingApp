package service;

import domain.Player;
import domain.SportEvent;
import domain.Wager;

import java.util.List;

public interface ISportBettingService {
    void savePlayer(Player p);
    Player findPlayer();
    List<SportEvent> findAllSportEvents();
    void saveWager(Wager w);
    List<Wager> findAllWagers();
    void calculateResults();
}
