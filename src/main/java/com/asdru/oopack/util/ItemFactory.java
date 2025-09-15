package com.asdru.oopack.util;

import com.asdru.oopack.Context;
import com.asdru.oopack.objects.data.LootTable;
import com.google.gson.*;

public class ItemFactory {

    private static JsonObject defaultComponents = new JsonObject();
    private static boolean randomNames = true;

    public static void setRandomNames(boolean randomNames) {
        ItemFactory.randomNames = randomNames;
    }

    public static void setDefaultComponents(JsonObject components) {
        ItemFactory.defaultComponents = components;
    }

    // builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String baseItem;
        private String id;
        private String displayName;
        private String components; // opzionale JSON string

        private Builder() {
        }

        public Builder base(String baseItem) {
            this.baseItem = baseItem;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder components(String componentsJson) {
            this.components = componentsJson;
            return this;
        }

        public LootTable build() {
            if (baseItem == null || id == null || displayName == null) {
                throw new IllegalStateException("baseItem, id, and displayName must all be set");
            }
            String content = makeLootTable().toString();
            return randomNames ? LootTable.f.of(content) : LootTable.f.ofName(id, content);
        }

        private JsonObject makeLootTable() {
            String ns = Context.getActiveNamespace().getName();
            String translationStr = "item.%s.%s".formatted(ns, id);

            Util.addTranslation(translationStr, displayName);

            // === root
            JsonObject root = new JsonObject();
            JsonArray pools = new JsonArray();
            root.add("pools", pools);

            // pool
            JsonObject pool = new JsonObject();
            pool.addProperty("rolls", 1);
            pools.add(pool);

            // entry
            JsonArray entries = new JsonArray();
            pool.add("entries", entries);

            JsonObject entry = new JsonObject();
            entry.addProperty("type", "minecraft:item");
            entry.addProperty("name", baseItem);
            entries.add(entry);

            // === unico set_components
            JsonArray functions = new JsonArray();
            pool.add("functions", functions);

            JsonObject setComponents = new JsonObject();
            setComponents.addProperty("function", "minecraft:set_components");

            // start from defaultComponents (clone!)
            JsonObject merged = deepCopy(defaultComponents);

            // merge in custom components (if present)
            if (components != null) {
                JsonObject compObj = JsonParser.parseString(components).getAsJsonObject();
                mergeJsonObjects(merged, compObj);
            }

            // aggiungi traduzione del nome dell’item
            JsonObject nameObj = new JsonObject();
            nameObj.addProperty("translate", translationStr);
            merged.add("minecraft:item_name", nameObj);

            // aggiungi custom_data di default (per esempio id)
            JsonObject customData = new JsonObject();
            JsonObject haywireObj = new JsonObject();
            haywireObj.addProperty("id", id);
            customData.add("haywire", haywireObj);
            merged.add("minecraft:custom_data", customData);

            setComponents.add("components", merged);
            functions.add(setComponents);

            return root;
        }

        // === helpers
        private static void mergeJsonObjects(JsonObject target, JsonObject src) {
            for (var entry : src.entrySet()) {
                target.add(entry.getKey(), entry.getValue());
            }
        }

        private static JsonObject deepCopy(JsonObject original) {
            return JsonParser.parseString(original.toString()).getAsJsonObject();
        }
    }
}
