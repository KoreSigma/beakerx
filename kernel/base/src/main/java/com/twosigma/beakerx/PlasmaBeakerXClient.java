/*
 *  Copyright 2019 TWO SIGMA OPEN SOURCE, LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.twosigma.beakerx;

import java.util.List;
import java.util.concurrent.SynchronousQueue;

public class PlasmaBeakerXClient implements BeakerXClient {
    private final BeakerXClient delegate;

    private final PlasmaObjectConverter[] plasmaObjectConverters;

    public PlasmaBeakerXClient(BeakerXClient delegate) {
        this.delegate = delegate;

        plasmaObjectConverters = new PlasmaObjectConverter[]{
                new PlasmaObjectCsvConverter()
        };
    }

    private Object convertToPlasma(Object value) {
        for (PlasmaObjectConverter converter : plasmaObjectConverters) {
            if (converter.canConvertToPlasma(value)) {
                return converter.convertToPlasma(value);
            }
        }

        return value;
    }

    private Object convertFromPlasma(Object value) {
        if (!(value instanceof PlasmaObjectInfo)) {
            return value;
        }

        PlasmaObjectInfo info = (PlasmaObjectInfo) value;

        for (PlasmaObjectConverter converter : plasmaObjectConverters) {
            if (converter.canConvertFromPlasma(info)) {
                return converter.convertFromPlasma(info);
            }
        }

        throw new RuntimeException("Unable to convert PlasmaObjectInfo with type " + info.getType());
    }

    @Override
    public String update(String name, Object value) {
        return delegate.update(name, convertToPlasma(value));
    }

    @Override
    public Object set(String name, Object value) {
        return delegate.update(name, convertToPlasma(value));
    }

    @Override
    public Object get(String name) {
        return convertFromPlasma(delegate.get(name));
    }

    @Override
    public void delBeaker() {
        delegate.delBeaker();
    }

    @Override
    public void showProgressUpdate(String message, int progress) {
        delegate.showProgressUpdate(message, progress);
    }

    @Override
    public SynchronousQueue<Object> getMessageQueue(String channel) {
        return delegate.getMessageQueue(channel);
    }

    @Override
    public List<CodeCell> getCodeCells(String tagFilter) {
        return delegate.getCodeCells(tagFilter);
    }

    @Override
    public void runByTag(String tag) {
        delegate.runByTag(tag);
    }

    @Override
    public String getContext() {
        return delegate.getContext();
    }

    @Override
    public String urlArg(String argName) {
        return delegate.urlArg(argName);
    }
}
