package ru.spb.iac.storager.server.domain.periods;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.spb.iac.storager.server.security.SecurityContext;

@RestController
@RequestMapping("/data/periods")
public class PeriodController {

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private PeriodService periodService;

    @RequestMapping(method = RequestMethod.GET)
    Collection<PeriodInfo> getAll() {
        securityContext.userAuthorizedWith("USER", "ADMIN");
        return periodService.getAll();
    }
}
