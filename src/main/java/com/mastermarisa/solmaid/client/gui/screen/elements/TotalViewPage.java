package com.mastermarisa.solmaid.client.gui.screen.elements;

import com.mastermarisa.solmaid.client.gui.UIConst;
import com.mastermarisa.solmaid.client.gui.elements.*;
import com.mastermarisa.solmaid.data.FoodRecord;
import com.mastermarisa.solmaid.init.InitConfig;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class TotalViewPage extends UIPage{
    private final UIContainerVertical UIInfosContainer;

    public TotalViewPage(String header, FoodRecord foodList, int totalFoodCount){
        super(header);

        java.util.List<UIElement> infos = new ArrayList<>();

        java.util.List<UIElement> foodInfo = new ArrayList<>();
        foodInfo.add(new UIImage(UIConst.hungerImage));
        foodInfo.add(new UILabel(Component.translatable("gui.solmaid.total_view.food_eaten").getString(), UILabel.TextAlignment.CENTER, UIConst.lessBlack, false));
        UIContainerHorizontal UIFoodInfo = UIContainerHorizontal.wrap(foodInfo,9,0,UIContainerHorizontal.ElementAlignment.LEFT);
        UIFoodInfo.setWidth(107);
        infos.add(UIFoodInfo);
        infos.add(new UILabel(foodList.size() + "/" + totalFoodCount, UILabel.TextAlignment.CENTER, UIConst.lessBlack, false));
        infos.add(UIBox.horizontalLine(title.getMinX(),title.getMaxX(),0,UIConst.leastBlack));

        java.util.List<UIElement> hpInfo = new ArrayList<>();
        hpInfo.add(new UIImage(UIConst.heartImage));
        hpInfo.add(new UILabel(Component.translatable("gui.solmaid.total_view.hp_gained").getString(), UILabel.TextAlignment.CENTER, UIConst.lessBlack, false));
        UIContainerHorizontal UIHPInfo = UIContainerHorizontal.wrap(hpInfo,9,0,UIContainerHorizontal.ElementAlignment.LEFT);
        UIHPInfo.setWidth(107);
        infos.add(UIHPInfo);
        infos.add(new UILabel((foodList.reached + 1) * InitConfig.HP() + "/" + InitConfig.MILESTONES().size() * InitConfig.HP(), UILabel.TextAlignment.CENTER, UIConst.lessBlack, false));
        infos.add(UIBox.horizontalLine(title.getMinX(),title.getMaxX(),0,UIConst.leastBlack));

        java.util.List<UIElement> armorInfo = new ArrayList<>();
        armorInfo.add(new UIImage(UIConst.armorImage));
        armorInfo.add(new UILabel(Component.translatable("gui.solmaid.total_view.armor_gained").getString(), UILabel.TextAlignment.CENTER, UIConst.lessBlack, false));
        UIContainerHorizontal UIArmorInfo = UIContainerHorizontal.wrap(armorInfo,9,0,UIContainerHorizontal.ElementAlignment.LEFT);
        UIArmorInfo.setWidth(107);
        infos.add(UIArmorInfo);
        infos.add(new UILabel((foodList.reached + 1) * InitConfig.ARMOR() + "/" + InitConfig.MILESTONES().size() * InitConfig.ARMOR(), UILabel.TextAlignment.CENTER, UIConst.lessBlack, false));
        infos.add(UIBox.horizontalLine(title.getMinX(),title.getMaxX(),0,UIConst.leastBlack));

        java.util.List<UIElement> attackDamageInfo = new ArrayList<>();
        attackDamageInfo.add(new UIImage(UIConst.attackDamageImage));
        attackDamageInfo.add(new UILabel(Component.translatable("gui.solmaid.total_view.attack_damage_gained").getString(), UILabel.TextAlignment.CENTER, UIConst.lessBlack, false));
        UIContainerHorizontal UIAttackDamageInfo = UIContainerHorizontal.wrap(attackDamageInfo,9,0,UIContainerHorizontal.ElementAlignment.LEFT);
        UIAttackDamageInfo.setWidth(107);
        infos.add(UIAttackDamageInfo);
        infos.add(new UILabel((foodList.reached + 1) * InitConfig.ATTACK_DAMAGE() + "/" + InitConfig.MILESTONES().size() * InitConfig.ATTACK_DAMAGE(), UILabel.TextAlignment.CENTER, UIConst.lessBlack, false));
        infos.add(UIBox.horizontalLine(title.getMinX(),title.getMaxX(),0,UIConst.leastBlack));

        UIInfosContainer = UIContainerVertical.wrap(infos,3,0,UIContainerVertical.ElementAlignment.UP);

        this.children = List.of(bg,title,underLine,UIInfosContainer);
    }

    public void onResize(){
        super.onResize();

        UIInfosContainer.setCenterX(getCenterX());
        UIInfosContainer.setMinY(title.getMaxY() + 7);
    }

    public void onSwitchedTo(){
        onResize();
    }
}
