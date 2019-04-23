package com.twosigma.beakerx;

import org.apache.arrow.plasma.PlasmaClient;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class PlasmaObjectStringConverter implements PlasmaObjectConverter {
    private static final String PLASMA_INFO_TYPE = "string";
    private static final Random random = new Random();

    private final PlasmaClient plasmaClient;

    public PlasmaObjectStringConverter(PlasmaClient plasmaClient) {
        this.plasmaClient = plasmaClient;
    }

    @Override
    public boolean canConvertToPlasma(Object value) {
        return value instanceof String;
    }

    @Override
    public PlasmaObjectInfo convertToPlasma(Object value) {
        byte[] objectId = new byte[20];
        random.nextBytes(objectId);

        byte[] objectValue = ((String) value).getBytes(StandardCharsets.UTF_8);

        plasmaClient.put(objectId, objectValue, null);

        return new PlasmaObjectInfo(objectId, PLASMA_INFO_TYPE);
    }

    @Override
    public boolean canConvertFromPlasma(PlasmaObjectInfo info) {
        return PLASMA_INFO_TYPE.equals(info.getType());
    }

    @Override
    public Object convertFromPlasma(PlasmaObjectInfo info) {
        byte[] stringBytes = plasmaClient.get(info.getId(), 500, false);

        return new String(stringBytes, StandardCharsets.UTF_8);
    }
}
