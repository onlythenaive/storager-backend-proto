package ru.spb.iac.storager.server.errors.domain;

import ru.spb.iac.storager.server.errors.shared.Reason;
import ru.spb.iac.storager.server.errors.shared.ReasonableException;

public final class InvalidInputException extends ReasonableException {

    public static final class InvalidInputReason implements Reason {

        private final String constraint;
        private final String name;
        private final Object value;

        public InvalidInputReason(final String constraint, final String name, final Object value) {
            this.constraint = constraint;
            this.name = name;
            this.value = value;
        }

        @Override
        public String getCode() {
            return "INVALID_INPUT";
        }

        @Override
        public String getDescription() {
            return "input value does not meet defined constraints";
        }

        public String getConstraint() {
            return constraint;
        }

        public String getName() {
            return name;
        }

        public Object getValue() {
            return value;
        }
    }

    public static InvalidInputException missing(final String name) {
        return new InvalidInputException("not optional", name, null);
    }

    public static InvalidInputException duplicated(final String name, final Object value) {
        return new InvalidInputException("must be unique", name, value);
    }

    public static InvalidInputException notPositive(final String name, final int value) {
        return new InvalidInputException("must be positive", name, value);
    }

    private final Reason reason;

    public InvalidInputException(final String constraint, final String name, final Object value) {
        this.reason = new InvalidInputReason(constraint, name, value);
    }

    @Override
    public Reason getReason() {
        return reason;
    }
}
