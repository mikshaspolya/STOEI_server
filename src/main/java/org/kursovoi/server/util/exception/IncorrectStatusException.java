package org.kursovoi.server.util.exception;

public class IncorrectStatusException extends RuntimeException {

    public IncorrectStatusException() {
    }

    public IncorrectStatusException(String message) {
        super(message);
    }

    public IncorrectStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectStatusException(Throwable cause) {
        super(cause);
    }
}
