/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.Aspect
 */
package thaumic.tinkerer.client.gui.button;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumic.tinkerer.client.core.helper.ClientHelper;
import thaumic.tinkerer.client.gui.GuiEnchanting;
import thaumic.tinkerer.client.gui.button.GuiButtonFramedEnchantment;
import thaumic.tinkerer.common.enchantment.core.EnchantmentData;
import thaumic.tinkerer.common.enchantment.core.EnchantmentManager;

public class GuiButtonEnchantment
extends GuiButton {
    public Enchantment enchant;
    GuiEnchanting parent;

    public GuiButtonEnchantment(GuiEnchanting parent, int par1, int par2, int par3) {
        super(par1, par2, par3, 16, 16, "");
        this.parent = parent;
    }

    boolean dontRender() {
        return this.enchant == null || !this.field_146124_l || !EnchantmentManager.enchantmentData.containsKey(this.enchant.field_77352_x);
    }

    public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
        if (this.dontRender()) {
            return;
        }
        EnchantmentData data = EnchantmentManager.enchantmentData.get(this.enchant.field_77352_x).get(1);
        ClientHelper.minecraft().field_71446_o.func_110577_a(data.texture);
        GL11.glEnable((int)3042);
        this.drawTexturedModalRect16(this.field_146128_h, this.field_146129_i, 0, 0, 16, 16);
        GL11.glDisable((int)3042);
        if (par2 >= this.field_146128_h && par2 < this.field_146128_h + 16 && par3 >= this.field_146129_i && par3 < this.field_146129_i + 16) {
            ArrayList<String> tooltip = new ArrayList<String>();
            tooltip.add(EnumChatFormatting.AQUA + StatCollector.func_74838_a((String)this.enchant.func_77320_a()));
            for (Aspect aspect : data.aspects.getAspectsSorted()) {
                tooltip.add(" \u00a7" + aspect.getChatcolor() + aspect.getName() + '\u00a7' + "r x " + data.aspects.getAmount(aspect) + " " + StatCollector.func_74838_a((String)"ttmisc.baseCost"));
            }
            if (this instanceof GuiButtonFramedEnchantment && !this.parent.enchanter.working) {
                tooltip.add(EnumChatFormatting.GRAY + "" + EnumChatFormatting.ITALIC + " " + StatCollector.func_74838_a((String)"ttmisc.clickToRemove"));
            }
            this.parent.tooltip = tooltip;
        }
    }

    public void drawTexturedModalRect16(int par1, int par2, int par3, int par4, int par5, int par6) {
        float f = 0.0625f;
        float f1 = 0.0625f;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a((double)par1, (double)(par2 + par6), (double)this.field_73735_i, (double)((float)par3 * f), (double)((float)(par4 + par6) * f1));
        tessellator.func_78374_a((double)(par1 + par5), (double)(par2 + par6), (double)this.field_73735_i, (double)((float)(par3 + par5) * f), (double)((float)(par4 + par6) * f1));
        tessellator.func_78374_a((double)(par1 + par5), (double)par2, (double)this.field_73735_i, (double)((float)(par3 + par5) * f), (double)((float)par4 * f1));
        tessellator.func_78374_a((double)par1, (double)par2, (double)this.field_73735_i, (double)((float)par3 * f), (double)((float)par4 * f1));
        tessellator.func_78381_a();
    }
}

