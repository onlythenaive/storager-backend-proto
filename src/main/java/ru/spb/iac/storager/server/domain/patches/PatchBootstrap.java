package ru.spb.iac.storager.server.domain.patches;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.points.PointProperties;

@Component
@Profile("bootstrap")
public class PatchBootstrap {

    @Autowired
    private PatchService service;

    @Transactional
    public void run() {
        service.bootstrap("Организация А", properties("Тестовая загрузка 1", new String[]{"1", "1.1"}));
        service.bootstrap("Организация А", properties("Тестовая загрузка 2", new String[]{"1", "1.1"}));
        service.bootstrap("Организация А", properties("Тестовая загрузка 3", new String[]{"1", "1.1"}));
        service.bootstrap("Организация А", properties("Тестовая загрузка 4", new String[]{"1", "1.1"}));
    }

    private PatchProperties properties(final String comment, final String[] indicatorCodes) {
        final PatchProperties properties = new PatchProperties();
        properties.setComment(comment);
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
        invoice.setDate("2015-06-30");
        return invoice;
    }
}
