/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.monster.EntityCultistCleric;
import thaumcraft.common.entities.monster.EntityCultistKnight;
import thaumcraft.common.entities.monster.boss.EntityCultistLeader;

public class EntityCultist
extends EntityMob {
    public EntityCultist(World p_i1745_1_) {
        super(p_i1745_1_);
        this.func_70105_a(0.6f, 1.8f);
        this.field_70728_aV = 10;
        this.func_70661_as().func_75498_b(true);
        this.func_70661_as().func_75491_a(true);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0);
    }

    protected void func_70088_a() {
        super.func_70088_a();
    }

    public boolean func_98052_bS() {
        return false;
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected Item func_146068_u() {
        return Item.func_150899_d((int)0);
    }

    protected void func_70628_a(boolean flag, int i) {
        int r = this.field_70146_Z.nextInt(10);
        if (r == 0) {
            this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 9), 1.5f);
        } else if (r <= 1) {
            this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 17), 1.5f);
        } else if (r <= 3 + i) {
            this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 18), 1.5f);
        }
        super.func_70628_a(flag, i);
    }

    protected void func_70600_l(int p_70600_1_) {
        this.func_70099_a(new ItemStack(ConfigItems.itemEldritchObject, 1, 1), 1.0f);
    }

    protected void func_82164_bB() {
    }

    protected void func_82162_bC() {
    }

    public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_) {
        this.func_82164_bB();
        this.func_82162_bC();
        return super.func_110161_a(p_110161_1_);
    }

    protected boolean func_70692_ba() {
        return true;
    }

    public boolean func_70652_k(Entity p_70652_1_) {
        return super.func_70652_k(p_70652_1_);
    }

    public void func_70037_a(NBTTagCompound nbt) {
        super.func_70037_a(nbt);
        if (nbt.func_74764_b("HomeD")) {
            this.func_110171_b(nbt.func_74762_e("HomeX"), nbt.func_74762_e("HomeY"), nbt.func_74762_e("HomeZ"), nbt.func_74762_e("HomeD"));
        }
    }

    public void func_70014_b(NBTTagCompound nbt) {
        super.func_70014_b(nbt);
        if (this.func_110172_bL() != null && this.func_110174_bM() > 0.0f) {
            nbt.func_74768_a("HomeD", (int)this.func_110174_bM());
            nbt.func_74768_a("HomeX", this.func_110172_bL().field_71574_a);
            nbt.func_74768_a("HomeY", this.func_110172_bL().field_71572_b);
            nbt.func_74768_a("HomeZ", this.func_110172_bL().field_71573_c);
        }
    }

    public boolean func_142014_c(EntityLivingBase el) {
        return el instanceof EntityCultist || el instanceof EntityCultistLeader;
    }

    public boolean func_70686_a(Class clazz) {
        if (clazz == EntityCultistCleric.class || clazz == EntityCultistLeader.class || clazz == EntityCultistKnight.class) {
            return false;
        }
        return super.func_70686_a(clazz);
    }
}

