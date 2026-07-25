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
import witchinggadgets.common.blocks.tiles.TileEntityCobbleGen;

public class TileRenderCobbleGen
extends TileEntitySpecialRenderer {
    public void renderTileEntityAt(TileEntityCobbleGen tile, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        Tessellator tes = Tessellator.field_78398_a;
        GL11.glDisable((int)2896);
        int tick = tile.tick;
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
        ClientUtilities.bindTexture("thaumcraft:textures/blocks/woodplain.png");
        TileRenderCobbleGen.renderPixelBlock(tes, 0.0, 0.0, 0.0, 1.0, 0.1875, 1.0, 0.0, 0.0, 1.0, 0.1875);
        TileRenderCobbleGen.renderPixelBlock(tes, 0.0625, 0.1875, 0.0625, 0.9375, 0.3125, 0.9375, 0.0, 0.0, 1.0, 0.125);
        TileRenderCobbleGen.renderPixelBlock(tes, 0.0, 0.8125, 0.0, 1.0, 0.9375, 1.0, 0.0, 0.0, 1.0, 0.1875);
        ClientUtilities.bindTexture("thaumcraft:textures/blocks/arcane_stone.png");
        TileRenderCobbleGen.renderPixelBlock(tes, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0);
        TileRenderCobbleGen.renderPixelBlock(tes, 0.0, 0.9375, 0.0, 1.0, 0.999, 1.0, 0.0, 0.0, 0.125, 0.125);
        TileRenderCobbleGen.renderPixelBlock(tes, 0.0625, 0.3125, 0.0625, 0.9375, 0.3125, 0.9375, 0.0625, 0.0625, 0.9375, 0.9375);
        TileRenderCobbleGen.renderPixelBlock(tes, 0.0, 0.8125, 0.0, 1.0, 0.8125, 1.0, 0.0, 0.0, 1.0, 1.0);
        ClientUtilities.bindTexture("thaumcraft:textures/blocks/pedestal_top.png");
        TileRenderCobbleGen.renderPixelBlock(tes, 0.0, 0.1875, 0.0, 0.125, 0.8125, 0.125, 0.0, 0.0, 0.125, 1.0);
        TileRenderCobbleGen.renderPixelBlock(tes, 0.0, 0.1875, 0.875, 0.125, 0.8125, 1.0, 0.0, 0.0, 0.125, 1.0);
        TileRenderCobbleGen.renderPixelBlock(tes, 0.875, 0.1875, 0.0, 1.0, 0.8125, 0.125, 0.0, 0.0, 0.125, 1.0);
        TileRenderCobbleGen.renderPixelBlock(tes, 0.875, 0.1875, 0.875, 1.0, 0.8125, 1.0, 0.0, 0.0, 0.125, 1.0);
        if (!tile.facing.equals((Object)ForgeDirection.UP) && !tile.facing.equals((Object)ForgeDirection.DOWN)) {
            ClientUtilities.bindTexture("thaumcraft:textures/blocks/liftertop.png");
        } else {
            ClientUtilities.bindTexture("thaumcraft:textures/blocks/lifterside.png");
        }
        TileRenderCobbleGen.renderPixelBlock(tes, 0.125, 0.125, 0.0624, 0.875, 0.875, 0.0625, 0.125, 0.125, 0.875, 0.875);
        ClientUtilities.bindTexture("thaumcraft:textures/blocks/lifterside.png");
        TileRenderCobbleGen.renderPixelBlock(tes, 0.125, 0.125, 0.9375, 0.875, 0.875, 0.9376, 0.125, 0.125, 0.875, 0.875);
        TileRenderCobbleGen.renderPixelBlock(tes, 0.9375, 0.125, 0.125, 0.9376, 0.625, 0.875, 0.125, 0.125, 0.875, 0.625);
        TileRenderCobbleGen.renderPixelBlock(tes, 0.0624, 0.125, 0.125, 0.0625, 0.625, 0.875, 0.125, 0.125, 0.875, 0.625);
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
        if (tile.func_145831_w() == null || tile.func_145831_w().func_94577_B(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e) <= 0 && !tile.func_145831_w().func_72864_z(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e)) {
            double slowTick = tick / 4;
            double loopTick = slowTick * 1.65;
            double inc = 0.001953125;
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            ClientUtilities.bindTexture("textures/blocks/lava_flow.png");
            TileRenderCobbleGen.renderPixelBlock(tes, 0.1875, 0.3125, 0.375, 0.3125, 0.8125, 0.625, 0.375, loopTick * inc, 0.625, (loopTick + 8.0) * inc);
            ClientUtilities.bindTexture("textures/blocks/water_flow.png");
            TileRenderCobbleGen.renderPixelBlock(tes, 0.6875, 0.3125, 0.375, 0.8125, 0.8125, 0.625, 0.375, loopTick * inc, 0.625, (loopTick + 8.0) * inc);
            GL11.glDisable((int)3042);
            ClientUtilities.bindTexture("textures/blocks/cobblestone.png");
            if (tick > 32) {
                TileRenderCobbleGen.renderPixelBlock(tes, 0.3125, 0.3125, 0.3125, 0.6875, 0.6875, 0.6875, 0.0, 0.0, 1.0, 1.0);
            }
        }
        GL11.glEnable((int)2896);
        GL11.glPopMatrix();
    }

    public static void renderPixelBlock(Tessellator tes, double x, double y, double z, double pixelLengthX, double pixelLengthY, double pixelLengthZ, double uMin, double vMin, double uMax, double vMax) {
        double dXMin = x;
        double dXMax = pixelLengthX;
        double dYMin = y;
        double dYMax = pixelLengthY;
        double dZMin = z;
        double dZMax = pixelLengthZ;
        tes.func_78382_b();
        tes.func_78374_a(dXMin, dYMin, dZMin, uMin, vMin);
        tes.func_78374_a(dXMax, dYMin, dZMin, uMax, vMin);
        tes.func_78374_a(dXMax, dYMin, dZMax, uMax, vMax);
        tes.func_78374_a(dXMin, dYMin, dZMax, uMin, vMax);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78374_a(dXMin, dYMax, dZMin, uMin, vMin);
        tes.func_78374_a(dXMin, dYMax, dZMax, uMin, vMax);
        tes.func_78374_a(dXMax, dYMax, dZMax, uMax, vMax);
        tes.func_78374_a(dXMax, dYMax, dZMin, uMax, vMin);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78374_a(dXMin, dYMin, dZMin, uMin, vMin);
        tes.func_78374_a(dXMin, dYMax, dZMin, uMin, vMax);
        tes.func_78374_a(dXMax, dYMax, dZMin, uMax, vMax);
        tes.func_78374_a(dXMax, dYMin, dZMin, uMax, vMin);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78374_a(dXMin, dYMin, dZMax, uMin, vMin);
        tes.func_78374_a(dXMax, dYMin, dZMax, uMax, vMin);
        tes.func_78374_a(dXMax, dYMax, dZMax, uMax, vMax);
        tes.func_78374_a(dXMin, dYMax, dZMax, uMin, vMax);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78374_a(dXMin, dYMin, dZMin, uMin, vMin);
        tes.func_78374_a(dXMin, dYMin, dZMax, uMax, vMin);
        tes.func_78374_a(dXMin, dYMax, dZMax, uMax, vMax);
        tes.func_78374_a(dXMin, dYMax, dZMin, uMin, vMax);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78374_a(dXMax, dYMin, dZMin, uMin, vMin);
        tes.func_78374_a(dXMax, dYMax, dZMin, uMin, vMax);
        tes.func_78374_a(dXMax, dYMax, dZMax, uMax, vMax);
        tes.func_78374_a(dXMax, dYMin, dZMax, uMax, vMin);
        tes.func_78381_a();
    }

    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float f) {
        this.renderTileEntityAt((TileEntityCobbleGen)tileentity, d0, d1, d2, f);
    }
}

