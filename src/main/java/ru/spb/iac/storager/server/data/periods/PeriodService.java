package ru.spb.iac.storager.server.data.periods;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PeriodService {

    @Autowired
    private PeriodRepository periodRepository;

    // TODO: add authorization policy (requires: USER)
    List<PeriodInfo> getAll() {
        return periodRepository
                .findAll()
                .stream()
                .map(PeriodInfo::fromPeriod)
                .collect(Collectors.toList());
    }
}
