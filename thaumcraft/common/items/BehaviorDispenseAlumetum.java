/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.dispenser.BehaviorDefaultDispenseItem
 *  net.minecraft.dispenser.BehaviorProjectileDispense
 *  net.minecraft.dispenser.IBlockSource
 *  net.minecraft.dispenser.IPosition
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IProjectile
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.world.World
 */
package thaumcraft.common.items;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import thaumcraft.common.entities.projectile.EntityAlumentum;

public class BehaviorDispenseAlumetum
extends BehaviorProjectileDispense {
    public ItemStack func_82487_b(IBlockSource par1IBlockSource, ItemStack par2ItemStack) {
        if (par2ItemStack.func_77960_j() != 0) {
            BehaviorDefaultDispenseItem def = new BehaviorDefaultDispenseItem();
            return def.func_82482_a(par1IBlockSource, par2ItemStack);
        }
        World var3 = par1IBlockSource.func_82618_k();
        IPosition var4 = BlockDispenser.func_149939_a((IBlockSource)par1IBlockSource);
        EnumFacing var5 = BlockDispenser.func_149937_b((int)par1IBlockSource.func_82620_h());
        IProjectile var6 = this.func_82499_a(var3, var4);
        var6.func_70186_c((double)var5.func_82601_c(), (double)((float)var5.func_96559_d() + 0.1f), (double)var5.func_82599_e(), this.func_82500_b(), this.func_82498_a());
        var3.func_72838_d((Entity)var6);
        par2ItemStack.func_77979_a(1);
        return par2ItemStack;
    }

    protected IProjectile func_82499_a(World par1World, IPosition par2IPosition) {
        return new EntityAlumentum(par1World, par2IPosition.func_82615_a(), par2IPosition.func_82617_b(), par2IPosition.func_82616_c());
    }

    protected void func_82485_a(IBlockSource par1IBlockSource) {
        par1IBlockSource.func_82618_k().func_72926_e(1009, par1IBlockSource.func_82623_d(), par1IBlockSource.func_82622_e(), par1IBlockSource.func_82621_f(), 0);
    }
}

