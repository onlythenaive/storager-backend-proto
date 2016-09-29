package ru.spb.iac.storager.server.errors;

public abstract class BusinessLogicException extends RuntimeException {

    public abstract Object getReason();
}
