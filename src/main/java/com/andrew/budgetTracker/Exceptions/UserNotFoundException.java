package com.andrew.budgetTracker.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //returns this status when this error is thrown
public class UserNotFoundException extends RuntimeException {
}
