package com.example.sportsbetting.repository;

import com.example.sportsbetting.domain.Wager;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WagerRepository extends CrudRepository<Wager, Integer> {
}
