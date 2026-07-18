/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockPistonBase
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.mana;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.ILens;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.api.wand.IWireframeAABBProvider;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.mana.TileSpreader;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockSpreader
extends BlockModContainer
implements IWandable,
IWandHUD,
ILexiconable,
IWireframeAABBProvider {
    Random random;

    public BlockSpreader() {
        super(Material.field_151575_d);
        this.func_149711_c(2.0f);
        this.func_149672_a(field_149766_f);
        this.func_149663_c("spreader");
        this.random = new Random();
    }

    @Override
    protected boolean shouldRegisterInNameSet() {
        return false;
    }

    @Override
    public Block func_149663_c(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)par1Str);
        return super.func_149663_c(par1Str);
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    public void func_149666_a(Item par1, CreativeTabs par2, List par3) {
        for (int i = 0; i < 4; ++i) {
            par3.add(new ItemStack(par1, 1, i));
        }
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        int orientation = BlockPistonBase.func_150071_a((World)par1World, (int)par2, (int)par3, (int)par4, (EntityLivingBase)par5EntityLivingBase);
        TileSpreader spreader = (TileSpreader)par1World.func_147438_o(par2, par3, par4);
        par1World.func_72921_c(par2, par3, par4, par6ItemStack.func_77960_j(), 3);
        switch (orientation) {
            case 0: {
                spreader.rotationY = -90.0f;
                break;
            }
            case 1: {
                spreader.rotationY = 90.0f;
                break;
            }
            case 2: {
                spreader.rotationX = 270.0f;
                break;
            }
            case 3: {
                spreader.rotationX = 90.0f;
                break;
            }
            case 4: {
                break;
            }
            default: {
                spreader.rotationX = 180.0f;
            }
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
        return par2 >= 2 ? ModBlocks.dreamwood.func_149691_a(par1, 0) : ModBlocks.livingwood.func_149691_a(par1, 0);
    }

    public int func_149645_b() {
        return LibRenderIDs.idSpreader;
    }

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        boolean wool;
        TileEntity tile = par1World.func_147438_o(par2, par3, par4);
        if (!(tile instanceof TileSpreader)) {
            return false;
        }
        TileSpreader spreader = (TileSpreader)tile;
        ItemStack lens = spreader.func_70301_a(0);
        ItemStack heldItem = par5EntityPlayer.func_71045_bC();
        boolean isHeldItemLens = heldItem != null && heldItem.func_77973_b() instanceof ILens;
        boolean bl = wool = heldItem != null && heldItem.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150325_L);
        if (heldItem != null && heldItem.func_77973_b() == ModItems.twigWand) {
            return false;
        }
        if (lens == null && isHeldItemLens) {
            if (!par5EntityPlayer.field_71075_bZ.field_75098_d) {
                par5EntityPlayer.field_71071_by.func_70299_a(par5EntityPlayer.field_71071_by.field_70461_c, null);
            }
            spreader.func_70299_a(0, heldItem.func_77946_l());
            spreader.func_70296_d();
        } else if (lens != null && !wool) {
            ItemStack add = lens.func_77946_l();
            if (!par5EntityPlayer.field_71071_by.func_70441_a(add)) {
                par5EntityPlayer.func_71019_a(add, false);
            }
            spreader.func_70299_a(0, null);
            spreader.func_70296_d();
        }
        if (wool && spreader.paddingColor == -1) {
            spreader.paddingColor = heldItem.func_77960_j();
            --heldItem.field_77994_a;
            if (heldItem.field_77994_a == 0) {
                par5EntityPlayer.field_71071_by.func_70299_a(par5EntityPlayer.field_71071_by.field_70461_c, null);
            }
        } else if (heldItem == null && spreader.paddingColor != -1 && lens == null) {
            ItemStack pad = new ItemStack(Blocks.field_150325_L, 1, spreader.paddingColor);
            if (!par5EntityPlayer.field_71071_by.func_70441_a(pad)) {
                par5EntityPlayer.func_71019_a(pad, false);
            }
            spreader.paddingColor = -1;
            spreader.func_70296_d();
        }
        return true;
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileEntity tile = par1World.func_147438_o(par2, par3, par4);
        if (!(tile instanceof TileSpreader)) {
            return;
        }
        TileSpreader inv = (TileSpreader)tile;
        if (inv != null) {
            for (int j1 = 0; j1 < inv.func_70302_i_() + 1; ++j1) {
                ItemStack itemstack;
                Object object = j1 >= inv.func_70302_i_() ? (inv.paddingColor == -1 ? null : new ItemStack(Blocks.field_150325_L, 1, inv.paddingColor)) : (itemstack = inv.func_70301_a(j1));
                if (itemstack == null) continue;
                float f = this.random.nextFloat() * 0.8f + 0.1f;
                float f1 = this.random.nextFloat() * 0.8f + 0.1f;
                float f2 = this.random.nextFloat() * 0.8f + 0.1f;
                while (itemstack.field_77994_a > 0) {
                    int k1 = this.random.nextInt(21) + 10;
                    if (k1 > itemstack.field_77994_a) {
                        k1 = itemstack.field_77994_a;
                    }
                    itemstack.field_77994_a -= k1;
                    EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
                    float f3 = 0.05f;
                    entityitem.field_70159_w = (float)this.random.nextGaussian() * f3;
                    entityitem.field_70181_x = (float)this.random.nextGaussian() * f3 + 0.2f;
                    entityitem.field_70179_y = (float)this.random.nextGaussian() * f3;
                    if (itemstack.func_77942_o()) {
                        entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
                    }
                    par1World.func_72838_d((Entity)entityitem);
                }
            }
            par1World.func_147453_f(par2, par3, par4, par5);
        }
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    @Override
    public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
        ((TileSpreader)world.func_147438_o(x, y, z)).onWanded(player, stack);
        return true;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileSpreader();
    }

    @Override
    public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {
        ((TileSpreader)world.func_147438_o(x, y, z)).renderHUD(mc, res);
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        int meta = world.func_72805_g(x, y, z);
        return meta == 0 ? LexiconData.spreader : (meta == 1 ? LexiconData.redstoneSpreader : LexiconData.dreamwoodSpreader);
    }

    @Override
    public AxisAlignedBB getWireframeAABB(World world, int x, int y, int z) {
        float f = 0.0625f;
        return AxisAlignedBB.func_72330_a((double)((float)x + f), (double)((float)y + f), (double)((float)z + f), (double)((float)(x + 1) - f), (double)((float)(y + 1) - f), (double)((float)(z + 1) - f));
    }
}

