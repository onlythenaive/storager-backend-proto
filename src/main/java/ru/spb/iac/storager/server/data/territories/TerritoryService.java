package ru.spb.iac.storager.server.data.territories;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TerritoryService {

    @Autowired
    private TerritoryRepository territoryRepository;

    public TerritoryInfo create(TerritoryInfo info) {
        Territory ascendant = info.getAscendantCode() != null ? territoryRepository.findByCode(info.getAscendantCode()) : null;
        Territory territory = Territory.of(info.getCode(), info.getTitle(), ascendant);
        return TerritoryInfo.fromTerritory(territoryRepository.save(territory));
    }

    public TerritoryInfo getByCode(String code) {
        return TerritoryInfo.fromTerritory(territoryRepository.findByCode(code));
    }

    public List<TerritoryInfo> getDescendants(String code) {
        return territoryRepository
                .findDescendants(code)
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
