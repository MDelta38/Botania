/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package witchinggadgets.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.client.render.TileRenderCobbleGen;
import witchinggadgets.common.blocks.tiles.TileEntitySnowGen;

public class TileRenderSnowGen
extends TileEntitySpecialRenderer {
    public void renderTileEntityAt(TileEntitySnowGen tile, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        Tessellator tes = Tessellator.field_78398_a;
        GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
        switch (tile.facing) {
            case NORTH: {
                break;
            }
            case SOUTH: {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-1.0f, (float)0.0f, (float)-1.0f);
                break;
            }
            case EAST: {
                GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-1.0f);
                break;
            }
            case WEST: {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-1.0f, (float)0.0f, (float)0.0f);
                break;
            }
        }
        ClientUtilities.bindTexture("thaumcraft:textures/models/Bore.png");
        if (tile.facing.equals((Object)ForgeDirection.UP)) {
            TileRenderCobbleGen.renderPixelBlock(tes, 0.375, 1.0, 0.375, 0.625, 1.125, 0.625, 0.859375, 0.65625, 0.8984375, 0.71875);
            TileRenderCobbleGen.renderPixelBlock(tes, 0.375, 1.125, 0.375, 0.625, 1.125, 0.625, 0.828125, 0.875, 0.8671875, 0.953125);
        } else if (tile.facing.equals((Object)ForgeDirection.DOWN)) {
            TileRenderCobbleGen.renderPixelBlock(tes, 0.375, -0.125, 0.375, 0.625, 0.0, 0.625, 0.859375, 0.65625, 0.8984375, 0.71875);
            TileRenderCobbleGen.renderPixelBlock(tes, 0.375, -0.125, 0.375, 0.625, -0.125, 0.625, 0.828125, 0.875, 0.8671875, 0.953125);
        } else {
            TileRenderCobbleGen.renderPixelBlock(tes, 0.4075, 0.4075, 0.0, 0.5925, 0.5925, 0.0624, 0.859375, 0.65625, 0.8984375, 0.71875);
            TileRenderCobbleGen.renderPixelBlock(tes, 0.375, 0.375, -0.125, 0.625, 0.625, 0.0, 0.859375, 0.65625, 0.8984375, 0.71875);
            TileRenderCobbleGen.renderPixelBlock(tes, 0.375, 0.375, -0.125, 0.625, 0.625, -0.125, 0.828125, 0.875, 0.8671875, 0.953125);
        }
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        ClientUtilities.bindTexture("textures/blocks/ice.png");
        TileRenderCobbleGen.renderPixelBlock(tes, 0.1875, 0.25, 0.1875, 0.375, 0.5, 0.375, 0.0, 0.0, 1.0, 1.0);
        TileRenderCobbleGen.renderPixelBlock(tes, 0.375, 0.25, 0.1875, 0.625, 0.5625, 0.4375, 0.0, 0.0, 1.0, 1.0);
        TileRenderCobbleGen.renderPixelBlock(tes, 0.15625, 0.25, 0.5, 0.4375, 0.5625, 0.8125, 0.0, 0.0, 1.0, 1.0);
        TileRenderCobbleGen.renderPixelBlock(tes, 0.5, 0.25, 0.5625, 0.75, 0.375, 0.875, 0.0, 0.0, 1.0, 1.0);
        TileRenderCobbleGen.renderPixelBlock(tes, 0.65625, 0.25, 0.3125, 0.8125, 0.75, 0.5, 0.0, 0.0, 1.0, 1.0);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2896);
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float f) {
        this.renderTileEntityAt((TileEntitySnowGen)tileentity, d0, d1, d2, f);
    }
}

