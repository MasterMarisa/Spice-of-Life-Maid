package com.mastermarisa.solmaid.client.gui.screen;

import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.mastermarisa.solmaid.client.gui.UIConst;
import com.mastermarisa.solmaid.client.gui.elements.UIElement;
import com.mastermarisa.solmaid.client.gui.elements.UILabel;
import com.mastermarisa.solmaid.client.gui.screen.elements.FoodViewPage;
import com.mastermarisa.solmaid.client.gui.screen.elements.PageFlipButton;
import com.mastermarisa.solmaid.client.gui.screen.elements.TotalViewPage;
import com.mastermarisa.solmaid.client.gui.screen.elements.UIPage;
import com.mastermarisa.solmaid.data.FoodRecord;
import com.mastermarisa.solmaid.utils.FilterHelper;
import com.mastermarisa.solmaid.utils.ItemHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class FoodListScreen extends Screen implements PageFlipButton.Pageable {
    private static final int itemSize = 16;
    private static final int startY = 40;
    private static final Minecraft mc = Minecraft.getInstance();
    private static final Font font;

    private final FoodRecord foodList;
    private final List<Item> allFoodList;

    private final List<UIPage> pages = new ArrayList<>();
    private UIPage curPage;

    private PageFlipButton prePageButton;
    private PageFlipButton nextPageButton;

    private UILabel UIPageNumber;

    static {
        font = mc.font;
    }

    private void initPages(){
        TotalViewPage totalViewPage = new TotalViewPage(net.minecraft.network.chat.Component.translatable("gui.solmaid.title.total_view").getString(),foodList, allFoodList.size());
        curPage = totalViewPage;
        pages.add(totalViewPage);

        List<Item> foods = foodList.toItems();
        List<FoodViewPage> eaten = generatePagesFromItemStacks(net.minecraft.network.chat.Component.translatable("gui.solmaid.title.tasted_food").getString(),foods.stream().map(ItemStack::new).toList());
        pages.addAll(eaten);
        List<FoodViewPage> notEaten = generatePagesFromItemStacks(net.minecraft.network.chat.Component.translatable("gui.solmaid.title.untried_food").getString(),allFoodList.stream().filter((o)-> !foods.contains(o)).map(ItemStack::new).toList());
        pages.addAll(notEaten);

        curPage.setCenterX(getScreenCenterX());
        curPage.setCenterY(getScreenCenterY());
        curPage.onSwitchedTo();
    }

    private void initButtons(){
        if (prePageButton != null) this.removeWidget(prePageButton);
        if (nextPageButton != null) this.removeWidget(nextPageButton);

        prePageButton = this.addRenderableWidget(new PageFlipButton(curPage.bg.getMinX() + 20,curPage.bg.getMaxY() - 23,this,-1));
        nextPageButton = this.addRenderableWidget(new PageFlipButton(curPage.bg.getMaxX() - 37,curPage.bg.getMaxY() - 23,this,1));
        updateButtonVisibility();
    }

    public FoodListScreen(EntityMaid maid){
        super(Component.empty());
        this.foodList = maid.getData(FoodRecord.TYPE);
        allFoodList = ItemHelper.allFoods.stream().filter(i -> FilterHelper.filter(new ItemStack(i))).toList();

        initPages();
        initButtons();

        UIPageNumber = new UILabel("1", UILabel.TextAlignment.CENTER, UIConst.lessBlack, false);
        resetUIPageNumber();
    }

    public static void open(EntityMaid maid) {
        Minecraft.getInstance().setScreen(new FoodListScreen(maid));
    }

    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.render(graphics, mouseX, mouseY, partialTicks);

        if (curPage != null) UIElement.render(graphics,curPage,mouseX,mouseY);
        UIElement.render(graphics,UIPageNumber,mouseX,mouseY);

        prePageButton.render(graphics,mouseX,mouseY,partialTicks);
        nextPageButton.render(graphics,mouseX,mouseY,partialTicks);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (scrollY > 0) {
            switchToPage(getCurrentPageNumber() - 1);
        } else if (scrollY < 0) {
            switchToPage(getCurrentPageNumber() + 1);
        }
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    @Override
    public void resize(Minecraft minecraft, int width, int height) {
        super.resize(minecraft, width, height);

        if (curPage != null){
            curPage.setCenterX(getScreenCenterX());
            curPage.setCenterY(getScreenCenterY());
            curPage.onResize();
        }
        initButtons();
        resetUIPageNumber();
    }

    public void resetUIPageNumber(){
        UIPageNumber.setWidth(107);
        UIPageNumber.setCenterX(getScreenCenterX());
        UIPageNumber.setMaxY(curPage.bg.getMaxY() - 13);
    }

    public List<FoodViewPage> generatePagesFromItemStacks(String header, java.util.List<ItemStack> itemStacks){
        List<FoodViewPage> foodViewPages = new ArrayList<>();
        for (int i = 0;i < (itemStacks.size() / FoodViewPage.itemsPerPage + 1);i++){
            if ((i + 1) * FoodViewPage.itemsPerPage <= itemStacks.size()){
                foodViewPages.add(new FoodViewPage(header,itemStacks.subList(i * FoodViewPage.itemsPerPage,(i + 1) * FoodViewPage.itemsPerPage)));
            } else {
                foodViewPages.add(new FoodViewPage(header,itemStacks.subList(i * FoodViewPage.itemsPerPage,itemStacks.size())));
            }
        }

        return foodViewPages;
    }

    public static int getScreenCenterX(){
        return mc.getWindow().getGuiScaledWidth() / 2;
    }

    public static int getScreenCenterY(){
        return mc.getWindow().getGuiScaledHeight() / 2;
    }

    private void updateButtonVisibility() {
        this.prePageButton.updateState();
        this.nextPageButton.updateState();
    }

    public void switchToPage(int pageNumber){
        if (isWithinRange(pageNumber)){
            curPage = pages.get(pageNumber);
            curPage.setCenter(getScreenCenterX(), getScreenCenterY());
            curPage.onSwitchedTo();
            UIPageNumber = new UILabel(String.valueOf(pageNumber + 1), UILabel.TextAlignment.CENTER, UIConst.lessBlack, false);
            resetUIPageNumber();
            updateButtonVisibility();
        }
    }

    public int getCurrentPageNumber() {
        return pages.indexOf(curPage);
    }

    public boolean isWithinRange(int pageNumber) {
        return pageNumber >= 0 && pageNumber < this.pages.size();
    }
}
