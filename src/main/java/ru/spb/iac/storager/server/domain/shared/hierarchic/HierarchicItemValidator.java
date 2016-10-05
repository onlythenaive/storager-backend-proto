package ru.spb.iac.storager.server.domain.shared.hierarchic;

public interface HierarchicItemValidator {

    HierarchicItemProperties validateForCreate(final HierarchicItemProperties properties);

    HierarchicItemProperties validateForUpdate(final String code, final HierarchicItemProperties properties);
}
