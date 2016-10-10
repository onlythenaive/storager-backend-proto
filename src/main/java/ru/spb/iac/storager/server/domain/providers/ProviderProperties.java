package ru.spb.iac.storager.server.domain.providers;

public final class ProviderProperties {

    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
