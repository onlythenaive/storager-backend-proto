package ru.spb.iac.storager.server.domain.territories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TerritoryMapper {

    @Autowired
    private TerritoryRepository territoryRepository;

    public TerritoryInfo intoInfo(final Territory territory) {
        final String code = territory.getCode();
        final String ascendantCode = getAscendantCode(territory);
        final String title = territory.getTitle();
        final String description = territory.getDescription();
        final Boolean terminal = isTerminal(territory);
        return new TerritoryInfo(code, ascendantCode, title, description, terminal);
    }

    public Territory intoEntity(final TerritoryProperties properties) {
        return intoEntity(properties, new Territory());
    }

    public Territory intoEntity(final TerritoryProperties properties, final Territory entity) {
        entity.setCode(properties.getCode());
        entity.setAscendant(getAscendant(properties.getAscendantCode()));
        entity.setTitle(properties.getTitle());
        entity.setDescription(properties.getDescription());
        return entity;
    }

    private Territory getAscendant(final String ascendantCode) {
        return ascendantCode != null ? territoryRepository.findByCode(ascendantCode) : null;
    }

    private String getAscendantCode(final Territory territory) {
        return territory.getAscendant() != null ? territory.getAscendant().getCode() : null;
    }

    private boolean isTerminal(final Territory territory) {
        return territory.getDescendants().size() == 0;
    }
}
