package com.mastermarisa.solmaid.registry;

import com.mastermarisa.solmaid.func.FoodList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.neoforged.neoforge.attachment.AttachmentSyncHandler;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

public class FoodLIstSyncHandler implements AttachmentSyncHandler<FoodList> {
    @Override
    public void write(RegistryFriendlyByteBuf registryFriendlyByteBuf, FoodList foodList, boolean b) {
        registryFriendlyByteBuf.writeCollection(foodList.getFoods(), FriendlyByteBuf::writeUtf);
    }

    @Override
    public @Nullable FoodList read(IAttachmentHolder iAttachmentHolder, RegistryFriendlyByteBuf registryFriendlyByteBuf, @Nullable FoodList foodList) {
        if(foodList == null){
            foodList = new FoodList();
        }
        foodList.setFoods(registryFriendlyByteBuf.readCollection(HashSet::new, FriendlyByteBuf::readUtf));
        return foodList;
    }
}
