package ru.spb.iac.storager.server.views;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class IndexPageController {

    @Autowired
    private ClientConfigurationService clientConfigurationService;

    @Autowired
    private TemplateService templateService;

    @RequestMapping(method = RequestMethod.GET)
    public String get(final HttpServletRequest request) {
        return templateService.render("index", clientConfigurationService.getConfiguration(request));
    }
}
