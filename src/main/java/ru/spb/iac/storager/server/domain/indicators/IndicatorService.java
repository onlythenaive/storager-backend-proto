package ru.spb.iac.storager.server.domain.indicators;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IndicatorService {

    @Autowired
    private IndicatorMapper indicatorMapper;

    @Autowired
    private IndicatorRepository indicatorRepository;

    public IndicatorData create(final IndicatorData data) {
        return indicatorMapper.fromEntity(indicatorRepository.save(indicatorMapper.fromData(data)));
    }

    public IndicatorData getByCode(final String code) {
        return indicatorMapper.fromEntity(indicatorRepository.findByCode(code));
    }

    public List<IndicatorData> getDescendants(final String code) {
        return indicatorRepository
                .findDescendants(code)
                .stream()
                .map(indicatorMapper::fromEntity)
                .collect(Collectors.toList());
    }

    public List<IndicatorData> getRoots() {
        return indicatorRepository
                .findRoots()
                .stream()
                .map(indicatorMapper::fromEntity)
                .collect(Collectors.toList());
    }

    public void remove(final String code) {
        indicatorRepository.delete(indicatorRepository.findByCode(code));
    }

    public IndicatorData update(final String code, final IndicatorData data) {
        final Indicator indicator = indicatorRepository.findByCode(code);
        indicatorMapper.withData(indicator, data);
        return indicatorMapper.fromEntity(indicatorRepository.save(indicator));
    }
}
