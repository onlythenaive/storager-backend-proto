package ru.spb.iac.storager.server.domain.periods;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.spb.iac.storager.server.security.SecurityContext;
import static ru.spb.iac.storager.server.security.SecurityRoles.ADMIN;
import static ru.spb.iac.storager.server.security.SecurityRoles.USER;

@RestController
@RequestMapping("/data/periods")
public class PeriodController {

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private PeriodService periodService;

    @RequestMapping(method = RequestMethod.GET)
    Collection<PeriodInfo> getAll() {
        securityContext.userAuthorizedWithAny(ADMIN, USER);
        return periodService.getAll();
    }
}
