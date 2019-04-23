package com.twosigma.beakerx.jvm.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.twosigma.beakerx.PlasmaObjectInfo;

import java.io.IOException;

public class PlasmaObjectInfoSerializer implements ObjectSerializer {

    static final String FIELD_OBJECT = "plasma_object_info";
    static final String FIELD_OBJECT_ID = "id";
    static final String FIELD_OBJECT_TYPE = "type";

    @Override
    public final boolean canBeUsed(Object obj, boolean expand) {
        return obj instanceof PlasmaObjectInfo;
    }

    @Override
    public boolean writeObject(Object obj, JsonGenerator jgen, boolean expand) throws JsonProcessingException, IOException {
        PlasmaObjectInfo plasmaObj = (PlasmaObjectInfo) obj;

        jgen.writeStartObject();
        jgen.writeObjectFieldStart(FIELD_OBJECT);
        jgen.writeBinaryField(FIELD_OBJECT_ID, plasmaObj.getId());
        jgen.writeStringField(FIELD_OBJECT_TYPE, plasmaObj.getType());
        jgen.writeEndObject();
        jgen.writeEndObject();

        return true;
    }
}
