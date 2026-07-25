/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IShearable
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.wands.IWandable
 */
package witchinggadgets.common.blocks;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.wands.IWandable;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.client.render.BlockRenderRoseVine;

public class BlockRoseVines
extends Block
implements IWandable,
IShearable {
    public BlockRoseVines() {
        super(Material.field_151585_k);
        this.func_149675_a(true);
        this.func_149647_a(WitchingGadgets.tabWG);
    }

    public void func_149674_a(World world, int x, int y, int z, Random random) {
        if (!world.field_72995_K) {
            this.spreadVines(world, x, y, z, random, false);
        }
    }

    public int func_149645_b() {
        return BlockRenderRoseVine.renderID;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return true;
    }

    public void func_149651_a(IIconRegister iconRegister) {
        this.field_149761_L = iconRegister.func_94245_a("witchinggadgets:roseVineBlock");
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        if (entity instanceof EntityLivingBase) {
            entity.func_70097_a(DamageSource.field_76377_j, 1.0f);
        }
    }

    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
        return null;
    }

    public void spreadVines(World world, int x, int y, int z, Random random, boolean ignoreRandom) {
        int growthStage = world.func_72805_g(x, y, z);
        if (growthStage >= 5) {
            if (this.isRoseVinePlaceable(world, x, y + 1, z) && (random.nextInt(4) == 0 || ignoreRandom) && (world.isSideSolid(x, y + 1, z - 1, ForgeDirection.SOUTH, false) || world.isSideSolid(x, y + 1, z + 1, ForgeDirection.NORTH, false) || world.isSideSolid(x - 1, y + 1, z, ForgeDirection.WEST, false) || world.isSideSolid(x + 1, y + 1, z, ForgeDirection.EAST, false))) {
                world.func_147449_b(x, y + 1, z, (Block)this);
            }
        } else {
            world.func_72921_c(x, y, z, growthStage + 1, 2);
        }
    }

    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
        return false;
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        float minX = 0.0f;
        float minY = 0.0f;
        float minZ = 0.0f;
        float maxX = 1.0f;
        float maxY = 1.0f;
        float maxZ = 1.0f;
        if (world.isSideSolid(x, y - 1, z, ForgeDirection.UP, false)) {
            maxY = 0.375f;
        }
        if (world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, false)) {
            maxY = 1.0f;
            minZ = 0.75f;
        }
        if (world.isSideSolid(x + 1, y, z, ForgeDirection.EAST, false)) {
            maxY = 1.0f;
            minX = 0.75f;
        }
        if (world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, false)) {
            maxY = 1.0f;
            maxZ = 0.25f;
        }
        if (world.isSideSolid(x - 1, y, z, ForgeDirection.WEST, false)) {
            maxY = 1.0f;
            maxX = 0.25f;
        }
        if (world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, false)) {
            maxZ = 1.0f;
        }
        if (world.isSideSolid(x + 1, y, z, ForgeDirection.EAST, false)) {
            maxX = 1.0f;
        }
        if (world.isSideSolid(x, y - 1, z, ForgeDirection.UP, false)) {
            minX = 0.0f;
            minZ = 0.0f;
            maxX = 1.0f;
            maxZ = 1.0f;
        }
        this.func_149676_a(minX, minY, minZ, maxX, maxY, maxZ);
    }

    private boolean hasClimbableWall(World world, int x, int y, int z) {
        return world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, false) || world.isSideSolid(x + 1, y, z, ForgeDirection.EAST, false) || world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, false) || world.isSideSolid(x - 1, y, z, ForgeDirection.WEST, false);
    }

    private boolean canRoot(World world, int x, int y, int z) {
        return world.isSideSolid(x, y - 1, z, ForgeDirection.UP, false);
    }

    private boolean isRoseVinePlaceable(World world, int x, int y, int z) {
        boolean flagBasic = world.func_147437_c(x, y, z) && world.func_147439_a(x, y, z) != this;
        return flagBasic && (this.canRoot(world, x, y, z) || this.hasClimbableWall(world, x, y, z));
    }

    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        Random random = new Random();
        if (player.field_71075_bZ.field_75098_d) {
            this.spreadVines(world, x, y, z, random, true);
        }
        return 1;
    }

    public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {
    }

    public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {
    }

    public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
        return null;
    }

    public int func_149745_a(Random par1Random) {
        return 0;
    }

    public void func_149636_a(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
        super.func_149636_a(par1World, par2EntityPlayer, par3, par4, par5, par6);
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack((Block)this, 1));
        return ret;
    }
}

