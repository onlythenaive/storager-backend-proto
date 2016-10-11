package ru.spb.iac.storager.server.domain.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableSet;

@Component
@Profile("bootstrap")
public class ProviderBootstrap {

    @Autowired
    private ProviderService service;

    @Transactional
    public void run() {
        final ProviderInfo infoA = service.bootstrap(properties("Организация А", "Некая организация А"));
        service.bootstrapGrants(infoA.getId(), ImmutableSet.of("1", "2", "3"));
        final ProviderInfo infoB = service.bootstrap(properties("Организация Б", "Некая организации Б"));
        service.bootstrapGrants(infoB.getId(), ImmutableSet.of("4"));
    }

    private ProviderProperties properties(final String title, final String description) {
        ProviderProperties properties = new ProviderProperties();
        properties.setTitle(title);
        properties.setDescription(description);
        return properties;
    }
}
