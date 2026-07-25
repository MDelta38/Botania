/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.util.MathHelper
 *  net.minecraft.village.Village
 *  net.minecraft.village.VillageDoorInfo
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.common.ExtendedVillager;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.village.VillageDoorInfo;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntityAISleep
extends EntityAIBase {
    private EntityVillager villager;
    private VillageDoorInfo doorInfo;
    private int insidePosX = -1;
    private int insidePosZ = -1;
    private World world;
    Village village;

    public EntityAISleep(EntityVillager villager) {
        this.villager = villager;
        this.world = villager.field_70170_p;
        this.func_75248_a(7);
    }

    public boolean func_75250_a() {
        int z;
        int x;
        boolean inside;
        int k;
        int j;
        long time = this.world.func_72820_D() % 24000L;
        if (time < 13000L || time >= 23999L || this.villager.field_70737_aN > 0) {
            return false;
        }
        if (this.villager.func_70681_au().nextInt(50) != 0) {
            return false;
        }
        int i = MathHelper.func_76128_c((double)this.villager.field_70165_t);
        Village village = this.world.field_72982_D.func_75550_a(i, j = MathHelper.func_76128_c((double)this.villager.field_70163_u), k = MathHelper.func_76128_c((double)this.villager.field_70161_v), 14);
        if (village == null) {
            return false;
        }
        this.doorInfo = village.func_75569_c(i, j, k);
        float DOOR_DIST = 4.0f;
        boolean bl = inside = this.villager.func_70092_e((double)this.doorInfo.func_75471_a() + 0.5, (double)this.doorInfo.func_75473_b(), (double)this.doorInfo.func_75472_c() + 0.5) < 16.0;
        if (this.villager.field_70170_p.func_72937_j(i, j, k)) {
            return false;
        }
        int count = 0;
        for (x = i - 1; x <= i + 1; ++x) {
            for (z = k - 1; z <= k + 1; ++z) {
                if (this.villager.field_70170_p.func_72937_j(x, j, z) || !this.villager.field_70170_p.func_147439_a(x, j + 1, z).func_149688_o().func_76222_j()) continue;
                ++count;
            }
        }
        if (count < 4) {
            return false;
        }
        count = 6;
        count = 0;
        for (x = -1; x <= 1; ++x) {
            for (z = -1; z <= 1; ++z) {
                if (this.world.func_147439_a(x + i, j - 1, z + k).isReplaceable((IBlockAccess)this.world, x + i, j - 1, z + k)) continue;
                ++count;
            }
        }
        return count >= 6;
    }

    public void func_75249_e() {
        ExtendedVillager ext = ExtendedVillager.get(this.villager);
        if (ext != null) {
            ext.setSleeping(true);
        }
    }

    public void func_75251_c() {
        this.village = null;
        ExtendedVillager ext = ExtendedVillager.get(this.villager);
        if (ext != null) {
            ext.setSleeping(false);
        }
    }

    public boolean func_75253_b() {
        long time = this.world.func_72820_D() % 24000L;
        return time > 13000L && time < 23999L && this.villager.field_70737_aN == 0;
    }

    public void func_75246_d() {
        ExtendedVillager ext = ExtendedVillager.get(this.villager);
        if (ext != null) {
            ext.incrementSleepingTicks();
        }
    }
}

