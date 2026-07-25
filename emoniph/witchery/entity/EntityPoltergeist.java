/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityHanging
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIOpenDoor
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.blocks.BlockBrazier;
import com.emoniph.witchery.blocks.BlockKettle;
import com.emoniph.witchery.entity.EntitySummonedUndead;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityPoltergeist
extends EntitySummonedUndead {
    private int attackTimer;

    public EntityPoltergeist(World par1World) {
        super(par1World);
        this.func_70661_as().func_75491_a(true);
        this.func_70661_as().func_75498_b(true);
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, EntityPlayer.class, 1.0, false));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(20.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
    }

    protected boolean func_70650_aV() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte par1) {
        if (par1 == 4) {
            this.attackTimer = 15;
        } else {
            super.func_70103_a(par1);
        }
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
        if (TimeUtil.secondsElapsed(5, this.field_70173_aa)) {
            double RADIUS = 16.0;
            double RADIUS_SQ = 256.0;
            double THROW_RANGE = 3.0;
            double THROW_RANGE_SQ = 9.0;
            double EVIL_RANGE = 8.0;
            double EVIL_RANGE_SQ = 64.0;
            double MAX_SPEED = 0.6;
            AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - 16.0), (double)(this.field_70163_u - 16.0), (double)(this.field_70161_v - 16.0), (double)(this.field_70165_t + 16.0), (double)(this.field_70163_u + 16.0), (double)(this.field_70161_v + 16.0));
            List hangingItems = this.field_70170_p.func_72872_a(EntityHanging.class, bounds);
            for (Object obj : hangingItems) {
                EntityHanging hanging = (EntityHanging)obj;
                if (!(this.func_70068_e((Entity)hanging) <= 256.0)) continue;
                if (this.func_70068_e((Entity)hanging) <= 9.0) {
                    if (!this.field_70170_p.field_72995_K) {
                        hanging.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), 3.0f);
                    }
                    this.attackTimer = 15;
                    this.field_70170_p.func_72960_a((Entity)this, (byte)4);
                } else {
                    this.func_70661_as().func_75492_a(hanging.field_70165_t, hanging.field_70163_u, hanging.field_70161_v, 1.0);
                }
                return;
            }
            EntityPlayer summoner = this.getSummoner();
            if (summoner != null && this.func_70068_e((Entity)summoner) <= 64.0) {
                TileEntity closest = null;
                double closestDist = -1.0;
                for (Object obj : this.field_70170_p.field_147482_g) {
                    if (!(obj instanceof IInventory) || obj instanceof BlockKettle.TileEntityKettle || obj instanceof BlockBrazier.TileEntityBrazier) continue;
                    TileEntity tile = (TileEntity)obj;
                    double distSq = this.func_70092_e(0.5 + (double)tile.field_145851_c, 0.5 + (double)tile.field_145848_d, 0.5 + (double)tile.field_145849_e);
                    if (!(distSq <= 256.0)) continue;
                    IInventory inventory = (IInventory)tile;
                    ArrayList<Integer> indices = new ArrayList<Integer>();
                    for (int i = 0; i < inventory.func_70302_i_(); ++i) {
                        if (inventory.func_70301_a(i) == null) continue;
                        indices.add(i);
                    }
                    if (indices.size() <= 0 || closest != null && !(distSq < closestDist)) continue;
                    closest = tile;
                    closestDist = distSq;
                }
                if (closest != null) {
                    IInventory inventory = (IInventory)closest;
                    ArrayList<Integer> indices = new ArrayList<Integer>();
                    for (int i = 0; i < inventory.func_70302_i_(); ++i) {
                        if (inventory.func_70301_a(i) == null) continue;
                        indices.add(i);
                    }
                    if (indices.size() > 0) {
                        if (this.func_70092_e(0.5 + (double)closest.field_145851_c, 0.5 + (double)closest.field_145848_d, 0.5 + (double)closest.field_145849_e) <= 9.0) {
                            if (!this.field_70170_p.field_72995_K) {
                                int slot = (Integer)indices.get(this.field_70170_p.field_73012_v.nextInt(indices.size()));
                                ItemStack stack = inventory.func_70301_a(slot);
                                if (stack.field_77994_a > 1) {
                                    --stack.field_77994_a;
                                    stack = stack.func_77946_l();
                                    stack.field_77994_a = 1;
                                } else {
                                    inventory.func_70299_a(slot, null);
                                }
                                EntityItem itemEntity = new EntityItem(this.field_70170_p, 0.5 + (double)closest.field_145851_c, 0.5 + (double)closest.field_145848_d, 0.5 + (double)closest.field_145849_e, stack);
                                this.field_70170_p.func_72838_d((Entity)itemEntity);
                                itemEntity.lifespan = TimeUtil.minsToTicks(15);
                                itemEntity.field_70159_w = -0.3 + this.field_70170_p.field_73012_v.nextDouble() * 0.6;
                                itemEntity.field_70181_x = 0.1 + this.field_70170_p.field_73012_v.nextDouble() * 0.2;
                                itemEntity.field_70179_y = -0.3 + this.field_70170_p.field_73012_v.nextDouble() * 0.6;
                            }
                            this.attackTimer = 15;
                            this.field_70170_p.func_72960_a((Entity)this, (byte)4);
                        } else {
                            this.func_70661_as().func_75492_a((double)closest.field_145851_c, (double)closest.field_145848_d, (double)closest.field_145849_e, 1.0);
                        }
                        return;
                    }
                }
            }
            List droppedItems = this.field_70170_p.func_72872_a(EntityItem.class, bounds);
            for (Object obj : droppedItems) {
                EntityItem dropped = (EntityItem)obj;
                if (!(this.func_70068_e((Entity)dropped) <= 256.0)) continue;
                if (this.func_70068_e((Entity)dropped) <= 9.0) {
                    if (!this.field_70170_p.field_72995_K) {
                        dropped.field_70159_w = -0.3 + this.field_70170_p.field_73012_v.nextDouble() * 0.6;
                        dropped.field_70181_x = 0.1 + this.field_70170_p.field_73012_v.nextDouble() * 0.2;
                        dropped.field_70179_y = -0.3 + this.field_70170_p.field_73012_v.nextDouble() * 0.6;
                    }
                    this.attackTimer = 15;
                    this.field_70170_p.func_72960_a((Entity)this, (byte)4);
                } else {
                    this.func_70661_as().func_75492_a(dropped.field_70165_t, dropped.field_70163_u, dropped.field_70161_v, 1.0);
                }
                return;
            }
        }
    }

    public void func_70071_h_() {
        super.func_70071_h_();
    }

    public boolean func_70652_k(Entity par1Entity) {
        boolean flag = super.func_70652_k(par1Entity);
        return flag;
    }

    protected String func_70639_aQ() {
        return null;
    }

    protected String func_70621_aR() {
        return "witchery:mob.spectre.spectre_die";
    }

    protected String func_70673_aS() {
        return "witchery:mob.spectre.spectre_die";
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.witchery.poltergeist.name");
    }

    @Override
    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
    }

    @Override
    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
    }

    public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData) {
        IEntityLivingData par1EntityLivingData1 = super.func_110161_a(par1EntityLivingData);
        this.func_70690_d(new PotionEffect(Potion.field_76441_p.field_76415_H, Integer.MAX_VALUE));
        return par1EntityLivingData1;
    }
}

