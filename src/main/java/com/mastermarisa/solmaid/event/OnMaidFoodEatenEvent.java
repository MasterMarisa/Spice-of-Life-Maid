package com.mastermarisa.solmaid.event;

import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.mastermarisa.solmaid.func.FoodList;
import com.mastermarisa.solmaid.registry.ModAttachmentTypes;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

public class OnMaidFoodEatenEvent {
    @SubscribeEvent
    public static void onFoodEaten(LivingEntityUseItemEvent.Finish event){
        LivingEntity entity = event.getEntity();
        if(entity instanceof EntityMaid maid){
            FoodList foodList = maid.getData(ModAttachmentTypes.FOOD_LIST);
            foodList.addFoodByMaid(event.getItem(),maid);
            maid.setData(ModAttachmentTypes.FOOD_LIST,foodList);
        }
    }
}
