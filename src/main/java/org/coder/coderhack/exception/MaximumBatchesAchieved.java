package org.coder.coderhack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MaximumBatchesAchieved extends RuntimeException {
    public MaximumBatchesAchieved(String message) {
        super(message);
    }
}
