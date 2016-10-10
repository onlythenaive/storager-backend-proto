package ru.spb.iac.storager.server.domain.providers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.spb.iac.storager.server.domain.shared.PagedResult;

@RestController
@RequestMapping("/data/providers")
public class ProviderController {

    @Autowired
    private ProviderService service;

    @RequestMapping(method = RequestMethod.POST)
    public ProviderInfo create(final @RequestBody ProviderProperties properties) {
        return service.createOnUserBehalf(properties);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ProviderInfo getById(final @PathVariable(name = "id") Integer id) {
        return service.getByIdOnUserBehalf(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public PagedResult<ProviderInfo> getPage(final @RequestParam(name = "page", defaultValue = "1") Integer page,
                                             final @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return service.getPageOnUserBehalf(page, size);
    }

    @RequestMapping(path = "/{id}/token", method = RequestMethod.GET)
    public ProviderTokenInfo getTokenInfoById(final @PathVariable(name = "id") Integer id) {
        return service.getTokenInfoByIdOnUserBehalf(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void remove(final @PathVariable(name = "id") Integer id) {
        service.removeOnUserBehalf(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ProviderInfo update(final @PathVariable(name = "id") Integer id,
                               final @RequestBody ProviderProperties properties) {
        return service.updateOnUserBehalf(id, properties);
    }

    @RequestMapping(path = "/{id}/grants", method = RequestMethod.PUT)
    public ProviderInfo updateGrants(final @PathVariable(name = "id") Integer id,
                                     final @RequestBody Set<String> indicatorCodes) {
        return service.updateGrantsOnUserBehalf(id, indicatorCodes);
    }

    @RequestMapping(path = "/{id}/token", method = RequestMethod.PUT)
    public ProviderInfo updateToken(final @PathVariable(name = "id") Integer id) {
        return service.updateTokenOnUserBehalf(id);
    }
}
