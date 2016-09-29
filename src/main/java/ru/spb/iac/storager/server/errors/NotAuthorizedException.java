package ru.spb.iac.storager.server.errors;

public class NotAuthorizedException extends RuntimeException {

    public static NotAuthorizedException insufficientPermissions(String login, String... requiredRoles) {
        return new NotAuthorizedException(String.format("user %s does not have any of required roles: [%s]", login, requiredRoles));
    }

    public static NotAuthorizedException invalidCredentials(String login) {
        if (login != null) {
            return new NotAuthorizedException(String.format("user %s does not exist or its credentials are invalid", login));
        } else {
            return new NotAuthorizedException("user login is missing");
        }
    }

    public static NotAuthorizedException noAuthorizedUser() {
        return new NotAuthorizedException(String.format("user is not authorized"));
    }

    private Object reason;

    private NotAuthorizedException(Object reason) {
        this.reason = reason;
    }

    public Object getReason() {
        return reason;
    }
}
