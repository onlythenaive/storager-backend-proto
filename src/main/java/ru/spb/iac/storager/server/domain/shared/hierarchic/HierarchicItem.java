package ru.spb.iac.storager.server.domain.shared.hierarchic;

import java.util.List;

public interface HierarchicItem<T extends HierarchicItem<T>> {

    Integer getId();

    String getCode();

    void setCode(String code);

    String getTitle();

    void setTitle(String title);

    String getDescription();

    void setDescription(String description);

    T getAscendant();

    void setAscendant(T ascendant);

    List<T> getDescendants();
}
