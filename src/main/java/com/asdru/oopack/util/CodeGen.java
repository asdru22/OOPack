package com.asdru.oopack.util;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Path;

public class CodeGen {

    private static final String packageName = "com.asdru.oopack.objects";
    private static final Path outputDir = Path.of("./src/main/java");

    public static void gen(
            String className,
            String superClassName,
            String folderName
    ) throws IOException {

        // imports
        ClassName jsonObject = ClassName.get("com.google.gson", "JsonObject");
        ClassName jsonFileFactory = ClassName.get("com.asdru.oopack.internal", "JsonFileFactory");
        ClassName superClass = ClassName.get("com.asdru.oopack.objects", superClassName);

        // static factory
        TypeName factoryType = ParameterizedTypeName.get(jsonFileFactory, ClassName.get(packageName, className));

        FieldSpec factoryField = FieldSpec.builder(factoryType, "f", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .initializer("new JsonFile.Factory<>($T.class)", ClassName.get(packageName, className))
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

        TypeSpec generatedClass = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .superclass(superClass)
                .addField(factoryField)
                .addMethod(constructor)
                .addMethod(getFolderName)
                .build();

        JavaFile javaFile = JavaFile.builder(packageName, generatedClass)
                .skipJavaLangImports(true)
                .build();

        // patch "public class" to "public non-sealed class"
        String source = javaFile.toString().replace("public class", "public non-sealed class");

        Path outputPath = outputDir.resolve(packageName.replace('.', '/'))
                .resolve(className + ".java");
        java.nio.file.Files.createDirectories(outputPath.getParent());
        java.nio.file.Files.writeString(outputPath, source);
    }

    private static void genData(String className, String folderName) throws IOException {
        gen(className, "DataJson", folderName);
    }

    private static void genTag(String className, String foldernName) throws IOException {
        genData(className + "Tag", "tags/" + foldernName);
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
