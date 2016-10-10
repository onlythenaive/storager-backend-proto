package ru.spb.iac.storager.server.domain.indicators;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemInfo;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemMapper;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemRepository;

@Service
public class IndicatorMapper extends HierarchicItemMapper<Indicator> {

    @Autowired
    private IndicatorRepository repository;

    public HierarchicItemInfo intoInfoWithGrantsApplied(final Indicator entity, final Set<String> grants) {
        final String code = entity.getCode();
        final String ascendantCode = getAscendantCodeWithGrantsApplied(entity, grants);
        final String title = entity.getTitle();
        final String description = entity.getDescription();
        final List<String> path = getPathWithGrantsApplied(entity, grants);
        final Boolean terminal = isTerminal(entity);
        return new HierarchicItemInfo(code, ascendantCode, title, description, path, terminal);
    }

    @Override
    protected HierarchicItemRepository<Indicator> getRepository() {
        return repository;
    }

    @Override
    protected Indicator createItemInstance() {
        return new Indicator();
    }

    private String getAscendantCodeWithGrantsApplied(final Indicator entity, final Set<String> grants) {
        final Indicator ascendant = entity.getAscendant();
        return ascendant != null && grants.contains(ascendant.getCode()) ? entity.getAscendant().getCode() : null;
    }

    private List<String> getPathWithGrantsApplied(final Indicator entity, final Set<String> grants) {
        return addToPath(getAscendantWithGrantsApplied(entity, grants), new ArrayList<>(), grants);
    }

    private List<String> addToPath(final Indicator entity, final List<String> path, final Set<String> grants) {
        if (entity != null) {
            addToPath(getAscendantWithGrantsApplied(entity, grants), path, grants);
            path.add(entity.getCode());
        }
        return path;
    }

    private Indicator getAscendantWithGrantsApplied(final Indicator entity, final Set<String> grants) {
        final Indicator ascendant = entity.getAscendant();
        return ascendant != null && grants.contains(ascendant.getCode()) ? entity.getAscendant() : null;
    }
}
