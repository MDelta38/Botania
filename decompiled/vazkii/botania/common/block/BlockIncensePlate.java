/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.common.util.RotationHelper
 */
package vazkii.botania.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.common.util.RotationHelper;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileIncensePlate;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockIncensePlate
extends BlockModContainer
implements ILexiconable {
    private static final int[] META_ROTATIONS = new int[]{2, 5, 3, 4};

    protected BlockIncensePlate() {
        super(Material.field_151575_d);
        this.func_149663_c("incensePlate");
        this.func_149711_c(2.0f);
        this.func_149672_a(field_149766_f);
        this.setBlockBounds(true);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int s, float xs, float ys, float zs) {
        TileIncensePlate plate = (TileIncensePlate)world.func_147438_o(x, y, z);
        ItemStack stack = player.func_71045_bC();
        ItemStack plateStack = plate.func_70301_a(0);
        boolean did = false;
        if (world.field_72995_K) {
            return true;
        }
        if (plateStack == null && plate.func_94041_b(0, stack)) {
            plate.func_70299_a(0, stack.func_77946_l());
            --stack.field_77994_a;
            did = true;
        } else if (plateStack != null && !plate.burning) {
            if (stack != null && stack.func_77973_b() == Items.field_151033_d) {
                plate.ignite();
                stack.func_77972_a(1, (EntityLivingBase)player);
                did = true;
            } else {
                ItemStack addStack = plateStack.func_77946_l();
                if (!player.field_71071_by.func_70441_a(addStack)) {
                    player.func_71019_a(addStack, false);
                }
                plate.func_70299_a(0, null);
                did = true;
            }
        }
        if (did) {
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(plate);
        }
        return did;
    }

    public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
        int l = MathHelper.func_76128_c((double)((double)(p_149689_5_.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, META_ROTATIONS[l], 2);
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World world, int x, int y, int z, int side) {
        return ((TileIncensePlate)world.func_147438_o((int)x, (int)y, (int)z)).comparatorOutput;
    }

    public void func_149719_a(IBlockAccess w, int x, int y, int z) {
        this.setBlockBounds(w.func_72805_g(x, y, z) < 4);
    }

    public void setBlockBounds(boolean horiz) {
        float f = 0.0625f;
        float w = 12.0f * f;
        float l = 4.0f * f;
        float xs = (1.0f - w) / 2.0f;
        float zs = (1.0f - l) / 2.0f;
        if (horiz) {
            this.func_149676_a(xs, 0.0f, zs, 1.0f - xs, f, 1.0f - zs);
        } else {
            this.func_149676_a(zs, 0.0f, xs, 1.0f - zs, f, 1.0f - xs);
        }
    }

    public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis) {
        return RotationHelper.rotateVanillaBlock((Block)Blocks.field_150460_al, (World)worldObj, (int)x, (int)y, (int)z, (ForgeDirection)axis);
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
        return ModBlocks.livingwood.func_149691_a(0, 0);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return LibRenderIDs.idIncensePlate;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileIncensePlate();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.incense;
    }
}

