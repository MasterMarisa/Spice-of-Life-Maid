package com.mastermarisa.solmaid.compat.jade;

import com.github.tartaricacid.touhoulittlemaid.api.event.AddJadeInfoEvent;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.mastermarisa.solmaid.data.FoodRecord;
import com.mastermarisa.solmaid.utils.FilterHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import snownee.jade.api.ITooltip;

public class OnAddJadeInfo {
    @SubscribeEvent
    public static void onAddJadeInfoEvent(AddJadeInfoEvent event){
        EntityMaid maid = event.getMaid();
        ITooltip tooltip = event.getTooltip();
        FoodRecord foodRecord = maid.getData(FoodRecord.TYPE);
        tooltip.add(Component.translatable("tooltip.solmaid.jade_maid.eaten_count", foodRecord.size()).withStyle(ChatFormatting.AQUA));
        Player player = (Player) event.getMaid().getOwner();
        if(player == null) return;
        ItemStack stack = player.getMainHandItem();
        if(stack.getItem().getFoodProperties(stack,player) != null){
            if (FilterHelper.filter(stack)) {
                if(foodRecord.isFoodEaten(stack)){
                    tooltip.add(Component.translatable("tooltip.solmaid.jade_maid.eaten").withStyle(ChatFormatting.GRAY));
                } else {
                    tooltip.add(Component.translatable("tooltip.solmaid.jade_maid.not_eaten").withStyle(ChatFormatting.AQUA));
                }
            } else {
                tooltip.add(Component.translatable("tooltip.solmaid.jade_maid.invalid").withStyle(ChatFormatting.GRAY));
            }
        }
    }
}
