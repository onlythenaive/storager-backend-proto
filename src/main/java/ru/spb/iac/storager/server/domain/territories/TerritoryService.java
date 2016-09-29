package ru.spb.iac.storager.server.domain.territories;

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
        Territory territory = new Territory(info.getCode(), info.getTitle(), getAscendant(info));
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

    public void remove(String code) {
        territoryRepository.delete(territoryRepository.findByCode(code));
    }

    public TerritoryInfo update(String code, TerritoryInfo info) {
        Territory territory = territoryRepository.findByCode(code);
        territory.setCode(info.getCode());
        territory.setTitle(info.getTitle());
        territory.setAscendant(getAscendant(info));
        return TerritoryInfo.fromTerritory(territoryRepository.save(territory));
    }

    private Territory getAscendant(TerritoryInfo info) {
        return info.getAscendantCode() != null ? territoryRepository.findByCode(info.getAscendantCode()) : null;
    }
}
