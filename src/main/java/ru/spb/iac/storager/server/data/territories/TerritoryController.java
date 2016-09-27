package ru.spb.iac.storager.server.data.territories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.spb.iac.storager.server.SecurityContext;

@RestController
@RequestMapping("/data/territories")
public class TerritoryController {

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private TerritoryService territoryService;

    @RequestMapping(method = RequestMethod.POST)
    public TerritoryInfo create(@RequestBody TerritoryInfo territoryInfo) {
        securityContext.userAuthorizedWith("ADMIN");
        return territoryService.create(territoryInfo);
    }

    @RequestMapping(path = "/{code}", method = RequestMethod.GET)
    public TerritoryInfo getByCode(@PathVariable(name = "code") String code) {
        securityContext.userAuthorizedWith("USER", "ADMIN");
        return territoryService.getByCode(code);
    }

    @RequestMapping(path = "/{code}/descendants", method = RequestMethod.GET)
    public List<TerritoryInfo> getDescendants(@PathVariable(name = "code") String code) {
        securityContext.userAuthorizedWith("USER", "ADMIN");
        return territoryService.getDescendants(code);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TerritoryInfo> getRoots() {
        securityContext.userAuthorizedWith("USER", "ADMIN");
        return territoryService.getRoots();
    }

    @RequestMapping(path = "/{code}", method = RequestMethod.DELETE)
    public void remove(@PathVariable(name = "code") String code) {
        securityContext.userAuthorizedWith("ADMIN");
        territoryService.remove(code);
    }

    @RequestMapping(path = "/{code}", method = RequestMethod.PUT)
    public TerritoryInfo update(@PathVariable(name = "code") String code, @RequestBody TerritoryInfo info) {
        securityContext.userAuthorizedWith("ADMIN");
        return territoryService.update(code, info);
    }
}
