package ru.spb.iac.storager.server.domain.shared.hierarchic;

import java.util.ArrayList;
import java.util.List;

public abstract class HierarchicItemMapperGeneric<T extends HierarchicItem<T>> implements HierarchicItemMapper<T> {

    @Override
    public T intoEntity(final HierarchicItemProperties properties) {
        return intoEntity(properties, createItemInstance());
    }

    @Override
    public T intoEntity(final HierarchicItemProperties properties, final T entity) {
        entity.setCode(properties.getCode());
        entity.setAscendant(getAscendant(properties.getAscendantCode()));
        entity.setTitle(properties.getTitle());
        entity.setDescription(properties.getDescription());
        return entity;
    }

    @Override
    public HierarchicItemInfo intoInfo(final T entity) {
        return null;
    }

    protected abstract HierarchicItemRepository<T> getRepository();

    protected abstract T createItemInstance();

    private T getAscendant(final String ascendantCode) {
        return ascendantCode != null ? getRepository().findByCode(ascendantCode) : null;
    }

    private String getAscendantCode(final T entity) {
        return entity.getAscendant() != null ? entity.getAscendant().getCode() : null;
    }

    private List<String> getPath(final T entity) {
        return addToPath(entity.getAscendant(), new ArrayList<>());
    }

    private List<String> addToPath(final HierarchicItem<T> entity, final List<String> path) {
        if (entity != null) {
            addToPath(entity.getAscendant(), path);
            path.add(entity.getCode());
        }
        return path;
    }

    private boolean isTerminal(final T entity) {
        return entity.getDescendants().size() == 0;
    }
}
