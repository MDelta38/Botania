/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.gui.lexicon.button;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.client.gui.lexicon.button.GuiButtonLexicon;
import vazkii.botania.common.item.ModItems;

public class GuiButtonDoot
extends GuiButtonLexicon {
    public GuiButtonDoot(int id, int x, int y) {
        super(id, x, y, 16, 16, "");
    }

    public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
        this.field_146123_n = par2 >= this.field_146128_h && par3 >= this.field_146129_i && par2 < this.field_146128_h + this.field_146120_f && par3 < this.field_146129_i + this.field_146121_g;
        int k = this.func_146114_a(this.field_146123_n);
        par1Minecraft.field_71446_o.func_110577_a(TextureMap.field_110576_c);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderItem.getInstance().func_77015_a(par1Minecraft.field_71466_p, par1Minecraft.field_71446_o, new ItemStack(ModItems.cacophonium), this.field_146128_h, this.field_146129_i);
        RenderItem.getInstance().func_77015_a(par1Minecraft.field_71466_p, par1Minecraft.field_71446_o, new ItemStack(Items.field_151152_bP), this.field_146128_h + 8, this.field_146129_i + 2);
        GL11.glDisable((int)2896);
        ArrayList<String> tooltip = new ArrayList<String>();
        tooltip.add(EnumChatFormatting.LIGHT_PURPLE + "Happy Birthday Vazkii!");
        tooltip.add(EnumChatFormatting.GRAY + "doot doot");
        if (k == 2) {
            RenderHelper.renderTooltip(this.field_146128_h - 100, this.field_146129_i + 36, tooltip);
        }
        GL11.glEnable((int)3008);
    }
}

