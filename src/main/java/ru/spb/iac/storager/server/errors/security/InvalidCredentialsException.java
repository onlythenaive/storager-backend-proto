package ru.spb.iac.storager.server.errors.security;

import ru.spb.iac.storager.server.errors.shared.Reason;

public final class InvalidCredentialsException extends SecurityException {

    public static final class ReasonImpl implements Reason {

        private final String login;

        public ReasonImpl(final String login) {
            this.login = login;
        }

        @Override
        public String getCode() {
            return "INVALID_CREDENTIALS";
        }

        @Override
        public String getDescription() {
            return "specified login does not exist or credentials are invalid";
        }

        public String getLogin() {
            return login;
        }
    }

    private final Reason reason;

    public InvalidCredentialsException(String login) {
        this.reason = new ReasonImpl(login);
    }

    @Override
    public Reason getReason() {
        return reason;
    }
}
