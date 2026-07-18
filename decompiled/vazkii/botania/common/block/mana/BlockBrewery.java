/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.mana;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileBrewery;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockBrewery
extends BlockModContainer
implements ILexiconable,
IWandHUD {
    Random random;

    public BlockBrewery() {
        super(Material.field_151576_e);
        float f = 0.375f;
        this.func_149676_a(f, 0.05f, f, 1.0f - f, 0.95f, 1.0f - f);
        this.func_149663_c("brewery");
        this.func_149711_c(2.0f);
        this.func_149752_b(10.0f);
        this.func_149672_a(field_149769_e);
        this.random = new Random();
    }

    public IIcon func_149691_a(int side, int meta) {
        return Blocks.field_150347_e.func_149691_a(side, meta);
    }

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileBrewery brew = (TileBrewery)par1World.func_147438_o(par2, par3, par4);
        if (par5EntityPlayer.func_70093_af()) {
            if (brew.recipe == null && par1World.func_72805_g(par2, par3, par4) == 0) {
                for (int i = brew.func_70302_i_() - 1; i >= 0; --i) {
                    ItemStack stackAt = brew.func_70301_a(i);
                    if (stackAt == null) continue;
                    ItemStack copy = stackAt.func_77946_l();
                    if (!par5EntityPlayer.field_71071_by.func_70441_a(copy)) {
                        par5EntityPlayer.func_71019_a(copy, false);
                    }
                    brew.func_70299_a(i, null);
                    par1World.func_147453_f(par2, par3, par4, (Block)this);
                    break;
                }
            }
        } else {
            ItemStack stack = par5EntityPlayer.func_71045_bC();
            if (stack != null) {
                return brew.addItem(par5EntityPlayer, stack);
            }
        }
        return false;
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileSimpleInventory inv = (TileSimpleInventory)par1World.func_147438_o(par2, par3, par4);
        if (inv != null) {
            for (int j1 = 0; j1 < inv.func_70302_i_(); ++j1) {
                ItemStack itemstack = inv.func_70301_a(j1);
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

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World par1World, int par2, int par3, int par4, int par5) {
        TileBrewery brew = (TileBrewery)par1World.func_147438_o(par2, par3, par4);
        return brew.signal;
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    public int func_149645_b() {
        return LibRenderIDs.idBrewery;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileBrewery();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.brewery;
    }

    @Override
    public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {
        ((TileBrewery)world.func_147438_o(x, y, z)).renderHUD(mc, res);
    }
}

