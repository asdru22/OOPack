package com.asdru.oopack.util;

import com.asdru.oopack.internal.VersionInfo;
import com.squareup.javapoet.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import javax.lang.model.element.Modifier;

public class CodeGen {

    private static final String
            packageName = "com.asdru.oopack",
            fileName = "Version";

    public static void generateVersionsEnum(Map<String, VersionInfo> versionInfoMap) throws Exception {
        // Create enum builder
        TypeSpec.Builder enumBuilder = TypeSpec.enumBuilder(fileName)
                .addModifiers(Modifier.PUBLIC)
                .addField(String.class, "id", Modifier.PRIVATE, Modifier.FINAL)
                .addField(int.class, "datapackVersion", Modifier.PRIVATE, Modifier.FINAL)
                .addField(int.class, "datapackVersionMinor", Modifier.PRIVATE, Modifier.FINAL)
                .addField(int.class, "resourcepackVersion", Modifier.PRIVATE, Modifier.FINAL)
                .addField(int.class, "resourcepackVersionMinor", Modifier.PRIVATE, Modifier.FINAL)
                .addField(int.class, "dataVersion", Modifier.PRIVATE, Modifier.FINAL)
                .addMethod(MethodSpec.constructorBuilder()
                        .addParameter(String.class, "id")
                        .addParameter(int.class, "datapackVersion")
                        .addParameter(int.class, "datapackVersionMinor")
                        .addParameter(int.class, "resourcepackVersion")
                        .addParameter(int.class, "resourcepackVersionMinor")
                        .addParameter(int.class, "dataVersion")
                        .addStatement("this.id = id")
                        .addStatement("this.datapackVersion = datapackVersion")
                        .addStatement("this.datapackVersionMinor = datapackVersionMinor")
                        .addStatement("this.resourcepackVersion = resourcepackVersion")
                        .addStatement("this.resourcepackVersionMinor = resourcepackVersionMinor")
                        .addStatement("this.dataVersion = dataVersion")
                        .build()
                );

        // Add getters
        enumBuilder.addMethod(MethodSpec.methodBuilder("getId")
                .addModifiers(Modifier.PUBLIC)
                .returns(String.class)
                .addStatement("return this.id")
                .build());

        enumBuilder.addMethod(MethodSpec.methodBuilder("getDatapackVersion")
                .addModifiers(Modifier.PUBLIC)
                .returns(int.class)
                .addStatement("return this.datapackVersion")
                .build());

        enumBuilder.addMethod(MethodSpec.methodBuilder("getDatapackVersionMinor")
                .addModifiers(Modifier.PUBLIC)
                .returns(int.class)
                .addStatement("return this.datapackVersionMinor")
                .build());

        enumBuilder.addMethod(MethodSpec.methodBuilder("getResourcepackVersion")
                .addModifiers(Modifier.PUBLIC)
                .returns(int.class)
                .addStatement("return this.resourcepackVersion")
                .build());

        enumBuilder.addMethod(MethodSpec.methodBuilder("getResourcepackVersionMinor")
                .addModifiers(Modifier.PUBLIC)
                .returns(int.class)
                .addStatement("return this.resourcepackVersionMinor")
                .build());

        // Get latest version
        Map.Entry<String, VersionInfo> firstEntry = versionInfoMap.entrySet().iterator().next();
        // Add the "LATEST" constant first
        enumBuilder.addEnumConstant("LATEST",
                TypeSpec.anonymousClassBuilder("$S, $L, $L, $L, $L, $L",
                        firstEntry.getKey(),
                        firstEntry.getValue().datapackVersion(),
                        firstEntry.getValue().datapackVersionMinor(),
                        firstEntry.getValue().resourcepackVersion(),
                        firstEntry.getValue().resourcepackVersionMinor(),
                        firstEntry.getValue().dataVersion()
                ).build()
        );

        // iterate over the rest of the map and add constants
        versionInfoMap.forEach((id, info) -> {
            String enumName = "V_" + id.replace("-", "_").replace(".", "_");
            enumBuilder.addEnumConstant(enumName,
                    TypeSpec.anonymousClassBuilder("$S, $L, $L, $L, $L, $L",
                            id,
                            info.datapackVersion(),
                            info.datapackVersionMinor(),
                            info.resourcepackVersion(),
                            info.resourcepackVersionMinor(),
                            info.dataVersion()
                    ).build()
            );
        });

        // Build the enum
        TypeSpec enumType = enumBuilder.build();

        // Write to target/generated-sources/com/asdru/oopack/Version.java
        JavaFile javaFile = JavaFile.builder(packageName, enumType)
                .build();
        Path path = Paths.get("target/generated-sources");
        javaFile.writeTo(path);
    }

}
