package ru.spb.iac.storager.server.security.errors;

import ru.spb.iac.storager.server.errors.Reason;

public final class MissingLoginException extends SecurityException {

    public static final class ReasonImpl implements Reason {

        @Override
        public String getCode() {
            return "LOGIN_MISSING";
        }

        @Override
        public String getDescription() {
            return "no login is available for user authentication";
        }
    }

    private static final Reason reason = new ReasonImpl();

    @Override
    public Reason getReason() {
        return reason;
    }
}
