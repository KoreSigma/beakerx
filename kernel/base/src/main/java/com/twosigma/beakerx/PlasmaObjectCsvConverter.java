package com.twosigma.beakerx;

import com.twosigma.beakerx.fileloader.CSV;

public class PlasmaObjectCsvConverter implements PlasmaObjectConverter {
    private static final String PLASMA_INFO_TYPE = "csv";

    @Override
    public boolean canConvertToPlasma(Object value) {
        return value instanceof CSV;
    }

    @Override
    public PlasmaObjectInfo convertToPlasma(Object value) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public boolean canConvertFromPlasma(PlasmaObjectInfo info) {
        return PLASMA_INFO_TYPE.equals(info.getType());
    }

    @Override
    public Object convertFromPlasma(PlasmaObjectInfo info) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
