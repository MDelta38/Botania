/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 */
package vazkii.botania.client.render.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import vazkii.botania.client.core.handler.RedStringRenderer;
import vazkii.botania.common.block.tile.string.TileRedString;

public class RenderTileRedString
extends TileEntitySpecialRenderer {
    public void func_147500_a(TileEntity tileentity, double x, double y, double z, float partticks) {
        TileRedString trs = (TileRedString)tileentity;
        RedStringRenderer.redStringTiles.add(trs);
    }
}

