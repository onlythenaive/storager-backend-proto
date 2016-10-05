package ru.spb.iac.storager.server.errors.domain;

import org.springframework.stereotype.Component;

@Component
public class InputValidationHelper {

    public <T> T required(final T input, final String name) {
        if (input == null) {
            throw InvalidInputException.missing(name);
        }
        return input;
    }

    public <T> T unique(final T input, final String name, final Object item) {
        if (item != null) {
            throw InvalidInputException.duplicated(name, input);
        }
        return input;
    }
}
