package ru.spb.iac.storager.server.domain.shared.hierarchic;


public interface HierarchicItemMapper<T extends HierarchicItem<T>> {

    T intoEntity(final HierarchicItemProperties properties);

    T intoEntity(final HierarchicItemProperties properties, final T entity);

    HierarchicItemInfo intoInfo(final T entity);
}
