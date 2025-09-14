package com.asdru.oopack.util;

import com.asdru.oopack.Context;
import com.asdru.oopack.objects.assets.Font;
import com.asdru.oopack.objects.data.LootTable;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ItemFactory {

    private static Font font;
    private static JsonObject loreObj;
    private static boolean randomNames = true;


    public static void setRandomNames(boolean randomNames) {
        ItemFactory.randomNames = randomNames;
    }

    public static void setFont(Font font) {
        ItemFactory.font = font;
    }

    public static void setLoreObj(JsonObject loreObj) {
        ItemFactory.loreObj = loreObj;
    }

    // builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String baseItem;
        private String id;
        private String displayName;
        private String components; // opzionale

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
            String translationStr = "item.%s.%s"
                    .formatted(Context.getActiveNamespace().getName(), id);

            Util.addTranslation(translationStr, displayName);

            // root
            JsonObject root = new JsonObject();
            JsonArray pools = new JsonArray();
            root.add("pools", pools);

            // pool object
            JsonObject pool = new JsonObject();
            pool.addProperty("rolls", 1);
            pools.add(pool);

            // entries
            JsonArray entries = new JsonArray();
            pool.add("entries", entries);

            JsonObject entry = new JsonObject();
            entry.addProperty("type", "minecraft:item");
            entry.addProperty("name", baseItem);
            entries.add(entry);

            // functions
            JsonArray functions = new JsonArray();
            pool.add("functions", functions);

            // components (solo se definiti)
            if (components != null) {
                JsonObject setComponents = new JsonObject();
                setComponents.addProperty("function", "minecraft:set_components");
                JsonObject compObj = com.google.gson.JsonParser
                        .parseString(components)
                        .getAsJsonObject();
                setComponents.add("components", compObj);
                functions.add(setComponents);
            }

            // set_name
            JsonObject setName = new JsonObject();
            setName.addProperty("function", "minecraft:set_name");
            setName.addProperty("target", "item_name");

            JsonObject nameObj = new JsonObject();
            nameObj.addProperty("translate", translationStr);
            setName.add("name", nameObj);

            functions.add(setName);

            // set_lore
            JsonObject setLore = new JsonObject();
            setLore.addProperty("function", "minecraft:set_lore");

            JsonArray loreArr = new JsonArray();
            loreArr.add(loreObj);

            setLore.add("lore", loreArr);
            setLore.addProperty("mode", "replace_all");

            functions.add(setLore);

            return root;
        }
    }
}
