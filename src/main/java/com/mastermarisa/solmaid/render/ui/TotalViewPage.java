package com.mastermarisa.solmaid.render.ui;

import com.mastermarisa.solmaid.config.ModConfig;
import com.mastermarisa.solmaid.func.FoodList;
import com.mastermarisa.solmaid.render.ui.element.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TotalViewPage extends Page{
    private final UIContainerVertical UIInfosContainer;

    public TotalViewPage(Rectangle frame, String header, FoodList foodList, int totalFoodCount){
        super(frame,header);

        List<UIElement> infos = new ArrayList<>();

        List<UIElement> foodInfo = new ArrayList<>();
        foodInfo.add(new UIImage(UIImages.hungerImage));
        foodInfo.add(new UILabel(Component.translatable("gui.solmaid.total_view.food_eaten").getString(),UIImages.lessBlack));
        UIContainerHorizontal UIFoodInfo = UIContainerHorizontal.wrap(foodInfo,9,0,UIContainerHorizontal.ElementAlignment.LEFT);
        UIFoodInfo.setWidth(107);
        infos.add(UIFoodInfo);
        infos.add(new UILabel(foodList.getFoods().size() + "/" + totalFoodCount,UIImages.lessBlack));
        infos.add(UIBox.horizontalLine(title.getMinX(),title.getMaxX(),0,UIImages.leastBlack));

        List<UIElement> hpInfo = new ArrayList<>();
        hpInfo.add(new UIImage(UIImages.heartImage));
        hpInfo.add(new UILabel(Component.translatable("gui.solmaid.total_view.hp_gained").getString(),UIImages.lessBlack));
        UIContainerHorizontal UIHPInfo = UIContainerHorizontal.wrap(hpInfo,9,0,UIContainerHorizontal.ElementAlignment.LEFT);
        UIHPInfo.setWidth(107);
        infos.add(UIHPInfo);
        infos.add(new UILabel((foodList.getCachedMilestone() + 1) * ModConfig.getHeartsPerStone() * 2 + "/" + ModConfig.getMileStones().size() * ModConfig.getHeartsPerStone() * 2,UIImages.lessBlack));
        infos.add(UIBox.horizontalLine(title.getMinX(),title.getMaxX(),0,UIImages.leastBlack));

        List<UIElement> armorInfo = new ArrayList<>();
        armorInfo.add(new UIImage(UIImages.armorImage));
        armorInfo.add(new UILabel(Component.translatable("gui.solmaid.total_view.armor_gained").getString(),UIImages.lessBlack));
        UIContainerHorizontal UIArmorInfo = UIContainerHorizontal.wrap(armorInfo,9,0,UIContainerHorizontal.ElementAlignment.LEFT);
        UIArmorInfo.setWidth(107);
        infos.add(UIArmorInfo);
        infos.add(new UILabel((foodList.getCachedMilestone() + 1) * ModConfig.getArmorPerStone() + "/" + ModConfig.getMileStones().size() * ModConfig.getArmorPerStone(),UIImages.lessBlack));
        infos.add(UIBox.horizontalLine(title.getMinX(),title.getMaxX(),0,UIImages.leastBlack));

        List<UIElement> attackDamageInfo = new ArrayList<>();
        attackDamageInfo.add(new UIImage(UIImages.attackDamageImage));
        attackDamageInfo.add(new UILabel(Component.translatable("gui.solmaid.total_view.attack_damage_gained").getString(),UIImages.lessBlack));
        UIContainerHorizontal UIAttackDamageInfo = UIContainerHorizontal.wrap(attackDamageInfo,9,0,UIContainerHorizontal.ElementAlignment.LEFT);
        UIAttackDamageInfo.setWidth(107);
        infos.add(UIAttackDamageInfo);
        infos.add(new UILabel((foodList.getCachedMilestone() + 1) * ModConfig.getAttackDamagePerStone() + "/" + ModConfig.getMileStones().size() * ModConfig.getAttackDamagePerStone(),UIImages.lessBlack));
        infos.add(UIBox.horizontalLine(title.getMinX(),title.getMaxX(),0,UIImages.leastBlack));

        UIInfosContainer = UIContainerVertical.wrap(infos,3,0,UIContainerVertical.ElementAlignment.UP);

        this.children = List.of(bg,title,underLine,UIInfosContainer);
    }

    public void onResize(){
        super.onResize();

        UIInfosContainer.setCenterX(FoodListScreen.getScreenCenterX());
        UIInfosContainer.setMinY(title.getMaxY() + 7);
    }

    public void onSwitchedTo(){
        onResize();
    }
}
