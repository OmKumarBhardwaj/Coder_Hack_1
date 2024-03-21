package org.coder.coderhack.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserScoreUpdateDto {
    @Min(value = 1, message = "Score must be at least 1")
    @Max(value = 100, message = "Score can not be greater than 100")
    private Integer score;
}
