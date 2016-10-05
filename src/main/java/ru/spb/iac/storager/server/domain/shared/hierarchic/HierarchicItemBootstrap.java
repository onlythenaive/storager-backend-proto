package ru.spb.iac.storager.server.domain.shared.hierarchic;

public abstract class HierarchicItemBootstrap {

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
