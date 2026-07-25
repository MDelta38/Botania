/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

@Effect(aspect="pannus")
public class Pannus
extends AspectEffect {
    @Override
    public int getCost() {
        return 1;
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149746_a(World world, int x, int y, int z, Entity ent, float fall) {
        ent.field_70143_R = 0.0f;
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149670_a(World world, int x, int y, int z, Entity ent) {
        ent.field_70143_R = 0.0f;
    }
}

