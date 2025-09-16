package com.asdru.oopack.util;

import com.asdru.oopack.Context;
import com.asdru.oopack.objects.assets.Item;
import com.asdru.oopack.objects.data.LootTable;
import com.google.gson.*;

public class ItemFactory {

    private static JsonObject defaultComponents = new JsonObject();
    private static boolean randomNames = true, internalIds = true;

    public static void setRandomNames(boolean randomNames) {
        ItemFactory.randomNames = randomNames;
    }

    public static void setInternalIds(boolean internalIds) {
        ItemFactory.internalIds = internalIds;
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
        private String components; // optional JSON string
        private Item itemModelDefinition;


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

        public Builder model(Item itemModelDefinition) {
            this.itemModelDefinition = itemModelDefinition;
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

            // root
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

            JsonArray functions = new JsonArray();
            pool.add("functions", functions);

            JsonObject setComponents = new JsonObject();
            setComponents.addProperty("function", "minecraft:set_components");

            // copy defaultComponents
            JsonObject merged = deepCopy(defaultComponents);

            // merge in custom components (if present)
            if (components != null) {
                JsonObject compObj = JsonParser.parseString(components).getAsJsonObject();
                mergeJsonObjects(merged, compObj);
            }

            JsonObject nameObj = new JsonObject();
            nameObj.addProperty("translate", translationStr);
            merged.add("minecraft:item_name", nameObj);

            if (itemModelDefinition != null) {
                String modelId = itemModelDefinition.toString();
                merged.addProperty("minecraft:item_model", modelId);
            }


            if (internalIds) {
                internalIds(merged);
            }

            setComponents.add("components", merged);
            functions.add(setComponents);

            return root;
        }

        private void internalIds(JsonObject merged) {
            JsonObject internalIdObj = new JsonObject();
            internalIdObj.addProperty("id", "$ns$:%s".formatted(id));

            if (merged.has("minecraft:custom_data") &&
                    merged.get("minecraft:custom_data").isJsonObject()) {
                // merge into existing custom_data
                JsonObject existingCustomData = merged.getAsJsonObject("minecraft:custom_data");
                mergeJsonObjects(existingCustomData, internalIdObj);
            } else {
                // create new custom_data
                JsonObject customData = new JsonObject();
                customData.add(Context.getActiveNamespace().getName(), internalIdObj);
                merged.add("minecraft:custom_data", customData);
            }
        }

        private static void mergeJsonObjects(JsonObject target, JsonObject src) {
            for (var entry : src.entrySet()) {
                if (target.has(entry.getKey()) &&
                        target.get(entry.getKey()).isJsonObject() &&
                        entry.getValue().isJsonObject()) {
                    // merge if both are objects
                    mergeJsonObjects(target.getAsJsonObject(entry.getKey()), entry.getValue().getAsJsonObject());
                } else {
                    // if not, overwrite
                    target.add(entry.getKey(), entry.getValue());
                }
            }
        }

        private static JsonObject deepCopy(JsonObject original) {
            return JsonParser.parseString(original.toString()).getAsJsonObject();
        }
    }
}
