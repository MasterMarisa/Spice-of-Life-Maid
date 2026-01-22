package com.mastermarisa.solmaid.utils;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FoodBookHelper {
    public static void setAllFoodsByComponent(HolderLookup.Provider lookupProvider) {
        List<Item> foodList = new ArrayList<>();
        lookupProvider.lookup(Registries.ITEM).ifPresent(itemLookup -> {
            itemLookup.listElements().forEach(itemHolder -> {
                Item item = itemHolder.value();
                if (item.getFoodProperties(new ItemStack(item),null) != null){
                    foodList.add(item);
                }
            });
        });
        allFoods = foodList;
    }

    public static List<Item> getAllFoods(){
        return allFoods;
    }

    public static List<Item> allFoods = new ArrayList<>();
}
