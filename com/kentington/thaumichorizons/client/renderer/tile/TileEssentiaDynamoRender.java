/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.model.AdvancedModelLoader
 *  net.minecraftforge.client.model.IModelCustom
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.fx.ParticleEngine
 *  thaumcraft.client.lib.UtilsFX
 */
package com.kentington.thaumichorizons.client.renderer.tile;

import com.kentington.thaumichorizons.client.fx.FXEssentiaTrail;
import com.kentington.thaumichorizons.client.renderer.model.ModelQuarterBlock;
import com.kentington.thaumichorizons.common.tiles.TileEssentiaDynamo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.lib.UtilsFX;

public class TileEssentiaDynamoRender
extends TileEntitySpecialRenderer {
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)SCANNER);
    private static final ResourceLocation SCANNER = new ResourceLocation("thaumcraft", "textures/models/scanner.obj");
    static String tx1 = "textures/models/thaumiumring.png";
    static String tx2 = "textures/models/dynamoessentiabase.png";
    static String tx3 = "textures/items/lightningringv.png";
    private ModelQuarterBlock base = new ModelQuarterBlock();

    public void func_147500_a(TileEntity te, double x, double y, double z, float f) {
        TileEssentiaDynamo tco = (TileEssentiaDynamo)te;
        if (tco.essentia != null && tco.ticksProvided > 0) {
            FXEssentiaTrail fb = new FXEssentiaTrail(tco.func_145831_w(), (double)tco.field_145851_c + 0.5, tco.field_145848_d, (double)tco.field_145849_e + 0.5, (double)tco.field_145851_c + 0.5, (double)tco.field_145848_d + 0.5, (double)tco.field_145849_e + 0.5, Minecraft.func_71410_x().field_71439_g.field_70173_aa, tco.essentia.getColor(), 0.3f);
            fb.field_70145_X = true;
            ParticleEngine.instance.addEffect(tco.func_145831_w(), (EntityFX)fb);
        }
        if (tco.rise >= 0.3f && tco.ticksProvided > 0) {
            GL11.glPushMatrix();
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)1);
            long nt = System.nanoTime();
            UtilsFX.bindTexture((String)tx3);
            int frames = UtilsFX.getTextureAnimationSize((String)tx3);
            int i = (int)(((double)(nt / 40000000L) + x) % (double)frames);
            UtilsFX.renderFacingQuad((double)((double)tco.field_145851_c + 0.5), (double)((float)tco.field_145848_d + 0.5f), (double)((double)tco.field_145849_e + 0.5), (float)0.0f, (float)0.2f, (float)0.9f, (int)frames, (int)i, (float)f, (int)tco.essentia.getColor());
            GL11.glDisable((int)3042);
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glPopMatrix();
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

