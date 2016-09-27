package ru.spb.iac.storager.server.data.providers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// TODO: add development-only restriction
@Component
@Transactional
public class ProviderBootstrap {

    @Autowired
    private ProviderService providerService;

    public void run() {
        providerService.create(ProviderInfo.of("Организация А", "описание организации А", UUID.randomUUID().toString()));
        providerService.create(ProviderInfo.of("Организация Б", "описание организации Б", UUID.randomUUID().toString()));
    }
}
