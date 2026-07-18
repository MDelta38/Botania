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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.ILexicon;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.api.lexicon.LexiconRecipeMappings;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.client.gui.lexicon.GuiLexiconEntry;
import vazkii.botania.common.lexicon.page.PageText;

public class PageRecipe
extends LexiconPage {
    int relativeMouseX;
    int relativeMouseY;
    ItemStack tooltipStack;
    ItemStack tooltipContainerStack;
    boolean tooltipEntry;
    static boolean mouseDownLastTick = false;

    public PageRecipe(String unlocalizedName) {
        super(unlocalizedName);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void renderScreen(IGuiLexiconEntry gui, int mx, int my) {
        this.relativeMouseX = mx;
        this.relativeMouseY = my;
        this.renderRecipe(gui, mx, my);
        int width = gui.getWidth() - 30;
        int height = gui.getHeight();
        int x = gui.getLeft() + 16;
        int y = gui.getTop() + height - 40;
        PageText.renderText(x, y, width, height, this.getUnlocalizedName());
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
            if (this.tooltipContainerStack != null) {
                RenderHelper.renderTooltipGreen(mx, my + tooltipY, Arrays.asList(EnumChatFormatting.AQUA + StatCollector.func_74838_a((String)"botaniamisc.craftingContainer"), this.tooltipContainerStack.func_82833_r()));
            }
        }
        this.tooltipContainerStack = null;
        this.tooltipStack = null;
        this.tooltipEntry = false;
        GL11.glDisable((int)3042);
        mouseDownLastTick = Mouse.isButtonDown((int)0);
    }

    @SideOnly(value=Side.CLIENT)
    public void renderRecipe(IGuiLexiconEntry gui, int mx, int my) {
    }

    @SideOnly(value=Side.CLIENT)
    public void renderItemAtAngle(IGuiLexiconEntry gui, float angle, ItemStack stack) {
        if (stack == null || stack.func_77973_b() == null) {
            return;
        }
        ItemStack workStack = stack.func_77946_l();
        if (workStack.func_77960_j() == Short.MAX_VALUE || workStack.func_77960_j() == -1) {
            workStack.func_77964_b(0);
        }
        int radius = 32;
        double xPos = (double)gui.getLeft() + Math.cos((double)(angle -= 90.0f) * Math.PI / 180.0) * (double)radius + (double)(gui.getWidth() / 2) - 8.0;
        double yPos = (double)gui.getTop() + Math.sin((double)angle * Math.PI / 180.0) * (double)radius + 53.0;
        this.renderItem(gui, xPos, yPos, workStack, false);
    }

    @SideOnly(value=Side.CLIENT)
    public void renderItemAtGridPos(IGuiLexiconEntry gui, int x, int y, ItemStack stack, boolean accountForContainer) {
        if (stack == null || stack.func_77973_b() == null) {
            return;
        }
        if ((stack = stack.func_77946_l()).func_77960_j() == Short.MAX_VALUE) {
            stack.func_77964_b(0);
        }
        int xPos = gui.getLeft() + x * 29 + 7 + (y == 0 && x == 3 ? 10 : 0);
        int yPos = gui.getTop() + y * 29 + 24 - (y == 0 ? 7 : 0);
        ItemStack stack1 = stack.func_77946_l();
        if (stack1.func_77960_j() == -1) {
            stack1.func_77964_b(0);
        }
        this.renderItem(gui, xPos, yPos, stack1, accountForContainer);
    }

    @SideOnly(value=Side.CLIENT)
    public void renderItem(IGuiLexiconEntry gui, double xPos, double yPos, ItemStack stack, boolean accountForContainer) {
        RenderItem render = new RenderItem();
        boolean mouseDown = Mouse.isButtonDown((int)0);
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        net.minecraft.client.renderer.RenderHelper.func_74520_c();
        GL11.glEnable((int)32826);
        GL11.glEnable((int)2929);
        GL11.glPushMatrix();
        GL11.glTranslated((double)xPos, (double)yPos, (double)0.0);
        render.func_82406_b(Minecraft.func_71410_x().field_71466_p, Minecraft.func_71410_x().func_110434_K(), stack, 0, 0);
        render.func_77021_b(Minecraft.func_71410_x().field_71466_p, Minecraft.func_71410_x().func_110434_K(), stack, 0, 0);
        GL11.glPopMatrix();
        net.minecraft.client.renderer.RenderHelper.func_74518_a();
        GL11.glPopMatrix();
        int xpi = (int)xPos;
        int ypi = (int)yPos;
        if (this.relativeMouseX >= xpi && this.relativeMouseY >= ypi && this.relativeMouseX <= xpi + 16 && this.relativeMouseY <= ypi + 16) {
            ItemStack containerStack;
            this.tooltipStack = stack;
            LexiconRecipeMappings.EntryData data = LexiconRecipeMappings.getDataForStack(this.tooltipStack);
            ItemStack book = Minecraft.func_71410_x().field_71439_g.func_71045_bC();
            if (data != null && (data.entry != gui.getEntry() || data.page != gui.getPageOn()) && book != null && book.func_77973_b() instanceof ILexicon && ((ILexicon)book.func_77973_b()).isKnowledgeUnlocked(book, data.entry.getKnowledgeType())) {
                this.tooltipEntry = true;
                if (!mouseDownLastTick && mouseDown && GuiScreen.func_146272_n()) {
                    GuiLexiconEntry newGui = new GuiLexiconEntry(data.entry, (GuiScreen)gui);
                    newGui.page = data.page;
                    Minecraft.func_71410_x().func_147108_a((GuiScreen)newGui);
                }
            } else {
                this.tooltipEntry = false;
            }
            if (accountForContainer && (containerStack = stack.func_77973_b().getContainerItem(stack)) != null && containerStack.func_77973_b() != null) {
                this.tooltipContainerStack = containerStack;
            }
        }
        GL11.glDisable((int)2896);
    }
}

