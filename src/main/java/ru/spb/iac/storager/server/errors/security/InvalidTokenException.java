package ru.spb.iac.storager.server.errors.security;

import ru.spb.iac.storager.server.errors.shared.Reason;

public final class InvalidTokenException extends SecurityException {

    public static final class InvalidTokenReason implements Reason {

        private final String token;

        public InvalidTokenReason(final String token) {
            this.token = token;
        }

        @Override
        public String getCode() {
            return "INVALID_TOKEN";
        }

        @Override
        public String getDescription() {
            return "specified security token is invalid";
        }

        public String getToken() {
            return token;
        }
    }

    private final Reason reason;

    public InvalidTokenException(final String token) {
        this.reason = new InvalidTokenReason(token);
    }

    @Override
    public Reason getReason() {
        return reason;
    }
}
