package ru.spb.iac.storager.server.domain.patches;

import ru.spb.iac.storager.server.errors.shared.Reason;
import ru.spb.iac.storager.server.errors.shared.ReasonableException;

public class PatchCreationException extends ReasonableException {

    public static final class PatchCreationReason implements Reason {

        public PatchCreationReason(final PatchInfo info) {

        }

        @Override
        public String getCode() {
            return "PATCH_CREATION_FAILED";
        }

        @Override
        public String getDescription() {
            return "patch creation failed";
        }
    }

    private final Reason reason;

    public PatchCreationException(final PatchInfo info) {
        this.reason = new PatchCreationReason(info);
    }

    @Override
    public Reason getReason() {
        return reason;
    }
}
