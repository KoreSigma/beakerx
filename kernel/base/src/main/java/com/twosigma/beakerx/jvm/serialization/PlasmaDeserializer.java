package com.twosigma.beakerx.jvm.serialization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class PlasmaDeserializer implements ObjectDeserializer {

    protected abstract boolean canBeUsed(PlasmaSerializer.SerialPlasmaInfo plasmaInfo);

    protected abstract Object deserialize(PlasmaSerializer.SerialPlasmaInfo plasmaInfo);

    @Override
    public boolean canBeUsed(JsonNode n) {
        return n.has(PlasmaSerializer.FIELD_OBJECT_ID) &&
                n.has(PlasmaSerializer.FIELD_OBJECT_TYPE) &&
                canBeUsed(getPlasmaInfo(n));
    }

    @Override
    public Object deserialize(JsonNode n, ObjectMapper mapper) {
        return deserialize(getPlasmaInfo(n));
    }

    private static PlasmaSerializer.SerialPlasmaInfo getPlasmaInfo(JsonNode n) {
        try {
            byte[] objectId = n.get(PlasmaSerializer.FIELD_OBJECT_ID).binaryValue();
            String type = n.get(PlasmaSerializer.FIELD_OBJECT_TYPE).textValue();

            return new PlasmaSerializer.SerialPlasmaInfo(objectId, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
