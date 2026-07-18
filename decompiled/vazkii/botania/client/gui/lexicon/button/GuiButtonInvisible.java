/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.gui.lexicon.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.handler.PersistentVariableHelper;
import vazkii.botania.client.core.helper.FontHelper;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.client.gui.lexicon.GuiLexiconIndex;
import vazkii.botania.client.gui.lexicon.button.GuiButtonLexicon;
import vazkii.botania.common.lib.LibMisc;

public class GuiButtonInvisible
extends GuiButtonLexicon {
    private static final ResourceLocation dogResource = new ResourceLocation("botania:textures/gui/dog.png");
    GuiLexiconIndex gui;
    public ItemStack displayStack = null;
    public boolean dog = false;
    float timeHover = 0.0f;
    boolean enableDog = false;
    double dogPos = 0.0;

    public GuiButtonInvisible(GuiLexiconIndex gui, int par1, int par2, int par3, int par4, int par5, String par6Str) {
        super(par1, par2, par3, par4, par5, par6Str);
        this.gui = gui;
    }

    public void click() {
        this.enableDog = true;
        PersistentVariableHelper.dog = true;
        PersistentVariableHelper.saveSafe();
    }

    public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
        boolean showStack;
        if (this.enableDog) {
            this.dogPos += (double)(ClientTickHandler.delta * 10.0f);
            par1Minecraft.field_71446_o.func_110577_a(dogResource);
            float f = 0.015625f;
            GL11.glTranslated((double)this.dogPos, (double)0.0, (double)0.0);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderHelper.drawTexturedModalRect(0, this.field_146129_i, this.field_73735_i + 10.0f, this.dogPos % 100.0 < 50.0 ? 23 : 0, 0, 23, 19, f, f);
            this.field_146128_h = (int)Math.max((double)this.field_146128_h, this.dogPos + 10.0);
            GL11.glTranslated((double)(-this.dogPos), (double)0.0, (double)0.0);
        }
        this.field_146123_n = par2 >= this.field_146128_h && par3 >= this.field_146129_i && par2 < this.field_146128_h + this.field_146120_f && par3 < this.field_146129_i + this.field_146121_g;
        int k = this.func_146114_a(this.field_146123_n);
        boolean bl = showStack = this.displayStack != null && !this.field_146126_j.isEmpty();
        if (!this.field_146126_j.isEmpty() && k == 2) {
            this.timeHover = Math.min(5.0f, this.timeHover + this.gui.timeDelta);
            this.gui.setHoveredButton(this);
        } else {
            this.timeHover = Math.max(0.0f, this.timeHover - this.gui.timeDelta);
        }
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)3008);
        int color = 0;
        String format = FontHelper.getFormatFromString(this.field_146126_j);
        if (format.length() > 1) {
            char key = format.charAt(format.length() - 1);
            if (key == 'o' && format.length() > 3) {
                key = format.charAt(1);
            }
            for (EnumChatFormatting ecf : (EnumChatFormatting[])EnumChatFormatting.class.getEnumConstants()) {
                if (ecf.func_96298_a() != key) continue;
                if (ecf.ordinal() > 15) {
                    ecf = EnumChatFormatting.BLACK;
                }
                color = LibMisc.CONTROL_CODE_COLORS[ecf.ordinal()];
                break;
            }
        }
        int maxalpha = 34;
        int alpha = Math.min(maxalpha, (int)(this.timeHover / 4.0f * (float)maxalpha));
        GuiButtonInvisible.func_73734_a((int)(this.field_146128_h - 5), (int)this.field_146129_i, (int)((int)((float)(this.field_146128_h - 5) + this.timeHover * 24.0f)), (int)(this.field_146129_i + this.field_146121_g), (int)(alpha << 24 | color));
        GL11.glEnable((int)3008);
        boolean unicode = par1Minecraft.field_71466_p.func_82883_a();
        par1Minecraft.field_71466_p.func_78264_a(true);
        par1Minecraft.field_71466_p.func_78276_b(this.field_146126_j, this.field_146128_h + (showStack ? 7 : 0), this.field_146129_i + (this.field_146121_g - 8) / 2, 0);
        par1Minecraft.field_71466_p.func_78264_a(unicode);
        if (showStack) {
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            net.minecraft.client.renderer.RenderHelper.func_74520_c();
            GL11.glEnable((int)32826);
            RenderItem.getInstance().func_77015_a(par1Minecraft.field_71466_p, par1Minecraft.field_71446_o, this.displayStack, this.field_146128_h * 2 - 6, this.field_146129_i * 2 + 4);
            net.minecraft.client.renderer.RenderHelper.func_74518_a();
            GL11.glEnable((int)3042);
        }
        GL11.glPopMatrix();
    }
}

