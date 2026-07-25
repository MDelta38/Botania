/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 */
package thaumic.tinkerer.client.gui.button;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import thaumic.tinkerer.client.core.helper.ClientHelper;
import thaumic.tinkerer.client.gui.GuiEnchanting;
import thaumic.tinkerer.common.block.tile.TileEnchanter;

public class GuiButtonEnchant
extends GuiButton {
    private static final ResourceLocation gui = new ResourceLocation("ttinkerer:textures/gui/enchanter.png");
    TileEnchanter enchanter;
    GuiEnchanting parent;

    public GuiButtonEnchant(GuiEnchanting parent, TileEnchanter enchanter, int par1, int par2, int par3) {
        super(par1, par2, par3, 15, 15, "");
        this.enchanter = enchanter;
        this.parent = parent;
    }

    public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
        if (!this.field_146124_l) {
            return;
        }
        int x = 176;
        int y = this.enchanter.working ? 39 : 24;
        ClientHelper.minecraft().field_71446_o.func_110577_a(gui);
        this.func_73729_b(this.field_146128_h, this.field_146129_i, 176, y, 15, 15);
        if (par2 >= this.field_146128_h && par2 < this.field_146128_h + 15 && par3 >= this.field_146129_i && par3 < this.field_146129_i + 15 && !this.enchanter.working) {
            ArrayList<String> tooltip = new ArrayList<String>();
            tooltip.add(EnumChatFormatting.AQUA + StatCollector.func_74838_a((String)"ttmisc.startEnchant"));
            this.parent.tooltip = tooltip;
        }
    }
}

