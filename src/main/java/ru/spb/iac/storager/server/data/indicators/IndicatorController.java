package ru.spb.iac.storager.server.data.indicators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data/indicators")
public class IndicatorController {

    @Autowired
    private IndicatorService indicatorService;


    @RequestMapping(method = RequestMethod.POST)
    public IndicatorInfo create(@RequestBody IndicatorInfo indicatorInfo) {
        return indicatorService.create(indicatorInfo);
    }

    @RequestMapping(path = "/{code}", method = RequestMethod.GET)
    public IndicatorInfo getByCode(@PathVariable(name = "code") String code) {
        return indicatorService.getByCode(code);
    }

    @RequestMapping(path = "/{code}/descendants", method = RequestMethod.GET)
    public List<IndicatorInfo> getDescendantsByCode(@PathVariable(name = "code") String code) {
        return indicatorService.getDescendantsByCode(code);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<IndicatorInfo> getRoots() {
        return indicatorService.getRoots();
    }
}
