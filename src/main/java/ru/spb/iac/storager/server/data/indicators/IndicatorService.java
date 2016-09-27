package ru.spb.iac.storager.server.data.indicators;

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

    // TODO: add authorization policy (requires: ADMIN)
    public IndicatorInfo create(IndicatorInfo info) {
        Indicator indicator = Indicator.of(info.getCode(), info.getTitle(), getAscendant(info));
        return IndicatorInfo.fromIndicator(indicatorRepository.save(indicator));
    }

    // TODO: add authorization policy (requires: USER | ADMIN)
    public IndicatorInfo getByCode(String code) {
        return IndicatorInfo.fromIndicator(indicatorRepository.findByCode(code));
    }

    // TODO: add authorization policy (requires: USER | ADMIN)
    public List<IndicatorInfo> getDescendants(String code) {
        return indicatorRepository
                .findDescendants(code)
                .stream()
                .map(IndicatorInfo::fromIndicator)
                .collect(Collectors.toList());
    }

    // TODO: add authorization policy (requires: USER | ADMIN)
    public List<IndicatorInfo> getRoots() {
        return indicatorRepository
                .findRoots()
                .stream()
                .map(IndicatorInfo::fromIndicator)
                .collect(Collectors.toList());
    }

    // TODO: add authorization policy (requires: ADMIN)
    public void remove(String code) {
        indicatorRepository.delete(indicatorRepository.findByCode(code));
    }

    // TODO: add authorization policy (requires: ADMIN)
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
