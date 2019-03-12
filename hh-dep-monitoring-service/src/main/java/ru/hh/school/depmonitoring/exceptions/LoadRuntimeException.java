package ru.hh.school.depmonitoring.exceptions;

public class LoadRuntimeException extends RuntimeException {
    private final LoadExceptionType loadExceptionType;

    public LoadRuntimeException(String message, Throwable cause, LoadExceptionType loadExceptionType) {
        super(message, cause);
        this.loadExceptionType = loadExceptionType;
    }

    public LoadExceptionType getLoadExceptionType() {
        return loadExceptionType;
    }
}
