package com.twosigma.beakerx;

public interface PlasmaObjectConverter {

    boolean canConvertToPlasma(Object value);

    PlasmaObjectInfo convertToPlasma(Object value);

    boolean canConvertFromPlasma(PlasmaObjectInfo info);

    Object convertFromPlasma(PlasmaObjectInfo info);
}
