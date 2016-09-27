package ru.spb.iac.storager.server.data.patches;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.data.points.PointInvoice;
import ru.spb.iac.storager.server.data.providers.ProviderRepository;

// TODO: add development-only restriction
@Component
@Transactional
public class PatchBootstrap {

    @Autowired
    private PatchService patchService;

    @Autowired
    private ProviderRepository providerRepository;

    public void run() {
        patchService.create(generateInvoice("Организация А", "Тестовая загрузка 1", new String[]{"1", "1.1"}));
        patchService.create(generateInvoice("Организация А", "Тестовая загрузка 2", new String[]{"1", "1.1"}));
        patchService.create(generateInvoice("Организация А", "Тестовая загрузка 3", new String[]{"1", "1.1"}));
        patchService.create(generateInvoice("Организация А", "Тестовая загрузка 4", new String[]{"1", "1.1"}));
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
