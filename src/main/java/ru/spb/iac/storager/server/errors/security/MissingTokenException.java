package ru.spb.iac.storager.server.errors.security;

import ru.spb.iac.storager.server.errors.shared.Reason;

public final class MissingTokenException extends SecurityException {

    public static final class ReasonImpl implements Reason {

        @Override
        public String getCode() {
            return "TOKEN_MISSING";
        }

        @Override
        public String getDescription() {
            return "no token is available for user authentication";
        }
    }

    private static final Reason reason = new ReasonImpl();

    @Override
    public Reason getReason() {
        return reason;
    }
}
