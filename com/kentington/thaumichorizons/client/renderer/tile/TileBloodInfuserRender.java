/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 */
package com.kentington.thaumichorizons.client.renderer.tile;

import com.kentington.thaumichorizons.client.renderer.model.ModelBloodInfuser;
import com.kentington.thaumichorizons.client.renderer.model.ModelSyringe;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileBloodInfuser;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class TileBloodInfuserRender
extends TileEntitySpecialRenderer {
    static String tx1 = "textures/models/bloodinfuser.png";
    private ModelBloodInfuser base = new ModelBloodInfuser();
    static String tx2 = "textures/models/syringe.png";
    private ModelSyringe syringe = new ModelSyringe();

    public void func_147500_a(TileEntity te, double x, double y, double z, float f) {
        TileBloodInfuser tco = (TileBloodInfuser)te;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 1.5f), (float)((float)z + 0.5f));
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        UtilsFX.bindTexture((String)"thaumichorizons", (String)tx1);
        this.base.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
        if (tco.hasBlood()) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)x + 1.90625f), (float)((float)y + 0.75f), (float)((float)z + 0.46875f));
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            UtilsFX.bindTexture((String)"thaumichorizons", (String)tx2);
            this.syringe.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, new ItemStack(ThaumicHorizons.itemSyringeHuman));
            GL11.glPopMatrix();
        }
    }
}

