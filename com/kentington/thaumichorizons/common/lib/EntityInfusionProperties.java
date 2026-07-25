/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IExtendedEntityProperties
 */
package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import thaumcraft.api.aspects.AspectList;

public class EntityInfusionProperties
implements IExtendedEntityProperties {
    public static final String EXT_PROP_NAME = "CreatureInfusion";
    public static final int NUM_INFUSIONS = 12;
    public int[] infusions = new int[12];
    public int[] playerInfusions = new int[12];
    public AspectList infusionCosts = new AspectList();
    boolean infusionsApplied = false;
    String owner;
    boolean sitting;
    Entity entity;
    public int tumorWarp;
    public int tumorWarpTemp;
    public int tumorWarpPermanent;
    public boolean toggleClimb;
    public boolean toggleInvisible;

    public void saveNBTData(NBTTagCompound compound) {
        NBTTagCompound properties = new NBTTagCompound();
        properties.func_74783_a("Infusions", this.infusions);
        properties.func_74783_a("PlayerInfusions", this.playerInfusions);
        NBTTagCompound aspects = new NBTTagCompound();
        this.infusionCosts.writeToNBT(aspects);
        properties.func_74782_a("InfusionCosts", (NBTBase)aspects);
        if (this.owner != null && this.owner != "") {
            properties.func_74778_a("owner", this.owner);
        }
        properties.func_74757_a("sitting", this.sitting);
        properties.func_74768_a("tumorWarp", this.tumorWarp);
        properties.func_74768_a("tumorWarpTemp", this.tumorWarpTemp);
        properties.func_74768_a("tumorWarpPermanent", this.tumorWarpPermanent);
        properties.func_74757_a("toggleClimb", this.toggleClimb);
        properties.func_74757_a("toggleInvisible", this.toggleInvisible);
        compound.func_74782_a(EXT_PROP_NAME, (NBTBase)properties);
    }

    public void loadNBTData(NBTTagCompound compound) {
        NBTTagCompound properties = (NBTTagCompound)compound.func_74781_a(EXT_PROP_NAME);
        this.infusionCosts = new AspectList();
        if (properties != null) {
            this.owner = properties.func_74779_i("owner");
            this.sitting = properties.func_74767_n("sitting");
            this.infusions = properties.func_74759_k("Infusions");
            if (this.infusions.length < 12) {
                this.convertInfusions();
            }
            this.playerInfusions = properties.func_74759_k("PlayerInfusions");
            if (this.playerInfusions.length == 0) {
                this.playerInfusions = new int[12];
            } else if (this.playerInfusions.length < 12) {
                this.convertPlayerInfusions();
            }
            this.tumorWarp = properties.func_74762_e("tumorWarp");
            this.tumorWarpTemp = properties.func_74762_e("tumorWarpTemp");
            this.tumorWarpPermanent = properties.func_74762_e("tumorWarpPermanent");
            this.toggleClimb = properties.func_74767_n("toggleClimb");
            this.toggleInvisible = properties.func_74767_n("toggleInvisible");
            this.infusionCosts.readFromNBT(properties.func_74775_l("InfusionCosts"));
            if (!this.infusionsApplied && this.entity instanceof EntityLivingBase && !(this.entity instanceof EntityPlayer)) {
                ThaumicHorizons.instance.eventHandlerEntity.applyInfusions((EntityLivingBase)this.entity);
                this.infusionsApplied = true;
            } else if (this.infusionsApplied || this.entity instanceof EntityPlayer) {
                // empty if block
            }
        }
    }

    void convertInfusions() {
        int[] newArray = new int[12];
        System.arraycopy(this.infusions, 0, newArray, 0, this.infusions.length);
        this.infusions = newArray;
    }

    void convertPlayerInfusions() {
        int[] newArray = new int[12];
        System.arraycopy(this.playerInfusions, 0, newArray, 0, this.playerInfusions.length);
        this.playerInfusions = newArray;
    }

    public void init(Entity entity, World world) {
        this.entity = entity;
    }

    public void addInfusion(int id) {
        if (id != 0) {
            for (int i = 0; i < this.infusions.length; ++i) {
                if (this.infusions[i] != 0) continue;
                this.infusions[i] = id;
                break;
            }
        }
    }

    public void addPlayerInfusion(int id) {
        if (id != 0) {
            for (int i = 0; i < this.playerInfusions.length; ++i) {
                if (this.playerInfusions[i] != 0) continue;
                this.playerInfusions[i] = id;
                break;
            }
        }
    }

    public void addCost(AspectList cost) {
        this.infusionCosts.add(cost);
    }

    public boolean hasInfusion(int id) {
        for (int i = 0; i < this.infusions.length; ++i) {
            if (this.infusions[i] != id) continue;
            return true;
        }
        return false;
    }

    public boolean hasPlayerInfusion(int id) {
        for (int i = 0; i < this.playerInfusions.length; ++i) {
            if (this.playerInfusions[i] != id) continue;
            return true;
        }
        return false;
    }

    public int[] getInfusions() {
        return this.infusions;
    }

    public int[] getPlayerInfusions() {
        return this.playerInfusions;
    }

    public void resetPlayerInfusions() {
        this.playerInfusions = new int[12];
        this.tumorWarp = 0;
        this.tumorWarpTemp = 0;
    }

    public AspectList getInfusionCosts() {
        return this.infusionCosts;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setSitting(boolean sit) {
        this.sitting = sit;
    }

    public boolean isSitting() {
        return this.sitting;
    }
}

