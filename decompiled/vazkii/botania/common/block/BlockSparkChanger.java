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
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block;

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
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.block.tile.TileSparkChanger;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockSparkChanger
extends BlockModContainer
implements ILexiconable {
    IIcon[] icons;
    Random random;

    public BlockSparkChanger() {
        super(Material.field_151576_e);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.1875f, 1.0f);
        this.func_149711_c(2.0f);
        this.func_149752_b(10.0f);
        this.func_149672_a(field_149769_e);
        this.func_149663_c("sparkChanger");
        this.random = new Random();
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149655_b(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_) {
        return false;
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[3];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forBlock(par1IconRegister, (Block)this, i);
        }
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.icons[Math.min(2, par1)];
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        boolean powered;
        boolean power = world.func_72864_z(x, y, z) || world.func_72864_z(x, y + 1, z);
        int meta = world.func_72805_g(x, y, z);
        boolean bl = powered = (meta & 8) != 0;
        if (power && !powered) {
            ((TileSparkChanger)world.func_147438_o(x, y, z)).doSwap();
            world.func_72921_c(x, y, z, meta | 8, 4);
        } else if (!power && powered) {
            world.func_72921_c(x, y, z, meta & 0xFFFFFFF7, 4);
        }
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int s, float xs, float ys, float zs) {
        TileSparkChanger changer = (TileSparkChanger)world.func_147438_o(x, y, z);
        ItemStack cstack = changer.func_70301_a(0);
        ItemStack pstack = player.func_71045_bC();
        if (cstack != null) {
            changer.func_70299_a(0, null);
            world.func_147453_f(x, y, z, (Block)this);
            changer.func_70296_d();
            if (!player.field_71071_by.func_70441_a(cstack)) {
                player.func_71019_a(cstack, false);
            }
            return true;
        }
        if (pstack != null && pstack.func_77973_b() == ModItems.sparkUpgrade) {
            changer.func_70299_a(0, pstack.func_77946_l().func_77979_a(1));
            world.func_147453_f(x, y, z, (Block)this);
            changer.func_70296_d();
            --pstack.field_77994_a;
            if (pstack.field_77994_a == 0) {
                player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
            }
            return true;
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

    public int func_149736_g(World world, int x, int y, int z, int s) {
        TileSparkChanger changer = (TileSparkChanger)world.func_147438_o(x, y, z);
        ItemStack stack = changer.func_70301_a(0);
        if (stack == null) {
            return 0;
        }
        return stack.func_77960_j() + 1;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileSparkChanger();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.sparkChanger;
    }
}

