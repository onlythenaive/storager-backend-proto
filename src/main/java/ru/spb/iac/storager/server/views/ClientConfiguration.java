package ru.spb.iac.storager.server.views;

public final class ClientConfiguration {

    private final String baseUrl;
    private final String dataBaseUrl;
    private final String kernelVersion;
    private final String title;

    public ClientConfiguration(final String baseUrl,
                               final String dataBaseUrl,
                               final String kernelVersion,
                               final String title) {
        this.baseUrl = baseUrl;
        this.dataBaseUrl = dataBaseUrl;
        this.kernelVersion = kernelVersion;
        this.title = title;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getDataBaseUrl() {
        return dataBaseUrl;
    }

    public String getKernelVersion() {
        return kernelVersion;
    }

    public String getTitle() {
        return title;
    }
}
