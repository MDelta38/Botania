/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockPistonBase
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.common.util.RotationHelper
 */
package vazkii.botania.common.block.string;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.common.util.RotationHelper;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.string.TileRedString;
import vazkii.botania.common.lexicon.LexiconData;

public abstract class BlockRedString
extends BlockModContainer<TileRedString>
implements ILexiconable {
    IIcon senderIcon;
    IIcon sideIcon;

    public BlockRedString(String name) {
        super(Material.field_151576_e);
        this.func_149711_c(2.0f);
        this.func_149752_b(10.0f);
        this.func_149672_a(field_149769_e);
        this.func_149663_c(name);
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        int orientation = BlockPistonBase.func_150071_a((World)par1World, (int)par2, (int)par3, (int)par4, (EntityLivingBase)par5EntityLivingBase);
        par1World.func_72921_c(par2, par3, par4, orientation, 3);
    }

    public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis) {
        return RotationHelper.rotateVanillaBlock((Block)Blocks.field_150331_J, (World)worldObj, (int)x, (int)y, (int)z, (ForgeDirection)axis);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.senderIcon = IconHelper.forName(par1IconRegister, "redStringSender");
        this.sideIcon = this.registerSideIcon(par1IconRegister);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon registerSideIcon(IIconRegister register) {
        return IconHelper.forBlock(register, (Block)this);
    }

    public IIcon func_149691_a(int side, int meta) {
        return side == meta ? this.senderIcon : this.sideIcon;
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.redString;
    }
}

