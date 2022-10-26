package com.sidummy.sidumb.repository;

import com.sidummy.sidumb.model.entity.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WebtoonRepository extends JpaRepository<Webtoon,Integer> {

    Optional<Webtoon> findByWebtoonName (String webtoonName);
}
