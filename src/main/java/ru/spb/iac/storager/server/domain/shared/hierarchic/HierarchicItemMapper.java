package ru.spb.iac.storager.server.domain.shared.hierarchic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public abstract class HierarchicItemMapper<T extends HierarchicItem<T>> {

    @Transactional(readOnly = true)
    public T intoEntity(final HierarchicItemProperties properties) {
        return intoEntity(properties, createItemInstance());
    }

    @Transactional(readOnly = true)
    public T intoEntity(final HierarchicItemProperties properties, final T entity) {
        entity.setCode(properties.getCode());
        entity.setAscendant(getAscendant(properties.getAscendantCode()));
        entity.setTitle(properties.getTitle());
        entity.setDescription(properties.getDescription());
        return entity;
    }

    @Transactional(readOnly = true)
    public HierarchicItemInfo intoInfo(final T entity) {
        final String code = entity.getCode();
        final String ascendantCode = getAscendantCode(entity);
        final String title = entity.getTitle();
        final String description = entity.getDescription();
        final List<String> path = getPath(entity);
        final Boolean terminal = isTerminal(entity);
        return new HierarchicItemInfo(code, ascendantCode, title, description, path, terminal);
    }

    protected abstract HierarchicItemRepository<T> getRepository();

    protected abstract T createItemInstance();

    protected boolean isTerminal(final T entity) {
        return entity.getDescendants().size() == 0;
    }

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
}
