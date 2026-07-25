/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.world.World
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

@Effect(aspect="infernus")
public class Infernus
extends AspectEffect {
    @Override
    public int getCost() {
        return 4;
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149670_a(World world, int x, int y, int z, Entity ent) {
        this.setOnFire(ent);
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149746_a(World world, int x, int y, int z, Entity ent, float fall) {
        this.setOnFire(ent);
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149724_b(World world, int x, int y, int z, Entity ent) {
        this.setOnFire(ent);
    }

    public void setOnFire(Entity ent) {
        if (!(ent instanceof EntityLivingBase)) {
            return;
        }
        ent.func_70015_d(8);
    }
}

