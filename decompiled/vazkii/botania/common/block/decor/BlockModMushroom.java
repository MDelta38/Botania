/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Optional$Interface
 *  cpw.mods.fml.common.Optional$Method
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockMushroom
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.crafting.IInfusionStabiliser
 */
package vazkii.botania.common.block.decor;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMushroom;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.crafting.IInfusionStabiliser;
import vazkii.botania.api.item.IHornHarvestable;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.integration.coloredlights.ColoredLightHelper;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;

@Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.crafting.IInfusionStabiliser", striprefs=true)
public class BlockModMushroom
extends BlockMushroom
implements IInfusionStabiliser,
IHornHarvestable,
ILexiconable {
    public static IIcon[] icons;
    public int originalLight;

    public BlockModMushroom() {
        this.func_149663_c("mushroom");
        this.func_149715_a(0.2f);
        this.func_149711_c(0.0f);
        this.func_149672_a(field_149779_h);
        this.func_149676_a(0.3f, 0.0f, 0.3f, 0.8f, 1.0f, 0.8f);
        this.func_149675_a(false);
        this.func_149647_a(BotaniaCreativeTab.INSTANCE);
    }

    public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
    }

    public boolean func_149718_j(World p_149718_1_, int p_149718_2_, int p_149718_3_, int p_149718_4_) {
        if (p_149718_3_ >= 0 && p_149718_3_ < 256) {
            Block block = p_149718_1_.func_147439_a(p_149718_2_, p_149718_3_ - 1, p_149718_4_);
            return block == Blocks.field_150391_bh || block == Blocks.field_150346_d && p_149718_1_.func_72805_g(p_149718_2_, p_149718_3_ - 1, p_149718_4_) == 2 || block.canSustainPlant((IBlockAccess)p_149718_1_, p_149718_2_, p_149718_3_ - 1, p_149718_4_, ForgeDirection.UP, (IPlantable)this);
        }
        return false;
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < 16; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    public Block func_149663_c(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)par1Str);
        return super.func_149663_c(par1Str);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        icons = new IIcon[16];
        for (int i = 0; i < icons.length; ++i) {
            BlockModMushroom.icons[i] = IconHelper.forBlock(par1IconRegister, (Block)this, i);
        }
    }

    public Block func_149715_a(float p_149715_1_) {
        this.originalLight = (int)(p_149715_1_ * 15.0f);
        return super.func_149715_a(p_149715_1_);
    }

    @Optional.Method(modid="easycoloredlights")
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return ColoredLightHelper.getPackedColor(world.func_72805_g(x, y, z), this.originalLight);
    }

    public IIcon func_149691_a(int par1, int par2) {
        return icons[par2];
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random) {
        int meta = par1World.func_72805_g(par2, par3, par4);
        float[] color = EntitySheep.field_70898_d[meta];
        if (par5Random.nextDouble() < ConfigHandler.flowerParticleFrequency * 0.25) {
            Botania.proxy.sparkleFX(par1World, (double)par2 + 0.3 + (double)par5Random.nextFloat() * 0.5, (double)par3 + 0.5 + (double)par5Random.nextFloat() * 0.5, (double)par4 + 0.3 + (double)par5Random.nextFloat() * 0.5, color[0], color[1], color[2], par5Random.nextFloat(), 5);
        }
    }

    public boolean canStabaliseInfusion(World world, int x, int y, int z) {
        return ConfigHandler.enableThaumcraftStablizers;
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.mushrooms;
    }

    @Override
    public boolean canHornHarvest(World world, int x, int y, int z, ItemStack stack, IHornHarvestable.EnumHornType hornType) {
        return false;
    }

    @Override
    public boolean hasSpecialHornHarvest(World world, int x, int y, int z, ItemStack stack, IHornHarvestable.EnumHornType hornType) {
        return false;
    }

    @Override
    public void harvestByHorn(World world, int x, int y, int z, ItemStack stack, IHornHarvestable.EnumHornType hornType) {
    }
}

