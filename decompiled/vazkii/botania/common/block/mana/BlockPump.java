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
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.common.util.RotationHelper
 */
package vazkii.botania.common.block.mana;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.common.util.RotationHelper;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.mana.TilePump;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockPump
extends BlockModContainer
implements ILexiconable {
    private static final int[] META_ROTATIONS = new int[]{2, 5, 3, 4};

    public BlockPump() {
        super(Material.field_151576_e);
        this.func_149711_c(2.0f);
        this.func_149752_b(10.0f);
        this.func_149672_a(field_149769_e);
        this.func_149663_c("pump");
        this.setBlockBounds(true);
    }

    public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
        int l = MathHelper.func_76128_c((double)((double)(p_149689_5_.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, META_ROTATIONS[l], 2);
    }

    public void func_149719_a(IBlockAccess w, int x, int y, int z) {
        this.setBlockBounds(w.func_72805_g(x, y, z) < 4);
    }

    public void setBlockBounds(boolean horiz) {
        if (horiz) {
            this.func_149676_a(0.25f, 0.0f, 0.0f, 0.75f, 0.5f, 1.0f);
        } else {
            this.func_149676_a(0.0f, 0.0f, 0.25f, 1.0f, 0.5f, 0.75f);
        }
    }

    public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis) {
        return RotationHelper.rotateVanillaBlock((Block)Blocks.field_150460_al, (World)worldObj, (int)x, (int)y, (int)z, (ForgeDirection)axis);
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    public IIcon func_149691_a(int par1, int par2) {
        return ModBlocks.livingrock.func_149691_a(0, 0);
    }

    public int func_149645_b() {
        return LibRenderIDs.idPump;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World world, int x, int y, int z, int side) {
        return ((TilePump)world.func_147438_o((int)x, (int)y, (int)z)).comparator;
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.poolCart;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TilePump();
    }
}

