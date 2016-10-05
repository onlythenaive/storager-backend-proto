package ru.spb.iac.storager.server.domain.indicators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class IndicatorMapper {

    @Autowired
    private IndicatorRepository repository;

    public Indicator intoEntity(final IndicatorProperties properties) {
        return intoEntity(properties, new Indicator());
    }

    public Indicator intoEntity(final IndicatorProperties properties, final Indicator entity) {
        entity.setCode(properties.getCode());
        entity.setAscendant(getAscendant(properties.getAscendantCode()));
        entity.setTitle(properties.getTitle());
        entity.setDescription(properties.getDescription());
        return entity;
    }

    public IndicatorInfo intoInfo(final Indicator entity) {
        final String code = entity.getCode();
        final String ascendantCode = getAscendantCode(entity);
        final String title = entity.getTitle();
        final String description = entity.getDescription();
        final List<String> path = getPath(entity);
        final Boolean terminal = isTerminal(entity);
        return new IndicatorInfo(code, ascendantCode, title, description, path, terminal);
    }

    private Indicator getAscendant(final String ascendantCode) {
        return ascendantCode != null ? repository.findByCode(ascendantCode) : null;
    }

    private String getAscendantCode(final Indicator entity) {
        return entity.getAscendant() != null ? entity.getAscendant().getCode() : null;
    }

    private List<String> getPath(final Indicator entity) {
        return addToPath(entity.getAscendant(), new ArrayList<>());
    }

    private List<String> addToPath(final Indicator entity, final List<String> path) {
        if (entity != null) {
            addToPath(entity.getAscendant(), path);
            path.add(entity.getCode());
        }
        return path;
    }

    private boolean isTerminal(final Indicator entity) {
        return entity.getDescendants().size() == 0;
    }
}
