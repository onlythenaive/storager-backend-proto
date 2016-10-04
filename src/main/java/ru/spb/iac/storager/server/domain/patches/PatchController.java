package ru.spb.iac.storager.server.domain.patches;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.spb.iac.storager.server.domain.points.PointInvoice;
import ru.spb.iac.storager.server.domain.providers.ProviderRepository;
import ru.spb.iac.storager.server.security.SecurityContext;
import ru.spb.iac.storager.server.domain.shared.PagedResult;

@RestController
@RequestMapping("/data/patches")
public class PatchController {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private PatchService patchService;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public PatchInfo getById(@PathVariable(name = "id") Integer id) {
        securityContext.userAuthorizedWithAny("USER", "ADMIN");
        return patchService.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public PagedResult<PatchInfo> getPage(@RequestParam(name = "providerTitle", required = false) String providerTitle,
                                          @RequestParam(name = "status", required = false) String status,
                                          @RequestParam(name = "createdSince", required = false) String createdSince,
                                          @RequestParam(name = "createdUntil", required = false) String createdUntil,
                                          @RequestParam(name = "page", defaultValue = "1") int page,
                                          @RequestParam(name = "size", defaultValue = "10") int size) {
        securityContext.userAuthorizedWithAny("USER", "ADMIN");
        return patchService.getPage(providerTitle, status, createdSince, createdUntil, page, size);
    }

    @RequestMapping(method = RequestMethod.POST)
    public PatchInfo createAndRollback() {
        return patchService.createAndRollback(generateInvoice("Организация А", "Тестовая загрузка 1", new String[]{"1", "1.1"}));
    }

    private PatchInvoice generateInvoice(String providerTitle, String comment, String[] indicatorCodes) {
        PatchInvoice invoice = new PatchInvoice();
        invoice.setComment(comment);
        invoice.setProviderToken(providerRepository.findByTitle(providerTitle).getToken());
        List<PointInvoice> points = new ArrayList<>();
        for (String indicatorCode : indicatorCodes) {
            points.add(generatePointInvoice(indicatorCode));
        }
        invoice.setPoints(points);
        return invoice;
    }

    private PointInvoice generatePointInvoice(String indicatorCode) {
        PointInvoice invoice = new PointInvoice();
        invoice.setIndicatorCode(indicatorCode);
        invoice.setPeriodCode("MONTH");
        invoice.setPlan(9000.0);
        invoice.setReal(42.0);
        invoice.setTerritoryCode("1");
        invoice.setTime("2015-06-30");
        return invoice;
    }
}
