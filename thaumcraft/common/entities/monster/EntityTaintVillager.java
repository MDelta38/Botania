/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAIMoveIndoors
 *  net.minecraft.entity.ai.EntityAIMoveThroughVillage
 *  net.minecraft.entity.ai.EntityAIMoveTowardsRestriction
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAIOpenDoor
 *  net.minecraft.entity.ai.EntityAIRestrictOpenDoor
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntityAIWatchClosest2
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.village.Village
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;

public class EntityTaintVillager
extends EntityMob
implements ITaintedMob {
    private int randomTickDivider = 0;
    private boolean isMatingFlag = false;
    private boolean isPlayingFlag = false;
    Village villageObj = null;

    public EntityTaintVillager(World par1World) {
        this(par1World, 0);
    }

    public EntityTaintVillager(World par1World, int par2) {
        super(par1World);
        this.func_70661_as().func_75498_b(true);
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIMoveIndoors((EntityCreature)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new AIAttackOnCollide((EntityCreature)this, EntityPlayer.class, 1.0, false));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIRestrictOpenDoor((EntityCreature)this));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIMoveThroughVillage((EntityCreature)this, 1.0, false));
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f));
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityVillager.class, 5.0f, 0.02f));
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(10, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLivingBase.class, 8.0f));
        this.field_70715_bh.func_75776_a(0, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3);
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70170_p.field_72995_K && this.field_70173_aa < 5) {
            for (int a = 0; a < Thaumcraft.proxy.particleCount(10); ++a) {
                Thaumcraft.proxy.splooshFX((Entity)this);
            }
        }
    }

    public boolean func_70650_aV() {
        return true;
    }

    protected void func_70629_bd() {
        if (--this.randomTickDivider <= 0) {
            this.field_70170_p.field_72982_D.func_75551_a(MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70163_u), MathHelper.func_76128_c((double)this.field_70161_v));
            this.randomTickDivider = 70 + this.field_70146_Z.nextInt(50);
            this.villageObj = this.field_70170_p.field_72982_D.func_75550_a(MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70163_u), MathHelper.func_76128_c((double)this.field_70161_v), 32);
            if (this.villageObj == null) {
                this.func_110177_bN();
            } else {
                ChunkCoordinates chunkcoordinates = this.villageObj.func_75577_a();
                this.func_110171_b(chunkcoordinates.field_71574_a, chunkcoordinates.field_71572_b, chunkcoordinates.field_71573_c, (int)((float)this.villageObj.func_75568_b() * 0.6f));
            }
        }
        super.func_70629_bd();
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, (Object)0);
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
    }

    protected boolean func_70692_ba() {
        return false;
    }

    protected Item func_146068_u() {
        return ConfigItems.itemResource;
    }

    protected void func_70628_a(boolean flag, int i) {
        if (this.field_70170_p.field_73012_v.nextInt(2) == 0) {
            if (this.field_70170_p.field_73012_v.nextBoolean()) {
                this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 11), this.field_70131_O / 2.0f);
            } else {
                this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 12), this.field_70131_O / 2.0f);
            }
        }
        if (this.field_70170_p.field_73012_v.nextInt(13) < 1 + i) {
            this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 18), 1.5f);
        }
    }

    protected String func_70639_aQ() {
        return "mob.villager.idle";
    }

    protected String func_70621_aR() {
        return "mob.villager.hit";
    }

    protected String func_70673_aS() {
        return "mob.villager.death";
    }

    protected float func_70647_i() {
        return 0.7f;
    }

    public void func_70604_c(EntityLivingBase par1EntityLiving) {
        super.func_70604_c(par1EntityLiving);
        if (this.villageObj != null && par1EntityLiving != null) {
            this.villageObj.func_75575_a(par1EntityLiving);
        }
    }
}

