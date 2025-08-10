package com.asdru.oopack;

import com.asdru.oopack.util.VersionUtils;
import com.asdru.oopack.util.CodeGen;

public class OOPACK {

    public static void main(String[] args) {
        updateVersions();
    }

    static void updateVersions() {
        VersionUtils.getVersion()
                .ifPresentOrElse(
                        versionInfo -> {
                            try {
                                CodeGen.generateVersionsEnum(versionInfo);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        },
                        () -> { throw new IllegalStateException("Failed to get version map"); }
                );
    }

}
