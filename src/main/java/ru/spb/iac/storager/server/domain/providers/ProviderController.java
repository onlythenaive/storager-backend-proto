package ru.spb.iac.storager.server.domain.providers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.spb.iac.storager.server.security.SecurityContext;
import ru.spb.iac.storager.server.domain.shared.PagedResult;
import static ru.spb.iac.storager.server.security.SecurityRoles.ADMIN;
import static ru.spb.iac.storager.server.security.SecurityRoles.USER;

@RestController
@RequestMapping("/data/providers")
public class ProviderController {

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private ProviderService service;

    @RequestMapping(method = RequestMethod.POST)
    public ProviderInfo create(final @RequestBody ProviderProperties properties) {
        securityContext.userAuthorizedWithAny(ADMIN);
        return service.create(properties);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ProviderInfo getById(final @PathVariable(name = "id") Integer id) {
        securityContext.userAuthorizedWithAny(ADMIN, USER);
        return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public PagedResult<ProviderInfo> getPage(final @RequestParam(name = "page", defaultValue = "1") Integer page,
                                             final @RequestParam(name = "size", defaultValue = "10") Integer size) {
        securityContext.userAuthorizedWithAny(ADMIN, USER);
        return service.getPage(page, size);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void remove(final @PathVariable(name = "id") Integer id) {
        securityContext.userAuthorizedWithAny(ADMIN);
        service.remove(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ProviderInfo update(final @PathVariable(name = "id") Integer id,
                               final @RequestBody ProviderProperties properties) {
        securityContext.userAuthorizedWithAny(ADMIN);
        return service.update(id, properties);
    }

    @RequestMapping(path = "/{id}/grants", method = RequestMethod.PUT)
    public ProviderInfo updateGrants(final @PathVariable(name = "id") Integer id,
                                     final @RequestBody Set<String> indicatorCodes) {
        securityContext.userAuthorizedWithAny(ADMIN);
        return service.updateGrants(id, indicatorCodes);
    }

    @RequestMapping(path = "/{id}/token", method = RequestMethod.PUT)
    public ProviderInfo updateToken(final @PathVariable(name = "id") Integer id) {
        securityContext.userAuthorizedWithAny(ADMIN);
        return service.updateToken(id);
    }
}
