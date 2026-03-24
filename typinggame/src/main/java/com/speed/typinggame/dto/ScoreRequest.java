package com.speed.typinggame.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ScoreRequest(
    @NotBlank(message = "Username is required")
    @Size(max = 50)
    String username,

    @NotNull(message = "WPM is required")
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "300.0") 
    Integer wpm,

    @NotNull(message = "Accuracy is required")
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "100.0")
    BigDecimal accuracy,

    @NotBlank
    String mode, 

    @NotNull(message = "Duration is required")
    @Min(10)
    Integer duration,

    String language
) {}