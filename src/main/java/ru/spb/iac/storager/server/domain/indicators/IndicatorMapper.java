package ru.spb.iac.storager.server.domain.indicators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.errors.domain.InvalidPropertyException;

@Service
@Transactional
public class IndicatorMapper {

    @Autowired
    private IndicatorRepository indicatorRepository;

    public IndicatorData createData(final Indicator indicator) {
        final IndicatorData data = new IndicatorData();
        data.setCode(indicator.getCode());
        data.setAscendantCode(getAscendantCode(indicator));
        data.setTitle(indicator.getTitle());
        data.setTerminal(isTerminal(indicator));
        return data;
    }

    public Indicator createEntity(final IndicatorData data) {
        validate(data);
        return new Indicator(data.getCode(), data.getTitle(), findAscendant(data.getAscendantCode()));
    }

    public Indicator updateEntity(final Indicator indicator, final IndicatorData data) {
        validate(data);
        indicator.setCode(data.getCode());
        indicator.setTitle(data.getTitle());
        indicator.setAscendant(findAscendant(data.getAscendantCode()));
        return indicator;
    }

    private void validate(final IndicatorData data) {
        if (data.getCode() == null) {
            throw InvalidPropertyException.missingProperty("code");
        }
        if (data.getTitle() == null) {
            throw InvalidPropertyException.missingProperty("title");
        }
        final String ascendantCode = data.getAscendantCode();
        final Indicator ascendant = findAscendant(ascendantCode);
        if (ascendantCode != null && ascendant == null) {
            throw new InvalidPropertyException("ascendant code must exist", "ascendantCode", ascendantCode);
        }
    }

    private Indicator findAscendant(final String ascendantCode) {
        return ascendantCode != null ? indicatorRepository.findByCode(ascendantCode) : null;
    }

    private String getAscendantCode(final Indicator indicator) {
        return indicator.getAscendant() != null ? indicator.getAscendant().getCode() : null;
    }

    private boolean isTerminal(final Indicator indicator) {
        return indicator.getDescendants().isEmpty();
    }
}
