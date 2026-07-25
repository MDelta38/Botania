/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 */
package thaumic.tinkerer.client.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import thaumic.tinkerer.client.core.helper.ClientHelper;
import thaumic.tinkerer.client.gui.GuiEnchanting;
import thaumic.tinkerer.client.gui.button.GuiButtonEnchantment;

public class GuiButtonFramedEnchantment
extends GuiButtonEnchantment {
    private static final ResourceLocation gui = new ResourceLocation("ttinkerer:textures/gui/enchanter.png");

    public GuiButtonFramedEnchantment(GuiEnchanting parent, int par1, int par2, int par3) {
        super(parent, par1, par2, par3);
    }

    @Override
    public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
        if (this.dontRender() || this.parent.enchanter.enchantments.isEmpty() || this.parent.enchanter.levels.isEmpty()) {
            return;
        }
        ClientHelper.minecraft().field_71446_o.func_110577_a(gui);
        this.func_73729_b(this.field_146128_h - 4, this.field_146129_i - 4, 176, 0, 24, 24);
        int index = this.parent.enchanter.enchantments.indexOf(this.enchant.field_77352_x);
        if (index != -1) {
            int level = this.parent.enchanter.levels.get(index);
            par1Minecraft.field_71466_p.func_78261_a(StatCollector.func_74838_a((String)("enchantment.level." + level)), this.field_146128_h + 26, this.field_146129_i + 8, 0xFFFFFF);
        }
        super.func_146112_a(par1Minecraft, par2, par3);
    }
}

