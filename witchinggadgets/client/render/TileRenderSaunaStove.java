/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package witchinggadgets.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import witchinggadgets.common.blocks.tiles.TileEntitySaunaStove;

public class TileRenderSaunaStove
extends TileEntitySpecialRenderer {
    static final boolean DEBUG = false;

    public void func_147500_a(TileEntity tileentity, double x, double y, double z, float f) {
        TileEntitySaunaStove tile = (TileEntitySaunaStove)tileentity;
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        GL11.glPopMatrix();
    }
}

