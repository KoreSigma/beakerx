package com.twosigma.beakerx.jvm.serialization;

import com.twosigma.beakerx.fileloader.CSV;

public class PlasmaCsvSerializer extends PlasmaBaseSerializer {

    static final String CSV_TYPE = "csv";

    @Override
    protected boolean canBeUsed(Object obj) {
        return obj instanceof CSV;
    }

    @Override
    protected SerialPlasmaInfo storeObject(Object obj) {
        return new SerialPlasmaInfo(new byte[20], CSV_TYPE); // TODO
    }
}
