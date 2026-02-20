package com.mastermarisa.solmaid.utils;

import com.mastermarisa.solmaid.init.InitConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FilterHelper {
    private static boolean initialized;
    private static final List<Item> blacklistItems;
    private static final List<TagKey<Item>> blacklistTags;

    public static boolean filter(ItemStack itemStack) {
        init();
        return !isInBlacklist(itemStack);
    }

    private static boolean isInBlacklist(ItemStack itemStack) {
        return InitConfig.ENABLE_BLACKLIST() && (blacklistItems.contains(itemStack.getItem()) || blacklistTags.stream().anyMatch(itemStack::is));
    }

    private static boolean isTagLocation(String location) {
        return location.startsWith("#");
    }

    private static void init() {
        if (initialized) return;
        for (String str : InitConfig.BLACKLIST()) {
            if (isTagLocation(str)) {
                ResourceLocation location = ResourceLocation.tryParse(str.substring(1));
                if (location == null) continue;
                blacklistTags.add(TagKey.create(Registries.ITEM, location));
                Debug.Log("TAG ADDED");
            } else {
                ResourceLocation location = ResourceLocation.tryParse(str);
                if (location == null) continue;
                blacklistItems.add(BuiltInRegistries.ITEM.get(location));
                Debug.Log("ITEM ADDED");
            }
        }
        initialized = true;
    }

    static {
        blacklistItems = new ArrayList<>();
        blacklistTags = new ArrayList<>();
    }
}
