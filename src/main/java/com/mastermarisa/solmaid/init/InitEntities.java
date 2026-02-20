package com.mastermarisa.solmaid.init;

import com.mastermarisa.solmaid.SOLMaid;
import com.mastermarisa.solmaid.data.FoodRecord;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class InitEntities {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.
            create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, SOLMaid.MOD_ID);

    public static final Supplier<AttachmentType<FoodRecord>> FOOD_RECORD = ATTACHMENT_TYPES.register("food_record", () -> FoodRecord.TYPE);

    public static void register(IEventBus mod) {
        ATTACHMENT_TYPES.register(mod);
    }
}
