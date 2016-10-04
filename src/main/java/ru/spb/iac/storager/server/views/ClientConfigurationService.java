package ru.spb.iac.storager.server.views;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class ClientConfigurationService {

    public Object getConfiguration(final HttpServletRequest request) {
        final String preconfiguredBaseUrl = getPreconfiguredBaseUrl();
        final String baseUrl = preconfiguredBaseUrl != null ? preconfiguredBaseUrl : getRequestBaseUrl(request);
        final String kernelVersion = getKernelVersion();
        return new ClientConfiguration(baseUrl, kernelVersion);
    }

    private String getKernelVersion() {
        return "1.0.0-SNAPSHOT";
    }

    private String getPreconfiguredBaseUrl() {
        return null;
    }

    private String getRequestBaseUrl(final HttpServletRequest request) {
        return request.getContextPath() + "/";
    }
}
