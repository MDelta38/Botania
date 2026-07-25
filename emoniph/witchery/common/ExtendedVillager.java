/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IExtendedEntityProperties
 */
package com.emoniph.witchery.common;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.potions.PotionResizing;
import com.emoniph.witchery.network.PacketExtendedEntityRequestSyncToClient;
import com.emoniph.witchery.network.PacketExtendedVillagerSync;
import com.emoniph.witchery.util.TimeUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedVillager
implements IExtendedEntityProperties {
    private static final String EXT_PROP_NAME = "WitcheryExtendedVillager";
    private final EntityVillager villager;
    private int blood = 500;
    private boolean sleeping;
    private int sleepingTicks;
    public boolean synced;
    private boolean trySync;

    public static final void register(EntityVillager villager) {
        villager.registerExtendedProperties(EXT_PROP_NAME, (IExtendedEntityProperties)new ExtendedVillager(villager));
    }

    public static final ExtendedVillager get(EntityVillager villager) {
        return (ExtendedVillager)villager.getExtendedProperties(EXT_PROP_NAME);
    }

    public ExtendedVillager(EntityVillager villager) {
        this.villager = villager;
    }

    public EntityVillager getVillager() {
        return this.villager;
    }

    public void saveNBTData(NBTTagCompound compound) {
        NBTTagCompound props = new NBTTagCompound();
        props.func_74768_a("Blood", this.blood);
        compound.func_74782_a(EXT_PROP_NAME, (NBTBase)props);
    }

    public void loadNBTData(NBTTagCompound compound) {
        if (compound.func_74764_b(EXT_PROP_NAME)) {
            NBTTagCompound props = (NBTTagCompound)compound.func_74781_a(EXT_PROP_NAME);
            this.blood = MathHelper.func_76125_a((int)props.func_74762_e("Blood"), (int)0, (int)500);
        }
    }

    public void init(Entity entity, World world) {
    }

    public void setBlood(int blood) {
        if (this.blood != blood) {
            this.blood = Math.max(Math.min(blood, 500), 0);
            this.sync();
        }
    }

    public int takeBlood(int quantity, EntityLivingBase player) {
        boolean isKnockedOut;
        PotionEffect potionEffect = this.villager.func_70660_b(Witchery.Potions.PARALYSED);
        boolean bl = isKnockedOut = this.isSleeping() || potionEffect != null && potionEffect.func_76458_c() >= 4;
        if (!isKnockedOut) {
            quantity = (int)Math.ceil(0.66f * (float)quantity);
        }
        int remainder = Math.max(this.blood - quantity, 0);
        int taken = this.blood - remainder;
        this.setBlood(remainder);
        if (player instanceof EntityPlayer) {
            if (this.blood < (int)Math.ceil(250.0)) {
                this.villager.func_70097_a((DamageSource)new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), (Entity)player), 1.3f);
            } else if (!isKnockedOut) {
                this.villager.func_70097_a((DamageSource)new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), (Entity)player), 0.1f);
            }
        }
        return taken;
    }

    public void giveBlood(int quantity) {
        if (this.blood < 500) {
            this.setBlood(this.blood + quantity);
        }
    }

    public int getBlood() {
        return this.blood;
    }

    public void setSleeping(boolean sleeping) {
        if (this.sleeping != sleeping) {
            this.sleeping = sleeping;
            if (this.sleeping) {
                PotionResizing.setEntitySize((Entity)this.villager, 0.8f, 1.1f);
            } else {
                PotionResizing.setEntitySize((Entity)this.villager, 0.6f, 1.8f);
                if (this.sleepingTicks >= TimeUtil.minsToTicks(2)) {
                    this.villager.func_70606_j(this.villager.func_110138_aP());
                }
                if (this.sleepingTicks > TimeUtil.minsToTicks(1)) {
                    int blops = this.sleepingTicks / TimeUtil.minsToTicks(1);
                    this.giveBlood(50 * blops);
                }
            }
            this.sleepingTicks = 0;
            this.sync();
        }
    }

    public boolean isSleeping() {
        return this.sleeping;
    }

    public void incrementSleepingTicks() {
        ++this.sleepingTicks;
    }

    public void sync() {
        if (!this.villager.field_70170_p.field_72995_K && this.villager.func_110143_aJ() > 0.0f && !this.villager.field_70128_L) {
            Witchery.packetPipeline.sendToAll(new PacketExtendedVillagerSync(this));
        }
    }

    public boolean isClientSynced() {
        if (this.villager.field_70170_p.field_72995_K) {
            if (this.synced) {
                return true;
            }
            if (this.trySync) {
                return false;
            }
            this.trySync = true;
            Witchery.packetPipeline.sendToServer(new PacketExtendedEntityRequestSyncToClient((EntityLivingBase)this.villager));
        }
        return false;
    }
}

