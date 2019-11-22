package com.example.sportsbetting.repository;

import com.example.sportsbetting.domain.Wager;
import org.springframework.data.repository.CrudRepository;

public interface WagerRepository extends CrudRepository<Wager, Integer> {
}
