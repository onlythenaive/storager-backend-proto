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
    private TerritoryMapper territoryMapper;

    @Autowired
    private TerritoryRepository territoryRepository;

    public TerritoryData create(final TerritoryData data) {
        return territoryMapper.createData(territoryRepository.save(territoryMapper.createEntity(data)));
    }

    public TerritoryData getByCode(final String code) {
        return territoryMapper.createData(territoryRepository.findByCode(code));
    }

    public List<TerritoryData> getDescendants(final String code) {
        return territoryRepository
                .findDescendants(code)
                .stream()
                .map(territoryMapper::createData)
                .collect(Collectors.toList());
    }

    public List<TerritoryData> getRoots() {
        return territoryRepository
                .findRoots()
                .stream()
                .map(territoryMapper::createData)
                .collect(Collectors.toList());
    }

    public void remove(final String code) {
        territoryRepository.delete(territoryRepository.findByCode(code));
    }

    public TerritoryData update(final String code, final TerritoryData data) {
        final Territory territory = territoryRepository.findByCode(code);
        territoryMapper.updateEntity(territory, data);
        return territoryMapper.createData(territoryRepository.save(territory));
    }
}
