package org.kursovoi.server.util.exception;

public class AccountInvalidException extends RuntimeException {

    public AccountInvalidException() {
    }

    public AccountInvalidException(String message) {
        super(message);
    }

    public AccountInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountInvalidException(Throwable cause) {
        super(cause);
    }
}
