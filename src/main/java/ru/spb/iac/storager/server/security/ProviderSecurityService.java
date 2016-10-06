package ru.spb.iac.storager.server.security;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.domain.providers.Provider;
import ru.spb.iac.storager.server.domain.providers.ProviderRepository;

@Component
@Transactional(readOnly = true)
public class ProviderSecurityService {

    @Autowired
    private ProviderRepository repository;

    public ProviderAuthentication authenticateByToken(final String token) {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("provider security token is missing");
        }
        final Provider provider = repository.findByToken(token);
        if (provider == null) {
            throw new RuntimeException("provider security token is invalid");
        }
        return new ProviderAuthentication(
                provider.getId(),
                provider.getGrants()
                        .stream()
                        .map(g -> g.getIndicator().getCode())
                        .collect(Collectors.toSet())
        );
    }
}
