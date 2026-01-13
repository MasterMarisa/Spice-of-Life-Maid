package com.mastermarisa.solmaid.event;

import com.github.tartaricacid.touhoulittlemaid.api.event.AddJadeInfoEvent;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.mastermarisa.solmaid.func.FoodList;
import com.mastermarisa.solmaid.registry.ModAttachmentTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import snownee.jade.api.ITooltip;

public class OnAddJadeInfoEvent {
    public static final boolean JADE_LOADED = ModList.get().isLoaded("jade");

    @SubscribeEvent
    public static void onAddJadeInfoEvent(AddJadeInfoEvent event){
        EntityMaid maid = event.getMaid();
        ITooltip tooltip = event.getTooltip();
        FoodList foodList = maid.getData(ModAttachmentTypes.FOOD_LIST);
        tooltip.add(Component.translatable("tooltip.solmaid.jade_maid.eaten_count",foodList.getEatenCount()).withStyle(ChatFormatting.AQUA));
        Player player = (Player) event.getMaid().getOwner();
        if(player == null) return;
        ItemStack stack = player.getMainHandItem();
        if(stack.getItem().getFoodProperties(stack,player) != null){
            if(foodList.isFoodEaten(stack)){
                tooltip.add(Component.translatable("tooltip.solmaid.jade_maid.eaten").withStyle(ChatFormatting.GRAY));
            } else {
                tooltip.add(Component.translatable("tooltip.solmaid.jade_maid.not_eaten").withStyle(ChatFormatting.AQUA));
            }
        }
    }
}
