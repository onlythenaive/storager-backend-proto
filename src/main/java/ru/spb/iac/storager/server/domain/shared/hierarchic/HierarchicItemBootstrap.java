package ru.spb.iac.storager.server.domain.shared.hierarchic;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public abstract class HierarchicItemBootstrap<T extends HierarchicItem<T>> {

    @Transactional
    public void run() {
        getPropertiesList().forEach(getService()::bootstrap);
    }

    protected abstract List<HierarchicItemProperties> getPropertiesList();

    protected abstract HierarchicItemService<T> getService();

    protected HierarchicItemProperties properties(final String code,
                                                  final String ascendantCode,
                                                  final String title,
                                                  final String description) {
        final HierarchicItemProperties properties = new HierarchicItemProperties();
        properties.setCode(code);
        properties.setAscendantCode(ascendantCode);
        properties.setTitle(title);
        properties.setDescription(description);
        return properties;
    }
}
