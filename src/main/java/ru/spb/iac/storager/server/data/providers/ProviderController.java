package ru.spb.iac.storager.server.data.providers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.spb.iac.storager.server.data.shared.PagedResult;

@RestController
@RequestMapping("/data/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    // TODO: add authorization policy (requires: ADMIN)
    @RequestMapping(method = RequestMethod.POST)
    public ProviderInfo create(@RequestBody ProviderInfo info) {
        return providerService.create(info);
    }

    // TODO: add authorization policy (requires: USER | ADMIN)
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ProviderInfo getById(@PathVariable(name = "id") Integer id) {
        return providerService.getById(id);
    }

    // TODO: add authorization policy (requires: USER | ADMIN)
    @RequestMapping(method = RequestMethod.GET)
    public PagedResult<ProviderInfo> getPage(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
        return providerService.getPage(page, size);
    }

    // TODO: add authorization policy (requires: ADMIN)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable(name = "id") Integer id) {
        providerService.remove(id);
    }

    // TODO: add authorization policy (requires: ADMIN)
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ProviderInfo update(@PathVariable(name = "id") Integer id, @RequestBody ProviderInfo info) {
        return providerService.update(id, info);
    }

    // TODO: add authorization policy (requires: ADMIN)
    @RequestMapping(path = "/{id}/grants", method = RequestMethod.PUT)
    public ProviderInfo updateGrants(@PathVariable(name = "id") Integer id, @RequestBody List<String> grants) {
        return providerService.updateGrants(id, grants);
    }

    // TODO: add authorization policy (requires: ADMIN)
    @RequestMapping(path = "/{id}/token", method = RequestMethod.PUT)
    public ProviderInfo updateToken(@PathVariable(name = "id") Integer id) {
        return providerService.updateToken(id);
    }
}
