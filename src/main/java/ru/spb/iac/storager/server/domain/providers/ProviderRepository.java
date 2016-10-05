package ru.spb.iac.storager.server.domain.providers;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProviderRepository extends PagingAndSortingRepository<Provider, Integer> {

    @Transactional(readOnly = true)
    Provider findByTitle(String title);

    @Transactional(readOnly = true)
    Provider findByToken(String token);
}
