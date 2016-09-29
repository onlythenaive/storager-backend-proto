package ru.spb.iac.storager.server.errors.shared;

public abstract class ReasonableException extends RuntimeException {

    public abstract Reason getReason();

    @Override
    public String getMessage() {
        return getReason().getDescription();
    }
}
