package com.mastermarisa.solmaid.client.gui;

import com.mastermarisa.solmaid.SOLMaid;
import com.mastermarisa.solmaid.client.gui.elements.ImageData;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public interface UIConst {
    Color fullBlack = Color.BLACK;
    Color lessBlack = new Color(0, 0, 0, 128);
    Color leastBlack = new Color(0, 0, 0, 64);
    ImageData hungerImage = new ImageData(SOLMaid.resourceLocation("textures/gui/food_book.png"),new Rectangle(19,227,9,9),9,9,400,256);
    ImageData bookImage = new ImageData(SOLMaid.resourceLocation("textures/gui/food_book.png"),new Rectangle(1,1,147,182),147,182,400,256);
    ImageData heartImage = new ImageData(SOLMaid.resourceLocation("textures/gui/food_book.png"),new Rectangle(3,227,9,9),9,9,400,256);
    ImageData armorImage = new ImageData(ResourceLocation.fromNamespaceAndPath("minecraft","textures/gui/sprites/hud/armor_full.png"),new Rectangle(0,0,9,9),9,9,9,9);
    ImageData armorToughnessImage = new ImageData(SOLMaid.resourceLocation("textures/gui/armor_toughness.png"),new Rectangle(0,0,9,9),9,9,9,9);
    ImageData potatoChipImage = new ImageData(SOLMaid.resourceLocation("textures/gui/food_book.png"),new Rectangle(128,240,16,16),16,16,400,256);
    ImageData watermelonImage = new ImageData(SOLMaid.resourceLocation("textures/gui/food_book.png"),new Rectangle(96,240,16,16),16,16,400,256);
    ImageData appleStickImage = new ImageData(SOLMaid.resourceLocation("textures/gui/food_book.png"),new Rectangle(112,240,16,16),16,16,400,256);
    ImageData attackDamageImage = new ImageData(SOLMaid.resourceLocation("textures/gui/icons.png"),new Rectangle(3,3,9,9),9,9,128,64);
}
