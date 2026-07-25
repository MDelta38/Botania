/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.TileMagicBox;

public class BlockMagicBox
extends BlockContainer {
    private Random random = new Random();
    public IIcon icon;

    public BlockMagicBox() {
        super(Material.field_151575_d);
        this.func_149711_c(2.5f);
        this.func_149672_a(field_149766_f);
        this.func_149647_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:woodplain");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return this.icon;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        InventoryUtils.dropItems(par1World, par2, par3, par4);
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileMagicBox var10 = (TileMagicBox)par1World.func_147438_o(par2, par3, par4);
        if (var10 == null) {
            return true;
        }
        if (par1World.field_72995_K) {
            return true;
        }
        par5EntityPlayer.openGui((Object)Thaumcraft.instance, 18, par1World, par2, par3, par4);
        return true;
    }

    public TileEntity func_149915_a(World par1World, int m) {
        return new TileMagicBox();
    }
}

