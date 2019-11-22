package com.example.sportsbetting.repository;

import com.example.sportsbetting.domain.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
}
