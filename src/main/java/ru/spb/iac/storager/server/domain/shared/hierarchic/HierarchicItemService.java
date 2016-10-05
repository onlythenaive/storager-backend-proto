package ru.spb.iac.storager.server.domain.shared.hierarchic;

import java.util.List;

public interface HierarchicItemService {

    HierarchicItemInfo create(final HierarchicItemProperties properties);

    HierarchicItemInfo getByCode(final String code);

    List<HierarchicItemInfo> getDescendants(final String code);

    List<HierarchicItemInfo> getRoots();

    void remove(final String code);

    HierarchicItemInfo update(final String code, final HierarchicItemProperties properties);
}
