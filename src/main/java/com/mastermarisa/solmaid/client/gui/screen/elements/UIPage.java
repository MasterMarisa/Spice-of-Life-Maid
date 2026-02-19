package com.mastermarisa.solmaid.client.gui.screen.elements;

import com.google.common.collect.Lists;
import com.mastermarisa.solmaid.client.gui.UIConst;
import com.mastermarisa.solmaid.client.gui.elements.UIBox;
import com.mastermarisa.solmaid.client.gui.elements.UIElement;
import com.mastermarisa.solmaid.client.gui.elements.UIImage;
import com.mastermarisa.solmaid.client.gui.elements.UILabel;

import java.awt.*;
import java.util.List;

public class UIPage extends UIElement {
    protected final UILabel title;
    public final UIImage bg;
    protected final UIBox underLine;

    public UIPage(String header) {
        super(new Rectangle(148,180));
        bg = new UIImage(UIConst.bookImage);
        title = new UILabel(new Rectangle(108, font.lineHeight), header, UILabel.TextAlignment.LEFT, UIConst.lessBlack, false);
        underLine = UIBox.horizontalLine(title.getMinX(), title.getMaxX(), title.getMaxY() + 3, UIConst.leastBlack);

        this.children = Lists.newArrayList(bg, title, underLine);
    }

    public void onResize(){
        bg.setCenter(getCenterX(), getCenterY());
        title.setCenter(getCenterX(), bg.getMinY() + 7 + font.lineHeight);
        underLine.setCenter(getCenterX(), title.getMaxY() + 3);
    }

    public void onSwitchedTo(){
        onResize();
    }
}
