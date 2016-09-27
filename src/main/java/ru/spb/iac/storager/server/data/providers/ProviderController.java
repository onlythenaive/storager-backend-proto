package ru.spb.iac.storager.server.data.providers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.spb.iac.storager.server.data.shared.LimitedResult;

@RestController
@RequestMapping("/data/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @RequestMapping(method = RequestMethod.POST)
    public ProviderInfo create(@RequestBody ProviderInfo info) {
        return providerService.create(info);
    }

    @RequestMapping(method = RequestMethod.GET)
    public LimitedResult<ProviderInfo> filter() {
        return providerService.filter();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ProviderInfo getById(@PathVariable(name = "id") Integer id) {
        return providerService.getById(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable(name = "id") Integer id) {
        providerService.remove(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ProviderInfo update(@PathVariable(name = "id") Integer id, @RequestBody ProviderInfo info) {
        return providerService.update(id, info);
    }

    @RequestMapping(path = "/{id}/grants", method = RequestMethod.PUT)
    public ProviderInfo updateGrants(@PathVariable(name = "id") Integer id, @RequestBody List<String> grants) {
        return providerService.updateGrants(id, grants);
    }

    @RequestMapping(path = "/{id}/token", method = RequestMethod.PUT)
    public ProviderInfo updateToken(@PathVariable(name = "id") Integer id) {
        return providerService.updateToken(id);
    }
}
