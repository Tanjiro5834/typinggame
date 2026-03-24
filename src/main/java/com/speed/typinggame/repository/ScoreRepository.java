package com.speed.typinggame.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.speed.typinggame.entity.GameMode;
import com.speed.typinggame.entity.Score;
import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long>{
    @Query("SELECT s FROM Score s WHERE s.mode = :mode AND s.duration = :duration " +
           "ORDER BY s.wpm DESC, s.accuracy DESC")
    List<Score> findTopScores(GameMode mode, Integer duration, Pageable pageable);

    List<Score> findByModeAndDurationOrderByWpmDesc(GameMode mode, Integer duration, Pageable pageable);

    List<Score> findByUsernameOrderByCreatedAtDesc(String username);

    @Query("SELECT AVG(s.wpm), MAX(s.wpm), AVG(s.accuracy), COUNT(s), SUM(s.duration), MAX(s.createdAt) FROM Score s WHERE s.username = :username")
    Object[] getUserStats(@Param("username") String username);
}
