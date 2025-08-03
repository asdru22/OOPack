package com.asdru.oopack.internal;

public enum Resource {
    DATA{
        @Override
        public String toString() {
            return "data";
        }
    },
    ASSETS{
        @Override
        public String toString() {
            return "assets";
        }
    }
}
