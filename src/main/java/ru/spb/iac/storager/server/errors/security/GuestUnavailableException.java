package ru.spb.iac.storager.server.errors.security;

import ru.spb.iac.storager.server.errors.shared.Reason;

public final class GuestUnavailableException extends SecurityException {

    public static final class GuestUnavailableReason implements Reason {

        @Override
        public String getCode() {
            return "GUEST_UNAVAILABLE";
        }

        @Override
        public String getDescription() {
            return "guest access is not available due to security configuration";
        }
    }

    private static final Reason reason = new GuestUnavailableReason();

    @Override
    public Reason getReason() {
        return reason;
    }
}
