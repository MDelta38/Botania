/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIEatGrass
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIPanic
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITempt
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.monster.EntitySpider
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IShearable
 */
package com.kentington.thaumichorizons.common.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class EntitySheeder
extends EntitySpider
implements IShearable {
    private int sheepTimer;
    private EntityAIEatGrass field_146087_bs = new EntityAIEatGrass((EntityLiving)this);

    public EntitySheeder(World p_i1743_1_) {
        super(p_i1743_1_);
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 0.5));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 0.44, Items.field_151015_O, false));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)this.field_146087_bs);
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.4));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70128_L && !this.field_70170_p.field_72995_K && this.field_70170_p.field_73013_u == EnumDifficulty.PEACEFUL && this.func_110143_aJ() > 0.0f) {
            this.field_70128_L = false;
        }
    }

    protected Entity func_70782_k() {
        return null;
    }

    protected String func_70639_aQ() {
        return "mob.sheep.say";
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return !this.getSheared() && !this.func_70631_g_();
    }

    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        this.setSheared(true);
        int i = 2 + this.field_70146_Z.nextInt(4);
        for (int j = 0; j < i; ++j) {
            ret.add(new ItemStack(Items.field_151007_F, 1, 0));
        }
        this.func_85030_a("mob.sheep.shear", 1.0f, 1.0f);
        return ret;
    }

    public void setSheared(boolean p_70893_1_) {
        byte b0 = this.field_70180_af.func_75683_a(16);
        if (p_70893_1_) {
            this.field_70180_af.func_75692_b(16, (Object)((byte)(b0 | 0x10)));
        } else {
            this.field_70180_af.func_75692_b(16, (Object)((byte)(b0 & 0xFFFFFFEF)));
        }
    }

    public boolean getSheared() {
        return (this.field_70180_af.func_75683_a(16) & 0x10) != 0;
    }

    public void func_70615_aA() {
        this.setSheared(false);
    }

    public void func_70014_b(NBTTagCompound p_70014_1_) {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74757_a("Sheared", this.getSheared());
    }

    public void func_70037_a(NBTTagCompound p_70037_1_) {
        super.func_70037_a(p_70037_1_);
        this.setSheared(p_70037_1_.func_74767_n("Sheared"));
    }

    protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
        if (!this.getSheared()) {
            this.func_70099_a(new ItemStack(Items.field_151007_F), 0.0f);
        }
    }

    protected Item func_146068_u() {
        return Items.field_151007_F;
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected void func_70619_bc() {
        this.sheepTimer = this.field_146087_bs.func_151499_f();
        super.func_70619_bc();
    }

    public void func_70636_d() {
        if (this.field_70170_p.field_72995_K) {
            this.sheepTimer = Math.max(0, this.sheepTimer - 1);
        }
        super.func_70636_d();
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte p_70103_1_) {
        if (p_70103_1_ == 10) {
            this.sheepTimer = 40;
        } else {
            super.func_70103_a(p_70103_1_);
        }
    }
}

