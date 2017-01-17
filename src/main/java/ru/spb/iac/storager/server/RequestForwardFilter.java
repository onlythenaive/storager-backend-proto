package ru.spb.iac.storager.server;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public final class RequestForwardFilter extends OncePerRequestFilter {

    private final String targetUri;
    private final Set<String> exclusions;
    private final Set<String> exclusionPrefixes;

    public RequestForwardFilter(final String targetUri,
                                final Set<String> exclusions,
                                final Set<String> exclusionPrefixes) {
        this.targetUri = targetUri;
        this.exclusions = exclusions != null ? new HashSet<>(exclusions) : null;
        this.exclusionPrefixes = exclusionPrefixes != null ? new HashSet<>(exclusionPrefixes) : null;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {

        final String rawRequestUri = request.getRequestURI();
        final String baseUrl = request.getContextPath();

        final String requestUri;
        if (baseUrl != null && baseUrl.length() > 0) {
            requestUri = rawRequestUri.substring(baseUrl.length());
        } else {
            requestUri = rawRequestUri;
        }

        final boolean notExcludedExplicitly = exclusions == null || exclusions
                .stream()
                .map(requestUri::equals)
                .filter(Boolean::new)
                .collect(Collectors.toList())
                .isEmpty();

        final boolean notExcludedByPrefix = exclusionPrefixes == null || exclusionPrefixes
                .stream()
                .map(requestUri::startsWith)
                .filter(Boolean::new)
                .collect(Collectors.toList())
                .isEmpty();

        final boolean shouldForward = notExcludedExplicitly && notExcludedByPrefix;

        if (shouldForward) {
            request.getRequestDispatcher(targetUri).forward(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
