/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.models.gear;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ModelWand
extends ModelBase {
    ModelRenderer Rod;
    ModelRenderer Focus;
    ModelRenderer Cap;
    ModelRenderer CapBottom;
    private final RenderBlocks renderBlocks = new RenderBlocks();

    public ModelWand() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.Cap = new ModelRenderer((ModelBase)this, 0, 0);
        this.Cap.func_78789_a(-1.0f, -1.0f, -1.0f, 2, 2, 2);
        this.Cap.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Cap.func_78787_b(64, 32);
        this.Cap.field_78809_i = true;
        this.setRotation(this.Cap, 0.0f, 0.0f, 0.0f);
        this.CapBottom = new ModelRenderer((ModelBase)this, 0, 0);
        this.CapBottom.func_78789_a(-1.0f, -1.0f, -1.0f, 2, 2, 2);
        this.CapBottom.func_78793_a(0.0f, 20.0f, 0.0f);
        this.CapBottom.func_78787_b(64, 32);
        this.CapBottom.field_78809_i = true;
        this.setRotation(this.CapBottom, 0.0f, 0.0f, 0.0f);
        this.Rod = new ModelRenderer((ModelBase)this, 0, 8);
        this.Rod.func_78789_a(-1.0f, -1.0f, -1.0f, 2, 18, 2);
        this.Rod.func_78793_a(0.0f, 2.0f, 0.0f);
        this.Rod.func_78787_b(64, 32);
        this.Rod.field_78809_i = true;
        this.setRotation(this.Rod, 0.0f, 0.0f, 0.0f);
        this.Focus = new ModelRenderer((ModelBase)this, 0, 0);
        this.Focus.func_78789_a(-3.0f, -6.0f, -3.0f, 6, 6, 6);
        this.Focus.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Focus.func_78787_b(64, 32);
        this.Focus.field_78809_i = true;
        this.setRotation(this.Focus, 0.0f, 0.0f, 0.0f);
    }

    public void render(ItemStack wandStack) {
        int rot;
        int l;
        if (RenderManager.field_78727_a.field_78724_e == null) {
            return;
        }
        ItemWandCasting wand = (ItemWandCasting)wandStack.func_77973_b();
        ItemStack focusStack = wand.getFocusItem(wandStack);
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        boolean staff = wand.isStaff(wandStack);
        boolean runes = wand.hasRunes(wandStack);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(wand.getRod(wandStack).getTexture());
        GL11.glPushMatrix();
        if (staff) {
            GL11.glTranslated((double)0.0, (double)0.2, (double)0.0);
        }
        GL11.glPushMatrix();
        if (wand.getRod(wandStack).isGlowing()) {
            int j = (int)(200.0f + MathHelper.func_76126_a((float)player.field_70173_aa) * 5.0f + 5.0f);
            int k = j % 65536;
            l = j / 65536;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
        }
        if (staff) {
            GL11.glTranslated((double)0.0, (double)-0.1, (double)0.0);
            GL11.glScaled((double)1.2, (double)2.0, (double)1.2);
        }
        this.Rod.func_78785_a(0.0625f);
        if (wand.getRod(wandStack).isGlowing()) {
            int i = player.func_70070_b(0.0f);
            int j = i % 65536;
            int k = i / 65536;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)j / 1.0f), (float)((float)k / 1.0f));
        }
        GL11.glPopMatrix();
        Minecraft.func_71410_x().field_71446_o.func_110577_a(wand.getCap(wandStack).getTexture());
        GL11.glPushMatrix();
        if (staff) {
            GL11.glScaled((double)1.3, (double)1.1, (double)1.3);
        } else {
            GL11.glScaled((double)1.2, (double)1.0, (double)1.2);
        }
        if (wand.isSceptre(wandStack)) {
            GL11.glPushMatrix();
            GL11.glScaled((double)1.3, (double)1.3, (double)1.3);
            this.Cap.func_78785_a(0.0625f);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated((double)0.0, (double)0.3, (double)0.0);
            GL11.glScaled((double)1.0, (double)0.66, (double)1.0);
            this.Cap.func_78785_a(0.0625f);
            GL11.glPopMatrix();
        } else {
            this.Cap.func_78785_a(0.0625f);
        }
        if (staff) {
            GL11.glTranslated((double)0.0, (double)0.225, (double)0.0);
            GL11.glPushMatrix();
            GL11.glScaled((double)1.0, (double)0.66, (double)1.0);
            this.Cap.func_78785_a(0.0625f);
            GL11.glPopMatrix();
            GL11.glTranslated((double)0.0, (double)0.65, (double)0.0);
        }
        this.CapBottom.func_78785_a(0.0625f);
        GL11.glPopMatrix();
        if (wand.getFocus(wandStack) != null) {
            if (wand.getFocus(wandStack).getOrnament(focusStack) != null) {
                GL11.glPushMatrix();
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                Tessellator tessellator = Tessellator.field_78398_a;
                IIcon icon = wand.getFocus(wandStack).getOrnament(focusStack);
                float f1 = icon.func_94212_f();
                float f2 = icon.func_94206_g();
                float f3 = icon.func_94209_e();
                float f4 = icon.func_94210_h();
                RenderManager.field_78727_a.field_78724_e.func_110577_a(TextureMap.field_110576_c);
                GL11.glPushMatrix();
                GL11.glTranslatef((float)-0.25f, (float)-0.1f, (float)0.0275f);
                GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
                ItemRenderer.func_78439_a((Tessellator)tessellator, (float)f1, (float)f2, (float)f3, (float)f4, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.1f);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.25f, (float)-0.1f, (float)0.0275f);
                GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
                ItemRenderer.func_78439_a((Tessellator)tessellator, (float)f1, (float)f2, (float)f3, (float)f4, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.1f);
                GL11.glPopMatrix();
                GL11.glPopMatrix();
            }
            float alpha = 0.95f;
            if (wand.getFocus(wandStack).getFocusDepthLayerIcon(focusStack) != null) {
                GL11.glPushMatrix();
                if (staff) {
                    GL11.glTranslatef((float)0.0f, (float)-0.15f, (float)0.0f);
                    GL11.glScaled((double)0.165, (double)0.1765, (double)0.165);
                } else {
                    GL11.glTranslatef((float)0.0f, (float)-0.09f, (float)0.0f);
                    GL11.glScaled((double)0.16, (double)0.16, (double)0.16);
                }
                Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
                this.renderBlocks.func_147775_a(Blocks.field_150348_b);
                BlockRenderer.drawFaces(this.renderBlocks, null, wand.getFocus(wandStack).getFocusDepthLayerIcon(focusStack), false);
                GL11.glPopMatrix();
                alpha = 0.6f;
            }
            if (Thaumcraft.isHalloween) {
                UtilsFX.bindTexture("textures/models/spec_h.png");
            } else {
                UtilsFX.bindTexture("textures/models/wand.png");
            }
            GL11.glPushMatrix();
            if (staff) {
                GL11.glTranslatef((float)0.0f, (float)-0.0475f, (float)0.0f);
                GL11.glScaled((double)0.525, (double)0.5525, (double)0.525);
            } else {
                GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
            }
            Color c = new Color(wand.getFocus(wandStack).getFocusColor(focusStack));
            GL11.glColor4f((float)((float)c.getRed() / 255.0f), (float)((float)c.getGreen() / 255.0f), (float)((float)c.getBlue() / 255.0f), (float)alpha);
            int j = (int)(195.0f + MathHelper.func_76126_a((float)((float)player.field_70173_aa / 3.0f)) * 10.0f + 10.0f);
            int k = j % 65536;
            int l2 = j / 65536;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l2 / 1.0f));
            this.Focus.func_78785_a(0.0625f);
            GL11.glPopMatrix();
        }
        if (wand.isSceptre(wandStack)) {
            GL11.glPushMatrix();
            int j = 200;
            int k = j % 65536;
            l = j / 65536;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
            GL11.glBlendFunc((int)770, (int)1);
            for (rot = 0; rot < 10; ++rot) {
                GL11.glPushMatrix();
                GL11.glRotated((double)(36 * rot + player.field_70173_aa), (double)0.0, (double)1.0, (double)0.0);
                this.drawRune(0.16, -0.01f, -0.125, rot, (EntityPlayer)player);
                GL11.glPopMatrix();
            }
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glPopMatrix();
        }
        if (runes) {
            GL11.glPushMatrix();
            int j = 200;
            int k = j % 65536;
            l = j / 65536;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
            GL11.glBlendFunc((int)770, (int)1);
            for (rot = 0; rot < 4; ++rot) {
                GL11.glRotated((double)90.0, (double)0.0, (double)1.0, (double)0.0);
                for (int a = 0; a < 14; ++a) {
                    int rune = (a + rot * 3) % 16;
                    this.drawRune(0.36 + (double)a * 0.14, -0.01f, -0.08, rune, (EntityPlayer)player);
                }
            }
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }

    private void drawRune(double x, double y, double z, int rune, EntityPlayer player) {
        GL11.glPushMatrix();
        UtilsFX.bindTexture("textures/misc/script.png");
        float r = MathHelper.func_76126_a((float)((float)(player.field_70173_aa + rune * 5) / 5.0f)) * 0.1f + 0.88f;
        float g = MathHelper.func_76126_a((float)((float)(player.field_70173_aa + rune * 5) / 7.0f)) * 0.1f + 0.63f;
        float alpha = MathHelper.func_76126_a((float)((float)(player.field_70173_aa + rune * 5) / 10.0f)) * 0.2f;
        GL11.glColor4f((float)r, (float)g, (float)0.2f, (float)(alpha + 0.6f));
        GL11.glRotated((double)90.0, (double)0.0, (double)0.0, (double)1.0);
        GL11.glTranslated((double)x, (double)y, (double)z);
        Tessellator tessellator = Tessellator.field_78398_a;
        float var8 = 0.0625f * (float)rune;
        float var9 = var8 + 0.0625f;
        float var10 = 0.0f;
        float var11 = 1.0f;
        tessellator.func_78382_b();
        tessellator.func_78369_a(r, g, 0.2f, alpha + 0.6f);
        tessellator.func_78374_a(-0.06 - (double)(alpha / 40.0f), 0.06 + (double)(alpha / 40.0f), 0.0, (double)var9, (double)var11);
        tessellator.func_78374_a(0.06 + (double)(alpha / 40.0f), 0.06 + (double)(alpha / 40.0f), 0.0, (double)var9, (double)var10);
        tessellator.func_78374_a(0.06 + (double)(alpha / 40.0f), -0.06 - (double)(alpha / 40.0f), 0.0, (double)var8, (double)var10);
        tessellator.func_78374_a(-0.06 - (double)(alpha / 40.0f), -0.06 - (double)(alpha / 40.0f), 0.0, (double)var8, (double)var11);
        tessellator.func_78381_a();
        GL11.glPopMatrix();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, null);
    }
}

