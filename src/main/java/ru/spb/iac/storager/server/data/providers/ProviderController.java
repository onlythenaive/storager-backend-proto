package ru.spb.iac.storager.server.data.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ProviderInfo> getAll() {
        return providerService.getAll();
    }
}
