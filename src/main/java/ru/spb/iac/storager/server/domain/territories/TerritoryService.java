package ru.spb.iac.storager.server.domain.territories;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.errors.domain.InputValidationHelper;
import ru.spb.iac.storager.server.errors.domain.ItemValidationHelper;

@Service
@Transactional
public class TerritoryService {

    @Autowired
    private InputValidationHelper inputValidation;

    @Autowired
    private ItemValidationHelper itemValidation;

    @Autowired
    private TerritoryMapper mapper;

    @Autowired
    private TerritoryRepository repository;

    @Autowired
    private TerritoryValidator validator;

    public TerritoryInfo create(final TerritoryProperties properties) {
        final Territory entity = mapper.intoEntity(validator.validateForCreate(properties));
        return mapper.intoInfo(repository.save(entity));
    }

    public TerritoryInfo getByCode(final String code) {
        return mapper.intoInfo(get(code));
    }

    public List<TerritoryInfo> getDescendants(final String code) {
        return get(code)
                .getDescendants()
                .stream()
                .map(mapper::intoInfo)
                .collect(Collectors.toList());
    }

    public List<TerritoryInfo> getRoots() {
        return repository
                .findRoots()
                .stream()
                .map(mapper::intoInfo)
                .collect(Collectors.toList());
    }

    public void remove(final String code) {
        repository.delete(get(code));
    }

    public TerritoryInfo update(final String code, final TerritoryProperties properties) {
        validator.validateForUpdate(code, properties);
        final Territory entity = get(code);
        mapper.intoEntity(properties, entity);
        return mapper.intoInfo(repository.save(entity));
    }

    private Territory get(final String code) {
        inputValidation.required(code, "code");
        return itemValidation.required(repository.findByCode(code), "code", code);
    }
}
