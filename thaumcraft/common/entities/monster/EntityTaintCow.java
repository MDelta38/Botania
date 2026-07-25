/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;

public class EntityTaintCow
extends EntityMob
implements ITaintedMob {
    public EntityTaintCow(World par1World) {
        super(par1World);
        this.func_70105_a(0.9f, 1.3f);
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new AIAttackOnCollide((EntityCreature)this, EntityPlayer.class, 1.0, false));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AIAttackOnCollide((EntityCreature)this, EntityVillager.class, 1.0, true));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new AIAttackOnCollide((EntityCreature)this, EntityAnimal.class, 1.0, false));
        this.field_70715_bh.func_75776_a(0, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityVillager.class, 0, false));
        this.field_70715_bh.func_75776_a(8, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityAnimal.class, 0, false));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(40.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(6.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.27);
    }

    public boolean func_70650_aV() {
        return true;
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
    }

    protected String func_70639_aQ() {
        return "mob.cow.say";
    }

    protected String func_70621_aR() {
        return "mob.cow.hurt";
    }

    protected String func_70673_aS() {
        return "mob.cow.hurt";
    }

    protected void playStepSound(int par1, int par2, int par3, int par4) {
        this.func_85030_a("mob.cow.step", 0.15f, 1.0f);
    }

    protected float func_70647_i() {
        return 0.7f;
    }

    protected float func_70599_aP() {
        return 0.4f;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70170_p.field_72995_K && this.field_70173_aa < 5) {
            for (int a = 0; a < Thaumcraft.proxy.particleCount(10); ++a) {
                Thaumcraft.proxy.splooshFX((Entity)this);
            }
        }
    }

    protected Item func_146068_u() {
        return ConfigItems.itemResource;
    }

    protected void func_70628_a(boolean flag, int i) {
        if (this.field_70170_p.field_73012_v.nextBoolean()) {
            this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 11), this.field_70131_O / 2.0f);
        } else {
            this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 12), this.field_70131_O / 2.0f);
        }
    }
}

