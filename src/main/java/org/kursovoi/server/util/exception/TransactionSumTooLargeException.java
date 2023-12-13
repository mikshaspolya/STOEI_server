package org.kursovoi.server.util.exception;

public class TransactionSumTooLargeException extends RuntimeException {

    public TransactionSumTooLargeException() {
    }

    public TransactionSumTooLargeException(String message) {
        super("ERROR " + message);
    }

    public TransactionSumTooLargeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionSumTooLargeException(Throwable cause) {
        super(cause);
    }
}
