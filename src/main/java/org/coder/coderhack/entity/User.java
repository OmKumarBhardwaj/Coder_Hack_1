package org.coder.coderhack.entity;

import lombok.Data;
import org.coder.coderhack.constant.Badge;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;


@Data
@Document
public class User {
    @Id
    private String userId;
    private String userName;
    private int score;
    private Set<Badge> badges;
}
