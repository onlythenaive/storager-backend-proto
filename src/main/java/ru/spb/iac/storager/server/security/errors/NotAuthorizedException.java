package ru.spb.iac.storager.server.security.errors;

import java.util.HashSet;
import java.util.Set;

import ru.spb.iac.storager.server.errors.Reason;

public final class NotAuthorizedException extends SecurityException {

    public static final class ReasonImpl implements Reason {

        private final String login;
        private final Set<String> requiredRoles;

        public ReasonImpl(final String login, final Set<String> requiredRoles) {
            this.login = login;
            this.requiredRoles = new HashSet<>(requiredRoles);
        }

        @Override
        public String getCode() {
            return "NOT_AUTHORIZED";
        }

        @Override
        public String getDescription() {
            return "authenticated user does not have sufficient permissions for the request";
        }

        public String getLogin() {
            return login;
        }

        public Set<String> getRequiredRoles() {
            return new HashSet<>(requiredRoles);
        }
    }

    private final Reason reason;

    public NotAuthorizedException(String login, Set<String> requiredRoles) {
        this.reason = new ReasonImpl(login, requiredRoles);
    }

    @Override
    public Reason getReason() {
        return reason;
    }
}
