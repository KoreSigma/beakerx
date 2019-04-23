package com.twosigma.beakerx.jvm.serialization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twosigma.beakerx.PlasmaObjectInfo;

public class PlasmaObjectInfoDeserializer implements ObjectDeserializer {

    @Override
    public boolean canBeUsed(JsonNode n) {
        return n.has(PlasmaObjectInfoSerializer.FIELD_OBJECT);
    }

    @Override
    public Object deserialize(JsonNode n, ObjectMapper mapper) {
        try {
            JsonNode plasmaObject = n.get(PlasmaObjectInfoSerializer.FIELD_OBJECT);
            byte[] id = plasmaObject.get(PlasmaObjectInfoSerializer.FIELD_OBJECT_ID).binaryValue();
            String type = plasmaObject.get(PlasmaObjectInfoSerializer.FIELD_OBJECT_TYPE).textValue();

            return new PlasmaObjectInfo(id, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
