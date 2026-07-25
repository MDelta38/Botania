/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingFallEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package com.emoniph.witchery.infusion.infusions.creature;

import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.util.Log;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class CreaturePower {
    private final int creaturePowerID;
    private final Class<? extends EntityLiving> creatureType;
    private static final String BEAST_POWER_KEY = "WITCBeastPower";
    private static final String BEAST_POWER_CHARGES_KEY = "WITCBeastPowerCharges";
    protected static final int DEFAULT_CHARGES_PER_SACRIFICE = 10;

    public CreaturePower(int creaturePowerID, Class<? extends EntityLiving> creatureType) {
        this.creaturePowerID = creaturePowerID;
        this.creatureType = creatureType;
    }

    public int getCreaturePowerID() {
        return this.creaturePowerID;
    }

    public int activateCost(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop) {
        return 1;
    }

    public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop) {
    }

    public void onUpdate(World world, EntityPlayer player) {
    }

    public void onDamage(World world, EntityPlayer player, LivingHurtEvent event) {
    }

    public void onFalling(World worldObj, EntityPlayer player, LivingFallEvent event) {
    }

    public static int getCreaturePowerID(EntityPlayer player) {
        NBTTagCompound nbt = Infusion.getNBT((Entity)player);
        return nbt.func_74762_e(BEAST_POWER_KEY);
    }

    public static void setCreaturePowerID(EntityPlayer playerEntity, int beastPower, int beastCharges) {
        NBTTagCompound nbt = Infusion.getNBT((Entity)playerEntity);
        if (beastPower > 0) {
            nbt.func_74768_a(BEAST_POWER_KEY, beastPower);
            nbt.func_74768_a(BEAST_POWER_CHARGES_KEY, beastCharges);
        } else {
            if (nbt.func_74764_b(BEAST_POWER_KEY)) {
                nbt.func_82580_o(BEAST_POWER_KEY);
            }
            if (nbt.func_74764_b(BEAST_POWER_CHARGES_KEY)) {
                nbt.func_82580_o(BEAST_POWER_CHARGES_KEY);
            }
        }
    }

    public static int getCreaturePowerCharges(EntityPlayer player) {
        NBTTagCompound nbt = Infusion.getNBT((Entity)player);
        if (nbt.func_74764_b(BEAST_POWER_KEY) && nbt.func_74764_b(BEAST_POWER_CHARGES_KEY)) {
            return nbt.func_74762_e(BEAST_POWER_CHARGES_KEY);
        }
        return 0;
    }

    public static void setCreaturePowerCharges(EntityPlayer player, int charges) {
        NBTTagCompound nbt = Infusion.getNBT((Entity)player);
        nbt.func_74768_a(BEAST_POWER_CHARGES_KEY, charges);
    }

    public IIcon getPowerBarIcon(World worldObj, EntityPlayer player) {
        return Blocks.field_150435_aG.func_149691_a(0, 0);
    }

    public int getChargesPerSacrifice() {
        return 10;
    }

    public static class Registry {
        private static final Registry INSTANCE = new Registry();
        private ArrayList<CreaturePower> registry = new ArrayList();

        public static Registry instance() {
            return INSTANCE;
        }

        private Registry() {
        }

        public void add(CreaturePower power) {
            if (power.creaturePowerID == this.registry.size() + 1) {
                this.registry.add(power);
            } else if (power.creaturePowerID > this.registry.size() + 1) {
                for (int i = this.registry.size(); i < power.creaturePowerID; ++i) {
                    this.registry.add(null);
                }
                this.registry.add(power);
            } else {
                CreaturePower existingPower = this.registry.get(power.creaturePowerID);
                if (existingPower != null) {
                    Log.instance().warning(String.format("Creature power %s at id %d is being overwritten by another creature power %s.", existingPower, power.creaturePowerID, power));
                }
                this.registry.set(power.creaturePowerID, power);
            }
        }

        public CreaturePower get(EntityLiving creature) {
            for (CreaturePower power : this.registry) {
                if (power == null || power.creatureType != creature.getClass()) continue;
                return power;
            }
            return null;
        }

        public CreaturePower get(int creaturePowerID) {
            return this.registry.get(creaturePowerID - 1);
        }
    }
}

