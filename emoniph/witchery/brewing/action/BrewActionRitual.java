/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.emoniph.witchery.brewing.action;

import com.emoniph.witchery.brewing.AltarPower;
import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.EffectLevelCounter;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.ModifiersImpact;
import com.emoniph.witchery.brewing.ModifiersRitual;
import com.emoniph.witchery.brewing.Probability;
import com.emoniph.witchery.brewing.RitualStatus;
import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
import com.emoniph.witchery.brewing.action.BrewAction;
import com.emoniph.witchery.brewing.action.BrewActionList;
import com.emoniph.witchery.util.BlockPosition;
import com.emoniph.witchery.util.Coord;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BrewActionRitual
extends BrewAction {
    private boolean aoeOnly;

    public BrewActionRitual(BrewItemKey itemKey, AltarPower powerCost, boolean aoeOnly) {
        super(itemKey, null, powerCost, new Probability(1.0), false);
        this.aoeOnly = aoeOnly;
    }

    @Override
    public final boolean triggersRitual() {
        return true;
    }

    @Override
    public final boolean canAdd(BrewActionList actionList, boolean isCauldronFull, boolean hasEffects) {
        return isCauldronFull && hasEffects && (!this.aoeOnly || WitcheryBrewRegistry.INSTANCE.isSplash(actionList.getTagCompound()));
    }

    @Override
    public RitualStatus updateRitual(MinecraftServer server, BrewActionList actionList, World world, int x, int y, int z, ModifiersRitual modifiers, ModifiersImpact impactModifiers) {
        BlockPosition target = modifiers.getTarget();
        if (!BrewActionRitual.isDistanceAllowed(world, x, y, z, target.x, target.y, target.z, target.dimension, modifiers.covenSize, modifiers.leonard)) {
            return RitualStatus.FAILED_DISTANCE;
        }
        if (!actionList.isTargetLocationValid(server, world, x, y, z, target, modifiers)) {
            return RitualStatus.FAILED_INVALID_CIRCLES;
        }
        return impactModifiers.getDispersal().onUpdateRitual(world, x, y, z, actionList.getTagCompound(), modifiers, impactModifiers);
    }

    public static boolean isDistanceAllowed(World world, int x, int y, int z, BlockPosition target, int covenSize, boolean leonard) {
        return BrewActionRitual.isDistanceAllowed(world, x, y, z, target.x, target.y, target.z, target.dimension, covenSize, leonard);
    }

    public static boolean isDistanceAllowed(World world, int x, int y, int z, double newX, double newY, double newZ, int newD, int covenSize, boolean leonard) {
        int rangeScale;
        int allowedRange;
        if (world.field_73011_w.field_76574_g != newD) {
            return covenSize >= 6 && leonard;
        }
        if (covenSize >= 6) {
            return true;
        }
        double rangeSq = Coord.distanceSq(x, y, z, newX, newY, newZ);
        return rangeSq <= (double)((allowedRange = 2 * (rangeScale = (1 + covenSize) * 4) * rangeScale) * allowedRange);
    }

    @Override
    public final boolean augmentEffectLevels(EffectLevelCounter totalEffects) {
        return true;
    }

    @Override
    public final void augmentEffectModifiers(ModifiersEffect modifiers) {
    }

    @Override
    public final void prepareRitual(World world, int x, int y, int z, ModifiersRitual modifiers, ItemStack stack) {
    }

    @Override
    public final void applyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack) {
    }

    @Override
    public final void applyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect effectModifiers, ItemStack stack) {
    }

    @Override
    public final void prepareSplashPotion(World world, BrewActionList actionList, ModifiersImpact modifiers) {
    }
}

