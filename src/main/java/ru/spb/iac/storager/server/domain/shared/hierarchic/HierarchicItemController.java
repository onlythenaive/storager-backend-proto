package ru.spb.iac.storager.server.domain.shared.hierarchic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ru.spb.iac.storager.server.security.SecurityContext;
import static ru.spb.iac.storager.server.security.SecurityRoles.ADMIN;
import static ru.spb.iac.storager.server.security.SecurityRoles.USER;

public abstract class HierarchicItemController<T extends HierarchicItem<T>> {

    @Autowired
    private SecurityContext securityContext;

    protected HierarchicItemInfo create(final HierarchicItemProperties properties) {
        securityContext.userAuthorizedWithAny(ADMIN);
        return getService().create(properties);
    }

    protected HierarchicItemInfo getByCode(final String code) {
        securityContext.userAuthorizedWithAny(ADMIN, USER);
        return getService().getByCode(code);
    }

    protected List<HierarchicItemInfo> getDescendants(final String code) {
        securityContext.userAuthorizedWithAny(ADMIN, USER);
        return getService().getDescendants(code);
    }

    protected List<HierarchicItemInfo> getRoots() {
        securityContext.userAuthorizedWithAny(ADMIN, USER);
        return getService().getRoots();
    }

    protected void remove(final String code) {
        securityContext.userAuthorizedWithAny(ADMIN);
        getService().remove(code);
    }

    protected HierarchicItemInfo update(final String code, final HierarchicItemProperties properties) {
        securityContext.userAuthorizedWithAny(ADMIN);
        return getService().update(code, properties);
    }

    protected abstract HierarchicItemService<T> getService();
}
