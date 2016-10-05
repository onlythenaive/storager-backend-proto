package ru.spb.iac.storager.server.domain.providers;

import com.google.common.collect.ImmutableSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@Profile("dev.local")
public class ProviderBootstrap {

    @Autowired
    private ProviderService providerService;

    public void run() {
        final ProviderInfo infoA = providerService.create(properties("Организация А", "Некая организация А"));
        providerService.updateGrants(infoA.getId(), ImmutableSet.of("1", "2", "3"));
        final ProviderInfo infoB = providerService.create(properties("Организация Б", "Некая организации Б"));
        providerService.updateGrants(infoB.getId(), ImmutableSet.of("4"));
    }

    private ProviderProperties properties(final String title, final String description) {
        ProviderProperties properties = new ProviderProperties();
        properties.setTitle(title);
        properties.setDescription(description);
        return properties;
    }
}
