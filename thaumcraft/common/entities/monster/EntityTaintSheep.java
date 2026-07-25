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
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IShearable
 */
package thaumcraft.common.entities.monster;

import java.util.ArrayList;
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
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
import thaumcraft.common.entities.ai.misc.AIConvertGrass;

public class EntityTaintSheep
extends EntityMob
implements IShearable,
ITaintedMob {
    private int sheepTimer;
    private AIConvertGrass field_48137_c = new AIConvertGrass((EntityLiving)this);

    public EntityTaintSheep(World par1World) {
        super(par1World);
        this.func_70105_a(0.9f, 1.3f);
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)this.field_48137_c);
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AIAttackOnCollide((EntityCreature)this, EntityPlayer.class, 1.0, false));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AIAttackOnCollide((EntityCreature)this, EntityVillager.class, 1.0, true));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(0, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.field_70715_bh.func_75776_a(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.field_70715_bh.func_75776_a(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityVillager.class, 0, false));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25);
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    public int func_70658_aO() {
        return 1;
    }

    protected void func_70619_bc() {
        this.sheepTimer = this.field_48137_c.func_48396_h();
        super.func_70619_bc();
    }

    public void func_70636_d() {
        if (this.field_70170_p.field_72995_K) {
            this.sheepTimer = Math.max(0, this.sheepTimer - 1);
        }
        super.func_70636_d();
        if (this.field_70170_p.field_72995_K && this.field_70173_aa < 5) {
            for (int a = 0; a < Thaumcraft.proxy.particleCount(10); ++a) {
                Thaumcraft.proxy.splooshFX((Entity)this);
            }
        }
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, (Object)new Byte(0));
    }

    protected Item func_146068_u() {
        return ConfigItems.itemResource;
    }

    protected void func_70628_a(boolean flag, int i) {
        if (this.field_70170_p.field_73012_v.nextInt(3) == 0) {
            if (this.field_70170_p.field_73012_v.nextBoolean()) {
                this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 11), this.field_70131_O / 2.0f);
            } else {
                this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 12), this.field_70131_O / 2.0f);
            }
        }
    }

    public void func_70103_a(byte par1) {
        if (par1 == 10) {
            this.sheepTimer = 40;
        } else {
            super.func_70103_a(par1);
        }
    }

    public float func_44003_c(float par1) {
        return this.sheepTimer <= 0 ? 0.0f : (this.sheepTimer >= 4 && this.sheepTimer <= 36 ? 1.0f : (this.sheepTimer < 4 ? ((float)this.sheepTimer - par1) / 4.0f : -((float)(this.sheepTimer - 40) - par1) / 4.0f));
    }

    public float func_44002_d(float par1) {
        if (this.sheepTimer > 4 && this.sheepTimer <= 36) {
            float var2 = ((float)(this.sheepTimer - 4) - par1) / 32.0f;
            return 0.62831855f + 0.2199115f * MathHelper.func_76126_a((float)(var2 * 28.7f));
        }
        return this.sheepTimer > 0 ? 0.62831855f : this.field_70125_A / 57.295776f;
    }

    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        return super.func_70085_c(par1EntityPlayer);
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a("Sheared", this.getSheared());
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.setSheared(par1NBTTagCompound.func_74767_n("Sheared"));
    }

    protected String func_70639_aQ() {
        return "mob.sheep.say";
    }

    protected String func_70621_aR() {
        return "mob.sheep.say";
    }

    protected String func_70673_aS() {
        return "mob.sheep.say";
    }

    protected void playStepSound(int par1, int par2, int par3, int par4) {
        this.func_85030_a("mob.sheep.step", 0.15f, 1.0f);
    }

    protected float func_70647_i() {
        return 0.7f;
    }

    public boolean getSheared() {
        return (this.field_70180_af.func_75683_a(16) & 0x10) != 0;
    }

    public void setSheared(boolean par1) {
        byte var2 = this.field_70180_af.func_75683_a(16);
        if (par1) {
            this.field_70180_af.func_75692_b(16, (Object)((byte)(var2 | 0x10)));
        } else {
            this.field_70180_af.func_75692_b(16, (Object)((byte)(var2 & 0xFFFFFFEF)));
        }
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int X, int Y, int Z) {
        return !this.getSheared();
    }

    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int X, int Y, int Z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        this.setSheared(true);
        int i = 1 + this.field_70146_Z.nextInt(3);
        for (int j = 0; j < i; ++j) {
            ret.add(new ItemStack(Blocks.field_150325_L, 1, 10));
        }
        return ret;
    }
}

