package ru.spb.iac.storager.server.data.patches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.spb.iac.storager.server.data.shared.PagedResult;

@RestController
@RequestMapping("/data/patches")
public class PatchController {

    @Autowired
    private PatchService patchService;

    // TODO: add authorization policy (requires: ADMIN)
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public PatchInfo getById(@PathVariable(name = "id") Integer id) {
        return patchService.getById(id);
    }

    // TODO: add authorization policy (requires: ADMIN)
    @RequestMapping(method = RequestMethod.GET)
    public PagedResult<PatchInfo> getPage(@RequestParam(name = "providerTitle", required = false) String providerTitle,
                                          @RequestParam(name = "status", required = false) String status,
                                          @RequestParam(name = "createdSince", required = false) String createdSince,
                                          @RequestParam(name = "createdUntil", required = false) String createdUntil,
                                          @RequestParam(name = "page") int page,
                                          @RequestParam(name = "size") int size) {
        return patchService.getPage(providerTitle, status, createdSince, createdUntil, page, size);
    }
}
