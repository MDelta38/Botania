/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockLivingwood
extends BlockMod
implements ILexiconable {
    private static final int TYPES = 6;
    IIcon[] icons;

    public BlockLivingwood() {
        this("livingwood");
    }

    public BlockLivingwood(String name) {
        super(Material.field_151575_d);
        this.func_149711_c(2.0f);
        this.func_149672_a(field_149766_f);
        this.func_149663_c(name);
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
        this.register(par1Str);
        return super.func_149663_c(par1Str);
    }

    void register(String name) {
        GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)name);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < 6; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[6];
        for (int i = 0; i < 6; ++i) {
            this.icons[i] = IconHelper.forBlock(par1IconRegister, (Block)this, i);
        }
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.icons[Math.min(5, par2)];
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);
        return new ItemStack((Block)this, 1, meta);
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return world.func_72805_g(x, y, z) == 5 ? 12 : 0;
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        int meta = world.func_72805_g(x, y, z);
        return meta == 0 ? LexiconData.pureDaisy : LexiconData.decorativeBlocks;
    }

    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return world.func_72805_g(x, y, z) == 0;
    }
}

