package ru.spb.iac.storager.server.data.shared;

public abstract class BusinessLogicException extends RuntimeException {

    public abstract Object getReason();
}
