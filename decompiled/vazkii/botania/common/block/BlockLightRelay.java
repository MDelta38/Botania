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
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileLightRelay;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockLightRelay
extends BlockModContainer
implements IWandable,
ILexiconable {
    public static IIcon invIcon;
    public static IIcon worldIcon;
    public static IIcon invIconRed;
    public static IIcon worldIconRed;

    protected BlockLightRelay() {
        super(Material.field_151592_s);
        float f = 0.3125f;
        this.func_149676_a(f, f, f, 1.0f - f, 1.0f - f, 1.0f - f);
        this.func_149663_c("lightRelay");
    }

    @Override
    public Block func_149663_c(String par1Str) {
        this.register(par1Str);
        return super.func_149663_c(par1Str);
    }

    void register(String name) {
        GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)name);
    }

    public void func_149666_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 2; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public int func_149692_a(int meta) {
        return meta == 0 ? 0 : 1;
    }

    @Override
    protected boolean shouldRegisterInNameSet() {
        return false;
    }

    public boolean func_149655_b(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_) {
        return false;
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int s, float xs, float ys, float zs) {
        ((TileLightRelay)world.func_147438_o(x, y, z)).mountEntity((Entity)player);
        return true;
    }

    public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return null;
    }

    public int func_149738_a(World p_149738_1_) {
        return 2;
    }

    public void func_149674_a(World world, int x, int y, int z, Random rand) {
        world.func_72921_c(x, y, z, world.func_72805_g(x, y, z) & 0xFFFFFFF7, 3);
    }

    public boolean func_149744_f() {
        return true;
    }

    public int func_149709_b(IBlockAccess world, int x, int y, int z, int s) {
        int meta = world.func_72805_g(x, y, z);
        return (meta & 8) != 0 ? 15 : 0;
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        invIcon = IconHelper.forBlock(par1IconRegister, (Block)this, 0);
        worldIcon = IconHelper.forBlock(par1IconRegister, (Block)this, 1);
        invIconRed = IconHelper.forBlock(par1IconRegister, (Block)this, 2);
        worldIconRed = IconHelper.forBlock(par1IconRegister, (Block)this, 3);
    }

    public IIcon func_149691_a(int side, int meta) {
        return meta > 0 ? invIconRed : invIcon;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return LibRenderIDs.idLightRelay;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileLightRelay();
    }

    @Override
    public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
        return false;
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.luminizerTransport;
    }
}

