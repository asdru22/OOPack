package com.asdru.oopack.objects;

import com.asdru.oopack.Namespace;
import com.google.gson.JsonObject;

sealed abstract public class DataJson extends JsonFile
        permits Advancement, BannerPattern, BannerPatternTag,
        BlockTag, DamageType, DamageTypeTag, Dialog, DialogTag,
        Dimension, DimensionType, Enchantment, EnchantmentProvider,
        EnchantmentTag, EntityTypeTag, FunctionTag, Instrument, InstrumentTag,
        ItemTag, JukeboxSong, LootTable, PaintingVariantTag, Recipes,
        TrimMaterial, TrimPattern, Worldgen, WorldgenTag {

    protected DataJson(String name, JsonObject content) {
        super(name, content);
    }

    @Override
    public void collectByType(Namespace data, Namespace assets) {
        data.add(this);
    }
}
