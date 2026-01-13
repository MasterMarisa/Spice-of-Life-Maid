package com.mastermarisa.solmaid.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class ModUtils {
    public static final Gson GSON;

    static {
        GSON = new GsonBuilder().setPrettyPrinting().create();
    }

//    public static String toJson(EffectAuraData data){
//        return GSON.toJson(data);
//    }
//
//    public static EffectAuraData toObject(String json){
//        return GSON.fromJson(json, EffectAuraData.class);
//    }

    public static void writeStringSet(CompoundTag tag, String key, Set<String> stringSet) {
        ListTag listTag = new ListTag();
        for (String str : stringSet) {
            listTag.add(StringTag.valueOf(str));
        }
        tag.put(key, listTag);
    }

    public static Set<String> readStringSet(CompoundTag tag, String key) {
        Set<String> stringSet = new HashSet<>();
        if (tag.contains(key, Tag.TAG_LIST)) {
            ListTag listTag = tag.getList(key, Tag.TAG_STRING);
            for (int i = 0; i < listTag.size(); i++) {
                stringSet.add(listTag.getString(i));
            }
        }
        return stringSet;
    }

    public static String encodeItem(Item item){
        return BuiltInRegistries.ITEM.getKey(item).toString();
    }

    public static Item decodeItem(String encoded){
        ResourceLocation name = ResourceLocation.tryParse(encoded);
        return BuiltInRegistries.ITEM.get(name);
    }
}
