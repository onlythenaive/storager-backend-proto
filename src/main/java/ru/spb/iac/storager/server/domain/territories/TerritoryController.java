package ru.spb.iac.storager.server.domain.territories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.spb.iac.storager.server.security.SecurityContext;

@RestController
@RequestMapping("/data/territories")
public class TerritoryController {

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private TerritoryService territoryService;

    @RequestMapping(method = RequestMethod.POST)
    public TerritoryInfo create(final @RequestBody TerritoryProperties properties) {
        securityContext.userAuthorizedWithAny("ADMIN");
        return territoryService.create(properties);
    }

    @RequestMapping(path = "/{code}", method = RequestMethod.GET)
    public TerritoryInfo getByCode(final @PathVariable(name = "code") String code) {
        securityContext.userAuthorizedWithAny("USER", "ADMIN");
        return territoryService.getByCode(code);
    }

    @RequestMapping(path = "/{code}/descendants", method = RequestMethod.GET)
    public List<TerritoryInfo> getDescendants(final @PathVariable(name = "code") String code) {
        securityContext.userAuthorizedWithAny("USER", "ADMIN");
        return territoryService.getDescendants(code);
    }

    @RequestMapping(path = "/roots", method = RequestMethod.GET)
    public List<TerritoryInfo> getRoots() {
        securityContext.userAuthorizedWithAny("USER", "ADMIN");
        return territoryService.getRoots();
    }

    @RequestMapping(path = "/{code}", method = RequestMethod.DELETE)
    public void remove(final @PathVariable(name = "code") String code) {
        securityContext.userAuthorizedWithAny("ADMIN");
        territoryService.remove(code);
    }

    @RequestMapping(path = "/{code}", method = RequestMethod.PUT)
    public TerritoryInfo update(final @PathVariable(name = "code") String code,
                                final @RequestBody TerritoryProperties properties) {
        securityContext.userAuthorizedWithAny("ADMIN");
        return territoryService.update(code, properties);
    }
}
