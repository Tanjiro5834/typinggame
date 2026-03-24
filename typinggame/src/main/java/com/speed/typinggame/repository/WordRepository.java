package com.speed.typinggame.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.speed.typinggame.entity.Difficulty;
import com.speed.typinggame.entity.WordSet;

public interface WordRepository extends JpaRepository<WordSet, Long>{
    WordSet findByDifficultyAndLanguage(Difficulty diff, String language);
}
