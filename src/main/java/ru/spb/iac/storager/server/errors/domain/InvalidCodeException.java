package ru.spb.iac.storager.server.errors.domain;

import ru.spb.iac.storager.server.errors.shared.Reason;
import ru.spb.iac.storager.server.errors.shared.ReasonableException;

public final class InvalidCodeException extends ReasonableException {

    public static final class InvalidCodeReason implements Reason {

        private final String itemCode;

        public InvalidCodeReason(final String itemCode) {
            this.itemCode = itemCode;
        }
        @Override
        public String getCode() {
            return "INVALID_CODE";
        }

        @Override
        public String getDescription() {
            return "no item with specified code does exist";
        }

        public String getItemCode() {
            return itemCode;
        }
    }

    private final Reason reason;

    public InvalidCodeException(final String itemCode) {
        this.reason = new InvalidCodeReason(itemCode);
    }

    @Override
    public Reason getReason() {
        return reason;
    }
}
