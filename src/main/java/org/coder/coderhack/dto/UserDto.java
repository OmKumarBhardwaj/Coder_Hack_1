package org.coder.coderhack.dto;

import lombok.Data;
import org.coder.coderhack.constant.Badge;

import java.util.Set;


@Data
public class UserDto {
    private String userId;
    private String userName;
    private Integer score;
    private Set<Badge> badges;
}
