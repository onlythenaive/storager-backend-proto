package ru.spb.iac.storager.server.domain.shared.hierarchic;

import java.util.List;
import java.util.stream.Collectors;

import ru.spb.iac.storager.server.errors.domain.InputValidationHelper;
import ru.spb.iac.storager.server.errors.domain.ItemValidationHelper;

public abstract class HierarchicItemServiceGeneric<T extends HierarchicItem<T>> implements HierarchicItemService {

    @Override
    public HierarchicItemInfo create(final HierarchicItemProperties properties) {
        final T entity = getMapper().intoEntity(getValidator().validateForCreate(properties));
        return getMapper().intoInfo(getRepository().save(entity));
    }

    @Override
    public HierarchicItemInfo getByCode(final String code) {
        return getMapper().intoInfo(get(code));
    }

    @Override
    public List<HierarchicItemInfo> getDescendants(final String code) {
        return get(code)
                .getDescendants()
                .stream()
                .map(getMapper()::intoInfo)
                .collect(Collectors.toList());
    }

    @Override
    public List<HierarchicItemInfo> getRoots() {
        return getRepository()
                .findRoots()
                .stream()
                .map(getMapper()::intoInfo)
                .collect(Collectors.toList());
    }

    @Override
    public void remove(final String code) {
        getRepository().delete(get(code));
    }

    @Override
    public HierarchicItemInfo update(final String code, final HierarchicItemProperties properties) {
        getValidator().validateForUpdate(code, properties);
        final T entity = get(code);
        getMapper().intoEntity(properties, entity);
        return getMapper().intoInfo(getRepository().save(entity));
    }

    protected abstract InputValidationHelper getInputValidationHelper();

    protected abstract ItemValidationHelper getItemValidationHelper();

    protected abstract HierarchicItemMapper<T> getMapper();

    protected abstract HierarchicItemRepository<T> getRepository();

    protected abstract HierarchicItemValidator getValidator();

    private T get(final String code) {
        getInputValidationHelper().required(code, "code");
        return getItemValidationHelper().required(getRepository().findByCode(code), "code", code);
    }
}
