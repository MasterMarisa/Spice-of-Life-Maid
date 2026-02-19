package com.mastermarisa.solmaid.event;

import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.mastermarisa.solmaid.SOLMaid;
import com.mastermarisa.solmaid.entity.FoodRecord;
import com.mastermarisa.solmaid.utils.EncodeUtils;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

@EventBusSubscriber(modid = SOLMaid.MOD_ID)
public class OnMaidFoodEaten {
    @SubscribeEvent
    public static void onFoodEaten(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity().level().isClientSide()) return;

        if (event.getEntity() instanceof EntityMaid maid) {
            FoodRecord record = maid.getData(FoodRecord.TYPE);
            if (record.add(EncodeUtils.encode(event.getItem()).toString(), maid))
                maid.setData(FoodRecord.TYPE, record);
        }
    }
}
