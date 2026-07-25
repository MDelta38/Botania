/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.item.ItemBase;
import com.emoniph.witchery.item.ItemDeathsClothes;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemDeathsHand
extends ItemBase {
    public ItemDeathsHand() {
        this.func_77625_d(1);
        this.func_77664_n();
    }

    @SideOnly(value=Side.CLIENT)
    public EnumRarity func_77613_e(ItemStack stack) {
        return EnumRarity.epic;
    }

    @Override
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips) {
        String localText = Witchery.resource(this.func_77658_a() + ".tip");
        if (localText != null) {
            for (String s : localText.split("\n")) {
                if (s.isEmpty()) continue;
                list.add(s);
            }
        }
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int inventorySlot, boolean isHeldItem) {
        if (entity instanceof EntityPlayer && !world.field_72995_K) {
            EntityPlayer player = (EntityPlayer)entity;
            if (this.isDeployed(stack) && TimeUtil.secondsElapsed(1, world.func_72820_D())) {
                if (!ItemDeathsClothes.isFullSetWorn((EntityLivingBase)player)) {
                    this.setDeployed(player, stack, false);
                } else {
                    int level = player.func_71024_bL().func_75116_a();
                    if (level > 0) {
                        player.func_71024_bL().func_75122_a(level == 1 ? -1 : -2, 0.0f);
                    }
                }
            }
        }
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity otherEntity) {
        if (!player.field_70170_p.field_72995_K && otherEntity instanceof EntityLivingBase) {
            EntityLivingBase victim = (EntityLivingBase)otherEntity;
            float MAX_DAMAGE = 15.0f;
            float DAMAGE_PERCENTAGE = 0.1f;
            boolean deployed = this.isDeployed(stack);
            float damagePct = 0.1f;
            float minDamage = 2.0f;
            int hungerRestore = 0;
            int healthRestore = 0;
            if (deployed) {
                int hunger = player.func_71024_bL().func_75116_a();
                if (hunger == 0) {
                    damagePct = 0.5f;
                    minDamage = 4.0f;
                    hungerRestore = 10;
                    healthRestore = 3;
                } else if (hunger <= 4) {
                    damagePct = 0.25f;
                    minDamage = 4.0f;
                    hungerRestore = 3;
                    healthRestore = 2;
                } else if (hunger <= 10) {
                    damagePct = 0.2f;
                    minDamage = 3.0f;
                    hungerRestore = 2;
                    healthRestore = 1;
                } else if (hunger <= 20) {
                    damagePct = 0.15f;
                    minDamage = 3.0f;
                    hungerRestore = 1;
                } else {
                    damagePct = 0.15f;
                    minDamage = 3.0f;
                }
            }
            if (deployed) {
                double r = 1.5;
                AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)(victim.field_70165_t - 1.5), (double)victim.field_70121_D.field_72338_b, (double)(victim.field_70161_v - 1.5), (double)(victim.field_70165_t + 1.5), (double)victim.field_70121_D.field_72337_e, (double)(victim.field_70161_v + 1.5));
                List entities = player.field_70170_p.func_72872_a(EntityLivingBase.class, bb);
                for (Object obj : entities) {
                    float maxHealth;
                    float damage;
                    boolean flag;
                    EntityLivingBase hitEntity = (EntityLivingBase)obj;
                    if (hitEntity == player || !(flag = EntityUtil.touchOfDeath((Entity)hitEntity, (EntityLivingBase)player, damage = Math.min(Math.max((maxHealth = Math.min(hitEntity.func_110138_aP(), 20.0f)) * damagePct, minDamage), 15.0f)))) continue;
                    if (hungerRestore > 0) {
                        player.func_71024_bL().func_75122_a(hungerRestore, 0.0f);
                    }
                    if (healthRestore <= 0) continue;
                    player.func_70691_i((float)healthRestore);
                }
            } else {
                float maxHealth = Math.min(victim.func_110138_aP(), 20.0f);
                float damage = Math.min(Math.max(maxHealth * damagePct, minDamage), 15.0f);
                boolean flag = EntityUtil.touchOfDeath((Entity)victim, (EntityLivingBase)player, damage);
            }
        }
        return true;
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (!world.field_72995_K && ItemDeathsClothes.isFullSetWorn((EntityLivingBase)player)) {
            NBTTagCompound nbtItem;
            if (!stack.func_77942_o()) {
                stack.func_77982_d(new NBTTagCompound());
            }
            boolean deployed = !this.isDeployed(nbtItem = stack.func_77978_p());
            this.setDeployed(player, stack, nbtItem, deployed);
            if (deployed) {
                ParticleEffect.MOB_SPELL.send(SoundEffect.MOB_ENDERDRAGON_GROWL, (Entity)player, 1.0, 2.0, 16);
            }
        }
        return stack;
    }

    private void setDeployed(EntityPlayer player, ItemStack stack, boolean deployed) {
        this.setDeployed(player, stack, stack.func_77978_p(), deployed);
    }

    private void setDeployed(EntityPlayer player, ItemStack stack, NBTTagCompound nbtItem, boolean deployed) {
        if (player != null && !player.field_70170_p.field_72995_K && nbtItem != null) {
            nbtItem.func_74757_a("WITCScytheDeployed", deployed);
            if (player instanceof EntityPlayerMP) {
                ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
            }
        }
    }

    public boolean isDeployed(EntityLivingBase player) {
        ItemStack heldItem = player.func_70694_bm();
        if (heldItem != null && heldItem.func_77973_b() == this) {
            return this.isDeployed(heldItem);
        }
        return false;
    }

    private boolean isDeployed(ItemStack stack) {
        return this.isDeployed(stack.func_77978_p());
    }

    private boolean isDeployed(NBTTagCompound nbtItem) {
        boolean deployed = nbtItem != null && nbtItem.func_74767_n("WITCScytheDeployed");
        return deployed;
    }
}

