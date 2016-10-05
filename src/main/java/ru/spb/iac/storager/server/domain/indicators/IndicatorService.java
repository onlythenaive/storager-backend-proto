package ru.spb.iac.storager.server.domain.indicators;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.errors.domain.InputValidationHelper;
import ru.spb.iac.storager.server.errors.domain.ItemValidationHelper;

@Service
@Transactional
public class IndicatorService {

    @Autowired
    private InputValidationHelper inputValidation;

    @Autowired
    private ItemValidationHelper itemValidation;

    @Autowired
    private IndicatorMapper mapper;

    @Autowired
    private IndicatorRepository repository;

    @Autowired
    private IndicatorValidator validator;

    public IndicatorInfo create(final IndicatorProperties properties) {
        final Indicator entity = mapper.intoEntity(validator.validateForCreate(properties));
        return mapper.intoInfo(repository.save(entity));
    }

    public IndicatorInfo getByCode(final String code) {
        return mapper.intoInfo(get(code));
    }

    public List<IndicatorInfo> getDescendants(final String code) {
        return get(code)
                .getDescendants()
                .stream()
                .map(mapper::intoInfo)
                .collect(Collectors.toList());
    }

    public List<IndicatorInfo> getRoots() {
        return repository
                .findRoots()
                .stream()
                .map(mapper::intoInfo)
                .collect(Collectors.toList());
    }

    public void remove(final String code) {
        repository.delete(get(code));
    }

    public IndicatorInfo update(final String code, final IndicatorProperties properties) {
        validator.validateForUpdate(code, properties);
        final Indicator entity = get(code);
        mapper.intoEntity(properties, entity);
        return mapper.intoInfo(repository.save(entity));
    }

    private Indicator get(final String code) {
        inputValidation.required(code, "code");
        return itemValidation.required(repository.findByCode(code), "code", code);
    }
}
