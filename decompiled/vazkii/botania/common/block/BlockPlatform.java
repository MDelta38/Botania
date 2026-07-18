/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockCamo;
import vazkii.botania.common.block.tile.TileCamo;
import vazkii.botania.common.block.tile.TilePlatform;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockPlatform
extends BlockCamo
implements ILexiconable,
IWandable {
    IIcon[] icons;
    private static final int SUBTYPES = 3;

    public BlockPlatform() {
        super(Material.field_151575_d);
        this.func_149711_c(2.0f);
        this.func_149752_b(5.0f);
        this.func_149672_a(Block.field_149766_f);
        this.func_149663_c("platform");
    }

    @Override
    protected boolean shouldRegisterInNameSet() {
        return false;
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    @Override
    public Block func_149663_c(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)par1Str);
        return super.func_149663_c(par1Str);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < 3; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[3];
        for (int i = 0; i < 3; ++i) {
            this.icons[i] = IconHelper.forBlock(par1IconRegister, (Block)this, i);
        }
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.icons[Math.min(2, par2)];
    }

    public void func_149743_a(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
        block3: {
            block2: {
                int meta = par1World.func_72805_g(par2, par3, par4);
                if (meta == 2) break block2;
                if (meta != 0 || par7Entity == null) break block3;
                double d = par7Entity.field_70163_u;
                int n = par7Entity instanceof EntityPlayer ? 2 : 0;
                if (!(d > (double)(par3 + n)) || par7Entity instanceof EntityPlayer && par7Entity.func_70093_af()) break block3;
            }
            super.func_149743_a(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        }
    }

    public float func_149712_f(World par1World, int par2, int par3, int par4) {
        int meta = par1World.func_72805_g(par2, par3, par4);
        return meta == 2 ? -1.0f : super.func_149712_f(par1World, par2, par3, par4);
    }

    public TileCamo createNewTileEntity(World world, int meta) {
        return new TilePlatform();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        int meta = world.func_72805_g(x, y, z);
        return meta == 0 ? LexiconData.platform : (meta == 2 ? null : LexiconData.spectralPlatform);
    }

    @Override
    public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
        TilePlatform tile = (TilePlatform)world.func_147438_o(x, y, z);
        return tile.onWanded(player);
    }
}

