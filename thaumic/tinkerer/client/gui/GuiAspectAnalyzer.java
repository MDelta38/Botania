/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.lib.crafting.ThaumcraftCraftingManager
 *  thaumcraft.common.lib.research.ScanManager
 */
package thaumic.tinkerer.client.gui;

import java.util.Arrays;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.research.ScanManager;
import thaumic.tinkerer.client.core.helper.ClientHelper;
import thaumic.tinkerer.common.block.tile.TileAspectAnalyzer;
import thaumic.tinkerer.common.block.tile.container.ContainerAspectAnalyzer;

public class GuiAspectAnalyzer
extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("ttinkerer:textures/gui/aspectAnalyzer.png");
    int x;
    int y;
    TileAspectAnalyzer analyzer;
    Aspect aspectHovered = null;

    public GuiAspectAnalyzer(TileAspectAnalyzer analyzer, InventoryPlayer inv) {
        super((Container)new ContainerAspectAnalyzer(analyzer, inv));
        this.analyzer = analyzer;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.x = (this.field_146294_l - this.field_146999_f) / 2;
        this.y = (this.field_146295_m - this.field_147000_g) / 2;
    }

    protected void func_146976_a(float f, int mx, int my) {
        this.aspectHovered = null;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(gui);
        this.func_73729_b(this.x, this.y, 0, 0, this.field_146999_f, this.field_147000_g);
        ItemStack stack = this.analyzer.func_70301_a(0);
        if (stack != null) {
            int h = ScanManager.generateItemHash((Item)stack.func_77973_b(), (int)stack.func_77960_j());
            List list = (List)Thaumcraft.proxy.getScannedObjects().get(ClientHelper.clientPlayer().func_146103_bH().getName());
            if (list != null && (list.contains("@" + h) || list.contains("#" + h))) {
                AspectList tags = ThaumcraftCraftingManager.getObjectTags((ItemStack)stack);
                if ((tags = ThaumcraftCraftingManager.getBonusTags((ItemStack)stack, (AspectList)tags)) != null) {
                    int i = 0;
                    for (Aspect aspect : tags.getAspectsSortedAmount()) {
                        int x = this.x + 20 + i * 18;
                        int y = this.y + 58;
                        UtilsFX.drawTag((int)x, (int)y, (Aspect)aspect, (float)tags.getAmount(aspect), (int)0, (double)this.field_73735_i);
                        if (mx > x && mx < x + 16 && my > y && my < y + 16) {
                            this.aspectHovered = aspect;
                        }
                        ++i;
                    }
                }
            }
        }
    }

    protected void func_146979_b(int mx, int my) {
        if (this.aspectHovered != null) {
            ClientHelper.renderTooltip(mx - this.x, my - this.y, Arrays.asList(EnumChatFormatting.AQUA + this.aspectHovered.getName(), EnumChatFormatting.GRAY + this.aspectHovered.getLocalizedDescription()));
        }
        super.func_146979_b(mx, my);
    }
}

