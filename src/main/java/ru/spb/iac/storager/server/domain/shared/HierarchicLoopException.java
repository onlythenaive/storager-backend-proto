package ru.spb.iac.storager.server.domain.shared;

import ru.spb.iac.storager.server.errors.shared.Reason;
import ru.spb.iac.storager.server.errors.shared.ReasonableException;

public final class HierarchicLoopException extends ReasonableException {

    public static final class HierarchicLoopReason implements Reason {

        private final String itemCode;

        public HierarchicLoopReason(final String itemCode) {
            this.itemCode = itemCode;
        }

        @Override
        public String getCode() {
            return "HIERARCHIC_LOOP";
        }

        @Override
        public String getDescription() {
            return "hierarchic item cannot be a descendant of itself";
        }

        public String getItemCode() {
            return itemCode;
        }
    }

    private final Reason reason;

    public HierarchicLoopException(final String itemCode) {
        this.reason = new HierarchicLoopReason(itemCode);
    }

    @Override
    public Reason getReason() {
        return reason;
    }
}
