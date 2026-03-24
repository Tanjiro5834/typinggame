package com.speed.typinggame.service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.speed.typinggame.dto.LeaderboardResponse;
import com.speed.typinggame.dto.ScoreRequest;
import com.speed.typinggame.dto.UserStats;
import com.speed.typinggame.entity.Difficulty;
import com.speed.typinggame.entity.GameMode;
import com.speed.typinggame.entity.Score;
import com.speed.typinggame.entity.WordSet;
import com.speed.typinggame.repository.ScoreRepository;
import com.speed.typinggame.repository.WordRepository;

import jakarta.transaction.Transactional;

@Service
public class ScoreService {
    private final ScoreRepository scoreRepository;
    private final WordRepository wordRepository;

    public ScoreService(ScoreRepository scoreRepository, WordRepository wordRepository){
        this.scoreRepository = scoreRepository;
        this.wordRepository = wordRepository;
    }

    public List<Score> getAllScores(){
        return scoreRepository.findAll();
    }

    @Transactional
    public LeaderboardResponse submitScore(ScoreRequest request){
        double estimatedChars = request.wpm().doubleValue() * (request.duration() / 60.0) * 5;
        if (estimatedChars > 2000) {
            throw new IllegalArgumentException("Score is not physically plausible");
        }

        Score score = new Score();
        score.setUsername(request.username());
        score.setWpm(request.wpm());
        score.setAccuracy(request.accuracy());
        score.setMode(GameMode.valueOf(request.mode().toUpperCase()));
        score.setDuration(request.duration());
        score.setLanguage(request.language() != null ? request.language() : "en");

        Score saved = scoreRepository.save(score);
        return new LeaderboardResponse(saved);
    }

    public List<LeaderboardResponse> getLeaderboard(String mode, Integer duration, int limit) {
        List<Score> scores;

        if(mode != null && !mode.isBlank()){
            GameMode convertMode = GameMode.valueOf(mode.toUpperCase());

            scores = scoreRepository.findByModeAndDurationOrderByWpmDesc(convertMode, duration, PageRequest.of(0, limit));
        }else{
            scores = scoreRepository.findAll(PageRequest.of(0, limit, Sort.by("wpm").descending())).getContent();
        }
        return scores.stream()
            .map(LeaderboardResponse::new)
            .toList();
    }

    public List<LeaderboardResponse> getPersonalHistory(String username){
        List<Score> scores = scoreRepository.findByUsernameOrderByCreatedAtDesc(username);
        return scores.stream()
        .map(LeaderboardResponse::new)
        .collect(Collectors.toList());
    }

    public UserStats getUserStatistics(String username){
        Object[] result = scoreRepository.getUserStats(username);
        
        BigDecimal avgWpm      = (BigDecimal) result[0];
        BigDecimal bestWpm     = (BigDecimal) result[1];
        BigDecimal avgAccuracy = (BigDecimal) result[2];
        long totalGames        = ((Long) result[3]);
        long totalMinutes      = ((Long) result[4]) / 60;
        LocalDateTime lastActive = (LocalDateTime) result[5];

        String rank = assignRank(bestWpm);
        return new UserStats(username, avgWpm, bestWpm, avgAccuracy, totalGames, totalMinutes, rank, lastActive);
    }

    private String assignRank(BigDecimal bestWpm) {
        int wpm = bestWpm.intValue();
        if (wpm >= 150) return "Legendary";
        if (wpm >= 100) return "Master";
        if (wpm >= 70)  return "Expert";
        if (wpm >= 40)  return "Intermediate";
        return "Novice";
    }

    public List<String> getRandomWordSet(String difficulty, String language) {
        Difficulty parsedDifficulty;
        try{
            parsedDifficulty = difficulty != null
            ? Difficulty.valueOf(difficulty.toUpperCase())
            : Difficulty.MEDIUM; 
        }catch(Exception e){
            parsedDifficulty = Difficulty.MEDIUM; 
            e.printStackTrace();
        }

        String safeLanguage = (language != null && !language.isBlank()) ? language : "en";
        WordSet wordSet = wordRepository.findByDifficultyAndLanguage(
                parsedDifficulty,
                safeLanguage
        );

        if (wordSet == null) {
            throw new IllegalArgumentException("No word set found for difficulty: " + difficulty);
        }

        List<String> words = new ArrayList<>(wordSet.getWordsAsList());
        Collections.shuffle(words);

        int gameSize = Math.min(80, words.size());
        return words.subList(0, gameSize);
    }
}
