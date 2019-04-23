package com.twosigma.beakerx;

public class PlasmaObjectInfo {
    private final byte[] id;
    private final String type;

    public PlasmaObjectInfo(byte[] id, String type) {
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
