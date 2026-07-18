/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.block.BlockRailBase
 *  net.minecraft.block.material.Material
 *  net.minecraft.dispenser.BehaviorDefaultDispenseItem
 *  net.minecraft.dispenser.IBlockSource
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.dispenser;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import vazkii.botania.common.entity.EntityPoolMinecart;

public class BehaviourPoolMinecart
extends BehaviorDefaultDispenseItem {
    public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
        double d3;
        int k;
        int j;
        EnumFacing enumfacing = BlockDispenser.func_149937_b((int)p_82487_1_.func_82620_h());
        World world = p_82487_1_.func_82618_k();
        double d0 = p_82487_1_.func_82615_a() + (double)((float)enumfacing.func_82601_c() * 1.125f);
        double d1 = p_82487_1_.func_82617_b() + (double)((float)enumfacing.func_96559_d() * 1.125f);
        double d2 = p_82487_1_.func_82616_c() + (double)((float)enumfacing.func_82599_e() * 1.125f);
        int i = p_82487_1_.func_82623_d() + enumfacing.func_82601_c();
        Block block = world.func_147439_a(i, j = p_82487_1_.func_82622_e() + enumfacing.func_96559_d(), k = p_82487_1_.func_82621_f() + enumfacing.func_82599_e());
        if (BlockRailBase.func_150051_a((Block)block)) {
            d3 = 0.0;
        } else {
            if (block.func_149688_o() != Material.field_151579_a || !BlockRailBase.func_150051_a((Block)world.func_147439_a(i, j - 1, k))) {
                return super.func_82487_b(p_82487_1_, p_82487_2_);
            }
            d3 = -1.0;
        }
        EntityPoolMinecart entityminecart = new EntityPoolMinecart(world, d0, d1 + d3, d2);
        if (p_82487_2_.func_82837_s()) {
            entityminecart.func_96094_a(p_82487_2_.func_82833_r());
        }
        world.func_72838_d((Entity)entityminecart);
        p_82487_2_.func_77979_a(1);
        return p_82487_2_;
    }

    protected void func_82485_a(IBlockSource p_82485_1_) {
        p_82485_1_.func_82618_k().func_72926_e(1000, p_82485_1_.func_82623_d(), p_82485_1_.func_82622_e(), p_82485_1_.func_82621_f(), 0);
    }
}

