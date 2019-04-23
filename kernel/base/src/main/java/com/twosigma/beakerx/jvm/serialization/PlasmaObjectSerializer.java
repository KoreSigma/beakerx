package com.twosigma.beakerx.jvm.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.twosigma.beakerx.jvm.object.PlasmaObject;

import java.io.IOException;
import java.util.Base64;

public class PlasmaObjectSerializer implements ObjectSerializer {

    static final String FIELD_OBJECT = "$plasma_object";
    static final String FIELD_OBJECT_ID = "id";
    static final String FIELD_OBJECT_TYPE = "type";

    @Override
    public final boolean canBeUsed(Object obj, boolean expand) {
        return obj instanceof PlasmaObject;
    }

    @Override
    public boolean writeObject(Object obj, JsonGenerator jgen, boolean expand) throws JsonProcessingException, IOException {
        PlasmaObject plasmaObj = (PlasmaObject) obj;

        jgen.writeStartObject();
        jgen.writeObjectFieldStart(FIELD_OBJECT);
        jgen.writeBinaryField(FIELD_OBJECT_ID, plasmaObj.getId());
        jgen.writeStringField(FIELD_OBJECT_TYPE, plasmaObj.getType());
        jgen.writeEndObject();
        jgen.writeEndObject();

        return true;
    }
}
