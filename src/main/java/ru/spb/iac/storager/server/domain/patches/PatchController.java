package ru.spb.iac.storager.server.domain.patches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.spb.iac.storager.server.domain.shared.PagedResult;

@RestController
@RequestMapping("/data/patches")
public class PatchController {

    @Autowired
    private PatchService service;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public PatchInfo getById(final @PathVariable(name = "id") Integer id) {
        return service.getByIdOnUserBehalf(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public PagedResult<PatchInfo> getPage(final @RequestParam(name = "providerTitlePattern", required = false) String providerTitlePattern,
                                          final @RequestParam(name = "status", required = false) String status,
                                          final @RequestParam(name = "createdSince", required = false) String createdSince,
                                          final @RequestParam(name = "createdUntil", required = false) String createdUntil,
                                          final @RequestParam(name = "page", defaultValue = "1") int page,
                                          final @RequestParam(name = "size", defaultValue = "10") int size) {
        return service.getPageOnUserBehalf(providerTitlePattern, status, createdSince, createdUntil, page, size);
    }
}
