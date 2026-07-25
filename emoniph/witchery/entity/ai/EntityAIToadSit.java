/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityAIToadSit
extends EntityAIBase {
    private final EntityTameable theOcelot;
    private final double field_75404_b;
    private int currentTick;
    private int field_75402_d;
    private int maxSittingTicks;
    private int sitableBlockX;
    private int sitableBlockY;
    private int sitableBlockZ;

    public EntityAIToadSit(EntityTameable par1EntityOcelot, double par2) {
        this.theOcelot = par1EntityOcelot;
        this.field_75404_b = par2;
        this.func_75248_a(5);
    }

    public boolean func_75250_a() {
        return !this.theOcelot.func_70906_o() && this.theOcelot.func_70681_au().nextDouble() <= (double)0.0065f && this.getNearbySitableBlockDistance();
    }

    public boolean func_75253_b() {
        return this.currentTick <= this.maxSittingTicks && this.field_75402_d <= 60 && this.isSittableBlock(this.theOcelot.field_70170_p, this.sitableBlockX, this.sitableBlockY, this.sitableBlockZ);
    }

    public void func_75249_e() {
        this.theOcelot.func_70661_as().func_75492_a((double)this.sitableBlockX + 0.5, (double)(this.sitableBlockY + 1), (double)this.sitableBlockZ + 0.5, this.field_75404_b);
        this.currentTick = 0;
        this.field_75402_d = 0;
        this.maxSittingTicks = this.theOcelot.func_70681_au().nextInt(this.theOcelot.func_70681_au().nextInt(1200) + 1200) + 1200;
    }

    public void func_75251_c() {
        this.theOcelot.func_70904_g(false);
    }

    public void func_75246_d() {
        ++this.currentTick;
        this.theOcelot.func_70907_r().func_75270_a(false);
        if (this.theOcelot.func_70092_e((double)this.sitableBlockX, (double)(this.sitableBlockY + 1), (double)this.sitableBlockZ) > 1.0) {
            this.theOcelot.func_70904_g(false);
            this.theOcelot.func_70661_as().func_75492_a((double)this.sitableBlockX + 0.5, (double)(this.sitableBlockY + 1), (double)this.sitableBlockZ + 0.5, this.field_75404_b);
            ++this.field_75402_d;
        } else if (!this.theOcelot.func_70906_o()) {
            this.theOcelot.func_70904_g(true);
        } else {
            --this.field_75402_d;
        }
    }

    protected boolean getNearbySitableBlockDistance() {
        int i = (int)this.theOcelot.field_70163_u;
        double d0 = 4.147483647E9;
        int j = (int)this.theOcelot.field_70165_t - 8;
        while ((double)j < this.theOcelot.field_70165_t + 8.0) {
            int k = (int)this.theOcelot.field_70161_v - 8;
            while ((double)k < this.theOcelot.field_70161_v + 8.0) {
                int y = (int)this.theOcelot.field_70163_u - 2;
                while ((double)y < this.theOcelot.field_70163_u + 3.0) {
                    double d1;
                    if (this.isSittableBlock(this.theOcelot.field_70170_p, j, y, k) && this.theOcelot.field_70170_p.func_147437_c(j, y + 1, k) && (d1 = this.theOcelot.func_70092_e((double)j, (double)y, (double)k)) < d0) {
                        this.sitableBlockX = j;
                        this.sitableBlockY = y;
                        this.sitableBlockZ = k;
                        d0 = d1;
                    }
                    ++y;
                }
                ++k;
            }
            ++j;
        }
        return d0 < 2.147483647E9;
    }

    protected boolean isSittableBlock(World par1World, int par2, int par3, int par4) {
        Block l = par1World.func_147439_a(par2, par3, par4);
        int i1 = par1World.func_72805_g(par2, par3, par4);
        return l == Blocks.field_150392_bi;
    }
}

