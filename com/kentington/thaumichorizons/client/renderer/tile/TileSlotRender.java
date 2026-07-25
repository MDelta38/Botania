/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 */
package com.kentington.thaumichorizons.client.renderer.tile;

import com.kentington.thaumichorizons.client.renderer.model.ModelReceptacle;
import com.kentington.thaumichorizons.common.tiles.TileSlot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

@SideOnly(value=Side.CLIENT)
public class TileSlotRender
extends TileEntitySpecialRenderer {
    static String tx1 = "textures/models/receptacle.png";
    private ModelReceptacle base = new ModelReceptacle();

    public void func_147500_a(TileEntity te, double x, double y, double z, float f) {
        TileSlot tco = (TileSlot)te;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y - 0.5f), (float)((float)z + 0.5f));
        UtilsFX.bindTexture((String)"thaumichorizons", (String)tx1);
        this.base.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, tco.hasKeystone, tco.pocketID);
        GL11.glPopMatrix();
    }
}

