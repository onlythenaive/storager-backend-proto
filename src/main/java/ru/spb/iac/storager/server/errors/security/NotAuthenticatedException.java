package ru.spb.iac.storager.server.errors.security;

import ru.spb.iac.storager.server.errors.shared.Reason;

public final class NotAuthenticatedException extends SecurityException {

    public static final class ReasonImpl implements Reason {

        @Override
        public String getCode() {
            return "NOT_AUTHENTICATED";
        }

        @Override
        public String getDescription() {
            return "no authenticated user is assigned to te request";
        }
    }

    private static final Reason reason = new ReasonImpl();

    @Override
    public Reason getReason() {
        return reason;
    }
}
