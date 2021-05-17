package com.futurumgame.base.exceptions;

import java.io.IOException;

public class FileTooLargeException extends IOException {

    public FileTooLargeException() {
        super("File is too large");
    }

    public FileTooLargeException(String message) {
        super(message);
    }

    public FileTooLargeException(Throwable throwable) {
        super(throwable);
    }

    public FileTooLargeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
