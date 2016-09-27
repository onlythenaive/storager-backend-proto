package ru.spb.iac.storager.server.data.indicators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/indicators")
public class IndicatorController {

    @Autowired
    private IndicatorService indicatorService;

    // TODO: add authorization policy (requires: ADMIN)
    @RequestMapping(method = RequestMethod.POST)
    public IndicatorInfo create(@RequestBody IndicatorInfo indicatorInfo) {
        return indicatorService.create(indicatorInfo);
    }

    // TODO: add authorization policy (requires: USER | ADMIN)
    @RequestMapping(path = "/{code}", method = RequestMethod.GET)
    public IndicatorInfo getByCode(@PathVariable(name = "code") String code) {
        return indicatorService.getByCode(code);
    }

    // TODO: add authorization policy (requires: USER | ADMIN)
    @RequestMapping(path = "/{code}/descendants", method = RequestMethod.GET)
    public List<IndicatorInfo> getDescendants(@PathVariable(name = "code") String code) {
        return indicatorService.getDescendants(code);
    }

    // TODO: add authorization policy (requires: USER | ADMIN)
    @RequestMapping(method = RequestMethod.GET)
    public List<IndicatorInfo> getRoots() {
        return indicatorService.getRoots();
    }

    // TODO: add authorization policy (requires: ADMIN)
    @RequestMapping(path = "/{code}", method = RequestMethod.DELETE)
    public void remove(@PathVariable(name = "code") String code) {
        indicatorService.remove(code);
    }

    // TODO: add authorization policy (requires: ADMIN)
    @RequestMapping(path = "/{code}", method = RequestMethod.PUT)
    public IndicatorInfo update(@PathVariable(name = "code") String code, @RequestBody IndicatorInfo info) {
        return indicatorService.update(code, info);
    }
}
