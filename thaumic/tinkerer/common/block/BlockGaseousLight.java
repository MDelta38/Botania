/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package thaumic.tinkerer.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockGas;

public class BlockGaseousLight
extends BlockGas {
    public BlockGaseousLight() {
        this.func_149715_a(0.85f);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (par5Random.nextFloat() < 0.0075f) {
            ThaumicTinkerer.tcProxy.sparkle((float)par2 + 0.5f, (float)par3 + 0.5f, (float)par4 + 0.5f, 1.0f, 1, par5Random.nextFloat() / 2.0f);
        }
    }

    @Override
    public void placeParticle(World world, int par2, int par3, int par4) {
        ThaumicTinkerer.tcProxy.sparkle((float)par2 + 0.5f, (float)par3 + 0.5f, (float)par4 + 0.5f, 1);
    }

    @Override
    public String getBlockName() {
        return "gaseousLight";
    }

    @Override
    public Class<? extends ItemBlock> getItemBlock() {
        return null;
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return null;
    }
}

