package com.sidummy.sidumb.repository;

import com.sidummy.sidumb.model.entity.CatPhotos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatRepository extends JpaRepository<CatPhotos, Integer> {
}
