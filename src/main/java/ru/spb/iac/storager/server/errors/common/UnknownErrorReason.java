package ru.spb.iac.storager.server.errors.common;

import java.util.UUID;

import ru.spb.iac.storager.server.errors.shared.Reason;

public final class UnknownErrorReason implements Reason {

    private final String errorId;

    public UnknownErrorReason() {
        this.errorId = UUID.randomUUID().toString();
    }

    @Override
    public String getCode() {
        return "UNKNOWN_ERROR";
    }

    @Override
    public String getDescription() {
        return "unknown error occurred";
    }

    public String getErrorId() {
        return errorId;
    }
}
