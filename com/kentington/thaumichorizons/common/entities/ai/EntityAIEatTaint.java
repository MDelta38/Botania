/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.lib.utils.Utils
 */
package com.kentington.thaumichorizons.common.entities.ai;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityTaintPig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import thaumcraft.common.config.Config;
import thaumcraft.common.lib.utils.Utils;

public class EntityAIEatTaint
extends EntityAIBase {
    private EntityTaintPig thePig;
    private Vec3 targetCoordinates;
    int cooldown;
    int count = 0;

    public EntityAIEatTaint(EntityTaintPig par1EntityCreature) {
        this.thePig = par1EntityCreature;
    }

    public boolean func_75250_a() {
        if (this.cooldown > 0) {
            --this.cooldown;
            return false;
        }
        return this.findTaint();
    }

    private boolean findTaint() {
        int z;
        for (int x = -2; x < 3; ++x) {
            for (int y = -2; y < 3; ++y) {
                for (z = -2; z < 3; ++z) {
                    if (this.thePig.field_70170_p.func_147439_a((int)this.thePig.field_70165_t + x, (int)this.thePig.field_70163_u + y, (int)this.thePig.field_70161_v + z).func_149688_o() != Config.taintMaterial && (this.thePig.field_70170_p.func_147439_a((int)this.thePig.field_70165_t + x, (int)this.thePig.field_70163_u + y, (int)this.thePig.field_70161_v + z) != Blocks.field_150349_c || this.thePig.field_70170_p.func_72807_a((int)((int)this.thePig.field_70165_t + x), (int)((int)this.thePig.field_70161_v + z)).field_76756_M != Config.biomeTaintID)) continue;
                    this.targetCoordinates = Vec3.func_72443_a((double)((int)this.thePig.field_70165_t + x), (double)((int)this.thePig.field_70163_u + y), (double)((int)this.thePig.field_70161_v + z));
                    return true;
                }
            }
        }
        for (int tries = 0; tries < 30; ++tries) {
            int x = (int)this.thePig.field_70165_t + this.thePig.field_70170_p.field_73012_v.nextInt(17) - 8;
            z = (int)this.thePig.field_70161_v + this.thePig.field_70170_p.field_73012_v.nextInt(17) - 8;
            int y = (int)this.thePig.field_70163_u + this.thePig.field_70170_p.field_73012_v.nextInt(5) - 2;
            if ((!this.thePig.field_70170_p.func_147437_c(x, y + 1, z) || this.thePig.field_70170_p.func_147439_a(x, y, z).func_149688_o() != Config.taintMaterial) && (this.thePig.field_70170_p.func_147439_a(x, y, z) != Blocks.field_150349_c || this.thePig.field_70170_p.func_72807_a((int)x, (int)z).field_76756_M != Config.biomeTaintID)) continue;
            this.targetCoordinates = Vec3.func_72443_a((double)x, (double)y, (double)z);
            return true;
        }
        return false;
    }

    public boolean func_75253_b() {
        return this.count-- > 0 && !this.thePig.func_70661_as().func_75500_f() && this.cooldown-- <= 0;
    }

    public void func_75251_c() {
        this.count = 0;
        this.targetCoordinates = null;
        this.thePig.func_70661_as().func_75499_g();
    }

    public void func_75246_d() {
        if (this.targetCoordinates == null) {
            return;
        }
        this.thePig.func_70671_ap().func_75650_a(this.targetCoordinates.field_72450_a + 0.5, this.targetCoordinates.field_72448_b + 0.5, this.targetCoordinates.field_72449_c + 0.5, 30.0f, 30.0f);
        double dist = this.thePig.func_70092_e(this.targetCoordinates.field_72450_a + 0.5, this.targetCoordinates.field_72448_b + 0.5, this.targetCoordinates.field_72449_c + 0.5);
        if (dist <= 4.0) {
            this.eatTaint();
        }
    }

    private void eatTaint() {
        if (this.thePig.field_70170_p.func_147439_a((int)this.targetCoordinates.field_72450_a, (int)this.targetCoordinates.field_72448_b, (int)this.targetCoordinates.field_72449_c).func_149688_o() == Config.taintMaterial) {
            ThaumicHorizons.proxy.blockSplosionFX((int)this.targetCoordinates.field_72450_a, (int)this.targetCoordinates.field_72448_b, (int)this.targetCoordinates.field_72449_c, this.thePig.field_70170_p.func_147439_a((int)this.targetCoordinates.field_72450_a, (int)this.targetCoordinates.field_72448_b, (int)this.targetCoordinates.field_72449_c), this.thePig.field_70170_p.func_72805_g((int)this.targetCoordinates.field_72450_a, (int)this.targetCoordinates.field_72448_b, (int)this.targetCoordinates.field_72449_c));
            this.thePig.field_70170_p.func_147468_f((int)this.targetCoordinates.field_72450_a, (int)this.targetCoordinates.field_72448_b, (int)this.targetCoordinates.field_72449_c);
            Utils.setBiomeAt((World)this.thePig.field_70170_p, (int)((int)this.targetCoordinates.field_72450_a), (int)((int)this.targetCoordinates.field_72449_c), (BiomeGenBase)BiomeGenBase.field_76772_c);
            this.thePig.field_70170_p.func_72956_a((Entity)this.thePig, "random.burp", 0.2f, ((this.thePig.field_70170_p.field_73012_v.nextFloat() - this.thePig.field_70170_p.field_73012_v.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            this.thePig.func_70691_i(1.0f);
            this.cooldown = 20;
        } else if (this.thePig.field_70170_p.func_147439_a((int)this.targetCoordinates.field_72450_a, (int)this.targetCoordinates.field_72448_b, (int)this.targetCoordinates.field_72449_c) == Blocks.field_150349_c && this.thePig.field_70170_p.func_72807_a((int)((int)this.targetCoordinates.field_72450_a), (int)((int)this.targetCoordinates.field_72449_c)).field_76756_M == Config.biomeTaintID) {
            this.thePig.field_70170_p.func_147449_b((int)this.targetCoordinates.field_72450_a, (int)this.targetCoordinates.field_72448_b, (int)this.targetCoordinates.field_72449_c, Blocks.field_150346_d);
            Utils.setBiomeAt((World)this.thePig.field_70170_p, (int)((int)this.targetCoordinates.field_72450_a), (int)((int)this.targetCoordinates.field_72449_c), (BiomeGenBase)BiomeGenBase.field_76772_c);
            this.thePig.field_70170_p.func_72956_a((Entity)this.thePig, "random.burp", 0.2f, ((this.thePig.field_70170_p.field_73012_v.nextFloat() - this.thePig.field_70170_p.field_73012_v.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            this.thePig.func_70691_i(1.0f);
            this.cooldown = 10;
        } else {
            this.func_75251_c();
        }
    }

    public void func_75249_e() {
        this.count = 500;
        if (this.targetCoordinates != null) {
            this.thePig.func_70661_as().func_75492_a(this.targetCoordinates.field_72450_a + 0.5, this.targetCoordinates.field_72448_b + 0.5, this.targetCoordinates.field_72449_c + 0.5, 1.0);
        }
    }
}

