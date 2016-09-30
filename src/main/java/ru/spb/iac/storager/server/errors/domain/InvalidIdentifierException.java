package ru.spb.iac.storager.server.errors.domain;

import ru.spb.iac.storager.server.errors.shared.Reason;
import ru.spb.iac.storager.server.errors.shared.ReasonableException;

public final class InvalidIdentifierException extends ReasonableException {

    public static final class InvalidIdentifierReason implements Reason {

        private final String itemId;

        public InvalidIdentifierReason(final String itemId) {
            this.itemId = itemId;
        }
        @Override
        public String getCode() {
            return "INVALID_ID";
        }

        @Override
        public String getDescription() {
            return "no item with specified ID does not exist";
        }

        public String getItemId() {
            return itemId;
        }
    }

    private final Reason reason;

    public InvalidIdentifierException(final String itemCode) {
        this.reason = new InvalidIdentifierReason(itemCode);
    }

    @Override
    public Reason getReason() {
        return reason;
    }
}
