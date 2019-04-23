package com.twosigma.beakerx.jvm.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.Base64;

public abstract class PlasmaSerializer implements ObjectSerializer {

    public static final String FIELD_OBJECT_ID = "$plasma_object_id";
    public static final String FIELD_OBJECT_TYPE = "$plasma_object_type";

    protected abstract boolean canBeUsed(Object obj);

    protected abstract SerialPlasmaInfo storeObject(Object obj);

    @Override
    public final boolean canBeUsed(Object obj, boolean expand) {
        return canBeUsed(obj);
    }

    @Override
    public boolean writeObject(Object obj, JsonGenerator jgen, boolean expand) throws JsonProcessingException, IOException {
        SerialPlasmaInfo plasmaInfo = storeObject(obj);

        jgen.writeStartObject();
        jgen.writeBinaryField(FIELD_OBJECT_ID, plasmaInfo.objectId);
        jgen.writeStringField(FIELD_OBJECT_TYPE, plasmaInfo.type);
        jgen.writeEndObject();

        return true;
    }

    public static class SerialPlasmaInfo {
        final byte[] objectId;
        final String type;

        public SerialPlasmaInfo(byte[] objectId, String type) {
            this.objectId = objectId;
            this.type = type;
        }
    }
}
