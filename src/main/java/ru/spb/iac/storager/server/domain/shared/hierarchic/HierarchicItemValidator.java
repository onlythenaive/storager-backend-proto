package ru.spb.iac.storager.server.domain.shared.hierarchic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.errors.domain.InputValidationHelper;
import ru.spb.iac.storager.server.errors.domain.InvalidInputException;

public abstract class HierarchicItemValidator<T extends HierarchicItem<T>> {

    @Autowired
    private InputValidationHelper inputValidationHelper;

    @Transactional(readOnly = true)
    public HierarchicItemProperties validateForCreate(final HierarchicItemProperties properties) {
        validateProperties(properties);
        final String code = properties.getCode();
        if (getRepository().findByCode(code) != null) {
            throw InvalidInputException.duplicated("code", code);
        }
        return properties;
    }

    @Transactional(readOnly = true)
    public HierarchicItemProperties validateForUpdate(final String code, final HierarchicItemProperties properties) {
        validateProperties(properties);
        final String ascendantCode = properties.getAscendantCode();
        if (code.equals(ascendantCode)) {
            throw new InvalidInputException("ascendant territory must exist", "ascendantCode", ascendantCode);
        }
        return properties;
    }

    private HierarchicItemProperties validateProperties(final HierarchicItemProperties properties) {
        inputValidationHelper.required(properties, "code");
        final String code = inputValidationHelper.required(properties.getCode(), "code");
        final String ascendantCode = properties.getAscendantCode();
        if (code.equals(ascendantCode)) {
            throw new InvalidInputException("indicator can not be self-ascendant", "ascendantCode", ascendantCode);
        }
        if (ascendantCode != null && getRepository().findByCode(ascendantCode) == null) {
            throw new InvalidInputException("ascendant indicator must exist", "ascendantCode", ascendantCode);
        }
        final String title = properties.getTitle();
        if (title == null) {
            throw InvalidInputException.missing("title");
        }
        return properties;
    }

    protected abstract HierarchicItemRepository<T> getRepository();
}
