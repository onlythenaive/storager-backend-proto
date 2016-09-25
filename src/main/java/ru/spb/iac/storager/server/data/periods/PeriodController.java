package ru.spb.iac.storager.server.data.periods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/data/periods")
public class PeriodController {

    @Autowired
    private PeriodService periodService;

    @RequestMapping(method = RequestMethod.GET)
    Collection<PeriodInfo> getAll() {
        return periodService.getAll();
    }
}
