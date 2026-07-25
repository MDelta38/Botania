/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.boss.BossStatus
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.monster.EntityCultistCleric;
import thaumcraft.common.entities.monster.boss.EntityCultistLeader;

@SideOnly(value=Side.CLIENT)
public class RenderCultist
extends RenderBiped {
    private static final ResourceLocation skin = new ResourceLocation("thaumcraft", "textures/models/cultist.png");

    public RenderCultist() {
        super(new ModelBiped(), 0.5f);
    }

    protected ResourceLocation func_110775_a(EntityLiving p_110775_1_) {
        return skin;
    }

    protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) {
        if (par1EntityLiving instanceof EntityCultistLeader) {
            BossStatus.func_82824_a((IBossDisplayData)((EntityCultistLeader)par1EntityLiving), (boolean)false);
            GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
        }
    }

    public void func_76986_a(Entity entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        boolean rit;
        GL11.glPushMatrix();
        float bob = 0.0f;
        boolean bl = rit = entity instanceof EntityCultistCleric && ((EntityCultistCleric)entity).getIsRitualist();
        if (rit) {
            int val = new Random(entity.func_145782_y()).nextInt(1000);
            float c = (float)((EntityCultistCleric)entity).field_70173_aa + p_76986_9_ + (float)val;
            bob = MathHelper.func_76126_a((float)(c / 9.0f)) * 0.1f + 0.21f;
            GL11.glTranslated((double)0.0, (double)bob, (double)0.0);
        }
        super.func_76986_a(entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
        if (rit) {
            GL11.glPushMatrix();
            this.drawFloatyLine(entity.field_70165_t, entity.field_70163_u + (double)(entity.func_70047_e() * 1.2f), entity.field_70161_v, (double)((EntityCultistCleric)entity).func_110172_bL().field_71574_a + 0.5, (double)((EntityCultistCleric)entity).func_110172_bL().field_71572_b + 1.5 - (double)bob, (double)((EntityCultistCleric)entity).func_110172_bL().field_71573_c + 0.5, p_76986_9_, 0x110011, "textures/misc/wispy.png", -0.03f, (float)Math.min(((EntityCultistCleric)entity).field_70173_aa, 10) / 10.0f, 0.25f);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }

    private void drawFloatyLine(double x, double y, double z, double x2, double y2, double z2, float partialTicks, int color, String texture, float speed, float distance, float width) {
        float f13;
        double dz;
        double dy;
        double dx;
        float f3;
        float f2a;
        float f2;
        EntityLivingBase player = Minecraft.func_71410_x().field_71451_h;
        double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)partialTicks;
        double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)partialTicks;
        double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)partialTicks;
        double ePX = x2;
        double ePY = y2;
        double ePZ = z2;
        GL11.glTranslated((double)(-iPX + ePX), (double)(-iPY + ePY), (double)(-iPZ + ePZ));
        float time = System.nanoTime() / 30000000L;
        Color co = new Color(color);
        float r = (float)co.getRed() / 255.0f;
        float g = (float)co.getGreen() / 255.0f;
        float b = (float)co.getBlue() / 255.0f;
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        Tessellator tessellator = Tessellator.field_78398_a;
        double ds1x = ePX;
        double ds1y = ePY;
        double ds1z = ePZ;
        double dd1x = x;
        double dd1y = y;
        double dd1z = z;
        double dc1x = (float)(dd1x - ds1x);
        double dc1y = (float)(dd1y - ds1y);
        double dc1z = (float)(dd1z - ds1z);
        UtilsFX.bindTexture(texture);
        tessellator.func_78371_b(5);
        double dx2 = 0.0;
        double dy2 = 0.0;
        double dz2 = 0.0;
        double d3 = x - ePX;
        double d4 = y - ePY;
        double d5 = z - ePZ;
        float dist = MathHelper.func_76133_a((double)(d3 * d3 + d4 * d4 + d5 * d5));
        float blocks = Math.round(dist);
        float length = blocks * ((float)Config.golemLinkQuality / 2.0f);
        float f9 = 0.0f;
        float f10 = 1.0f;
        int i = 0;
        while ((float)i <= length * distance) {
            f2 = (float)i / length;
            f2a = (float)i * 1.5f / length;
            f2a = Math.min(0.75f, f2a);
            f3 = 1.0f - Math.abs((float)i - length / 2.0f) / (length / 2.0f);
            dx = dc1x + (double)(MathHelper.func_76126_a((float)((float)((z % 16.0 + (double)(dist * (1.0f - f2) * (float)Config.golemLinkQuality / 2.0f) - (double)(time % 32767.0f / 5.0f)) / 4.0))) * 0.5f * f3);
            dy = dc1y + (double)(MathHelper.func_76126_a((float)((float)((x % 16.0 + (double)(dist * (1.0f - f2) * (float)Config.golemLinkQuality / 2.0f) - (double)(time % 32767.0f / 5.0f)) / 3.0))) * 0.5f * f3);
            dz = dc1z + (double)(MathHelper.func_76126_a((float)((float)((y % 16.0 + (double)(dist * (1.0f - f2) * (float)Config.golemLinkQuality / 2.0f) - (double)(time % 32767.0f / 5.0f)) / 2.0))) * 0.5f * f3);
            tessellator.func_78369_a(r, g, b, 0.8f);
            f13 = (1.0f - f2) * dist - time * speed;
            tessellator.func_78374_a(dx * (double)f2, dy * (double)f2 - (double)width, dz * (double)f2, (double)f13, (double)f10);
            tessellator.func_78374_a(dx * (double)f2, dy * (double)f2 + (double)width, dz * (double)f2, (double)f13, (double)f9);
            ++i;
        }
        tessellator.func_78381_a();
        tessellator.func_78371_b(5);
        i = 0;
        while ((float)i <= length * distance) {
            f2 = (float)i / length;
            f2a = (float)i * 1.5f / length;
            f2a = Math.min(0.75f, f2a);
            f3 = 1.0f - Math.abs((float)i - length / 2.0f) / (length / 2.0f);
            dx = dc1x + (double)(MathHelper.func_76126_a((float)((float)((z % 16.0 + (double)(dist * (1.0f - f2) * (float)Config.golemLinkQuality / 2.0f) - (double)(time % 32767.0f / 5.0f)) / 4.0))) * 0.5f * f3);
            dy = dc1y + (double)(MathHelper.func_76126_a((float)((float)((x % 16.0 + (double)(dist * (1.0f - f2) * (float)Config.golemLinkQuality / 2.0f) - (double)(time % 32767.0f / 5.0f)) / 3.0))) * 0.5f * f3);
            dz = dc1z + (double)(MathHelper.func_76126_a((float)((float)((y % 16.0 + (double)(dist * (1.0f - f2) * (float)Config.golemLinkQuality / 2.0f) - (double)(time % 32767.0f / 5.0f)) / 2.0))) * 0.5f * f3);
            tessellator.func_78369_a(r, g, b, 0.8f);
            f13 = (1.0f - f2) * dist - time * speed;
            tessellator.func_78374_a(dx * (double)f2 - (double)width, dy * (double)f2, dz * (double)f2, (double)f13, (double)f10);
            tessellator.func_78374_a(dx * (double)f2 + (double)width, dy * (double)f2, dz * (double)f2, (double)f13, (double)f9);
            ++i;
        }
        tessellator.func_78381_a();
        GL11.glDisable((int)3042);
    }
}

