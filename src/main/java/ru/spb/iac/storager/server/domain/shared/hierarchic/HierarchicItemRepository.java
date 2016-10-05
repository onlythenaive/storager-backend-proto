package ru.spb.iac.storager.server.domain.shared.hierarchic;

import java.util.List;

public interface HierarchicItemRepository<T extends HierarchicItem<T>> {

    void delete(T entity);

    T findByCode(String code);

    List<T> findRoots();

    T save(T entity);
}
