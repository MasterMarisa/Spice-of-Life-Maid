package com.mastermarisa.solmaid;

import com.mastermarisa.solmaid.init.InitCompats;
import com.mastermarisa.solmaid.init.InitConfig;
import com.mastermarisa.solmaid.init.InitEntities;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;

@Mod(SOLMaid.MOD_ID)
public class SOLMaid {
    public static final String MOD_ID = "solmaid";

    public static ResourceLocation resourceLocation(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public SOLMaid(IEventBus modEventBus, ModContainer modContainer) {
        InitEntities.register(modEventBus);

        InitCompats.register();

        InitConfig.register(modContainer);
    }
}
