package ru.spb.iac.storager.server.domain.providers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class ProviderMapper {

    Provider intoEntity(final ProviderProperties properties) {
        return intoEntity(properties, new Provider());
    }

    Provider intoEntity(final ProviderProperties properties, final Provider entity) {
        entity.setTitle(properties.getTitle());
        entity.setDescription(properties.getDescription());
        return entity;
    }

    ProviderInfo intoInfo(final Provider entity) {
        return new ProviderInfo(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getToken(),
                entity.getRegisteredAt().toString(),
                entity.getGrants()
                        .stream()
                        .map(g -> g.getIndicator().getCode())
                        .collect(Collectors.toSet())
        );
    }
}
