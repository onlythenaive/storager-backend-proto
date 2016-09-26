package ru.spb.iac.storager.server.data.indicators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class IndicatorService {

    @Autowired
    private IndicatorRepository indicatorRepository;

    public IndicatorInfo create(IndicatorInfo indicatorInfo) {
        Indicator indicator = new Indicator();
        indicator.setCode(indicatorInfo.getCode());
        indicator.setAscendant(indicatorInfo.getAscendantCode() != null ? indicatorRepository.findByCode(indicatorInfo.getAscendantCode()) : null);
        indicator.setTitle(indicatorInfo.getTitle());
        indicatorRepository.save(indicator);
        return getByCode(indicatorInfo.getCode());
    }

    public IndicatorInfo getByCode(String code) {
        return IndicatorInfo.fromIndicator(indicatorRepository.findByCode(code));
    }

    public List<IndicatorInfo> getDescendantsByCode(String code) {
        return indicatorRepository
                .findDescendantsByCode(code)
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
}
