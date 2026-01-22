package com.mastermarisa.solmaid.event;

import com.mastermarisa.solmaid.utils.FoodBookHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.server.MinecraftServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

public class OnServerStartingEvent {
    @SubscribeEvent
    public static void onServerStartingEvent(ServerStartingEvent event){
        MinecraftServer server = event.getServer();
        HolderLookup.Provider provider = server.registryAccess();

        FoodBookHelper.setAllFoodsByComponent(provider);
    }
}
