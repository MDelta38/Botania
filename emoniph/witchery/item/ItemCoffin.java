/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockCoffin;
import com.emoniph.witchery.item.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ItemCoffin
extends ItemBase {
    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (world.field_72995_K) {
            return true;
        }
        if (side != 1) {
            return false;
        }
        ++y;
        BlockCoffin coffin = Witchery.Blocks.COFFIN;
        int i1 = MathHelper.func_76128_c((double)((double)(player.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        int b0 = 0;
        int b1 = 0;
        if (i1 == 0) {
            b1 = 1;
        }
        if (i1 == 1) {
            b0 = -1;
        }
        if (i1 == 2) {
            b1 = -1;
        }
        if (i1 == 3) {
            b0 = 1;
        }
        if (player.func_82247_a(x, y, z, side, stack) && player.func_82247_a(x + b0, y, z + b1, side, stack)) {
            if (world.func_147437_c(x, y, z) && world.func_147437_c(x + b0, y, z + b1) && World.func_147466_a((IBlockAccess)world, (int)x, (int)(y - 1), (int)z) && World.func_147466_a((IBlockAccess)world, (int)(x + b0), (int)(y - 1), (int)(z + b1))) {
                world.func_147465_d(x, y, z, (Block)coffin, i1, 3);
                if (world.func_147439_a(x, y, z) == coffin) {
                    world.func_147465_d(x + b0, y, z + b1, (Block)coffin, i1 + 8, 3);
                }
                --stack.field_77994_a;
                return true;
            }
            return false;
        }
        return false;
    }
}

