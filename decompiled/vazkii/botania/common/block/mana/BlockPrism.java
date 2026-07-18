/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.mana;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.ILens;
import vazkii.botania.api.mana.IManaTrigger;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.block.tile.mana.TilePrism;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockPrism
extends BlockModContainer
implements IManaTrigger,
ILexiconable {
    Random random;
    IIcon[] icons;

    public BlockPrism() {
        super(Material.field_151592_s);
        this.func_149711_c(0.3f);
        this.func_149672_a(field_149778_k);
        this.func_149715_a(1.0f);
        this.func_149663_c("prism");
        float f = 0.25f;
        this.func_149676_a(f, 0.0f, f, 1.0f - f, 1.0f, 1.0f - f);
        this.random = new Random();
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[2];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forBlock(par1IconRegister, (Block)this, i);
        }
    }

    public IIcon func_149691_a(int side, int meta) {
        return side > 1 ? this.icons[1] : this.icons[0];
    }

    public int func_149701_w() {
        return 1;
    }

    public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return null;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileEntity tile = par1World.func_147438_o(par2, par3, par4);
        if (!(tile instanceof TilePrism)) {
            return false;
        }
        TilePrism prism = (TilePrism)tile;
        ItemStack lens = prism.func_70301_a(0);
        ItemStack heldItem = par5EntityPlayer.func_71045_bC();
        boolean isHeldItemLens = heldItem != null && heldItem.func_77973_b() instanceof ILens;
        int meta = par1World.func_72805_g(par2, par3, par4);
        if (lens == null && isHeldItemLens) {
            if (!par5EntityPlayer.field_71075_bZ.field_75098_d) {
                par5EntityPlayer.field_71071_by.func_70299_a(par5EntityPlayer.field_71071_by.field_70461_c, null);
            }
            prism.func_70299_a(0, heldItem.func_77946_l());
            prism.func_70296_d();
            par1World.func_72921_c(par2, par3, par4, meta | 1, 3);
        } else if (lens != null) {
            ItemStack add = lens.func_77946_l();
            if (!par5EntityPlayer.field_71071_by.func_70441_a(add)) {
                par5EntityPlayer.func_71019_a(add, false);
            }
            prism.func_70299_a(0, null);
            prism.func_70296_d();
            par1World.func_72921_c(par2, par3, par4, meta & 0xE, 3);
        }
        return true;
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        boolean powered;
        boolean power = world.func_72864_z(x, y, z) || world.func_72864_z(x, y + 1, z);
        int meta = world.func_72805_g(x, y, z);
        boolean bl = powered = (meta & 8) != 0;
        if (!world.field_72995_K) {
            if (power && !powered) {
                world.func_72921_c(x, y, z, meta | 8, 3);
            } else if (!power && powered) {
                world.func_72921_c(x, y, z, meta & 0xFFFFFFF7, 3);
            }
        }
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileEntity tile = par1World.func_147438_o(par2, par3, par4);
        if (!(tile instanceof TileSimpleInventory)) {
            return;
        }
        TileSimpleInventory inv = (TileSimpleInventory)tile;
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

    public TileEntity func_149915_a(World world, int meta) {
        return new TilePrism();
    }

    @Override
    public void onBurstCollision(IManaBurst burst, World world, int x, int y, int z) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TilePrism) {
            ((TilePrism)tile).onBurstCollision(burst);
        }
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.prism;
    }
}

