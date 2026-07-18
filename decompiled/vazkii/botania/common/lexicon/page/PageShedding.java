/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.lexicon.page;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.LexiconRecipeMappings;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.client.gui.lexicon.GuiLexiconEntry;
import vazkii.botania.common.lexicon.page.PageEntity;

public class PageShedding
extends PageEntity {
    private static final ResourceLocation sheddingOverlay = new ResourceLocation("botania:textures/gui/sheddingOverlay.png");
    ItemStack shedStack;
    ItemStack tooltipStack;
    boolean tooltipEntry;
    static boolean mouseDownLastTick = false;

    public PageShedding(String unlocalizedName, String entity, int size, ItemStack shedStack) {
        super(unlocalizedName, entity, size);
        this.shedStack = shedStack;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void renderScreen(IGuiLexiconEntry gui, int mx, int my) {
        this.prepDummy();
        this.relativeMouseX = mx;
        this.relativeMouseY = my;
        int stack_x = gui.getLeft() + gui.getWidth() / 2 - 8;
        int stack_y = gui.getTop() + gui.getHeight() - 40 - 18 - 5;
        int entity_scale = this.getEntityScale(this.size);
        int entity_x = gui.getLeft() + gui.getWidth() / 2;
        int entity_y = gui.getTop() + gui.getHeight() / 2 + MathHelper.func_76141_d((float)(this.dummyEntity.field_70131_O * (float)entity_scale / 2.0f)) - 29;
        this.renderEntity(gui, this.dummyEntity, entity_x, entity_y, entity_scale, this.dummyEntity.field_70173_aa * 2);
        this.renderItem(gui, stack_x, stack_y, this.shedStack);
        TextureManager render = Minecraft.func_71410_x().field_71446_o;
        render.func_110577_a(sheddingOverlay);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ((GuiScreen)gui).func_73729_b(gui.getLeft(), gui.getTop(), 0, 0, gui.getWidth(), gui.getHeight());
        if (this.tooltipStack != null) {
            List tooltipData = this.tooltipStack.func_82840_a((EntityPlayer)Minecraft.func_71410_x().field_71439_g, false);
            ArrayList<String> parsedTooltip = new ArrayList<String>();
            boolean first = true;
            Iterator iterator = tooltipData.iterator();
            while (iterator.hasNext()) {
                String s;
                String s_ = s = (String)iterator.next();
                if (!first) {
                    s_ = EnumChatFormatting.GRAY + s;
                }
                parsedTooltip.add(s_);
                first = false;
            }
            RenderHelper.renderTooltip(mx, my, parsedTooltip);
            int tooltipY = 8 + tooltipData.size() * 11;
            if (this.tooltipEntry) {
                RenderHelper.renderTooltipOrange(mx, my + tooltipY, Arrays.asList(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"botaniamisc.clickToRecipe")));
                tooltipY += 18;
            }
        } else if (this.tooltipEntity) {
            ArrayList<String> parsedTooltip = new ArrayList<String>();
            parsedTooltip.add(EntityList.func_75621_b((Entity)this.dummyEntity));
            RenderHelper.renderTooltip(mx, my, parsedTooltip);
        }
        this.tooltipStack = null;
        this.tooltipEntity = false;
        this.tooltipEntry = false;
        GL11.glDisable((int)3042);
        mouseDownLastTick = Mouse.isButtonDown((int)0);
    }

    @SideOnly(value=Side.CLIENT)
    public void renderItem(IGuiLexiconEntry gui, int xPos, int yPos, ItemStack stack) {
        RenderItem render = new RenderItem();
        boolean mouseDown = Mouse.isButtonDown((int)0);
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        net.minecraft.client.renderer.RenderHelper.func_74520_c();
        GL11.glEnable((int)32826);
        GL11.glEnable((int)2929);
        render.func_82406_b(Minecraft.func_71410_x().field_71466_p, Minecraft.func_71410_x().func_110434_K(), stack, xPos, yPos);
        render.func_77021_b(Minecraft.func_71410_x().field_71466_p, Minecraft.func_71410_x().func_110434_K(), stack, xPos, yPos);
        net.minecraft.client.renderer.RenderHelper.func_74518_a();
        GL11.glPopMatrix();
        if (this.relativeMouseX >= xPos && this.relativeMouseY >= yPos && this.relativeMouseX <= xPos + 16 && this.relativeMouseY <= yPos + 16) {
            this.tooltipStack = stack;
            LexiconRecipeMappings.EntryData data = LexiconRecipeMappings.getDataForStack(this.tooltipStack);
            if (data != null && (data.entry != gui.getEntry() || data.page != gui.getPageOn())) {
                this.tooltipEntry = true;
                if (!mouseDownLastTick && mouseDown && GuiScreen.func_146272_n()) {
                    GuiLexiconEntry newGui = new GuiLexiconEntry(data.entry, (GuiScreen)gui);
                    newGui.page = data.page;
                    Minecraft.func_71410_x().func_147108_a((GuiScreen)newGui);
                }
            }
        }
        GL11.glDisable((int)2896);
    }

    @Override
    public List<ItemStack> getDisplayedRecipes() {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        list.add(this.shedStack);
        return list;
    }
}

