package ru.spb.iac.storager.server.domain.patches;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.spb.iac.storager.server.domain.indicators.Indicator;
import ru.spb.iac.storager.server.domain.indicators.IndicatorRepository;
import ru.spb.iac.storager.server.domain.periods.Period;
import ru.spb.iac.storager.server.domain.periods.PeriodRepository;
import ru.spb.iac.storager.server.domain.points.Point;
import ru.spb.iac.storager.server.domain.providers.Provider;
import ru.spb.iac.storager.server.domain.providers.ProviderRepository;
import ru.spb.iac.storager.server.domain.territories.Territory;
import ru.spb.iac.storager.server.domain.territories.TerritoryRepository;
import static ru.spb.iac.storager.server.domain.patches.PatchStatuses.FAILURE;
import static ru.spb.iac.storager.server.domain.patches.PatchStatuses.SUCCESS;

@Component
public class PatchMapper {

    @Autowired
    private IndicatorRepository indicatorRepository;

    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private TerritoryRepository territoryRepository;

    Patch intoEntityWithFailure(final Integer providerId, final PatchProperties properties) {
        final Patch patch = new Patch(
                getProvider(providerId),
                properties.getComment(),
                FAILURE,
                "unknown failure"
        );
        return patch;
    }

    Patch intoEntityWithSuccess(final Integer providerId, final PatchProperties properties) {
        final Patch patch = new Patch(
                getProvider(providerId),
                properties.getComment(),
                SUCCESS,
                ""
        );
        properties.getPoints().forEach(p -> {
            final Double real = p.getReal();
            final Double plan = p.getPlan();
            final String time = p.getDate();
            final Indicator indicator = getIndicator(p.getIndicatorCode());
            final Period period = getPeriod(p.getPeriodCode());
            final Territory territory = getTerritory(p.getTerritoryCode());
            patch.getPoints().add(new Point(real, plan, time, indicator, patch, period, territory));
        });
        return patch;
    }

    PatchInfo intoInfo(final Patch entity) {
        return new PatchInfo(
                entity.getId(),
                entity.getComment(),
                entity.getProvider().getId(),
                entity.getCreatedAt().toString(),
                entity.getStatus(),
                entity.getReason(),
                getIndicatorInfos(entity)
        );
    }

    private Indicator getIndicator(final String code) {
        return indicatorRepository.findByCode(code);
    }

    private Period getPeriod(final String code) {
        return periodRepository.findByCode(code);
    }

    private Provider getProvider(final Integer id) {
        return providerRepository.findOne(id);
    }

    private Territory getTerritory(final String code) {
        return territoryRepository.findByCode(code);
    }

    private List<PatchIndicatorInfo> getIndicatorInfos(final Patch entity) {
        return entity.getPoints()
                .stream()
                .collect(Collectors.groupingBy(point -> point.getIndicator().getCode()))
                .entrySet()
                .stream()
                .map(entry -> new PatchIndicatorInfo(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());
    }
}
