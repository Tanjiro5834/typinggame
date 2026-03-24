package com.speed.typinggame.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for the User Dashboard / Profile Overview.
 * Provides aggregated performance metrics.
 */
public record UserStats(
    String username,
    
    // Aggregated data
    BigDecimal averageWpm,
    BigDecimal bestWpm,
    BigDecimal averageAccuracy,
    
    // Activity tracking
    long totalGamesPlayed,
    long totalMinutesTyped,
    
    // Meta info
    String rankTier, // e.g., "Legendary", "Master", "Novice"
    LocalDateTime lastActive
) {}