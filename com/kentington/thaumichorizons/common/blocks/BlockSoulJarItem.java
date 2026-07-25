/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileSoulJar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSoulJarItem
extends ItemBlock {
    @SideOnly(value=Side.CLIENT)
    public IIcon icon;

    public BlockSoulJarItem(Block p_i45328_1_) {
        super(p_i45328_1_);
        this.func_77656_e(0);
        this.func_77625_d(1);
        this.func_77627_a(true);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        ItemStack soul = new ItemStack((Item)this, 1, 0);
        soul.field_77990_d = new NBTTagCompound();
        soul.field_77990_d.func_74757_a("isSoul", true);
        par3List.add(soul);
    }

    public String func_77653_i(ItemStack stack) {
        String stringy = StatCollector.func_74838_a((String)"item.jarredSoul.jarred");
        if (stack.func_77942_o()) {
            stringy = stringy + " " + stack.func_77978_p().func_74779_i("jarredCritterName");
            if (stack.func_77978_p().func_74767_n("isSoul")) {
                stringy = stringy + " " + StatCollector.func_74838_a((String)"item.jarredSoul.soul");
            }
        } else {
            stringy = StatCollector.func_74838_a((String)"item.jarredSoul.0.name");
        }
        return stringy;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:blank");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
        Block block = world.func_147439_a(x, y, z);
        if (block == Blocks.field_150431_aC && (world.func_72805_g(x, y, z) & 7) < 1) {
            side = 1;
        } else if (block != Blocks.field_150395_bd && block != Blocks.field_150329_H && block != Blocks.field_150330_I && !block.isReplaceable((IBlockAccess)world, x, y, z)) {
            if (side == 0) {
                --y;
            }
            if (side == 1) {
                ++y;
            }
            if (side == 2) {
                --z;
            }
            if (side == 3) {
                ++z;
            }
            if (side == 4) {
                --x;
            }
            if (side == 5) {
                ++x;
            }
        }
        if (stack.field_77994_a == 0) {
            return false;
        }
        if (!player.func_82247_a(x, y, z, side, stack)) {
            return false;
        }
        if (world.func_147472_a(ThaumicHorizons.blockJar, x, y, z, false, side, (Entity)player, stack)) {
            Block var12 = ThaumicHorizons.blockJar;
            int var13 = this.func_77647_b(stack.func_77960_j());
            int var14 = ThaumicHorizons.blockJar.func_149660_a(world, x, y, z, side, par8, par9, par10, var13);
            if (this.placeBlockAt(stack, player, world, x, y, z, side, par8, par9, par10, var14)) {
                TileEntity te = world.func_147438_o(x, y, z);
                if (te != null && te instanceof TileSoulJar && stack.func_77942_o()) {
                    ((TileSoulJar)te).jarTag = stack.func_77978_p();
                }
                world.func_72908_a((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), var12.field_149762_H.func_150496_b(), (var12.field_149762_H.func_150497_c() + 1.0f) / 2.0f, var12.field_149762_H.func_150494_d() * 0.8f);
                --stack.field_77994_a;
            }
            return true;
        }
        return false;
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        if (!world.func_147465_d(x, y, z, ThaumicHorizons.blockJar, metadata, 3)) {
            return false;
        }
        if (world.func_147439_a(x, y, z) == ThaumicHorizons.blockJar) {
            ThaumicHorizons.blockJar.func_149689_a(world, x, y, z, (EntityLivingBase)player, stack);
            ThaumicHorizons.blockJar.func_149714_e(world, x, y, z, metadata);
        }
        return true;
    }

    public boolean func_77651_p() {
        return true;
    }
}

