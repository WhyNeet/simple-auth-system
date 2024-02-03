package dev.whyneet.authsystem.api.exception;

import java.util.List;

public class ApiError {
    private String message;
    private List<String> error;

    public ApiError(String message, String error) {
        this.message = message;
        this.error = List.of(error);
    }

    public ApiError(String message, List<String> error) {
        this.message = message;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }
}
