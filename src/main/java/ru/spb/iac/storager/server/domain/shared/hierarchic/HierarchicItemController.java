package ru.spb.iac.storager.server.domain.shared.hierarchic;

import java.util.List;

public abstract class HierarchicItemController<T extends HierarchicItem<T>> {

    protected HierarchicItemInfo create(final HierarchicItemProperties properties) {
        return getService().createOnUserBehalf(properties);
    }

    protected HierarchicItemInfo getByCode(final String code) {
        return getService().getByCodeOnUserBehalf(code);
    }

    protected List<HierarchicItemInfo> getDescendants(final String code) {
        return getService().getDescendantsOnUserBehalf(code);
    }

    protected List<HierarchicItemInfo> getRoots() {
        return getService().getRootsOnUserBehalf();
    }

    protected void remove(final String code) {
        getService().removeOnUserBehalf(code);
    }

    protected HierarchicItemInfo update(final String code, final HierarchicItemProperties properties) {
        return getService().updateOnUserBehalf(code, properties);
    }

    protected abstract HierarchicItemService<T> getService();
}
