package ru.spb.iac.storager.server.errors.common;

import org.springframework.web.servlet.NoHandlerFoundException;

import ru.spb.iac.storager.server.errors.shared.Reason;

public final class ResourceUnavailableReason implements Reason {

    private final String httpMethod;
    private final String requestUrl;

    public ResourceUnavailableReason(final NoHandlerFoundException exception) {
        this.httpMethod = exception.getHttpMethod();
        this.requestUrl = exception.getRequestURL();
    }

    @Override
    public String getCode() {
        return "RESOURCE_UNAVAILABLE";
    }

    @Override
    public String getDescription() {
        return "requested resource is not available";
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getRequestUrl() {
        return requestUrl;
    }
}
