package com.mastermarisa.solmaid.utils;

import com.mastermarisa.solmaid.SOLMaid;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = SOLMaid.MOD_ID)
public class ItemHelper {
    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event){
        if(event.getEntity() instanceof ServerPlayer player){
            MinecraftServer server = player.getServer();
            HolderLookup.Provider provider = server.registryAccess();

            List<Item> foodList = new ArrayList<>();
            provider.lookup(Registries.ITEM).ifPresent(itemLookup -> {
                itemLookup.listElements().forEach(itemHolder -> {
                    Item item = itemHolder.value();
                    if (item.getFoodProperties(new ItemStack(item),null) != null){
                        foodList.add(item);
                    }
                });
            });
            allFoods = foodList;
        }
    }

    public static List<Item> allFoods = new ArrayList<>();
}
