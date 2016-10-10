package ru.spb.iac.storager.server.domain.periods;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.security.SecurityContext;
import static ru.spb.iac.storager.server.security.SecurityRoles.ADMIN;
import static ru.spb.iac.storager.server.security.SecurityRoles.USER;

@Service
public class PeriodService {

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private PeriodMapper mapper;

    @Autowired
    private PeriodRepository repository;

    @Transactional
    public PeriodInfo bootstrap(final PeriodProperties properties) {
        return mapper.intoInfo(repository.save(mapper.intoEntity(properties)));
    }

    @Transactional(readOnly = true)
    public List<PeriodInfo> getAllOnProviderBehalf() {
        securityContext.providerAuthenticated();
        return getAll();
    }

    @Transactional(readOnly = true)
    public List<PeriodInfo> getAllOnUserBehalf() {
        securityContext.userAuthorizedWithAny(ADMIN, USER);
        return getAll();
    }

    private List<PeriodInfo> getAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::intoInfo)
                .collect(Collectors.toList());
    }
}
