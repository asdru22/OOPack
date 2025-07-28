package com.asdru.oopack;

abstract class Pack<T> extends Resource<T> {

    protected int version;

    public Pack(String name) {
        super(name);
    }
}
