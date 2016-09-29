package ru.spb.iac.storager.server.errors.security;

import ru.spb.iac.storager.server.errors.shared.Reason;

public final class MissingLoginException extends SecurityException {

    public static final class MissingLoginReason implements Reason {

        @Override
        public String getCode() {
            return "LOGIN_MISSING";
        }

        @Override
        public String getDescription() {
            return "no login is available for user authentication";
        }
    }

    private static final Reason reason = new MissingLoginReason();

    @Override
    public Reason getReason() {
        return reason;
    }
}
