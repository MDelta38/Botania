/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package thaumic.tinkerer.client.render.tile.kami;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumic.tinkerer.client.model.kami.ModelSpinningCubes;

public class RenderTileWarpGate
extends TileEntitySpecialRenderer {
    ModelSpinningCubes cubes = new ModelSpinningCubes();

    public void func_147500_a(TileEntity tileentity, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)(x + 0.5), (double)(y + 2.5), (double)(z + 0.5));
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)1.0f);
        int repeat = 5;
        this.cubes.renderSpinningCubes(12, repeat, repeat);
        GL11.glPopMatrix();
    }
}

