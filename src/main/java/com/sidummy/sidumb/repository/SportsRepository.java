package com.sidummy.sidumb.repository;

import com.sidummy.sidumb.model.entity.Sports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SportsRepository extends JpaRepository<Sports, Integer> {

    Optional<Sports> findBySportsName (String sportsName);
}

