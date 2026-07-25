/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package witchinggadgets.common.blocks;

import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import witchinggadgets.common.blocks.tiles.TileEntityVoidWalkway;

public class BlockVoidWalkway
extends BlockContainer {
    public BlockVoidWalkway() {
        super(Material.field_151594_q);
        this.func_149711_c(0.0f);
        this.func_149752_b(0.0f);
        this.func_149672_a(field_149769_e);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        ItemBlock ib;
        if (!player.func_70093_af() && player.func_71045_bC() != null && player.func_71045_bC().func_77973_b() instanceof ItemBlock && (ib = (ItemBlock)player.func_71045_bC().func_77973_b()).placeBlockAt(player.func_71045_bC(), player, world, x, y, z, side, hitX, hitY, hitZ, player.func_71045_bC().func_77960_j())) {
            if (!player.field_71075_bZ.field_75098_d) {
                --player.func_71045_bC().field_77994_a;
            }
            return true;
        }
        return false;
    }

    public int func_149701_w() {
        return 1;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149646_a(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
        return super.func_149646_a(iBlockAccess, x, y, z, side) && !iBlockAccess.func_147439_a(x, y, z).equals((Object)this);
    }

    public int func_149745_a(Random par1Random) {
        return 0;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return null;
    }

    public void func_149651_a(IIconRegister iconRegister) {
        this.field_149761_L = iconRegister.func_94245_a("witchinggadgets:voidWalkway");
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileEntityVoidWalkway();
    }
}

