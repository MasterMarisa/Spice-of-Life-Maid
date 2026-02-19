package com.mastermarisa.solmaid.event;

import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.mastermarisa.solmaid.SOLMaid;
import com.mastermarisa.solmaid.entity.FoodRecord;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber(modid = SOLMaid.MOD_ID)
public class OnMaidJoin {
    @SubscribeEvent
    public static void onMaidJoinWorld(EntityJoinLevelEvent event){
        if (event.getLevel().isClientSide()) return;

        if(event.getEntity() instanceof EntityMaid maid){
            FoodRecord record = maid.getData(FoodRecord.TYPE);
            record.resolveAvailability(maid);
            maid.setData(FoodRecord.TYPE, record);
        }
    }
}
