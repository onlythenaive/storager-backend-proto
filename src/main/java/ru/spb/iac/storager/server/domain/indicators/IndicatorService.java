package ru.spb.iac.storager.server.domain.indicators;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.errors.domain.InvalidCodeException;

@Service
@Transactional
public class IndicatorService {

    @Autowired
    private IndicatorMapper indicatorMapper;

    @Autowired
    private IndicatorRepository indicatorRepository;

    public IndicatorData create(final IndicatorData data) {
        final Indicator indicator = indicatorMapper.createEntity(data);
        return saveAndCreateData(indicator);
    }

    public IndicatorData getByCode(final String code) {
        final Indicator indicator = get(code);
        return indicatorMapper.createData(indicator);
    }

    public List<IndicatorData> getDescendants(final String code) {
        final Indicator indicator = get(code);
        return indicator
                .getDescendants()
                .stream()
                .map(indicatorMapper::createData)
                .collect(Collectors.toList());
    }

    public List<IndicatorData> getRoots() {
        return indicatorRepository
                .findRoots()
                .stream()
                .map(indicatorMapper::createData)
                .collect(Collectors.toList());
    }

    public void remove(final String code) {
        final Indicator indicator = get(code);
        indicatorRepository.delete(indicator);
    }

    public IndicatorData update(final String code, final IndicatorData data) {
        final Indicator indicator = get(code);
        return saveAndCreateData(indicatorMapper.updateEntity(indicator, data));
    }

    private Indicator get(final String code) {
        final Indicator indicator = indicatorRepository.findByCode(code);
        if (indicator == null) {
            throw new InvalidCodeException(code);
        }
        return indicator;
    }

    private IndicatorData saveAndCreateData(final Indicator indicator) {
        return indicatorMapper.createData(indicatorRepository.save(indicator));
    }
}
