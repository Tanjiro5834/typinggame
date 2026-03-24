package com.speed.typinggame.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import com.speed.typinggame.entity.Score;
@Data
public class LeaderboardResponse{
    Long id;
    String username;
    Integer wpm;
    BigDecimal accuracy;
    String mode;
    Integer duration; 
    LocalDateTime achievedAt;
    
    public LeaderboardResponse(Score saved) {
        this.id = saved.getId();
        this.username = saved.getUsername();
        this.wpm = saved.getWpm();
        this.accuracy = saved.getAccuracy();
        this.mode = saved.getMode().toString();
        this.achievedAt = saved.getCreatedAt();
    }
}