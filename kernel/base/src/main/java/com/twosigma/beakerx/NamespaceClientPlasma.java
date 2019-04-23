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

import com.twosigma.beakerx.fileloader.CSV;

import java.util.List;
import java.util.concurrent.SynchronousQueue;

public class NamespaceClientPlasma implements BeakerXClient {
    private final BeakerXClient delegate;

    public NamespaceClientPlasma(BeakerXClient delegate) {
        this.delegate = delegate;
    }

    @Override
    public String update(String name, Object value) {
        PlasmaData mapped = toPlasma(value);

        if (mapped == null) {
            return delegate.update(name, value);
        } else {
            return delegate.update(name, mapped);
        }
    }

    @Override
    public Object set(String name, Object value) {
        PlasmaData mapped = toPlasma(value);

        if (mapped == null) {
            return delegate.set(name, value);
        } else {
            return delegate.set(name, mapped);
        }
    }

    @Override
    public Object get(String name) {
        Object value = delegate.get(name);

        if (value instanceof PlasmaData) {
            return ((PlasmaData) value).readValue();
        } else {
            return value;
        }
    }

    private interface PlasmaData<T> {
        T readValue();
    }


    // ============================== Mappers ============================== //

    private static PlasmaData<Object> toPlasma(Object value) {
        return null;
    }

    // For testing purposes
    private static PlasmaData<String> toPlasma(String string) {
        return () -> string;
    }

    private static PlasmaData<CSV> toPlasma(CSV csv) {
        return null;
    }


    // ============================== Delegation ============================== //

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
