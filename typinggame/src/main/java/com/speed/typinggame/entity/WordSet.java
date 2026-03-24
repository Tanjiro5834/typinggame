package com.speed.typinggame.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.speed.typinggame.repository.WordRepository;

@Entity
@Table(name = "word_sets")
@Data
@NoArgsConstructor
public class WordSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String language = "en"; // e.g., 'en', 'es', 'fil'

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    /**
     * We store the words as a comma-separated string or use a 
     * AttributeConverter to map a List<String> to a single TEXT column.
     */
    @Lob
    @Column(columnDefinition = "TEXT")
    private String rawWords;

    // Helper method to get as List for the Service Layer
    public List<String> getWordsAsList() {
        if (rawWords == null || rawWords.isEmpty()) return List.of();
        return List.of(rawWords.split("[,\\s]+"));
    }
}