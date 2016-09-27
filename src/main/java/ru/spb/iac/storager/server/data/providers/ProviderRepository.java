package ru.spb.iac.storager.server.data.providers;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProviderRepository extends PagingAndSortingRepository<Provider, Integer> {

}
