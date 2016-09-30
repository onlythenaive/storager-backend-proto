package ru.spb.iac.storager.server.domain.territories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerritoryMapper {

    @Autowired
    private TerritoryRepository territoryRepository;

    public TerritoryData createData(final Territory territory) {
        final TerritoryData data = new TerritoryData();
        data.setCode(territory.getCode());
        data.setAscendantCode(getAscendantCode(territory));
        data.setTitle(territory.getTitle());
        data.setTerminal(isTerminal(territory));
        return data;
    }

    public Territory createEntity(final TerritoryData data) {
        return new Territory(data.getCode(), data.getTitle(), getAscendant(data.getAscendantCode()));
    }

    public Territory updateEntity(final Territory territory, final TerritoryData data) {
        territory.setCode(data.getCode());
        territory.setTitle(data.getTitle());
        territory.setAscendant(getAscendant(data.getAscendantCode()));
        return territory;
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
