/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

@Effect(aspect="humanus")
public class Humanus
extends AspectEffect {
    @Override
    public int getCost() {
        return 8;
    }

    @OverrideBlock
    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @OverrideBlock
    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        return false;
    }

    @OverrideBlock
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @OverrideBlock
    public float func_149712_f(World world, int x, int y, int z) {
        return -1.0f;
    }

    @OverrideBlock
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        return 999.0f;
    }

    @OverrideBlock
    public boolean func_149655_b(IBlockAccess access, int x, int y, int z) {
        return false;
    }

    @OverrideBlock
    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
    }
}

