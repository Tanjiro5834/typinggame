package com.speed.typinggame.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(
    name = "scores",
    indexes = {
        @Index(name = "idx_wpm", columnList = "wpm"),
        @Index(name = "idx_username", columnList = "username"),
        @Index(name = "idx_created_at", columnList = "created_at")
    }
)
@NoArgsConstructor
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String username;

    @Column(nullable = false)
    private Integer wpm;

    @Column(precision = 5, scale = 2)
    private BigDecimal accuracy;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GameMode mode;

    private Integer duration;

    @Column(nullable = false)
    private String language = "en";

    @CreationTimestamp
    private LocalDateTime createdAt;
}