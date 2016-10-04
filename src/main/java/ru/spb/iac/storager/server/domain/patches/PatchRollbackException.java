package ru.spb.iac.storager.server.domain.patches;

public class PatchRollbackException extends Exception {

    private final PatchInfo info;

    public PatchRollbackException(final PatchInfo info) {
        this.info = info;
    }

    public PatchInfo getInfo() {
        return info;
    }
}
