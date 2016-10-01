package ru.spb.iac.storager.server.domain.territories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TerritoryValidator {

    @Autowired
    private TerritoryRepository repository;

    public TerritoryProperties validateForCreate(final TerritoryProperties properties) {
        validateProperties(properties);
        final String code = properties.getCode();
        if (repository.findByCode(code) != null) {
            // NOTE: code already exists
            throw new RuntimeException();
        }
        return properties;
    }

    public TerritoryProperties validateForUpdate(final String code, final TerritoryProperties properties) {
        validateProperties(properties);
        final String ascendantCode = properties.getAscendantCode();
        if (code.equals(ascendantCode)) {
            // NOTE: no ascendant
            throw new RuntimeException();
        }
        return properties;
    }

    private TerritoryProperties validateProperties(final TerritoryProperties properties) {
        if (properties == null) {
            // NOTE: no properties specified
            throw new RuntimeException();
        }
        final String code = properties.getCode();
        if (code == null) {
            // NOTE: no code specified
            throw new RuntimeException();
        }
        final String ascendantCode = properties.getAscendantCode();
        if (code.equals(ascendantCode)) {
            // NOTE: hierarchic loop
            throw new RuntimeException();
        }
        if (ascendantCode != null && repository.findByCode(ascendantCode) == null) {
            // NOTE: no ascendant
            throw new RuntimeException();
        }
        final String title = properties.getTitle();
        if (title == null) {
            // NOTE: no title specified
            throw new RuntimeException();
        }
        return properties;
    }
}
