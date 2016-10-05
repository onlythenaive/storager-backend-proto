package ru.spb.iac.storager.server.domain.patches;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.points.PointProperties;
import ru.spb.iac.storager.server.domain.providers.ProviderRepository;

@Component
@Transactional
@Profile("dev.local")
public class PatchBootstrap {

    @Autowired
    private PatchService patchService;

    @Autowired
    private ProviderRepository providerRepository;

    public void run() {
        patchService.create(properties("Организация А", "Тестовая загрузка 1", new String[]{"1", "1.1"}));
        patchService.create(properties("Организация А", "Тестовая загрузка 2", new String[]{"1", "1.1"}));
        patchService.create(properties("Организация А", "Тестовая загрузка 3", new String[]{"1", "1.1"}));
        patchService.create(properties("Организация А", "Тестовая загрузка 4", new String[]{"1", "1.1"}));
    }

    private PatchProperties properties(final String providerTitle,
                                       final String comment,
                                       final String[] indicatorCodes) {
        final PatchProperties properties = new PatchProperties();
        properties.setComment(comment);
        properties.setProviderToken(providerRepository.findByTitle(providerTitle).getToken());
        final List<PointProperties> points = new ArrayList<>();
        for (String indicatorCode : indicatorCodes) {
            points.add(pointProperties(indicatorCode));
        }
        properties.setPoints(points);
        return properties;
    }

    private PointProperties pointProperties(final String indicatorCode) {
        PointProperties invoice = new PointProperties();
        invoice.setIndicatorCode(indicatorCode);
        invoice.setPeriodCode("MONTH");
        invoice.setPlan(9000.0);
        invoice.setReal(42.0);
        invoice.setTerritoryCode("1");
        invoice.setTime("2015-06-30");
        return invoice;
    }
}
