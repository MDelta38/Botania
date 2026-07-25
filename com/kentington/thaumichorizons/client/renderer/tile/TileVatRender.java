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

import com.kentington.thaumichorizons.client.renderer.model.ModelVat;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class TileVatRender
extends TileEntitySpecialRenderer {
    private static ModelVat model = new ModelVat();
    private String tx1 = "textures/models/vat.png";

    public void func_147500_a(TileEntity te, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y - 0.5f), (float)((float)z + 0.5f));
        UtilsFX.bindTexture((String)"thaumichorizons", (String)this.tx1);
        model.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
    }
}

