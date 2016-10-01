package ru.spb.iac.storager.server.domain.periods;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PeriodService {

    @Autowired
    private PeriodMapper periodMapper;

    @Autowired
    private PeriodRepository periodRepository;

    public List<PeriodInfo> getAll() {
        return periodRepository
                .findAll()
                .stream()
                .map(periodMapper::intoInfo)
                .collect(Collectors.toList());
    }
}
