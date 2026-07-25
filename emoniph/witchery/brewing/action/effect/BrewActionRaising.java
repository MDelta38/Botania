/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.monster.EntityPigZombie
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.emoniph.witchery.brewing.action.effect;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.AltarPower;
import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.BrewNamePart;
import com.emoniph.witchery.brewing.EffectLevel;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.Probability;
import com.emoniph.witchery.brewing.action.BrewActionEffect;
import com.emoniph.witchery.brewing.potions.PotionEnslaved;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BrewActionRaising
extends BrewActionEffect {
    public BrewActionRaising(Item axe, AltarPower powerCost, EffectLevel effectLevel) {
        super(new BrewItemKey(axe, Short.MAX_VALUE), new BrewNamePart("witchery:brew.raising"), powerCost, new Probability(1.0), effectLevel);
    }

    @Override
    protected void doApplyToBlock(World world, int posX, int posY, int posZ, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack) {
        BrewActionRaising.raiseDead(world, new Coord(posX, posY, posZ, side), modifiers.ritualised ? 0 : modifiers.getStrength(), modifiers.caster, modifiers.ritualised ? TimeUtil.secsToTicks(10 * (modifiers.getStrength() + 1)) : 0);
    }

    public static void raiseDead(World world, Coord coord, int strength, EntityPlayer raiser, int lifetime) {
        int MAX_DISTANCE = 3;
        int MAX_DROP = 6;
        BrewActionRaising.raiseUndead(world, coord, raiser, lifetime);
        int extraCount = 0;
        double chance = world.field_73012_v.nextDouble();
        if (strength >= 1 && world.field_73012_v.nextDouble() < (double)strength * 0.5) {
            ++extraCount;
        }
        if (strength >= 2 && world.field_73012_v.nextDouble() < (double)strength * 0.25) {
            ++extraCount;
        }
        if (strength >= 3 && world.field_73012_v.nextDouble() < (double)strength * 0.25) {
            ++extraCount;
        }
        block0: for (int i = 0; i < extraCount; ++i) {
            int x = coord.x - 3 + world.field_73012_v.nextInt(6) + 1;
            int z = coord.z - 3 + world.field_73012_v.nextInt(6) + 1;
            int minY = coord.y - 6;
            for (int dy = coord.y + 6; dy >= minY; --dy) {
                if (!world.func_147439_a(x, dy - 1, z).func_149688_o().func_76220_a() || !world.func_147437_c(x, dy, z)) continue;
                BrewActionRaising.raiseUndead(world, new Coord(x, dy, z), raiser, lifetime);
                continue block0;
            }
        }
    }

    private static void raiseUndead(World world, Coord coord, EntityPlayer thrower, int lifetime) {
        if (!world.field_72995_K) {
            EntityLiving undeadEntity = BrewActionRaising.createUndeadCreature(world);
            undeadEntity.func_70012_b(0.5 + (double)coord.x, 0.1 + (double)coord.y, 0.5 + (double)coord.z, 0.0f, 0.0f);
            IEntityLivingData entitylivingData = null;
            entitylivingData = undeadEntity.func_110161_a(entitylivingData);
            EntityUtil.persistanceRequired(undeadEntity);
            EntityUtil.setNoDrops(undeadEntity);
            if (lifetime > 0) {
                undeadEntity.func_70690_d(new PotionEffect(Witchery.Potions.MORTAL_COIL.field_76415_H, lifetime));
            }
            if (thrower != null) {
                try {
                    PotionEnslaved.setEnslaverForMob(undeadEntity, thrower);
                }
                catch (Exception e) {
                    Log.instance().warning(e, "Unhandled exception occurred setting enslaver from raiseUnded potion.");
                }
            }
            world.func_72838_d((Entity)undeadEntity);
            ParticleEffect.LARGE_SMOKE.send(SoundEffect.NONE, (Entity)undeadEntity, 0.5, 2.0, 16);
        }
    }

    private static EntityLiving createUndeadCreature(World world) {
        double value = world.field_73012_v.nextDouble();
        if (value < 0.6) {
            return new EntityZombie(world);
        }
        if (value < 0.97) {
            return new EntitySkeleton(world);
        }
        return new EntityPigZombie(world);
    }
}

