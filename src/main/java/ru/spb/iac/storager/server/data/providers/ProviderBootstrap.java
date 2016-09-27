package ru.spb.iac.storager.server.data.providers;

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
        providerService.create(createInfo("Организация А", "описание организации А"));
        providerService.create(createInfo("Организация Б", "описание организации Б"));
    }

    private ProviderInfo createInfo(String title, String description) {
        ProviderInfo info = new ProviderInfo();
        info.setTitle(title);
        info.setDescription(description);
        return info;
    }
}
