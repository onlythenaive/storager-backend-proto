package ru.spb.iac.storager.server.domain.territories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.errors.domain.InputValidationHelper;
import ru.spb.iac.storager.server.errors.domain.InvalidInputException;

@Service
@Transactional(readOnly = true)
public class TerritoryValidator {

    @Autowired
    private InputValidationHelper inputValidation;

    @Autowired
    private TerritoryRepository repository;

    public TerritoryProperties validateForCreate(final TerritoryProperties properties) {
        validateProperties(properties);
        final String code = properties.getCode();
        if (repository.findByCode(code) != null) {
            throw InvalidInputException.duplicated("code", code);
        }
        return properties;
    }

    public TerritoryProperties validateForUpdate(final String code, final TerritoryProperties properties) {
        validateProperties(properties);
        final String ascendantCode = properties.getAscendantCode();
        if (code.equals(ascendantCode)) {
            throw new InvalidInputException("ascendant territory must exist", "ascendantCode", ascendantCode);
        }
        return properties;
    }

    private TerritoryProperties validateProperties(final TerritoryProperties properties) {
        inputValidation.required(properties, "code");
        final String code = inputValidation.required(properties.getCode(), "code");
        final String ascendantCode = properties.getAscendantCode();
        if (code.equals(ascendantCode)) {
            throw new InvalidInputException("territory can not be self-ascendant", "ascendantCode", ascendantCode);
        }
        if (ascendantCode != null && repository.findByCode(ascendantCode) == null) {
            throw new InvalidInputException("ascendant territory must exist", "ascendantCode", ascendantCode);
        }
        final String title = properties.getTitle();
        if (title == null) {
            throw InvalidInputException.missing("title");
        }
        return properties;
    }
}
