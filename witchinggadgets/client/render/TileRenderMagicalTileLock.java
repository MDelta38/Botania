/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package witchinggadgets.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.blocks.tiles.TileEntityMagicalTileLock;

public class TileRenderMagicalTileLock
extends TileEntitySpecialRenderer {
    public void func_147500_a(TileEntity tileentity, double x, double y, double z, float f) {
        TileEntityMagicalTileLock tile = (TileEntityMagicalTileLock)tileentity;
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        ClientUtilities.bindTexture("witchinggadgets:textures/blocks/white.png");
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)0.125f, (float)0.0f, (float)0.1875f, (float)0.5f);
        float scale = 0.16666667f;
        for (int i = 0; i < 9; ++i) {
            if (tile.tiles[i] != 1) continue;
            ClientUtilities.renderPixelBlock(Tessellator.field_78398_a, 1.5 + (double)(i % 3), 0.0, 1.5 + (double)(i / 3), scale, 0.0, 0.0, 1.0, 1.0);
            ClientUtilities.renderPixelBlock(Tessellator.field_78398_a, 1.5 + (double)(i % 3), 5.0, 1.5 + (double)(i / 3), scale, 0.0, 0.0, 1.0, 1.0);
            ClientUtilities.renderPixelBlock(Tessellator.field_78398_a, 0.0, 3.5 - (double)(i % 3), 1.5 + (double)(i / 3), scale, 0.0, 0.0, 1.0, 1.0);
            ClientUtilities.renderPixelBlock(Tessellator.field_78398_a, 5.0, 3.5 - (double)(i % 3), 3.5 - (double)(i / 3), scale, 0.0, 0.0, 1.0, 1.0);
            ClientUtilities.renderPixelBlock(Tessellator.field_78398_a, 3.5 - (double)(i / 3), 3.5 - (double)(i % 3), 0.0, scale, 0.0, 0.0, 1.0, 1.0);
            ClientUtilities.renderPixelBlock(Tessellator.field_78398_a, 1.5 + (double)(i / 3), 3.5 - (double)(i % 3), 5.0, scale, 0.0, 0.0, 1.0, 1.0);
        }
        GL11.glEnable((int)2896);
        GL11.glPopMatrix();
    }
}

