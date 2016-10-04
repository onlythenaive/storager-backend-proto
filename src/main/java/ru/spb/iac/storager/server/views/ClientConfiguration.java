package ru.spb.iac.storager.server.views;

public final class ClientConfiguration {

    private final String baseUrl;
    private final String kernelVersion;

    public ClientConfiguration(final String baseUrl, final String kernelVersion) {
        this.baseUrl = baseUrl;
        this.kernelVersion = kernelVersion;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getKernelVersion() {
        return kernelVersion;
    }
}
