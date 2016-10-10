package ru.spb.iac.storager.server.domain.shared.hierarchic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.errors.domain.InputValidationHelper;
import ru.spb.iac.storager.server.errors.domain.ItemValidationHelper;
import ru.spb.iac.storager.server.security.SecurityContext;
import static ru.spb.iac.storager.server.security.SecurityRoles.ADMIN;
import static ru.spb.iac.storager.server.security.SecurityRoles.USER;

public abstract class HierarchicItemService<T extends HierarchicItem<T>> {

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private InputValidationHelper inputValidationHelper;

    @Autowired
    private ItemValidationHelper itemValidationHelper;

    @Transactional
    public HierarchicItemInfo bootstrap(final HierarchicItemProperties properties) {
        return create(properties);
    }

    @Transactional
    public HierarchicItemInfo createOnUserBehalf(final HierarchicItemProperties properties) {
        securityContext.userAuthorizedWithAny(ADMIN);
        return create(properties);
    }

    @Transactional(readOnly = true)
    public HierarchicItemInfo getByCodeOnUserBehalf(final String code) {
        securityContext.userAuthorizedWithAny(ADMIN, USER);
        return getMapper().intoInfo(get(code));
    }

    @Transactional(readOnly = true)
    public List<HierarchicItemInfo> getDescendantsOnUserBehalf(final String code) {
        securityContext.userAuthorizedWithAny(ADMIN, USER);
        return get(code)
                .getDescendants()
                .stream()
                .map(getMapper()::intoInfo)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<HierarchicItemInfo> getRootsOnUserBehalf() {
        securityContext.userAuthorizedWithAny(ADMIN, USER);
        return getRepository()
                .findRoots()
                .stream()
                .map(getMapper()::intoInfo)
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeOnUserBehalf(final String code) {
        securityContext.userAuthorizedWithAny(ADMIN);
        getRepository().delete(get(code));
    }

    @Transactional
    public HierarchicItemInfo updateOnUserBehalf(final String code, final HierarchicItemProperties properties) {
        securityContext.userAuthorizedWithAny(ADMIN);
        getValidator().validateForUpdate(code, properties);
        final T entity = get(code);
        getMapper().intoEntity(properties, entity);
        return getMapper().intoInfo(getRepository().save(entity));
    }

    protected T get(final String code) {
        inputValidationHelper.required(code, "code");
        return itemValidationHelper.required(getRepository().findByCode(code), "code", code);
    }

    protected abstract HierarchicItemMapper<T> getMapper();

    protected abstract HierarchicItemRepository<T> getRepository();

    protected abstract HierarchicItemValidator getValidator();

    private HierarchicItemInfo create(final HierarchicItemProperties properties) {
        final T entity = getMapper().intoEntity(getValidator().validateForCreate(properties));
        return getMapper().intoInfo(getRepository().save(entity));
    }
}
