package com.asdru.oopack.internal;

public record VersionInfo(
        int datapackVersion,
        int datapackVersionMinor,
        int resourcepackVersion,
        int resourcepackVersionMinor,
        int dataVersion) {
}