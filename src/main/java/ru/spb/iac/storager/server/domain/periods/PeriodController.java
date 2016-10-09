package ru.spb.iac.storager.server.domain.periods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/periods")
public class PeriodController {

    @Autowired
    private PeriodService service;

    @RequestMapping(method = RequestMethod.GET)
    List<PeriodInfo> getAll() {
        return service.getAllForUser();
    }
}
