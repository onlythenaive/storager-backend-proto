package ru.spb.iac.storager.server.domain.shared.hierarchic;

import java.util.List;

public abstract class HierarchicItemController {

    protected HierarchicItemInfo create(final HierarchicItemProperties properties) {
        return getService().create(properties);
    }

    protected HierarchicItemInfo getByCode(final String code) {
        return getService().getByCode(code);
    }

    protected List<HierarchicItemInfo> getDescendants(final String code) {
        return getService().getDescendants(code);
    }

    protected List<HierarchicItemInfo> getRoots() {
        return getService().getRoots();
    }

    protected void remove(final String code) {
        getService().remove(code);
    }

    protected HierarchicItemInfo update(final String code, final HierarchicItemProperties properties) {
        return getService().update(code, properties);
    }

    protected abstract HierarchicItemService getService();
}
