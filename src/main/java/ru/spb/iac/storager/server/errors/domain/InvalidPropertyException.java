package ru.spb.iac.storager.server.errors.domain;

import ru.spb.iac.storager.server.errors.shared.Reason;
import ru.spb.iac.storager.server.errors.shared.ReasonableException;

public final class InvalidPropertyException extends ReasonableException {

    public static final class InvalidPropertyReason implements Reason {

        private final String constraint;
        private final String name;
        private final Object value;

        public InvalidPropertyReason(final String constraint, final String name, final Object value) {
            this.constraint = constraint;
            this.name = name;
            this.value = value;
        }

        @Override
        public String getCode() {
            return "INVALID_PROPERTY";
        }

        @Override
        public String getDescription() {
            return "property value does not meet required constraints";
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

    public static InvalidPropertyException missingProperty(final String name) {
        return new InvalidPropertyException("property is not optional", name, null);
    }

    public static InvalidPropertyException duplicateProperty(final String name, final Object value) {
        return new InvalidPropertyException("property can not be duplicated", name, value);
    }

    private final Reason reason;

    public InvalidPropertyException(final String constraint, final String name, final Object value) {
        this.reason = new InvalidPropertyReason(constraint, name, value);
    }

    @Override
    public Reason getReason() {
        return reason;
    }
}
