/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSourceIndirect
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.projectile;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEmber
extends EntityThrowable
implements IEntityAdditionalSpawnData {
    public int duration = 20;
    public int firey = 0;
    public float damage = 1.0f;

    public EntityEmber(World par1World) {
        super(par1World);
    }

    public EntityEmber(World par1World, EntityLivingBase par2EntityLiving, float scatter) {
        super(par1World, par2EntityLiving);
        this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, this.func_70182_d(), scatter);
    }

    protected float func_70185_h() {
        return 0.0f;
    }

    protected float func_70182_d() {
        return 1.0f;
    }

    public void func_70071_h_() {
        if (this.field_70173_aa > this.duration) {
            this.func_70106_y();
        }
        if (this.duration <= 20) {
            this.field_70159_w *= 0.95;
            this.field_70181_x *= 0.95;
            this.field_70179_y *= 0.95;
        } else {
            this.field_70159_w *= 0.975;
            this.field_70181_x *= 0.975;
            this.field_70179_y *= 0.975;
        }
        if (this.field_70122_E) {
            this.field_70159_w *= 0.66;
            this.field_70181_x *= 0.66;
            this.field_70179_y *= 0.66;
        }
        super.func_70071_h_();
    }

    public void writeSpawnData(ByteBuf data) {
        data.writeByte(this.duration);
    }

    public void readSpawnData(ByteBuf data) {
        this.duration = data.readByte();
    }

    protected void func_70184_a(MovingObjectPosition mop) {
        if (!this.field_70170_p.field_72995_K) {
            if (mop.field_72308_g != null) {
                if (!mop.field_72308_g.func_70045_F() && mop.field_72308_g.func_70097_a(new EntityDamageSourceIndirect("fireball", (Entity)this, (Entity)this.func_85052_h()).func_76361_j(), this.damage)) {
                    mop.field_72308_g.func_70015_d(3 + this.firey);
                }
            } else if (this.field_70146_Z.nextFloat() < 0.025f * (float)this.firey) {
                int i = mop.field_72311_b;
                int j = mop.field_72312_c;
                int k = mop.field_72309_d;
                switch (mop.field_72310_e) {
                    case 0: {
                        --j;
                        break;
                    }
                    case 1: {
                        ++j;
                        break;
                    }
                    case 2: {
                        --k;
                        break;
                    }
                    case 3: {
                        ++k;
                        break;
                    }
                    case 4: {
                        --i;
                        break;
                    }
                    case 5: {
                        ++i;
                    }
                }
                if (this.field_70170_p.func_147437_c(i, j, k)) {
                    this.field_70170_p.func_147449_b(i, j, k, (Block)Blocks.field_150480_ab);
                }
            }
        }
        this.func_70106_y();
    }

    protected boolean func_70041_e_() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public float func_70053_R() {
        return 0.0f;
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74776_a("damage", this.damage);
        par1NBTTagCompound.func_74768_a("firey", this.firey);
        par1NBTTagCompound.func_74768_a("duration", this.duration);
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.damage = par1NBTTagCompound.func_74760_g("damage");
        this.firey = par1NBTTagCompound.func_74762_e("firey");
        this.duration = par1NBTTagCompound.func_74762_e("duration");
    }

    public boolean func_70067_L() {
        return false;
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
        return false;
    }
}

