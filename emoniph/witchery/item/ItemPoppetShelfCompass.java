/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.blocks.BlockPoppetShelf;
import com.emoniph.witchery.item.ItemBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemPoppetShelfCompass
extends ItemBase {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] icons;

    public ItemPoppetShelfCompass() {
        this.func_77656_e(0);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister iconRegister) {
        this.icons = new IIcon[6];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = iconRegister.func_94245_a(this.func_111208_A() + "_" + i);
        }
        this.field_77791_bV = this.icons[0];
    }

    public IIcon func_77617_a(int damageValue) {
        if (damageValue > 0 && damageValue < this.icons.length) {
            return this.icons[damageValue];
        }
        return this.icons[0];
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int inventorySlot, boolean isHeldItem) {
        if (world.field_72995_K && world.field_73012_v.nextInt(20) == 0) {
            List list = world.field_147482_g;
            double closest = Double.MAX_VALUE;
            for (TileEntity tile : list) {
                double distSq;
                if (!(tile instanceof BlockPoppetShelf.TileEntityPoppetShelf) || !((distSq = entity.func_70092_e((double)tile.field_145851_c, entity.field_70163_u, (double)tile.field_145849_e)) < closest)) continue;
                closest = distSq;
            }
            if (closest < 64.0) {
                stack.func_77964_b(5);
            } else if (closest < 256.0) {
                stack.func_77964_b(4);
            } else if (closest < 1024.0) {
                stack.func_77964_b(3);
            } else if (closest < 4096.0) {
                stack.func_77964_b(2);
            } else if (closest < 16384.0) {
                stack.func_77964_b(1);
            } else {
                stack.func_77964_b(0);
            }
        }
    }

    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
        item.func_77964_b(0);
        return super.onDroppedByPlayer(item, player);
    }
}

