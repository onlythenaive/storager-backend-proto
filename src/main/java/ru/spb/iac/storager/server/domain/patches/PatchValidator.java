package ru.spb.iac.storager.server.domain.patches;

import org.springframework.stereotype.Component;

@Component
public class PatchValidator {

    public PatchProperties validateForCreateWithFailure(final PatchProperties properties)  {
        return properties;
    }

    public PatchProperties validateForCreateWithSuccess(final PatchProperties properties)  {
        return properties;
    }
}
