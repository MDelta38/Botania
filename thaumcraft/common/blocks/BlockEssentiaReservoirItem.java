/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.tiles.TileEssentiaReservoir;

public class BlockEssentiaReservoirItem
extends ItemBlock {
    public BlockEssentiaReservoirItem(Block par1) {
        super(par1);
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        boolean placed = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
        if (placed) {
            try {
                TileEssentiaReservoir ts = (TileEssentiaReservoir)world.func_147438_o(x, y, z);
                ts.facing = ForgeDirection.getOrientation((int)side).getOpposite();
                ts.func_70296_d();
                world.func_147471_g(x, y, z);
            }
            catch (Exception e) {
                // empty catch block
            }
        }
        return placed;
    }
}

