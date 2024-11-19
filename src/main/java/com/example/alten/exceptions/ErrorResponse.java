package com.example.alten.exceptions;
import lombok.Getter;
import lombok.Setter;
public class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
