package ru.spb.iac.storager.server.errors.domain;

import org.springframework.stereotype.Component;

@Component
public class ItemValidationHelper {

    public <T> T required(final T item, final String identifier, final String value) {
        if (item == null) {
            throw new MissingItemException(identifier, value);
        }
        return item;
    }
}
