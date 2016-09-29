package ru.spb.iac.storager.server.domain.providers;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProviderRepository extends PagingAndSortingRepository<Provider, Integer> {

    Provider findByTitle(String title);

    Provider findByToken(String token);
}
