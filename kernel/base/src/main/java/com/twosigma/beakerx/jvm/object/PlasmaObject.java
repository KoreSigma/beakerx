package com.twosigma.beakerx.jvm.object;

public class PlasmaObject {
    private final byte[] id;
    private final String type;

    public PlasmaObject(byte[] id, String type) {
        assert id != null;
        assert type != null;

        this.id = id;
        this.type = type;
    }

    public byte[] getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
