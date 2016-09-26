package ru.spb.iac.storager.server.data.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProviderBootstrap {

    @Autowired
    private ProviderService providerService;

    public void run() {
        providerService.create(new ProviderInfo("Организация А", "описание организации А"));
        providerService.create(new ProviderInfo("Организация Б", "описание организации Б"));
    }
}
