package com.ducninh.ann.repository.jpa;

import com.ducninh.ann.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    Optional<Color> findByCode(String code);
    Optional<Color> findByName(String code);
}
