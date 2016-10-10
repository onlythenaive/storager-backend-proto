package ru.spb.iac.storager.server.domain.providers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProviderMapper {

    @Transactional(readOnly = true)
    public Provider intoEntity(final ProviderProperties properties) {
        return intoEntity(properties, new Provider());
    }

    @Transactional(readOnly = true)
    public Provider intoEntity(final ProviderProperties properties, final Provider entity) {
        entity.setTitle(properties.getTitle());
        entity.setDescription(properties.getDescription());
        return entity;
    }

    @Transactional(readOnly = true)
    public ProviderInfo intoInfo(final Provider entity) {
        return new ProviderInfo(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getRegisteredAt().toString(),
                entity.getGrants()
                        .stream()
                        .map(g -> g.getIndicator().getCode())
                        .collect(Collectors.toSet())
        );
    }

    @Transactional(readOnly = true)
    public ProviderTokenInfo intoTokenInfo(final Provider entity) {
        return new ProviderTokenInfo(
                entity.getId(),
                entity.getToken()
        );
    }
}
