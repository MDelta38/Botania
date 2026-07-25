/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.tiles.TileEssentiaCrystalizer;
import thaumcraft.common.tiles.TileTube;

public class BlockTubeItem
extends ItemBlock {
    public BlockTubeItem(Block par1) {
        super(par1);
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        boolean ret = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
        if (ret) {
            TileEntity te = world.func_147438_o(x, y, z);
            if (te instanceof TileTube) {
                ((TileTube)te).facing = ForgeDirection.getOrientation((int)side);
            }
            if (te instanceof TileEssentiaCrystalizer) {
                ((TileEssentiaCrystalizer)te).facing = ForgeDirection.getOrientation((int)side).getOpposite();
            }
        }
        return ret;
    }
}

