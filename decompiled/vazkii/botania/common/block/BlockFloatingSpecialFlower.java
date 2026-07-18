/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.oredict.RecipeSorter
 *  net.minecraftforge.oredict.RecipeSorter$Category
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.RecipeSorter;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.ISpecialFlower;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.common.block.decor.BlockFloatingFlower;
import vazkii.botania.common.block.tile.TileFloatingSpecialFlower;
import vazkii.botania.common.block.tile.TileSpecialFlower;
import vazkii.botania.common.crafting.recipe.SpecialFloatingFlowerRecipe;
import vazkii.botania.common.integration.coloredlights.LightHelper;
import vazkii.botania.common.item.block.ItemBlockFloatingSpecialFlower;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public class BlockFloatingSpecialFlower
extends BlockFloatingFlower
implements ISpecialFlower,
IWandable,
ILexiconable,
IWandHUD {
    public BlockFloatingSpecialFlower() {
        super("floatingSpecialFlower");
        GameRegistry.addRecipe((IRecipe)new SpecialFloatingFlowerRecipe());
        RecipeSorter.register((String)"botania:floatingSpecialFlower", SpecialFloatingFlowerRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"");
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int currentLight = ((TileSpecialFlower)world.func_147438_o(x, y, z)).getLightValue();
        if (currentLight == -1) {
            currentLight = this.originalLight;
        }
        return LightHelper.getPackedColor(world.func_72805_g(x, y, z), currentLight);
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World world, int x, int y, int z, int side) {
        return ((TileSpecialFlower)world.func_147438_o(x, y, z)).getComparatorInputOverride(side);
    }

    public int func_149709_b(IBlockAccess world, int x, int y, int z, int side) {
        return ((TileSpecialFlower)world.func_147438_o(x, y, z)).getPowerLevel(side);
    }

    public int func_149748_c(IBlockAccess world, int x, int y, int z, int side) {
        return this.func_149709_b(world, x, y, z, side);
    }

    public boolean func_149744_f() {
        return true;
    }

    @Override
    public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random) {
    }

    @Override
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (String s : BotaniaAPI.subtilesForCreativeMenu) {
            par3List.add(ItemBlockSpecialFlower.ofType(new ItemStack(par1), s));
            if (!BotaniaAPI.miniFlowers.containsKey((Object)s)) continue;
            par3List.add(ItemBlockSpecialFlower.ofType(new ItemStack(par1), (String)BotaniaAPI.miniFlowers.get((Object)s)));
        }
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        String name = ((TileSpecialFlower)world.func_147438_o((int)x, (int)y, (int)z)).subTileName;
        return ItemBlockSpecialFlower.ofType(new ItemStack(world.func_147439_a(x, y, z)), name);
    }

    public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
        if (!par6EntityPlayer.field_71075_bZ.field_75098_d) {
            this.func_149697_b(par1World, par2, par3, par4, par5, 0);
            ((TileSpecialFlower)par1World.func_147438_o(par2, par3, par4)).onBlockHarvested(par1World, par2, par3, par4, par5, par6EntityPlayer);
        }
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null) {
            String name = ((TileSpecialFlower)tile).subTileName;
            list.add(ItemBlockSpecialFlower.ofType(new ItemStack(world.func_147439_a(x, y, z)), name));
            ((TileSpecialFlower)tile).getDrops(list);
        }
        return list;
    }

    public boolean func_149696_a(World par1World, int par2, int par3, int par4, int par5, int par6) {
        super.func_149696_a(par1World, par2, par3, par4, par5, par6);
        TileEntity tileentity = par1World.func_147438_o(par2, par3, par4);
        return tileentity != null ? tileentity.func_145842_c(par5, par6) : false;
    }

    @Override
    public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
        return ((TileSpecialFlower)world.func_147438_o(x, y, z)).onWanded(stack, player);
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        ((TileSpecialFlower)world.func_147438_o(x, y, z)).onBlockPlacedBy(world, x, y, z, entity, stack);
    }

    @Override
    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        return ((TileSpecialFlower)world.func_147438_o(x, y, z)).onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ) || super.func_149727_a(world, x, y, z, player, side, hitX, hitY, hitZ);
    }

    public void func_149726_b(World world, int x, int y, int z) {
        ((TileSpecialFlower)world.func_147438_o(x, y, z)).onBlockAdded(world, x, y, z);
    }

    @Override
    public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {
        ((TileSpecialFlower)world.func_147438_o(x, y, z)).renderHUD(mc, res);
    }

    @Override
    protected void register(String name) {
        GameRegistry.registerBlock((Block)this, ItemBlockFloatingSpecialFlower.class, (String)name);
    }

    @Override
    public TileEntity func_149915_a(World world, int meta) {
        return new TileFloatingSpecialFlower();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return ((TileSpecialFlower)world.func_147438_o(x, y, z)).getEntry();
    }
}

