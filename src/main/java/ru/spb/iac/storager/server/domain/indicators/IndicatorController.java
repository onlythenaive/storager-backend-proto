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
    private IndicatorService indicatorService;

    @RequestMapping(method = RequestMethod.POST)
    public IndicatorData create(@RequestBody IndicatorData data) {
        securityContext.userAuthorizedWith("ADMIN");
        return indicatorService.create(data);
    }

    @RequestMapping(path = "/{code}", method = RequestMethod.GET)
    public IndicatorData getByCode(@PathVariable(name = "code") String code) {
        securityContext.userAuthorizedWith("USER", "ADMIN");
        return indicatorService.getByCode(code);
    }

    @RequestMapping(path = "/{code}/descendants", method = RequestMethod.GET)
    public List<IndicatorData> getDescendants(@PathVariable(name = "code") String code) {
        securityContext.userAuthorizedWith("USER", "ADMIN");
        return indicatorService.getDescendants(code);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<IndicatorData> getRoots() {
        securityContext.userAuthorizedWith("USER", "ADMIN");
        return indicatorService.getRoots();
    }

    @RequestMapping(path = "/{code}", method = RequestMethod.DELETE)
    public void remove(@PathVariable(name = "code") String code) {
        securityContext.userAuthorizedWith("ADMIN");
        indicatorService.remove(code);
    }

    @RequestMapping(path = "/{code}", method = RequestMethod.PUT)
    public IndicatorData update(@PathVariable(name = "code") String code, @RequestBody IndicatorData data) {
        securityContext.userAuthorizedWith("ADMIN");
        return indicatorService.update(code, data);
    }
}
