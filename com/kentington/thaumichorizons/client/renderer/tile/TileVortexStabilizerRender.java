/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 */
package com.kentington.thaumichorizons.client.renderer.tile;

import com.kentington.thaumichorizons.client.renderer.model.ModelVortexAttenuator;
import com.kentington.thaumichorizons.common.tiles.TileVortexStabilizer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class TileVortexStabilizerRender
extends TileEntitySpecialRenderer {
    private ModelVortexAttenuator model = new ModelVortexAttenuator();
    static String tx1 = "textures/models/attenuator.png";

    public void func_147500_a(TileEntity p_147500_1_, double x, double y, double z, float p_147500_8_) {
        TileVortexStabilizer te = (TileVortexStabilizer)p_147500_1_;
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y), (float)((float)z + 0.5f));
        switch (te.field_145847_g) {
            case 0: {
                GL11.glTranslatef((float)0.0f, (float)-0.5f, (float)0.0f);
                break;
            }
            case 1: {
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)0.0f, (float)-1.5f, (float)0.0f);
                break;
            }
            case 2: {
                GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)0.5f, (float)-1.0f, (float)0.0f);
                break;
            }
            case 3: {
                GL11.glRotatef((float)270.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.5f, (float)-1.0f, (float)0.0f);
                break;
            }
            case 4: {
                GL11.glRotatef((float)270.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)0.0f, (float)-1.0f, (float)-0.5f);
                break;
            }
            case 5: {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)0.0f, (float)-1.0f, (float)0.5f);
            }
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
        UtilsFX.bindTexture((String)"thaumichorizons", (String)tx1);
        this.model.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }
}

