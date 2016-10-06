package ru.spb.iac.storager.server.domain.shared.hierarchic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import ru.spb.iac.storager.server.errors.domain.InputValidationHelper;
import ru.spb.iac.storager.server.errors.domain.ItemValidationHelper;

@Transactional
public abstract class HierarchicItemService<T extends HierarchicItem<T>> {

    @Autowired
    private InputValidationHelper inputValidationHelper;

    @Autowired
    private ItemValidationHelper itemValidationHelper;

    public HierarchicItemInfo create(final HierarchicItemProperties properties) {
        final T entity = getMapper().intoEntity(getValidator().validateForCreate(properties));
        return getMapper().intoInfo(getRepository().save(entity));
    }

    public HierarchicItemInfo getByCode(final String code) {
        return getMapper().intoInfo(get(code));
    }

    public List<HierarchicItemInfo> getDescendants(final String code) {
        return get(code)
                .getDescendants()
                .stream()
                .map(getMapper()::intoInfo)
                .collect(Collectors.toList());
    }

    public List<HierarchicItemInfo> getRoots() {
        return getRepository()
                .findRoots()
                .stream()
                .map(getMapper()::intoInfo)
                .collect(Collectors.toList());
    }

    public void remove(final String code) {
        getRepository().delete(get(code));
    }

    public HierarchicItemInfo update(final String code, final HierarchicItemProperties properties) {
        getValidator().validateForUpdate(code, properties);
        final T entity = get(code);
        getMapper().intoEntity(properties, entity);
        return getMapper().intoInfo(getRepository().save(entity));
    }

    protected abstract HierarchicItemMapper<T> getMapper();

    protected abstract HierarchicItemRepository<T> getRepository();

    protected abstract HierarchicItemValidator getValidator();

    private T get(final String code) {
        inputValidationHelper.required(code, "code");
        return itemValidationHelper.required(getRepository().findByCode(code), "code", code);
    }
}
