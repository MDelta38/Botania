/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFlower
 *  net.minecraft.block.IGrowable
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.Botania;
import vazkii.botania.common.achievement.IPickupAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockModFlower
extends BlockFlower
implements ILexiconable,
IPickupAchievement,
IGrowable {
    public static IIcon[] icons;
    public static IIcon[] iconsAlt;
    public int originalLight;
    public static final String ALT_DIR = "alt";

    protected BlockModFlower() {
        this("flower");
    }

    protected BlockModFlower(String name) {
        super(0);
        this.func_149663_c(name);
        this.func_149711_c(0.0f);
        this.func_149672_a(field_149779_h);
        this.func_149676_a(0.3f, 0.0f, 0.3f, 0.8f, 1.0f, 0.8f);
        this.func_149675_a(false);
        this.func_149647_a(this.registerInCreative() ? BotaniaCreativeTab.INSTANCE : null);
    }

    public boolean registerInCreative() {
        return true;
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
        icons = new IIcon[17];
        iconsAlt = new IIcon[17];
        for (int i = 0; i < icons.length; ++i) {
            BlockModFlower.icons[i] = IconHelper.forBlock(par1IconRegister, (Block)this, i);
            BlockModFlower.iconsAlt[i] = IconHelper.forBlock(par1IconRegister, (Block)this, i, ALT_DIR);
        }
    }

    public Block func_149715_a(float p_149715_1_) {
        this.originalLight = (int)(p_149715_1_ * 15.0f);
        return super.func_149715_a(p_149715_1_);
    }

    public IIcon func_149691_a(int par1, int par2) {
        return (ConfigHandler.altFlowerTextures ? iconsAlt : icons)[Math.min(icons.length - 1, par2)];
    }

    public int func_149645_b() {
        return LibRenderIDs.idSpecialFlower;
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random) {
        int meta = par1World.func_72805_g(par2, par3, par4);
        float[] color = EntitySheep.field_70898_d[meta];
        if (par5Random.nextDouble() < ConfigHandler.flowerParticleFrequency) {
            Botania.proxy.sparkleFX(par1World, (double)par2 + 0.3 + (double)par5Random.nextFloat() * 0.5, (double)par3 + 0.5 + (double)par5Random.nextFloat() * 0.5, (double)par4 + 0.3 + (double)par5Random.nextFloat() * 0.5, color[0], color[1], color[2], par5Random.nextFloat(), 5);
        }
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.flowers;
    }

    @Override
    public Achievement getAchievementOnPickup(ItemStack stack, EntityPlayer player, EntityItem item) {
        return ModAchievements.flowerPickup;
    }

    public boolean func_149851_a(World world, int x, int y, int z, boolean fuckifiknow) {
        return world.func_147437_c(x, y + 1, z);
    }

    public boolean func_149852_a(World world, Random rand, int x, int y, int z) {
        return this.func_149851_a(world, x, y, z, false);
    }

    public void func_149853_b(World world, Random rand, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);
        BlockModFlower.placeDoubleFlower(world, x, y, z, meta, 3);
    }

    public static void placeDoubleFlower(World world, int x, int y, int z, int meta, int flags) {
        Block flower = meta >= 8 ? ModBlocks.doubleFlower2 : ModBlocks.doubleFlower1;
        int placeMeta = meta & 7;
        world.func_147465_d(x, y, z, flower, placeMeta, flags);
        world.func_147465_d(x, y + 1, z, flower, placeMeta | 8, flags);
    }
}

