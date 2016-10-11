package ru.spb.iac.storager.server.domain.patches;

public class PatchSandboxCreationException extends Exception {

    private final PatchInfo info;

    public PatchSandboxCreationException(final PatchInfo info) {
        this.info = info;
    }

    public PatchInfo getInfo() {
        return info;
    }
}
