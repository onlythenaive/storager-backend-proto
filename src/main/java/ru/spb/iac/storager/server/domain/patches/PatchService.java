package ru.spb.iac.storager.server.domain.patches;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.indicators.Indicator;
import ru.spb.iac.storager.server.domain.indicators.IndicatorRepository;
import ru.spb.iac.storager.server.domain.periods.Period;
import ru.spb.iac.storager.server.domain.periods.PeriodRepository;
import ru.spb.iac.storager.server.domain.points.Point;
import ru.spb.iac.storager.server.domain.providers.Provider;
import ru.spb.iac.storager.server.domain.providers.ProviderRepository;
import ru.spb.iac.storager.server.domain.shared.PagedResult;
import ru.spb.iac.storager.server.domain.territories.Territory;
import ru.spb.iac.storager.server.domain.territories.TerritoryRepository;

@Service
@Transactional
public class PatchService {

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

    public PatchInfo create(PatchInvoice invoice) {
        String comment = invoice.getComment();
        Provider provider = providerRepository.findByToken(invoice.getProviderToken());
        Patch patch = Patch.of(comment, "SUCCESS", provider);
        invoice.getPoints().forEach(p -> {
            Double real = p.getReal();
            Double plan = p.getPlan();
            String time = p.getTime();
            Indicator indicator = indicatorRepository.findByCode(p.getIndicatorCode());
            Period period = periodRepository.findByCode(p.getPeriodCode());
            Territory territory = territoryRepository.findByCode(p.getTerritoryCode());
            patch.getPoints().add(Point.of(real, plan, time, indicator, patch, period, territory));
        });
        return PatchInfo.fromPatch(patchRepository.save(patch));
    }

    public PatchInfo getById(Integer id) {
        return PatchInfo.fromPatch(patchRepository.findOne(id));
    }

    public PagedResult<PatchInfo> getPage(String providerTitle, String status, String createdSince, String createdUntil, int page, int size) {
        Page<Patch> patchPage = patchRepository.findPageWithFilter(
                defaultedProviderTitle(providerTitle),
                defaultedStatus(status),
                defaultedCreatedSince(createdSince),
                defaultedCreatedUntil(createdUntil),
                new PageRequest(page - 1, size)
        );
        List<PatchInfo> infos = patchPage
                .getContent()
                .stream()
                .map(PatchInfo::fromPatch)
                .collect(Collectors.toList());
        return PagedResult.of(infos, page, patchPage.getTotalPages());
    }

    private Instant defaultedCreatedSince(String createdSince) {
        return createdSince != null ? Instant.parse(createdSince) : Instant.ofEpochMilli(0);
    }

    private Instant defaultedCreatedUntil(String createdUntil) {
        return createdUntil != null ? Instant.parse(createdUntil) : Instant.now();
    }

    private String defaultedProviderTitle(String providerTitle) {
        return providerTitle = providerTitle != null ? providerTitle : "%";
    }

    private String defaultedStatus(String status) {
        return status != null ? status : "%";
    }
}
