package ru.rightcode.arm.exceptions;

public class NoPermissionException extends RuntimeException {

    public NoPermissionException() {
        super();
    }

    public NoPermissionException(String message) {
        super(message);
    }
}
