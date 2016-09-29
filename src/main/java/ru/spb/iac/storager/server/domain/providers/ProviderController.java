package ru.spb.iac.storager.server.domain.providers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.spb.iac.storager.server.security.SecurityContext;
import ru.spb.iac.storager.server.domain.shared.PagedResult;

@RestController
@RequestMapping("/data/providers")
public class ProviderController {

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private ProviderService providerService;

    @RequestMapping(method = RequestMethod.POST)
    public ProviderInfo create(@RequestBody ProviderInfo info) {
        securityContext.userAuthorizedWith("ADMIN");
        return providerService.create(info);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ProviderInfo getById(@PathVariable(name = "id") Integer id) {
        securityContext.userAuthorizedWith("USER", "ADMIN");
        return providerService.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public PagedResult<ProviderInfo> getPage(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                             @RequestParam(name = "size", defaultValue = "10") Integer size) {
        securityContext.userAuthorizedWith("USER", "ADMIN");
        return providerService.getPage(page, size);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable(name = "id") Integer id) {
        securityContext.userAuthorizedWith("ADMIN");
        providerService.remove(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ProviderInfo update(@PathVariable(name = "id") Integer id, @RequestBody ProviderInfo info) {
        securityContext.userAuthorizedWith("ADMIN");
        return providerService.update(id, info);
    }

    @RequestMapping(path = "/{id}/grants", method = RequestMethod.PUT)
    public ProviderInfo updateGrants(@PathVariable(name = "id") Integer id, @RequestBody List<String> grants) {
        securityContext.userAuthorizedWith("ADMIN");
        return providerService.updateGrants(id, grants);
    }

    @RequestMapping(path = "/{id}/token", method = RequestMethod.PUT)
    public ProviderInfo updateToken(@PathVariable(name = "id") Integer id) {
        securityContext.userAuthorizedWith("ADMIN");
        return providerService.updateToken(id);
    }
}
