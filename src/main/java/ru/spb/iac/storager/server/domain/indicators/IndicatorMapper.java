package ru.spb.iac.storager.server.domain.indicators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndicatorMapper {

    @Autowired
    private IndicatorRepository indicatorRepository;

    public Indicator fromData(final IndicatorData data) {
        return new Indicator(data.getCode(), data.getTitle(), getAscendant(data));
    }

    public void withData(final Indicator indicator, final IndicatorData data) {
        indicator.setCode(data.getCode());
        indicator.setTitle(data.getTitle());
        indicator.setAscendant(getAscendant(data));
    }

    public IndicatorData fromEntity(final Indicator indicator) {
        final IndicatorData data = new IndicatorData();
        data.setCode(indicator.getCode());
        data.setAscendantCode(indicator.getAscendant() != null ? indicator.getAscendant().getCode() : null);
        data.setTitle(indicator.getTitle());
        data.setTerminal(indicator.getDescendants().size() == 0);
        return data;
    }

    private Indicator getAscendant(final IndicatorData data) {
        return data.getAscendantCode() != null ? indicatorRepository.findByCode(data.getAscendantCode()) : null;
    }
}
