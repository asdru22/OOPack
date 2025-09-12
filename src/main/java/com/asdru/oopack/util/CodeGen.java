package com.asdru.oopack.util;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CodeGen {

    private static final String packageName = "com.asdru.oopack.objects";
    private static final Path outputDir = Path.of("./src/main/java");

    public static void gen(
            String className,
            String superClassName,
            String folderName
    ) throws IOException {

        // derive subpackage: remove "Json" suffix, lowercase remainder
        String subPackage = superClassName.replace("Json", "").toLowerCase();

        // check if className has subpackage path
        String actualClassName = className;
        String extraSubPackage = "";
        if (className.contains("/")) {
            int idx = className.lastIndexOf('/');
            extraSubPackage = "." + className.substring(0, idx).replace('/', '.').toLowerCase();
            actualClassName = className.substring(idx + 1);
        }

        String fullPackage = packageName + "." + subPackage + extraSubPackage;

        // imports
        ClassName jsonObject = ClassName.get("com.google.gson", "JsonObject");
        ClassName jsonFileFactory = ClassName.get("com.asdru.oopack.internal", "JsonFileFactory");
        ClassName jsonFile = ClassName.get("com.asdru.oopack.objects", "JsonFile");
        ClassName superClass = ClassName.get(packageName, superClassName);

        // static factory
        TypeName factoryType = ParameterizedTypeName.get(
                jsonFileFactory,
                ClassName.get(fullPackage, actualClassName)
        );

        FieldSpec factoryField = FieldSpec.builder(factoryType, "f", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .initializer("new $T.Factory<>($T.class)", jsonFile, ClassName.get(fullPackage, actualClassName))
                .build();

        // Protected constructor
        MethodSpec constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PROTECTED)
                .addParameter(String.class, "name")
                .addParameter(jsonObject, "content")
                .addStatement("super(name, content)")
                .build();

        MethodSpec getFolderName = MethodSpec.methodBuilder("getFolderName")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(String.class)
                .addStatement("return $S", folderName)
                .build();

        TypeSpec generatedClass = TypeSpec.classBuilder(actualClassName)
                .addModifiers(Modifier.PUBLIC)
                .superclass(superClass)
                .addField(factoryField)
                .addMethod(constructor)
                .addMethod(getFolderName)
                .build();

        JavaFile javaFile = JavaFile.builder(fullPackage, generatedClass)
                .skipJavaLangImports(true)
                .build();

        Path outputPath = outputDir.resolve(fullPackage.replace('.', '/'))
                .resolve(actualClassName + ".java");
        Files.createDirectories(outputPath.getParent());
        Files.writeString(outputPath, javaFile.toString());
    }



    private static void genData(String className, String folderName) throws IOException {
        gen(className, "DataJson", folderName);
    }

    private static void genTag(String className, String foldernName) throws IOException {
        genData("tags/"+className + "Tag", "tags/" + foldernName);
    }

    private static void genAssets(String className, String folderName) throws IOException {
        gen(className, "AssetsJson", folderName);
    }

    public static void main(String[] args) throws IOException {
        genData("Advancement", "advancement");
        genData("BannerPattern", "banner_pattern");
        genData("DamageType", "damage_type");
        genData("Dialog", "dialog");
        genData("Dimension", "dimension");
        genData("DimensionType", "dimension_type");
        genData("Enchantment", "enchantment");
        genData("EnchantmentProvider", "enchantment_provider");
        genData("Instrument", "instrument");
        genData("JukeboxSong", "jukebox_song");
        genData("LootTable", "loot_table");
        genData("Recipes", "recipe");
        genData("TrimMaterial", "trim_material");
        genData("TrimPattern", "trim_pattern");
        genData("Worldgen", "worldgen");

        genTag("BannerPattern", "banner_pattern");
        genTag("Block", "block");
        genTag("DamageType", "damage_type");
        genTag("Dialog", "dialog");
        genTag("Enchantment", "enchantment");
        genTag("EntityType", "entity_type");
        genTag("Instrument", "instrument");
        genTag("Item", "item");
        genTag("PaintingVariant", "painting_variant");
        genTag("Worldgen", "worldgen");

        genAssets("Atlas", "atlases");
        genAssets("Blockstate", "blockstates");
        genAssets("Equipment", "equipment");
        genAssets("Font", "font");
        genAssets("Item", "items");
        genAssets("Lang", "lang");
        genAssets("Model", "models");


    }
}
