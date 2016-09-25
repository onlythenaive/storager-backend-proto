package ru.spb.iac.storager.server.data.periods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PeriodService {

    @Autowired
    private PeriodRepository periodRepository;

    List<PeriodInfo> getAll() {
        return periodRepository
                .findAll()
                .stream()
                .map(PeriodInfo::fromPeriod)
                .collect(Collectors.toList());
    }
}
