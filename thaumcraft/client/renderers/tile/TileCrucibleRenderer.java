/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileCrucible;

public class TileCrucibleRenderer
extends TileEntitySpecialRenderer {
    public void renderEntityAt(TileCrucible cr, double x, double y, double z, float fq) {
        if (cr.tank.getFluidAmount() > 0) {
            this.renderFluid(cr, x, y, z);
        }
    }

    public void renderFluid(TileCrucible cr, double x, double y, double z) {
        IIcon icon = Blocks.field_150355_j.func_149691_a(0, 0);
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)(y + (double)cr.getFluidHeight()), (double)(z + 1.0));
        GL11.glRotatef((float)90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
        if (cr.tank.getFluidAmount() > 0) {
            float f = cr.tagAmount();
            cr.getClass();
            float recolor = f / 100.0f;
            if (recolor > 0.0f) {
                recolor = 0.5f + recolor / 2.0f;
            }
            UtilsFX.renderQuadFromIcon(true, icon, 1.0f, 1.0f - recolor / 3.0f, 1.0f - recolor, 1.0f - recolor / 2.0f, ConfigBlocks.blockMetalDevice.func_149677_c((IBlockAccess)cr.func_145831_w(), cr.field_145851_c, cr.field_145848_d, cr.field_145849_e), 771, 1.0f);
        }
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity te, double d, double d1, double d2, float f) {
        this.renderEntityAt((TileCrucible)te, d, d1, d2, f);
    }
}

