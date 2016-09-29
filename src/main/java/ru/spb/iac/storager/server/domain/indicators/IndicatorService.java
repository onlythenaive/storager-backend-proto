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
    private IndicatorRepository indicatorRepository;

    public IndicatorInfo create(IndicatorInfo info) {
        Indicator indicator = new Indicator(info.getCode(), info.getTitle(), getAscendant(info));
        return IndicatorInfo.fromIndicator(indicatorRepository.save(indicator));
    }

    public IndicatorInfo getByCode(String code) {
        return IndicatorInfo.fromIndicator(indicatorRepository.findByCode(code));
    }

    public List<IndicatorInfo> getDescendants(String code) {
        return indicatorRepository
                .findDescendants(code)
                .stream()
                .map(IndicatorInfo::fromIndicator)
                .collect(Collectors.toList());
    }

    public List<IndicatorInfo> getRoots() {
        return indicatorRepository
                .findRoots()
                .stream()
                .map(IndicatorInfo::fromIndicator)
                .collect(Collectors.toList());
    }

    public void remove(String code) {
        indicatorRepository.delete(indicatorRepository.findByCode(code));
    }

    public IndicatorInfo update(String code, IndicatorInfo info) {
        Indicator indicator = indicatorRepository.findByCode(code);
        indicator.setCode(info.getCode());
        indicator.setTitle(info.getTitle());
        indicator.setAscendant(getAscendant(info));
        return IndicatorInfo.fromIndicator(indicatorRepository.save(indicator));
    }

    private Indicator getAscendant(IndicatorInfo info) {
        return info.getAscendantCode() != null ? indicatorRepository.findByCode(info.getAscendantCode()) : null;
    }
}
