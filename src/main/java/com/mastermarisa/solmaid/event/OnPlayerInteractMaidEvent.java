package com.mastermarisa.solmaid.event;

import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.mastermarisa.solmaid.SOLMaid;
import com.mastermarisa.solmaid.ui.FoodScreen;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = SOLMaid.MOD_ID,value = Dist.CLIENT)
public class OnPlayerInteractMaidEvent {
    @SubscribeEvent
    public static void onPlayerInteractMaidEvent(PlayerInteractEvent.EntityInteract event){
        if(!event.getLevel().isClientSide()){
            return;
        }
        Player player = event.getEntity();
        Entity entity = event.getTarget();
        if(entity instanceof EntityMaid maid){
            if(player.isSecondaryUseActive() && player.getMainHandItem().is(Items.BOOK)){
                FoodScreen.open(player,maid);
                event.setCanceled(true);
            }
        }
    }
}
