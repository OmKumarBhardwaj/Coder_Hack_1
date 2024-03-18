package org.coder.coderhack.dto;

import lombok.Data;
import org.coder.coderhack.constant.Badge;

import java.util.Set;


@Data
public class UserRegistrationDto {
    private String userId;
    private String userName;
}
