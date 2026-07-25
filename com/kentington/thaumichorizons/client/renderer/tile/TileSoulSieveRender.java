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

import com.kentington.thaumichorizons.client.renderer.model.ModelSoulSieve;
import com.kentington.thaumichorizons.common.tiles.TileSoulExtractor;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class TileSoulSieveRender
extends TileEntitySpecialRenderer {
    static String tx1 = "textures/models/soulsieve.png";
    private ModelSoulSieve model = new ModelSoulSieve();

    public void func_147500_a(TileEntity te, double x, double y, double z, float f) {
        TileSoulExtractor tco = (TileSoulExtractor)te;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 1.5f), (float)((float)z + 0.5f));
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        UtilsFX.bindTexture((String)"thaumichorizons", (String)tx1);
        this.model.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, 0.15625f * (float)(Math.cos(Math.toRadians(tco.sieveMotion)) - 1.0), tco.ticksLeft);
        GL11.glPopMatrix();
    }
}

