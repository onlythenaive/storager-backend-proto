package ru.spb.iac.storager.server.domain.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.errors.domain.InputValidationHelper;

@Component
public class ProviderValidator {

    @Autowired
    private InputValidationHelper inputValidationHelper;

    @Transactional(readOnly = true)
    public ProviderProperties validateForCreate(final ProviderProperties properties) {
        validateProperties(properties);
        return properties;
    }

    @Transactional(readOnly = true)
    public ProviderProperties validateForUpdate(final Integer id, final ProviderProperties properties) {
        validateProperties(properties);
        return properties;
    }

    private ProviderProperties validateProperties(final ProviderProperties properties) {
        inputValidationHelper.required(properties.getTitle(), "title");
        return properties;
    }
}
