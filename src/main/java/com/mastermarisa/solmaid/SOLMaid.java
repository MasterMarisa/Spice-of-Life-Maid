package com.mastermarisa.solmaid;

import com.mastermarisa.solmaid.config.ModConfig;
import com.mastermarisa.solmaid.event.*;
import com.mastermarisa.solmaid.registry.ModAttachmentTypes;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;

@Mod(SOLMaid.MOD_ID)
public class SOLMaid {
    public static final String MOD_ID = "solmaid";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation resourceLocation(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public SOLMaid(IEventBus modEventBus, ModContainer modContainer, Dist dist) {
        ModAttachmentTypes.ATTACHMENT_TYPES.register(modEventBus);

        NeoForge.EVENT_BUS.register(OnMaidFoodEatenEvent.class);
        NeoForge.EVENT_BUS.register(OnMaidJoinWorldEvent.class);
        NeoForge.EVENT_BUS.register(OnPlayerFeedMaidEvent.class);
        NeoForge.EVENT_BUS.register(OnPlayerInteractMaidEvent.class);
        NeoForge.EVENT_BUS.register(OnServerStartingEvent.class);
        if(OnAddJadeInfoEvent.JADE_LOADED){
            NeoForge.EVENT_BUS.register(OnAddJadeInfoEvent.class);
        }

        modContainer.registerConfig(net.neoforged.fml.config.ModConfig.Type.SERVER, ModConfig.SPEC);
    }
}
