package ru.spb.iac.storager.server.data.providers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProviderRepository extends JpaRepository<Provider, Integer> {

}
