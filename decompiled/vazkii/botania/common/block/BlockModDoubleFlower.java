/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDoublePlant
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatList
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.event.ForgeEventFactory
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockModDoubleFlower
extends BlockDoublePlant
implements ILexiconable {
    private static final int COUNT = 8;
    IIcon[] field_149894_N;
    IIcon[] field_149893_M;
    IIcon[] doublePlantTopIconsAlt;
    IIcon[] doublePlantBottomIconsAlt;
    final int offset;

    public BlockModDoubleFlower(boolean second) {
        this.offset = second ? 8 : 0;
        this.func_149663_c("doubleFlower" + (second ? 2 : 1));
        this.func_149711_c(0.0f);
        this.func_149672_a(field_149779_h);
        this.func_149675_a(false);
        this.func_149647_a(BotaniaCreativeTab.INSTANCE);
    }

    public Block func_149663_c(String par1Str) {
        if (!par1Str.equals("doublePlant")) {
            GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)par1Str);
        }
        return super.func_149663_c(par1Str);
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return null;
    }

    public int func_149692_a(int p_149692_1_) {
        return p_149692_1_ & 7;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149888_a(boolean top, int index) {
        return (ConfigHandler.altFlowerTextures ? (top ? this.doublePlantTopIconsAlt : this.doublePlantBottomIconsAlt) : (top ? this.field_149894_N : this.field_149893_M))[index & 7];
    }

    public void func_149889_c(World p_149889_1_, int p_149889_2_, int p_149889_3_, int p_149889_4_, int p_149889_5_, int p_149889_6_) {
        p_149889_1_.func_147465_d(p_149889_2_, p_149889_3_, p_149889_4_, (Block)this, p_149889_5_, p_149889_6_);
        p_149889_1_.func_147465_d(p_149889_2_, p_149889_3_ + 1, p_149889_4_, (Block)this, p_149889_5_ | 8, p_149889_6_);
    }

    public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
        p_149689_1_.func_147465_d(p_149689_2_, p_149689_3_ + 1, p_149689_4_, (Block)this, p_149689_6_.func_77960_j() | 8, 2);
    }

    public boolean func_149851_a(World world, int x, int y, int z, boolean fuckifiknow) {
        return false;
    }

    public void func_149636_a(World p_149636_1_, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_, int p_149636_5_, int p_149636_6_) {
        if (p_149636_1_.field_72995_K || p_149636_2_.func_71045_bC() == null || p_149636_2_.func_71045_bC().func_77973_b() != Items.field_151097_aZ || BlockModDoubleFlower.func_149887_c((int)p_149636_6_)) {
            this.harvestBlockCopy(p_149636_1_, p_149636_2_, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_);
        }
    }

    public void harvestBlockCopy(World p_149636_1_, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_, int p_149636_5_, int p_149636_6_) {
        p_149636_2_.func_71064_a(StatList.field_75934_C[BlockModDoubleFlower.func_149682_b((Block)this)], 1);
        p_149636_2_.func_71020_j(0.025f);
        if (this.canSilkHarvest(p_149636_1_, p_149636_2_, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_) && EnchantmentHelper.func_77502_d((EntityLivingBase)p_149636_2_)) {
            ArrayList<ItemStack> items = new ArrayList<ItemStack>();
            ItemStack itemstack = this.func_149644_j(p_149636_6_);
            if (itemstack != null) {
                items.add(itemstack);
            }
            ForgeEventFactory.fireBlockHarvesting(items, (World)p_149636_1_, (Block)this, (int)p_149636_3_, (int)p_149636_4_, (int)p_149636_5_, (int)p_149636_6_, (int)0, (float)1.0f, (boolean)true, (EntityPlayer)p_149636_2_);
            for (ItemStack is : items) {
                this.func_149642_a(p_149636_1_, p_149636_3_, p_149636_4_, p_149636_5_, is);
            }
        } else {
            this.harvesters.set(p_149636_2_);
            int i1 = EnchantmentHelper.func_77517_e((EntityLivingBase)p_149636_2_);
            this.func_149697_b(p_149636_1_, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_, i1);
            this.harvesters.set(null);
        }
    }

    public void func_149681_a(World p_149681_1_, int p_149681_2_, int p_149681_3_, int p_149681_4_, int p_149681_5_, EntityPlayer p_149681_6_) {
        if (BlockModDoubleFlower.func_149887_c((int)p_149681_5_)) {
            if (p_149681_1_.func_147439_a(p_149681_2_, p_149681_3_ - 1, p_149681_4_) == this) {
                if (!p_149681_6_.field_71075_bZ.field_75098_d) {
                    int i1 = p_149681_1_.func_72805_g(p_149681_2_, p_149681_3_ - 1, p_149681_4_);
                    int j1 = BlockModDoubleFlower.func_149890_d((int)i1);
                    if (j1 == 3 || j1 == 2) {
                        p_149681_1_.func_147468_f(p_149681_2_, p_149681_3_ - 1, p_149681_4_);
                    }
                } else {
                    p_149681_1_.func_147468_f(p_149681_2_, p_149681_3_ - 1, p_149681_4_);
                }
            }
        } else if (p_149681_6_.field_71075_bZ.field_75098_d && p_149681_1_.func_147439_a(p_149681_2_, p_149681_3_ + 1, p_149681_4_) == this) {
            p_149681_1_.func_147465_d(p_149681_2_, p_149681_3_ + 1, p_149681_4_, Blocks.field_150350_a, 0, 2);
        }
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack((Block)this, 1, world.func_72805_g(x, y, z) & 7));
        return ret;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
        return new ArrayList<ItemStack>();
    }

    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
        boolean top = BlockModDoubleFlower.func_149887_c((int)p_149691_2_);
        return (ConfigHandler.altFlowerTextures ? (top ? this.doublePlantTopIconsAlt : this.doublePlantBottomIconsAlt) : (top ? this.field_149894_N : this.field_149893_M))[p_149691_2_ & 7];
    }

    public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side) {
        int meta = world.func_72805_g(x, y, z);
        boolean top = BlockModDoubleFlower.func_149887_c((int)meta);
        if (top) {
            meta = world.func_72805_g(x, y - 1, z);
        }
        return (ConfigHandler.altFlowerTextures ? (top ? this.doublePlantBottomIconsAlt : this.doublePlantTopIconsAlt) : (top ? this.field_149893_M : this.field_149894_N))[meta & 7];
    }

    public void func_149651_a(IIconRegister register) {
        this.field_149894_N = new IIcon[8];
        this.field_149893_M = new IIcon[8];
        this.doublePlantTopIconsAlt = new IIcon[8];
        this.doublePlantBottomIconsAlt = new IIcon[8];
        for (int i = 0; i < 8; ++i) {
            int off = this.offset(i);
            this.field_149894_N[i] = IconHelper.forName(register, "flower" + off + "Tall0");
            this.field_149893_M[i] = IconHelper.forName(register, "flower" + off + "Tall1");
            this.doublePlantTopIconsAlt[i] = IconHelper.forName(register, "flower" + off + "Tall0", "alt");
            this.doublePlantBottomIconsAlt[i] = IconHelper.forName(register, "flower" + off + "Tall1", "alt");
        }
    }

    public int func_149720_d(IBlockAccess blockAccess, int x, int y, int z) {
        return 0xFFFFFF;
    }

    public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
        for (int i = 0; i < 8; ++i) {
            p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
        }
    }

    public int func_149645_b() {
        return LibRenderIDs.idDoubleFlower;
    }

    public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random) {
        int meta = par1World.func_72805_g(par2, par3, par4);
        float[] color = EntitySheep.field_70898_d[this.offset(meta & 7)];
        if (par5Random.nextDouble() < ConfigHandler.flowerParticleFrequency) {
            Botania.proxy.sparkleFX(par1World, (double)par2 + 0.3 + (double)par5Random.nextFloat() * 0.5, (double)par3 + 0.5 + (double)par5Random.nextFloat() * 0.5, (double)par4 + 0.3 + (double)par5Random.nextFloat() * 0.5, color[0], color[1], color[2], par5Random.nextFloat(), 5);
        }
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.flowers;
    }

    int offset(int meta) {
        return meta + this.offset;
    }
}

