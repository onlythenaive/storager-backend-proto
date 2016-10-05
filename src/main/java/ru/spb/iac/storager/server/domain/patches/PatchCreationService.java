package ru.spb.iac.storager.server.domain.patches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatchCreationService {

    @Autowired
    private PatchRepository repository;

    @Autowired
    private PatchMapper mapper;

    @Autowired
    private PatchValidator validator;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PatchInfo createWithFailure(final PatchProperties properties) {
        final Patch patch = mapper.intoEntityWithFailure(validator.validateForCreateWithFailure(properties));
        return mapper.intoInfo(repository.save(patch));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PatchInfo createWithSuccess(final PatchProperties properties) {
        final Patch patch = mapper.intoEntityWithSuccess(validator.validateForCreateWithSuccess(properties));
        return mapper.intoInfo(repository.save(patch));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = PatchRollbackException.class)
    public void createAndRollbackWithFailure(final PatchProperties properties) throws PatchRollbackException {
        final Patch patch = mapper.intoEntityWithFailure(validator.validateForCreateWithFailure(properties));
        PatchInfo info =  mapper.intoInfo(repository.save(patch));
        throw new PatchRollbackException(info);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = PatchRollbackException.class)
    public void createAndRollbackWithSuccess(final PatchProperties properties) throws PatchRollbackException {
        final Patch patch = mapper.intoEntityWithSuccess(validator.validateForCreateWithSuccess(properties));
        PatchInfo info =  mapper.intoInfo(repository.save(patch));
        throw new PatchRollbackException(info);
    }
}
