/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.emoniph.witchery.brewing;

import com.emoniph.witchery.brewing.Dispersal;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.ModifiersImpact;
import com.emoniph.witchery.brewing.ModifiersRitual;
import com.emoniph.witchery.brewing.RitualStatus;
import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
import com.emoniph.witchery.util.BlockPosition;
import com.emoniph.witchery.util.EntityPosition;
import com.emoniph.witchery.util.EntityUtil;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class DispersalInstant
extends Dispersal {
    @Override
    public void onImpactSplashPotion(World world, NBTTagCompound nbtBrew, MovingObjectPosition mop, ModifiersImpact modifiers) {
        double R = 3 + modifiers.extent;
        double R_SQ = R * R;
        EntityPosition position = modifiers.impactPosition;
        AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)(position.x - R), (double)(position.y - R), (double)(position.z - R), (double)(position.x + R), (double)(position.y + R), (double)(position.z + R));
        List list1 = world.func_72872_a(EntityLivingBase.class, bb);
        for (EntityLivingBase targetEntity : list1) {
            double distanceSq = position.getDistanceSqToEntity((Entity)targetEntity);
            if (!(distanceSq <= R_SQ)) continue;
            boolean directHit = targetEntity == mop.field_72308_g;
            double effectScalingFactor = directHit ? 1.0 : 1.0 - Math.sqrt(distanceSq) / R;
            WitcheryBrewRegistry.INSTANCE.applyToEntity(world, targetEntity, nbtBrew, new ModifiersEffect(effectScalingFactor, 0.5 * effectScalingFactor, !directHit, position, modifiers));
        }
        if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            WitcheryBrewRegistry.INSTANCE.applyToBlock(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, ForgeDirection.getOrientation((int)mop.field_72310_e), MathHelper.func_76143_f((double)R), nbtBrew, new ModifiersEffect(1.0, 1.0, false, position, modifiers));
        }
    }

    @Override
    public String getUnlocalizedName() {
        return "witchery:brew.dispersal.splash";
    }

    @Override
    public RitualStatus onUpdateRitual(World world, int x, int y, int z, NBTTagCompound nbtBrew, ModifiersRitual modifiers, ModifiersImpact impactModifiers) {
        BlockPosition target = modifiers.getTarget();
        ModifiersEffect effectModifiers = new ModifiersEffect(1.0, 1.0, false, new EntityPosition(target), true, modifiers.covenSize, EntityUtil.playerOrFake(world, (EntityLivingBase)((EntityPlayer)null)));
        WitcheryBrewRegistry.INSTANCE.applyRitualToBlock(world, target.x, target.y, target.z, ForgeDirection.UP, 3 + impactModifiers.extent, nbtBrew, modifiers, effectModifiers);
        return modifiers.getRitualStatus();
    }
}

