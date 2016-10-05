package ru.spb.iac.storager.server.domain.indicators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemController;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemInfo;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemProperties;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemService;
import ru.spb.iac.storager.server.security.SecurityContext;
import static ru.spb.iac.storager.server.security.SecurityRoles.ADMIN;
import static ru.spb.iac.storager.server.security.SecurityRoles.USER;

@RestController
@RequestMapping("/data/indicators")
public class IndicatorController extends HierarchicItemController {

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private IndicatorService service;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public HierarchicItemInfo create(final @RequestBody HierarchicItemProperties properties) {
        securityContext.userAuthorizedWithAny(ADMIN);
        return super.create(properties);
    }

    @Override
    @RequestMapping(path = "/{code}", method = RequestMethod.GET)
    public HierarchicItemInfo getByCode(final @PathVariable(name = "code") String code) {
        securityContext.userAuthorizedWithAny(ADMIN, USER);
        return super.getByCode(code);
    }

    @Override
    @RequestMapping(path = "/{code}/descendants", method = RequestMethod.GET)
    public List<HierarchicItemInfo> getDescendants(final @PathVariable(name = "code") String code) {
        securityContext.userAuthorizedWithAny(ADMIN, USER);
        return super.getDescendants(code);
    }

    @Override
    @RequestMapping(path = "/roots", method = RequestMethod.GET)
    public List<HierarchicItemInfo> getRoots() {
        securityContext.userAuthorizedWithAny(ADMIN, USER);
        return super.getRoots();
    }

    @Override
    @RequestMapping(path = "/{code}", method = RequestMethod.DELETE)
    public void remove(final @PathVariable(name = "code") String code) {
        securityContext.userAuthorizedWithAny(ADMIN);
        super.remove(code);
    }

    @Override
    @RequestMapping(path = "/{code}", method = RequestMethod.PUT)
    public HierarchicItemInfo update(final @PathVariable(name = "code") String code,
                                     final @RequestBody HierarchicItemProperties properties) {
        securityContext.userAuthorizedWithAny(ADMIN);
        return super.update(code, properties);
    }

    @Override
    protected HierarchicItemService getService() {
        return service;
    }
}
