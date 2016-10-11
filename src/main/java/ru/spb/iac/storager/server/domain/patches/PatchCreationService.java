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
    public PatchInfo createWithFailure(final Integer providerId, final PatchProperties properties, final String reason) {
        final Patch patch = mapper.intoEntityWithFailure(providerId, validator.validateForCreateWithFailure(properties), reason);
        return mapper.intoInfo(repository.save(patch));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PatchInfo createWithSuccess(final Integer providerId, final PatchProperties properties) {
        final Patch patch = mapper.intoEntityWithSuccess(providerId, validator.validateForCreateWithSuccess(properties));
        return mapper.intoInfo(repository.save(patch));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = PatchSandboxCreationException.class)
    public void createAndRollbackWithFailure(final Integer providerId, final PatchProperties properties, final String reason) throws PatchSandboxCreationException {
        final Patch patch = mapper.intoEntityWithFailure(providerId, validator.validateForCreateWithFailure(properties), reason);
        PatchInfo info =  mapper.intoInfo(repository.save(patch));
        throw new PatchSandboxCreationException(info);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = PatchSandboxCreationException.class)
    public void createAndRollbackWithSuccess(final Integer providerId, final PatchProperties properties) throws PatchSandboxCreationException {
        final Patch patch = mapper.intoEntityWithSuccess(providerId, validator.validateForCreateWithSuccess(properties));
        PatchInfo info =  mapper.intoInfo(repository.save(patch));
        throw new PatchSandboxCreationException(info);
    }
}
