/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Optional$Interface
 *  cpw.mods.fml.common.Optional$Method
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.api.crafting.IInfusionStabiliser
 */
package vazkii.botania.common.block.decor;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.crafting.IInfusionStabiliser;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.decor.IFloatingFlower;
import vazkii.botania.common.block.tile.TileFloatingFlower;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.integration.coloredlights.ColoredLightHelper;
import vazkii.botania.common.item.IFloatingFlowerVariant;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;

@Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.crafting.IInfusionStabiliser", striprefs=true)
public class BlockFloatingFlower
extends BlockModContainer
implements ILexiconable,
IInfusionStabiliser {
    public BlockFloatingFlower() {
        this("miniIsland");
    }

    public BlockFloatingFlower(String name) {
        super(Material.field_151578_c);
        this.func_149663_c(name);
        this.func_149711_c(0.5f);
        this.func_149672_a(field_149767_g);
        this.func_149715_a(1.0f);
        float f = 0.1f;
        this.func_149676_a(f, f, f, 1.0f - f, 1.0f - f, 1.0f - f);
    }

    @Optional.Method(modid="easycoloredlights")
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return ColoredLightHelper.getPackedColor(world.func_72805_g(x, y, z), this.originalLight);
    }

    @Override
    protected boolean shouldRegisterInNameSet() {
        return false;
    }

    @Override
    public Block func_149663_c(String par1Str) {
        this.register(par1Str);
        return super.func_149663_c(par1Str);
    }

    protected void register(String name) {
        GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)name);
    }

    public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random) {
        int meta = par1World.func_72805_g(par2, par3, par4);
        float[] color = EntitySheep.field_70898_d[meta];
        if (par5Random.nextDouble() < ConfigHandler.flowerParticleFrequency) {
            Botania.proxy.sparkleFX(par1World, (double)par2 + 0.3 + (double)par5Random.nextFloat() * 0.5, (double)par3 + 0.5 + (double)par5Random.nextFloat() * 0.5, (double)par4 + 0.3 + (double)par5Random.nextFloat() * 0.5, color[0], color[1], color[2], par5Random.nextFloat(), 5);
        }
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    public void func_149666_a(Item par1, CreativeTabs par2, List par3) {
        for (int i = 0; i < 16; ++i) {
            par3.add(new ItemStack(par1, 1, i));
        }
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public IIcon func_149691_a(int par1, int par2) {
        return Blocks.field_150346_d.func_149691_a(par1, par2);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.func_71045_bC();
        if (stack != null) {
            IFloatingFlower.IslandType newType;
            IFloatingFlower flower = (IFloatingFlower)world.func_147438_o(x, y, z);
            IFloatingFlower.IslandType type = null;
            if (stack.func_77973_b() == Items.field_151126_ay) {
                type = IFloatingFlower.IslandType.SNOW;
            } else if (stack.func_77973_b() instanceof IFloatingFlowerVariant && (newType = ((IFloatingFlowerVariant)stack.func_77973_b()).getIslandType(stack)) != null) {
                type = newType;
            }
            if (type != null && type != flower.getIslandType()) {
                if (!world.field_72995_K) {
                    flower.setIslandType(type);
                    VanillaPacketDispatcher.dispatchTEToNearbyPlayers(world, x, y, z);
                }
                if (!player.field_71075_bZ.field_75098_d) {
                    --stack.field_77994_a;
                }
                return true;
            }
        }
        return false;
    }

    public int func_149645_b() {
        return LibRenderIDs.idMiniIsland;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileFloatingFlower();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.shinyFlowers;
    }

    public boolean canStabaliseInfusion(World world, int x, int y, int z) {
        return ConfigHandler.enableThaumcraftStablizers;
    }
}

