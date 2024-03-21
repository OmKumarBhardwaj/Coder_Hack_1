package org.coder.coderhack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserScoreAlreadyModifiedOnce extends RuntimeException {
    public UserScoreAlreadyModifiedOnce(String message) {
        super(message);
    }
}
