package com.twosigma.beakerx.jvm.serialization;

import com.twosigma.beakerx.fileloader.CSV;

public class PlasmaCsvDeserializer extends PlasmaBaseDeserializer {

    @Override
    protected boolean canBeUsed(PlasmaBaseSerializer.SerialPlasmaInfo plasmaInfo) {
        return PlasmaCsvSerializer.CSV_TYPE.equals(plasmaInfo.type);
    }

    @Override
    protected Object deserialize(PlasmaBaseSerializer.SerialPlasmaInfo plasmaInfo) {
        return null; // TODO
    }
}
