package com.example.sportsbetting.app.service;

import com.example.sportsbetting.domain.Player;
import com.example.sportsbetting.domain.SportEvent;
import com.example.sportsbetting.domain.Wager;

import java.util.List;

public interface ISportBettingService {
    void savePlayer(Player p);
    Player findPlayer();
    List<SportEvent> findAllSportEvents();
    void saveWager(Wager w);
    List<Wager> findAllWagers();
    void calculateResults();
}
