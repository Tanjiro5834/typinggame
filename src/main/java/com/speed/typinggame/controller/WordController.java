package com.speed.typinggame.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.speed.typinggame.service.ScoreService;

@RestController
@RequestMapping("/api/words")
public class WordController {

    private final ScoreService scoreService;

    public WordController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("/random")
    public ResponseEntity<Map<String, List<String>>> getRandomWords(
        @RequestParam(defaultValue = "medium") String difficulty,
        @RequestParam(defaultValue = "en") String language,
        @RequestParam(defaultValue = "80") int count
    ) {
        List<String> words = scoreService.getRandomWordSet(difficulty, language, count);
        return ResponseEntity.ok(Map.of("words", words));
    }
}
