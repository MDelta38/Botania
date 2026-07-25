/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.client.renderers.models.ModelBrain
 *  thaumcraft.client.renderers.models.ModelJar
 */
package com.kentington.thaumichorizons.client.renderer.tile;

import com.kentington.thaumichorizons.client.renderer.model.ModelInspiratron;
import com.kentington.thaumichorizons.common.tiles.TileInspiratron;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelBrain;
import thaumcraft.client.renderers.models.ModelJar;

public class TileInspiratronRender
extends TileEntitySpecialRenderer {
    private ModelJar model = new ModelJar();
    private ModelBrain brain = new ModelBrain();
    private ModelInspiratron inspiratron = new ModelInspiratron();
    static String tx1 = "textures/models/inspiratron.png";

    public void func_147500_a(TileEntity tile, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y), (float)((float)z + 0.5f));
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)-0.125f, (float)0.0f);
        this.renderBrain((TileInspiratron)tile, x, y, z, f);
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glTranslatef((float)0.0f, (float)-1.5f, (float)0.0f);
        UtilsFX.bindTexture((String)"thaumichorizons", (String)tx1);
        this.inspiratron.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }

    public void renderBrain(TileInspiratron te, double x, double y, double z, float f) {
        float bob = MathHelper.func_76126_a((float)((float)Minecraft.func_71410_x().field_71439_g.field_70173_aa / 14.0f)) * 0.03f + 0.03f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)(-0.8f + bob), (float)0.0f);
        if (te != null) {
            float f2;
            for (f2 = te.rota - te.rotb; f2 < -3.141593f; f2 += 6.283185f) {
            }
            float f3 = te.rotb + f2 * f;
            GL11.glRotatef((float)(f3 * 180.0f / 3.141593f), (float)0.0f, (float)1.0f, (float)0.0f);
        }
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        UtilsFX.bindTexture((String)"thaumichorizons", (String)"textures/models/brain.png");
        GL11.glScalef((float)0.4f, (float)0.4f, (float)0.4f);
        this.brain.render();
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
        UtilsFX.bindTexture((String)"thaumichorizons", (String)"textures/models/jarbrine.png");
        this.model.renderBrine();
    }
}

