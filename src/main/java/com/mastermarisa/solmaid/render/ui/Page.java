package com.mastermarisa.solmaid.render.ui;

import com.mastermarisa.solmaid.render.ui.element.UIBox;
import com.mastermarisa.solmaid.render.ui.element.UIElement;
import com.mastermarisa.solmaid.render.ui.element.UIImage;
import com.mastermarisa.solmaid.render.ui.element.UILabel;
import net.minecraft.client.gui.GuiGraphics;

import java.awt.*;
import java.util.List;

public abstract class Page extends UIElement {
    final UILabel title;
    final UIImage bg;
    final UIBox underLine;

    Page(Rectangle frame, String header){
        super(frame);
        bg = new UIImage(UIImages.bookImage);

        title = new UILabel(header,UIImages.fullBlack);
        title.setWidth(107);
        title.alignment = UILabel.TextAlignment.LEFT;

        underLine = UIBox.horizontalLine(title.getMinX(),title.getMaxX(),title.getMaxY() + 3,UIImages.leastBlack);

        this.children = List.of(bg,title,underLine);
    }

    public void onResize(){
        bg.setCenterX(FoodListScreen.getScreenCenterX());
        bg.setCenterY(FoodListScreen.getScreenCenterY());

        title.setCenterX(FoodListScreen.getScreenCenterX());
        title.setCenterY(bg.getMinY() + 7 + font.lineHeight);

        underLine.setCenterX(FoodListScreen.getScreenCenterX());
        underLine.setCenterY(title.getMaxY() + 3);
    }

    public void onSwitchedTo(){
        onResize();
    }
}
