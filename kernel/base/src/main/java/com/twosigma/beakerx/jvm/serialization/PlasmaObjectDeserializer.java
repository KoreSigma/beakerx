package com.twosigma.beakerx.jvm.serialization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twosigma.beakerx.jvm.object.PlasmaObject;

public class PlasmaObjectDeserializer implements ObjectDeserializer {

    @Override
    public boolean canBeUsed(JsonNode n) {
        return n.has(PlasmaObjectSerializer.FIELD_OBJECT);
    }

    @Override
    public Object deserialize(JsonNode n, ObjectMapper mapper) {
        try {
            JsonNode plasmaObject = n.get(PlasmaObjectSerializer.FIELD_OBJECT);
            byte[] id = plasmaObject.get(PlasmaObjectSerializer.FIELD_OBJECT_ID).binaryValue();
            String type = plasmaObject.get(PlasmaObjectSerializer.FIELD_OBJECT_TYPE).textValue();

            return new PlasmaObject(id, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
