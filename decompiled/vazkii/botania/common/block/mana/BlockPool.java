/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.mana;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.mana.TilePool;
import vazkii.botania.common.item.block.ItemBlockPool;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockPool
extends BlockModContainer
implements IWandHUD,
IWandable,
ILexiconable,
ICraftAchievement {
    boolean lastFragile = false;
    public static IIcon manaIcon;

    public BlockPool() {
        super(Material.field_151576_e);
        this.func_149711_c(2.0f);
        this.func_149752_b(10.0f);
        this.func_149672_a(field_149769_e);
        this.func_149663_c("pool");
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
        BotaniaAPI.blacklistBlockFromMagnet((Block)this, Short.MAX_VALUE);
    }

    @Override
    protected boolean shouldRegisterInNameSet() {
        return false;
    }

    @Override
    public Block func_149663_c(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockPool.class, (String)par1Str);
        return super.func_149663_c(par1Str);
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        manaIcon = IconHelper.forName(par1IconRegister, "manaWater");
    }

    public int func_149692_a(int meta) {
        return meta;
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TilePool pool = (TilePool)par1World.func_147438_o(par2, par3, par4);
        this.lastFragile = pool.fragile;
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        if (!this.lastFragile) {
            drops.add(new ItemStack((Block)this, 1, metadata));
        }
        return drops;
    }

    public void func_149666_a(Item par1, CreativeTabs par2, List par3) {
        par3.add(new ItemStack(par1, 1, 0));
        par3.add(new ItemStack(par1, 1, 2));
        par3.add(new ItemStack(par1, 1, 3));
        par3.add(new ItemStack(par1, 1, 1));
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TilePool();
    }

    public void func_149670_a(World par1World, int par2, int par3, int par4, Entity par5Entity) {
        TilePool tile;
        if (par5Entity instanceof EntityItem && (tile = (TilePool)par1World.func_147438_o(par2, par3, par4)).collideEntityItem((EntityItem)par5Entity)) {
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(par1World, par2, par3, par4);
        }
    }

    public void func_149743_a(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_) {
        float f = 0.0625f;
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, f, 1.0f);
        super.func_149743_a(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        this.func_149676_a(0.0f, 0.0f, 0.0f, f, 0.5f, 1.0f);
        super.func_149743_a(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, f);
        super.func_149743_a(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        this.func_149676_a(1.0f - f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
        super.func_149743_a(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        this.func_149676_a(0.0f, 0.0f, 1.0f - f, 1.0f, 0.5f, 1.0f);
        super.func_149743_a(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return side == ForgeDirection.DOWN;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public IIcon func_149691_a(int par1, int par2) {
        return ModBlocks.livingrock.func_149691_a(par1, 0);
    }

    public int func_149645_b() {
        return LibRenderIDs.idPool;
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World par1World, int par2, int par3, int par4, int par5) {
        TilePool pool = (TilePool)par1World.func_147438_o(par2, par3, par4);
        int val = (int)((double)pool.getCurrentMana() / (double)pool.manaCap * 15.0);
        if (pool.getCurrentMana() > 0) {
            val = Math.max(val, 1);
        }
        return val;
    }

    @Override
    public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {
        ((TilePool)world.func_147438_o(x, y, z)).renderHUD(mc, res);
    }

    @Override
    public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
        ((TilePool)world.func_147438_o(x, y, z)).onWanded(player, stack);
        return true;
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return world.func_72805_g(x, y, z) == 3 ? LexiconData.rainbowRod : LexiconData.pool;
    }

    @Override
    public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
        return ModAchievements.manaPoolPickup;
    }
}

