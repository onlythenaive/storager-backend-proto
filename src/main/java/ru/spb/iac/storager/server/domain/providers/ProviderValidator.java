package ru.spb.iac.storager.server.domain.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.spb.iac.storager.server.errors.domain.InputValidationHelper;

@Component
public class ProviderValidator {

    @Autowired
    private InputValidationHelper inputValidationHelper;

    public ProviderProperties validateForCreate(final ProviderProperties properties) {
        validateProperties(properties);
        return properties;
    }

    public ProviderProperties validateForUpdate(final Integer id, final ProviderProperties properties) {
        validateProperties(properties);
        return properties;
    }

    private ProviderProperties validateProperties(final ProviderProperties properties) {
        inputValidationHelper.required(properties.getTitle(), "title");
        return properties;
    }
}
