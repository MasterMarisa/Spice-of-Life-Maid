package com.mastermarisa.solmaid.registry;

import com.mastermarisa.solmaid.func.FoodList;
import com.mastermarisa.solmaid.SOLMaid;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachmentTypes {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, SOLMaid.MOD_ID);

    public static final Supplier<AttachmentType<FoodList>> FOOD_LIST = ATTACHMENT_TYPES.register("foodlist", ()-> AttachmentType.serializable(FoodList::new).sync(new FoodLIstSyncHandler()).build());
}
