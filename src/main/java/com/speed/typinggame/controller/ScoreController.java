package com.speed.typinggame.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.speed.typinggame.dto.LeaderboardResponse;
import com.speed.typinggame.dto.ScoreRequest;
import com.speed.typinggame.dto.UserStats;
import com.speed.typinggame.service.ScoreService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    // POST /api/scores
    @PostMapping
    public ResponseEntity<LeaderboardResponse> submitScore(@Valid @RequestBody ScoreRequest request) {
        return ResponseEntity.ok(scoreService.submitScore(request));
    }

    // GET /api/scores/top?mode=timed&duration=60&limit=10
    @GetMapping("/top")
    public ResponseEntity<List<LeaderboardResponse>> getLeaderboard(
        @RequestParam(required = false) String mode,
        @RequestParam(required = false) Integer duration,
        @RequestParam(defaultValue = "10") int limit
    ) {
        return ResponseEntity.ok(scoreService.getLeaderboard(mode, duration, limit));
    }

    // GET /api/scores/history?username=nathaniel
    @GetMapping("/history")
    public ResponseEntity<List<LeaderboardResponse>> getPersonalHistory(
        @RequestParam String username
    ) {
        return ResponseEntity.ok(scoreService.getPersonalHistory(username));
    }

    // GET /api/scores/stats?username=nathaniel
    @GetMapping("/stats")
    public ResponseEntity<UserStats> getUserStatistics(
        @RequestParam String username
    ) {
        return ResponseEntity.ok(scoreService.getUserStatistics(username));
    }
}