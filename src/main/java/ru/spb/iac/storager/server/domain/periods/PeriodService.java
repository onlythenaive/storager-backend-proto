package ru.spb.iac.storager.server.domain.periods;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PeriodService {

    @Autowired
    private PeriodMapper mapper;

    @Autowired
    private PeriodRepository repository;

    public List<PeriodInfo> getAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::intoInfo)
                .collect(Collectors.toList());
    }
}
