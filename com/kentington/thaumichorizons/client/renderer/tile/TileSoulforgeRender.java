/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.client.renderers.models.ModelBrain
 */
package com.kentington.thaumichorizons.client.renderer.tile;

import com.kentington.thaumichorizons.client.renderer.model.ModelSoulforge;
import com.kentington.thaumichorizons.common.tiles.TileSoulforge;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelBrain;

public class TileSoulforgeRender
extends TileEntitySpecialRenderer {
    private ModelBrain brain = new ModelBrain();
    private ModelSoulforge forge = new ModelSoulforge();
    static String tx1 = "textures/models/soulforge.png";
    static String tx2 = "textures/items/lightningringv.png";
    static String tx3 = "textures/misc/soul.png";

    public void func_147500_a(TileEntity tile, double x, double y, double z, float f) {
        int frames;
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y), (float)((float)z + 0.5f));
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)-0.25f, (float)0.0f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        this.renderBrains((TileSoulforge)tile, x, y, z, f);
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
        GL11.glTranslatef((float)0.0f, (float)-1.5f, (float)0.0f);
        UtilsFX.bindTexture((String)"thaumichorizons", (String)tx1);
        this.forge.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
        long nt = System.nanoTime();
        if (((TileSoulforge)tile).forging > 0) {
            frames = UtilsFX.getTextureAnimationSize((String)tx2);
            int i = (int)(((double)(nt / 40000000L) + x) % (double)frames);
            UtilsFX.bindTexture((String)"thaumcraft", (String)tx2);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPushMatrix();
            UtilsFX.renderFacingQuad((double)((double)tile.field_145851_c + 0.025), (double)((double)tile.field_145848_d + 0.75), (double)((double)tile.field_145849_e + 0.025), (float)0.0f, (float)0.2f, (float)0.9f, (int)frames, (int)i, (float)f, (int)0xFFFFFF);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            UtilsFX.renderFacingQuad((double)((double)tile.field_145851_c + 0.975), (double)((double)tile.field_145848_d + 0.75), (double)((double)tile.field_145849_e + 0.025), (float)0.0f, (float)0.2f, (float)0.9f, (int)frames, (int)i, (float)f, (int)0xFFFFFF);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            UtilsFX.renderFacingQuad((double)((double)tile.field_145851_c + 0.975), (double)((double)tile.field_145848_d + 0.75), (double)((double)tile.field_145849_e + 0.975), (float)0.0f, (float)0.2f, (float)0.9f, (int)frames, (int)i, (float)f, (int)0xFFFFFF);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            UtilsFX.renderFacingQuad((double)((double)tile.field_145851_c + 0.025), (double)((double)tile.field_145848_d + 0.75), (double)((double)tile.field_145849_e + 0.975), (float)0.0f, (float)0.2f, (float)0.9f, (int)frames, (int)i, (float)f, (int)0xFFFFFF);
            GL11.glPopMatrix();
            GL11.glDisable((int)3042);
        }
        if (((TileSoulforge)tile).souls > 0) {
            double offset = Math.PI * 2 / (double)((TileSoulforge)tile).souls;
            frames = 16;
            double radian = Math.toRadians((int)(nt / 40000000L % 360L));
            double dist = 0.1 + 0.1 * Math.cos(radian);
            UtilsFX.bindTexture((String)"thaumichorizons", (String)tx3);
            GL11.glEnable((int)3042);
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            GL11.glDisable((int)2929);
            GL11.glDisable((int)2884);
            for (int which = 0; which < ((TileSoulforge)tile).souls; ++which) {
                GL11.glPushMatrix();
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                UtilsFX.renderFacingQuad((double)((double)tile.field_145851_c + 0.5 + dist * Math.sin(2.0 * radian + offset * (double)which)), (double)((double)tile.field_145848_d + 0.85), (double)((double)tile.field_145849_e + 0.5 + dist * Math.cos(2.0 * radian + offset * (double)which)), (float)0.0f, (float)0.1f, (float)0.9f, (int)frames, (int)((int)(nt / 40000000L % (long)frames)), (float)f, (int)0xFFFFFF);
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
            }
            GL11.glEnable((int)2884);
            GL11.glEnable((int)2929);
            GL11.glDisable((int)3042);
        }
    }

    public void renderBrains(TileSoulforge te, double x, double y, double z, float f) {
        float f2;
        GL11.glPushMatrix();
        if (te != null) {
            f2 = te.rota;
            GL11.glRotatef((float)f2, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)-0.55f, (float)0.0f);
        UtilsFX.bindTexture((String)"thaumichorizons", (String)"textures/models/brain.png");
        GL11.glScalef((float)0.4f, (float)0.4f, (float)0.4f);
        this.brain.render();
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        if (te != null) {
            f2 = te.rota;
            GL11.glRotatef((float)f2, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)-0.55f, (float)0.0f);
        UtilsFX.bindTexture((String)"thaumichorizons", (String)"textures/models/brain.png");
        GL11.glScalef((float)0.4f, (float)0.4f, (float)0.4f);
        this.brain.render();
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
        GL11.glTranslatef((float)0.0f, (float)-1.25f, (float)0.0f);
        UtilsFX.bindTexture((String)"thaumichorizons", (String)"textures/models/jarbrine2.png");
        this.forge.renderBrine();
    }
}

