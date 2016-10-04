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
    private final Set<String> exclusionPrefixes;

    public RequestForwardFilter(final String targetUri, final Set<String> exclusionPrefixes) {
        this.targetUri = targetUri;
        this.exclusionPrefixes = new HashSet<>(exclusionPrefixes);
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {

        final String requestUri = request.getRequestURI();

        final boolean shouldForward = exclusionPrefixes
                .stream()
                .map(requestUri::startsWith)
                .filter(Boolean::new)
                .collect(Collectors.toList())
                .isEmpty();

        if (shouldForward) {
            request.getRequestDispatcher(targetUri).forward(request, response);
        }
        filterChain.doFilter(request, response);
    }
}
