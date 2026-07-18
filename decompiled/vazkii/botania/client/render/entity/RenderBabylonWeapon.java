/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.entity;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.common.entity.EntityBabylonWeapon;
import vazkii.botania.common.item.relic.ItemKingKey;

public class RenderBabylonWeapon
extends Render {
    private static final ResourceLocation babylon = new ResourceLocation("botania:textures/misc/babylon.png");

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        EntityBabylonWeapon weapon = (EntityBabylonWeapon)par1Entity;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
        GL11.glRotatef((float)weapon.getRotation(), (float)0.0f, (float)1.0f, (float)0.0f);
        int live = weapon.getLiveTicks();
        int delay = weapon.getDelay();
        float charge = Math.min(10.0f, (float)Math.max(live, weapon.getChargeTicks()) + par9);
        float chargeMul = charge / 10.0f;
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
        GL11.glPushMatrix();
        float s = 1.5f;
        GL11.glScalef((float)s, (float)s, (float)s);
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)45.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        IIcon icon = ItemKingKey.weaponIcons[weapon.getVariety()];
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)chargeMul);
        float f = icon.func_94209_e();
        float f1 = icon.func_94212_f();
        float f2 = icon.func_94206_g();
        float f3 = icon.func_94210_h();
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)240.0f);
        GL11.glDisable((int)2896);
        ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
        GL11.glPopMatrix();
        GL11.glDisable((int)2884);
        GL11.glShadeModel((int)7425);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)chargeMul);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(babylon);
        Tessellator tes = Tessellator.field_78398_a;
        ShaderHelper.useShader(ShaderHelper.halo);
        Random rand = new Random(weapon.func_110124_au().getMostSignificantBits());
        GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)(-0.3f + rand.nextFloat() * 0.1f), (float)1.0f);
        s = chargeMul;
        if (live > delay) {
            s -= Math.min(1.0f, ((float)(live - delay) + par9) * 0.2f);
        }
        GL11.glScalef((float)(s *= 2.0f), (float)s, (float)s);
        GL11.glRotatef((float)(charge * 9.0f + ((float)weapon.field_70173_aa + par9) * 0.5f + rand.nextFloat() * 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        tes.func_78382_b();
        tes.func_78374_a(-1.0, 0.0, -1.0, 0.0, 0.0);
        tes.func_78374_a(-1.0, 0.0, 1.0, 0.0, 1.0);
        tes.func_78374_a(1.0, 0.0, 1.0, 1.0, 1.0);
        tes.func_78374_a(1.0, 0.0, -1.0, 1.0, 0.0);
        tes.func_78381_a();
        ShaderHelper.releaseShader();
        GL11.glEnable((int)2896);
        GL11.glShadeModel((int)7424);
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_) {
        return null;
    }
}

