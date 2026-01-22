package com.mastermarisa.solmaid.event;

import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.mastermarisa.solmaid.render.ui.FoodListScreen;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class OnPlayerInteractMaidEvent {
    @SubscribeEvent
    public static void onPlayerInteractMaidEvent(PlayerInteractEvent.EntityInteract event){
        Player player = event.getEntity();
        Entity entity = event.getTarget();
        if(entity instanceof EntityMaid maid){
            if(player.getMainHandItem().is(Items.BOOK)){
                if (event.getLevel().isClientSide)
                    FoodListScreen.open(player,maid);
                event.setCanceled(true);
            }
        }
    }
}
