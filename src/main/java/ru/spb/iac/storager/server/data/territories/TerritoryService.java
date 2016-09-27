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

    // TODO: add authorization policy (requires: ADMIN)
    public TerritoryInfo create(TerritoryInfo info) {
        Territory territory = Territory.of(info.getCode(), info.getTitle(), getAscendant(info));
        return TerritoryInfo.fromTerritory(territoryRepository.save(territory));
    }

    // TODO: add authorization policy (requires: USER | ADMIN)
    public TerritoryInfo getByCode(String code) {
        return TerritoryInfo.fromTerritory(territoryRepository.findByCode(code));
    }

    // TODO: add authorization policy (requires: USER | ADMIN)
    public List<TerritoryInfo> getDescendants(String code) {
        return territoryRepository
                .findDescendants(code)
                .stream()
                .map(TerritoryInfo::fromTerritory)
                .collect(Collectors.toList());
    }

    // TODO: add authorization policy (requires: USER | ADMIN)
    public List<TerritoryInfo> getRoots() {
        return territoryRepository
                .findRoots()
                .stream()
                .map(TerritoryInfo::fromTerritory)
                .collect(Collectors.toList());
    }

    // TODO: add authorization policy (requires: ADMIN)
    public void remove(String code) {
        territoryRepository.delete(territoryRepository.findByCode(code));
    }

    // TODO: add authorization policy (requires: ADMIN)
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
