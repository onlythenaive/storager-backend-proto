package ru.spb.iac.storager.server.domain.patches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.indicators.Indicator;
import ru.spb.iac.storager.server.domain.indicators.IndicatorRepository;
import ru.spb.iac.storager.server.domain.periods.Period;
import ru.spb.iac.storager.server.domain.periods.PeriodRepository;
import ru.spb.iac.storager.server.domain.points.Point;
import ru.spb.iac.storager.server.domain.providers.Provider;
import ru.spb.iac.storager.server.domain.providers.ProviderRepository;
import ru.spb.iac.storager.server.domain.territories.Territory;
import ru.spb.iac.storager.server.domain.territories.TerritoryRepository;

@Service
@Transactional
public class PatchCreationService {

    @Autowired
    private IndicatorRepository indicatorRepository;

    @Autowired
    private PatchRepository patchRepository;

    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private TerritoryRepository territoryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PatchInfo create(PatchInvoice invoice) {
        String comment = invoice.getComment();
        Provider provider = providerRepository.findByToken(invoice.getProviderToken());
        Patch patch = new Patch(comment, "SUCCESS", provider);
        invoice.getPoints().forEach(p -> {
            Double real = p.getReal();
            Double plan = p.getPlan();
            String time = p.getTime();
            Indicator indicator = indicatorRepository.findByCode(p.getIndicatorCode());
            Period period = periodRepository.findByCode(p.getPeriodCode());
            Territory territory = territoryRepository.findByCode(p.getTerritoryCode());
            patch.getPoints().add(new Point(real, plan, time, indicator, patch, period, territory));
        });
        return PatchInfo.fromPatch(patchRepository.save(patch));
    }
}
