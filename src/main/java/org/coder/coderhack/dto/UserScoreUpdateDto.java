package org.coder.coderhack.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserScoreUpdateDto {
    @NotEmpty(message = "UserId can not be null or empty")
    @Size(min = 1, max = 30, message = "The length of the userId should be between 1 and 30")
    private String userId;
    @Min(value = 1, message = "Score must be at least 1")
    @Max(value = 100, message = "Score can not be greater than 100")
    private Integer score;
}
