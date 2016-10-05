package ru.spb.iac.storager.server.domain.indicators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.spb.iac.storager.server.security.SecurityContext;

@RestController
@RequestMapping("/data/indicators")
public class IndicatorController {

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private IndicatorService service;

    @RequestMapping(method = RequestMethod.POST)
    public IndicatorInfo create(final @RequestBody IndicatorProperties properties) {
        securityContext.userAuthorizedWithAny("ADMIN");
        return service.create(properties);
    }

    @RequestMapping(path = "/{code}", method = RequestMethod.GET)
    public IndicatorInfo getByCode(final @PathVariable(name = "code") String code) {
        securityContext.userAuthorizedWithAny("USER", "ADMIN");
        return service.getByCode(code);
    }

    @RequestMapping(path = "/{code}/descendants", method = RequestMethod.GET)
    public List<IndicatorInfo> getDescendants(final @PathVariable(name = "code") String code) {
        securityContext.userAuthorizedWithAny("USER", "ADMIN");
        return service.getDescendants(code);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<IndicatorInfo> getRoots() {
        securityContext.userAuthorizedWithAny("USER", "ADMIN");
        return service.getRoots();
    }

    @RequestMapping(path = "/{code}", method = RequestMethod.DELETE)
    public void remove(final @PathVariable(name = "code") String code) {
        securityContext.userAuthorizedWithAny("ADMIN");
        service.remove(code);
    }

    @RequestMapping(path = "/{code}", method = RequestMethod.PUT)
    public IndicatorInfo update(final @PathVariable(name = "code") String code,
                                      final @RequestBody IndicatorProperties properties) {
        securityContext.userAuthorizedWithAny("ADMIN");
        return service.update(code, properties);
    }
}
