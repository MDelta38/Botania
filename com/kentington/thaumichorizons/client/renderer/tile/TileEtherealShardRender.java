/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.client.renderers.models.ModelCrystal
 */
package com.kentington.thaumichorizons.client.renderer.tile;

import com.kentington.thaumichorizons.common.tiles.TileSyntheticNode;
import java.awt.Color;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelCrystal;

public class TileEtherealShardRender
extends TileEntitySpecialRenderer {
    private ModelCrystal model = new ModelCrystal();
    static String tx1 = "textures/items/lightningringv.png";
    static String tx2 = "textures/misc/nodes.png";

    public void func_147500_a(TileEntity te, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        TileSyntheticNode tco = (TileSyntheticNode)te;
        int red = 255;
        int green = 255;
        int blue = 255;
        int numPoints = 0;
        int numPointsFilled = 0;
        if (tco != null && tco.getAspectsBase() != null && tco.getAspects() != null && tco.getAspects().size() > 0 && tco.getAspectsBase().size() > 0) {
            for (Aspect asp : tco.getAspectsBase().getAspects()) {
                int amt = tco.getAspectsBase().getAmount(asp);
                Color col = new Color(asp.getColor());
                red += col.getRed() * amt;
                green += col.getGreen() * amt;
                blue += col.getBlue() * amt;
                numPoints += amt;
                numPointsFilled += tco.getAspects().getAmount(asp);
            }
            red /= numPoints + 1;
            green /= numPoints + 1;
            blue /= numPoints + 1;
        }
        Color col = new Color(red, green, blue);
        UtilsFX.bindTexture((String)"textures/models/crystal.png");
        Random rand = new Random(tco.func_145832_p() + tco.field_145851_c + tco.field_145848_d * tco.field_145849_e);
        this.drawCrystal(0, (float)x, (float)y, (float)z, tco.rotation, 0.0f, rand, col.getRGB(), 1.1f);
        long nt = System.nanoTime();
        UtilsFX.bindTexture((String)tx2);
        int frames = 32;
        int i = (int)(((double)(nt / 40000000L) + x) % (double)frames);
        if (tco != null && tco.getAspectsBase() != null && tco.getAspects() != null && tco.getAspectsBase().size() > 0 && tco.getAspects().getAspects()[0] != null && tco.getAspectsBase().getAspects()[0] != null) {
            double offset = Math.PI * 2 / (double)tco.getAspectsBase().size();
            int which = 0;
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            GL11.glDepthMask((boolean)false);
            for (Aspect asp : tco.getAspectsBase().getAspects()) {
                if (asp == null) break;
                Color colo = new Color(asp.getColor());
                GL11.glPushMatrix();
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                double radian = Math.toRadians(tco.rotation);
                double dist = 0.4 + 0.1 * Math.cos(radian);
                UtilsFX.renderFacingStrip((double)((double)tco.field_145851_c + 0.5 + dist * Math.sin(2.0 * radian + offset * (double)which)), (double)((double)tco.field_145848_d + 0.64 + (double)0.1f * Math.sin(Math.toRadians(tco.rotation))), (double)((double)tco.field_145849_e + 0.5 + dist * Math.cos(2.0 * radian + offset * (double)which)), (float)0.0f, (float)(0.1f + 0.005f * (float)tco.getAspects().getAmount(asp)), (float)0.9f, (int)frames, (int)1, (int)((int)tco.rotation % frames), (float)f, (int)colo.getRGB());
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
                ++which;
            }
            GL11.glDepthMask((boolean)true);
            GL11.glAlphaFunc((int)516, (float)0.1f);
        }
        if (tco != null && tco.drainEntity != null && tco.drainCollision != null) {
            Entity drainEntity = tco.drainEntity;
            if (drainEntity instanceof EntityPlayer && !((EntityPlayer)drainEntity).func_71039_bw()) {
                tco.drainEntity = null;
                tco.drainCollision = null;
                return;
            }
            MovingObjectPosition drainCollision = tco.drainCollision;
            GL11.glPushMatrix();
            float f10 = 0.0f;
            int iiud = ((EntityPlayer)drainEntity).func_71057_bx();
            if (drainEntity instanceof EntityPlayer) {
                f10 = MathHelper.func_76126_a((float)((float)iiud / 10.0f)) * 10.0f;
            }
            Vec3 vec3 = Vec3.func_72443_a((double)-0.1, (double)-0.1, (double)0.5);
            vec3.func_72440_a(-(drainEntity.field_70127_C + (drainEntity.field_70125_A - drainEntity.field_70127_C) * f) * 3.141593f / 180.0f);
            vec3.func_72442_b(-(drainEntity.field_70126_B + (drainEntity.field_70177_z - drainEntity.field_70126_B) * f) * 3.141593f / 180.0f);
            vec3.func_72442_b(-f10 * 0.01f);
            vec3.func_72440_a(-f10 * 0.015f);
            double d3 = drainEntity.field_70169_q + (drainEntity.field_70165_t - drainEntity.field_70169_q) * (double)f + vec3.field_72450_a;
            double d4 = drainEntity.field_70167_r + (drainEntity.field_70163_u - drainEntity.field_70167_r) * (double)f + vec3.field_72448_b;
            double d5 = drainEntity.field_70166_s + (drainEntity.field_70161_v - drainEntity.field_70166_s) * (double)f + vec3.field_72449_c;
            double d6 = drainEntity == Minecraft.func_71410_x().field_71439_g ? 0.0 : (double)drainEntity.func_70047_e();
            UtilsFX.drawFloatyLine((double)d3, (double)(d4 + d6), (double)d5, (double)((double)drainCollision.field_72311_b + 0.5), (double)((double)drainCollision.field_72312_c + 0.5), (double)((double)drainCollision.field_72309_d + 0.5), (float)f, (int)tco.color.getRGB(), (String)"textures/misc/wispy.png", (float)-0.02f, (float)((float)Math.min(iiud, 10) / 10.0f));
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
    }

    private void drawCrystal(int ori, float x, float y, float z, float a1, float a2, Random rand, int color, float size) {
        EntityClientPlayerMP p = Minecraft.func_71410_x().field_71439_g;
        float shade = MathHelper.func_76126_a((float)((float)(p.field_70173_aa + rand.nextInt(10)) / (5.0f + rand.nextFloat()))) * 0.075f + 0.925f;
        Color c = new Color(color);
        float r = (float)c.getRed() / 220.0f;
        float g = (float)c.getGreen() / 220.0f;
        float b = (float)c.getBlue() / 220.0f;
        GL11.glPushMatrix();
        GL11.glEnable((int)2977);
        GL11.glEnable((int)3042);
        GL11.glEnable((int)32826);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glTranslatef((float)(x + 0.5f), (float)((float)((double)(y - 0.15f) + (double)0.1f * Math.sin(Math.toRadians(a1)))), (float)(z + 0.5f));
        GL11.glRotatef((float)a1, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)a2, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glScalef((float)((0.15f + rand.nextFloat() * 0.075f) * size), (float)((0.5f + rand.nextFloat() * 0.1f) * size), (float)((0.15f + rand.nextFloat() * 0.05f) * size));
        int var19 = (int)(210.0f * shade);
        int var20 = var19 % 65536;
        int var21 = var19 / 65536;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)var20 / 1.0f), (float)((float)var21 / 1.0f));
        GL11.glColor4f((float)r, (float)g, (float)b, (float)1.0f);
        this.model.render();
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)32826);
        GL11.glDisable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }
}

