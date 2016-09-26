package ru.spb.iac.storager.server.data.territories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TerritoryService {

    @Autowired
    private TerritoryRepository territoryRepository;

    public TerritoryInfo create(TerritoryInfo territoryInfo) {
        Territory territory = new Territory();
        territory.setCode(territoryInfo.getCode());
        territory.setAscendant(territoryInfo.getAscendantCode() != null ? territoryRepository.findByCode(territoryInfo.getAscendantCode()) : null);
        territory.setTitle(territoryInfo.getTitle());
        territoryRepository.save(territory);
        return getByCode(territoryInfo.getCode());
    }

    public TerritoryInfo getByCode(String code) {
        return TerritoryInfo.fromTerritory(territoryRepository.findByCode(code));
    }

    public List<TerritoryInfo> getDescendantsByCode(String code) {
        return territoryRepository
                .findDescendantsByCode(code)
                .stream()
                .map(TerritoryInfo::fromTerritory)
                .collect(Collectors.toList());
    }

    public List<TerritoryInfo> getRoots() {
        return territoryRepository
                .findRoots()
                .stream()
                .map(TerritoryInfo::fromTerritory)
                .collect(Collectors.toList());
    }
}
