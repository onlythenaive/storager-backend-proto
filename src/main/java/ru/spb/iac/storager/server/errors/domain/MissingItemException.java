package ru.spb.iac.storager.server.errors.domain;

import ru.spb.iac.storager.server.errors.shared.Reason;
import ru.spb.iac.storager.server.errors.shared.ReasonableException;

public final class MissingItemException extends ReasonableException {

    public static final class MissingItemReason implements Reason {

        private final String identifier;
        private final Object value;

        public MissingItemReason(final String identifier, final Object value) {
            this.identifier = identifier;
            this.value = value;
        }

        @Override
        public String getCode() {
            return "MISSING_ITEM";
        }

        @Override
        public String getDescription() {
            return "no item found by specified identifier";
        }

        public String getIdentifier() {
            return identifier;
        }

        public Object getValue() {
            return value;
        }
    }

    private final Reason reason;

    public MissingItemException(final String identifier, final Object value) {
        this.reason = new MissingItemReason(identifier, value);
    }

    @Override
    public Reason getReason() {
        return reason;
    }
}
