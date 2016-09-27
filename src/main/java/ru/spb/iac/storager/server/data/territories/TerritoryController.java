package ru.spb.iac.storager.server.data.territories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/territories")
public class TerritoryController {

    @Autowired
    private TerritoryService territoryService;

    // TODO: add authorization policy (requires: ADMIN)
    @RequestMapping(method = RequestMethod.POST)
    public TerritoryInfo create(@RequestBody TerritoryInfo territoryInfo) {
        return territoryService.create(territoryInfo);
    }

    // TODO: add authorization policy (requires: USER | ADMIN)
    @RequestMapping(path = "/{code}", method = RequestMethod.GET)
    public TerritoryInfo getByCode(@PathVariable(name = "code") String code) {
        return territoryService.getByCode(code);
    }

    // TODO: add authorization policy (requires: USER | ADMIN)
    @RequestMapping(path = "/{code}/descendants", method = RequestMethod.GET)
    public List<TerritoryInfo> getDescendants(@PathVariable(name = "code") String code) {
        return territoryService.getDescendants(code);
    }

    // TODO: add authorization policy (requires: USER | ADMIN)
    @RequestMapping(method = RequestMethod.GET)
    public List<TerritoryInfo> getRoots() {
        return territoryService.getRoots();
    }

    // TODO: add authorization policy (requires: ADMIN)
    @RequestMapping(path = "/{code}", method = RequestMethod.DELETE)
    public void remove(@PathVariable(name = "code") String code) {
        territoryService.remove(code);
    }

    // TODO: add authorization policy (requires: ADMIN)
    @RequestMapping(path = "/{code}", method = RequestMethod.PUT)
    public TerritoryInfo update(@PathVariable(name = "code") String code, @RequestBody TerritoryInfo info) {
        return territoryService.update(code, info);
    }
}
