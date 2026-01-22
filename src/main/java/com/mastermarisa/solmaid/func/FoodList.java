package com.mastermarisa.solmaid.func;

import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.mastermarisa.solmaid.SOLMaid;
import com.mastermarisa.solmaid.config.ModConfig;
import com.mastermarisa.solmaid.utils.ModUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.util.INBTSerializable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

@ParametersAreNonnullByDefault
public class FoodList implements INBTSerializable<CompoundTag> {
    private Set<String> foods = new HashSet();
    private int cachedMilestone = -100;
    private List<Item> cachedItemList;

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        ModUtils.writeStringSet(tag,"foodlist",foods);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        foods = ModUtils.readStringSet(tag, "foodlist");
    }

    public void addFood(ItemStack stack){
        this.foods.add(ModUtils.encodeItem(stack.getItem()));
    }

    public void addFoodByMaid(ItemStack stack, EntityMaid maid) {;
        int milestone = getCachedMilestone();
        addFood(stack);
        if(milestone < ModConfig.getMileStones().size() - 1){
            if(this.foods.size() >= ModConfig.getMileStones().get(milestone + 1)){
                this.cachedMilestone += 1;
                MaxHealthHandler.updateFoodHPModifier(maid);
                ArmorHandler.updateFoodArmorModifier(maid);
                AttackDamageHandler.updateFoodArmorModifier(maid);
                maid.getFavorabilityManager().add(ModConfig.getFavorabilityPerMilestone());
                LivingEntity player = maid.getOwner();
                if(player instanceof ServerPlayer serverPlayer){
                    serverPlayer.sendChatMessage(new OutgoingChatMessage.Disguised(Component.translatable("message.solmaid.milestone_achieved",Component.translatable("message.solmaid.hearts",ModConfig.getHeartsPerStone())).withStyle(ChatFormatting.AQUA)),false, ChatType.bind(ChatType.CHAT, serverPlayer));
                    if(ModConfig.getArmorPerStone() != 0){
                        serverPlayer.sendChatMessage(new OutgoingChatMessage.Disguised(Component.translatable("message.solmaid.milestone_achieved",Component.translatable("message.solmaid.armor",ModConfig.getArmorPerStone())).withStyle(ChatFormatting.AQUA)),false, ChatType.bind(ChatType.CHAT, serverPlayer));
                    }
                    if(ModConfig.getAttackDamagePerStone() != 0){
                        serverPlayer.sendChatMessage(new OutgoingChatMessage.Disguised(Component.translatable("message.solmaid.milestone_achieved",Component.translatable("message.solmaid.attack_damage",ModConfig.getAttackDamagePerStone())).withStyle(ChatFormatting.AQUA)),false, ChatType.bind(ChatType.CHAT, serverPlayer));
                    }
                    serverPlayer.sendChatMessage(new OutgoingChatMessage.Disguised(Component.translatable("message.solmaid.favorability_increased",ModConfig.getFavorabilityPerMilestone()).withStyle(ChatFormatting.AQUA)),false, ChatType.bind(ChatType.CHAT, serverPlayer));
                    if(this.getCachedMilestone() == ModConfig.getMileStones().size() - 1){
                        serverPlayer.sendChatMessage(new OutgoingChatMessage.Disguised(Component.translatable("message.solmaid.finished").withStyle(ChatFormatting.AQUA)),false, ChatType.bind(ChatType.CHAT, serverPlayer));
                    }
                }
            }
        }
    }


    public int getEatenCount(){
        if(ModConfig.doCountInvalidModFood()){
            return this.foods.size();
        }
        return getFoodItems().size();
    }

    public Set<String> getFoods() { return this.foods; }

    public void setFoods(Set<String> set){
        this.foods = set;
    }

    public boolean isFoodEaten(ItemStack stack){
        return this.foods.contains(ModUtils.encodeItem(stack.getItem()));
    }

    public int getMileStoneReached(){
        List<Integer> milestones = ModConfig.getMileStones();
        int count = foods.size();
        int res = -1;
        for(int i = 0;i < milestones.size();i++){
            if(count >= milestones.get(i)){
                res += 1;
            }
        }

        return res;
    }

    public int getCachedMilestone(){
        SOLMaid.LOGGER.debug("cachedMilestone:" + String.valueOf(this.cachedMilestone));
        if(this.cachedMilestone == -100){
            this.cachedMilestone = getMileStoneReached();
        }
        return this.cachedMilestone;
    }

    public List<Item> getFoodItems(){
        return foods.stream().map(ModUtils::decodeItem).toList();
    }
}
