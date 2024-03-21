package org.coder.coderhack.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.coder.coderhack.constant.Badge;

import java.util.Set;


@Data
public class UserRegistrationDto {
    @NotEmpty(message = "UserId can not be null or empty")
    @Size(min = 1, max = 30, message = "The length of the userId should be between 1 and 30")
    private String userId;
    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 2, max = 30, message = "The length of the userName should be between 2 and 30")
    private String userName;
}
