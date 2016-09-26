package ru.spb.iac.storager.server.data.territories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data/territories")
public class TerritoryController {

    @Autowired
    private TerritoryService territoryService;


    @RequestMapping(method = RequestMethod.POST)
    public TerritoryInfo create(@RequestBody TerritoryInfo territoryInfo) {
        return territoryService.create(territoryInfo);
    }

    @RequestMapping(path = "/{code}", method = RequestMethod.GET)
    public TerritoryInfo getByCode(@PathVariable(name = "code") String code) {
        return territoryService.getByCode(code);
    }

    @RequestMapping(path = "/{code}/descendants", method = RequestMethod.GET)
    public List<TerritoryInfo> getDescendantsByCode(@PathVariable(name = "code") String code) {
        return territoryService.getDescendantsByCode(code);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TerritoryInfo> getRoots() {
        return territoryService.getRoots();
    }
}
