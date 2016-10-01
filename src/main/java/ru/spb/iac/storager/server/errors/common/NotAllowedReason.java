package ru.spb.iac.storager.server.errors.common;

import org.springframework.web.HttpRequestMethodNotSupportedException;

import ru.spb.iac.storager.server.errors.shared.Reason;

public final class NotAllowedReason implements Reason {

    private final String httpMethod;

    public NotAllowedReason(final HttpRequestMethodNotSupportedException exception) {
        this.httpMethod = exception.getMethod();
    }

    @Override
    public String getCode() {
        return "NOT_ALLOWED";
    }

    @Override
    public String getDescription() {
        return "this resource does not allow specified HTTP method";
    }

    public String getHttpMethod() {
        return httpMethod;
    }
}
