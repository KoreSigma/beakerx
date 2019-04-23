package com.twosigma.beakerx.jvm.serialization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class PlasmaBaseDeserializer implements ObjectDeserializer {

    protected abstract boolean canBeUsed(PlasmaBaseSerializer.SerialPlasmaInfo plasmaInfo);

    protected abstract Object deserialize(PlasmaBaseSerializer.SerialPlasmaInfo plasmaInfo);

    @Override
    public boolean canBeUsed(JsonNode n) {
        return n.has(PlasmaBaseSerializer.FIELD_OBJECT_ID) &&
                n.has(PlasmaBaseSerializer.FIELD_OBJECT_TYPE) &&
                canBeUsed(getPlasmaInfo(n));
    }

    @Override
    public Object deserialize(JsonNode n, ObjectMapper mapper) {
        return deserialize(getPlasmaInfo(n));
    }

    private static PlasmaBaseSerializer.SerialPlasmaInfo getPlasmaInfo(JsonNode n) {
        try {
            byte[] objectId = n.get(PlasmaBaseSerializer.FIELD_OBJECT_ID).binaryValue();
            String type = n.get(PlasmaBaseSerializer.FIELD_OBJECT_TYPE).textValue();

            return new PlasmaBaseSerializer.SerialPlasmaInfo(objectId, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
