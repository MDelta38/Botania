/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Vec3
 *  net.minecraftforge.client.model.AdvancedModelLoader
 *  net.minecraftforge.client.model.IModelCustom
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 */
package com.kentington.thaumichorizons.client.renderer.tile;

import com.kentington.thaumichorizons.client.renderer.model.ModelQuarterBlock;
import com.kentington.thaumichorizons.common.tiles.TileVisDynamo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class TileVisDynamoRender
extends TileEntitySpecialRenderer {
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)SCANNER);
    private static final ResourceLocation SCANNER = new ResourceLocation("thaumcraft", "textures/models/scanner.obj");
    static String tx1 = "textures/models/goldring.png";
    static String tx2 = "textures/models/dynamobase.png";
    static String tx3 = "textures/items/lightningringv.png";
    private ModelQuarterBlock base = new ModelQuarterBlock();

    public void func_147500_a(TileEntity te, double x, double y, double z, float f) {
        TileVisDynamo tco = (TileVisDynamo)te;
        if (tco.rise >= 0.3f && tco.ticksProvided > 0) {
            GL11.glPushMatrix();
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)1);
            long nt = System.nanoTime();
            UtilsFX.bindTexture((String)tx3);
            int frames = UtilsFX.getTextureAnimationSize((String)tx3);
            int i = (int)(((double)(nt / 40000000L) + x) % (double)frames);
            UtilsFX.renderFacingQuad((double)((double)tco.field_145851_c + 0.5), (double)((float)tco.field_145848_d + 0.5f), (double)((double)tco.field_145849_e + 0.5), (float)0.0f, (float)0.2f, (float)0.9f, (int)frames, (int)i, (float)f, (int)tco.color.getRGB());
            GL11.glDisable((int)3042);
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glPopMatrix();
        }
        if (tco.drainEntity != null && tco.drainCollision != null) {
            Entity drainEntity = tco.drainEntity;
            if (drainEntity instanceof EntityPlayer && !((EntityPlayer)drainEntity).func_71039_bw()) {
                tco.drainEntity = null;
                tco.drainCollision = null;
            } else {
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
                UtilsFX.drawFloatyLine((double)((double)drainCollision.field_72311_b + 0.5), (double)((double)drainCollision.field_72312_c + 0.5), (double)((double)drainCollision.field_72309_d + 0.5), (double)d3, (double)(d4 + d6), (double)d5, (float)f, (int)tco.color.getRGB(), (String)"textures/misc/wispy.png", (float)-0.02f, (float)((float)Math.min(iiud, 10) / 10.0f));
                GL11.glPopMatrix();
            }
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
        UtilsFX.bindTexture((String)"thaumichorizons", (String)tx2);
        this.base.render();
        GL11.glTranslatef((float)0.5f, (float)(0.2f + tco.rise), (float)0.5f);
        GL11.glRotatef((float)tco.rotation, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)tco.rotation2, (float)1.0f, (float)0.0f, (float)0.0f);
        UtilsFX.bindTexture((String)"thaumichorizons", (String)tx1);
        GL11.glScalef((float)0.36f, (float)0.36f, (float)0.36f);
        this.model.renderAll();
        GL11.glRotatef((float)(-2.0f * tco.rotation2), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glScalef((float)0.5f, (float)1.0f, (float)0.5f);
        this.model.renderAll();
        GL11.glPopMatrix();
    }
}

