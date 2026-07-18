/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFlower
 *  net.minecraft.block.ITileEntityProvider
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.ISpecialFlower;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.BlockModFlower;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileSpecialFlower;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.integration.coloredlights.LightHelper;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public class BlockSpecialFlower
extends BlockFlower
implements ITileEntityProvider,
ISpecialFlower,
IWandable,
ILexiconable,
IWandHUD {
    public static Map<String, IIcon> icons = new HashMap<String, IIcon>();
    public static Map<String, IIcon> iconsAlt = new HashMap<String, IIcon>();

    protected BlockSpecialFlower() {
        super(0);
        this.func_149663_c("specialFlower");
        this.func_149711_c(0.1f);
        this.func_149672_a(field_149779_h);
        this.func_149675_a(false);
        this.func_149647_a(BotaniaCreativeTab.INSTANCE);
        this.func_149676_a(0.3f, 0.0f, 0.3f, 0.8f, 1.0f, 0.8f);
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int currentLight = ((TileSpecialFlower)world.func_147438_o(x, y, z)).getLightValue();
        if (currentLight == -1) {
            currentLight = 0;
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

    public int func_149645_b() {
        return LibRenderIDs.idSpecialFlower;
    }

    public Block func_149663_c(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockSpecialFlower.class, (String)par1Str);
        return super.func_149663_c(par1Str);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (String s : BotaniaAPI.subtilesForCreativeMenu) {
            par3List.add(ItemBlockSpecialFlower.ofType(s));
            if (!BotaniaAPI.miniFlowers.containsKey((Object)s)) continue;
            par3List.add(ItemBlockSpecialFlower.ofType((String)BotaniaAPI.miniFlowers.get((Object)s)));
        }
    }

    public void func_149651_a(IIconRegister par1IconRegister) {
        for (String s : BotaniaAPI.getAllSubTiles()) {
            if (s.isEmpty()) continue;
            BotaniaAPI.getSignatureForName(s).registerIcons(par1IconRegister);
        }
    }

    public IIcon func_149673_e(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
        return ((TileSpecialFlower)par1iBlockAccess.func_147438_o(par2, par3, par4)).getIcon();
    }

    public IIcon func_149691_a(int par1, int par2) {
        return BlockModFlower.icons[16];
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        String name = ((TileSpecialFlower)world.func_147438_o((int)x, (int)y, (int)z)).subTileName;
        return ItemBlockSpecialFlower.ofType(name);
    }

    protected boolean func_149854_a(Block block) {
        return super.func_149854_a(block) || block == ModBlocks.redStringRelay || block == Blocks.field_150391_bh;
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
            list.add(ItemBlockSpecialFlower.ofType(name));
            ((TileSpecialFlower)tile).getDrops(list);
        }
        return list;
    }

    public boolean func_149696_a(World par1World, int par2, int par3, int par4, int par5, int par6) {
        super.func_149696_a(par1World, par2, par3, par4, par5, par6);
        TileEntity tileentity = par1World.func_147438_o(par2, par3, par4);
        return tileentity != null ? tileentity.func_145842_c(par5, par6) : false;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileSpecialFlower();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return ((TileSpecialFlower)world.func_147438_o(x, y, z)).getEntry();
    }

    @Override
    public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
        return ((TileSpecialFlower)world.func_147438_o(x, y, z)).onWanded(stack, player);
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        ((TileSpecialFlower)world.func_147438_o(x, y, z)).onBlockPlacedBy(world, x, y, z, entity, stack);
    }

    public void func_149726_b(World world, int x, int y, int z) {
        ((TileSpecialFlower)world.func_147438_o(x, y, z)).onBlockAdded(world, x, y, z);
    }

    public int func_149720_d(IBlockAccess world, int x, int y, int z) {
        float[] rgb = EntitySheep.field_70898_d[world.func_72805_g(x, y, z)];
        return ((int)(rgb[0] * 255.0f) << 16) + ((int)(rgb[1] * 255.0f) << 8) + (int)(rgb[2] * 255.0f);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        int oldMeta;
        int newMeta;
        ItemStack stack = player.func_71045_bC();
        if (stack != null && stack.func_77973_b() == ModItems.dye && (newMeta = stack.func_77960_j()) != (oldMeta = world.func_72805_g(x, y, z))) {
            world.func_72921_c(x, y, z, newMeta, 3);
        }
        return ((TileSpecialFlower)world.func_147438_o(x, y, z)).onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    }

    @Override
    public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {
        ((TileSpecialFlower)world.func_147438_o(x, y, z)).renderHUD(mc, res);
    }

    static {
        BotaniaAPI.subtilesForCreativeMenu.addAll(Arrays.asList("puredaisy", "manastar", "daybloom", "nightshade", "endoflame", "hydroangeas", "thermalily", "arcanerose", "munchdew", "entropinnyum", "kekimurus", "gourmaryllis", "narslimmus", "spectrolus", "rafflowsia", "dandelifeon", "jadedAmaranthus", "bellethorn", "dreadthorn", "heiseiDream", "tigerseye", "marimorphosis", "orechid", "orechidIgnem", "fallenKanade", "exoflame", "agricarnation", "hopperhock", "rannuncarpus", "tangleberrie", "jiyuulia", "hyacidus", "medumone", "pollidisiac", "clayconia", "loonium", "daffomill", "vinculotus", "spectranthemum", "bubbell", "solegnolia"));
    }
}

