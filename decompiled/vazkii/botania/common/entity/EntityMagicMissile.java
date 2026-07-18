/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package vazkii.botania.common.entity;

import cpw.mods.fml.relauncher.ReflectionHelper;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.lib.LibObfuscation;

public class EntityMagicMissile
extends EntityThrowable {
    private static final String TAG_TIME = "time";
    double lockX;
    double lockY = -1.0;
    double lockZ;
    int time = 0;

    public EntityMagicMissile(World world) {
        super(world);
        this.func_70105_a(0.0f, 0.0f);
    }

    public EntityMagicMissile(EntityLivingBase thrower, boolean evil) {
        this(thrower.field_70170_p);
        ReflectionHelper.setPrivateValue(EntityThrowable.class, (Object)((Object)this), (Object)thrower, (String[])LibObfuscation.THROWER);
        this.setEvil(evil);
    }

    protected void func_70088_a() {
        this.field_70180_af.func_75682_a(25, (Object)0);
        this.field_70180_af.func_75682_a(26, (Object)0);
    }

    public void setEvil(boolean evil) {
        this.field_70180_af.func_75692_b(25, (Object)((byte)(evil ? 1 : 0)));
    }

    public boolean isEvil() {
        return this.field_70180_af.func_75683_a(25) == 1;
    }

    public void setTarget(EntityLivingBase e) {
        this.field_70180_af.func_75692_b(26, (Object)(e == null ? -1 : e.func_145782_y()));
    }

    public EntityLivingBase getTargetEntity() {
        int id = this.field_70180_af.func_75679_c(26);
        Entity e = this.field_70170_p.func_73045_a(id);
        if (e != null && e instanceof EntityLivingBase) {
            return (EntityLivingBase)e;
        }
        return null;
    }

    public void func_70071_h_() {
        double lastTickPosX = this.field_70142_S;
        double lastTickPosY = this.field_70137_T;
        double lastTickPosZ = this.field_70136_U;
        super.func_70071_h_();
        if (!(this.field_70170_p.field_72995_K || this.getTarget() && this.time <= 40)) {
            this.func_70106_y();
            return;
        }
        boolean evil = this.isEvil();
        Vector3 thisVec = Vector3.fromEntityCenter((Entity)this);
        Vector3 oldPos = new Vector3(lastTickPosX, lastTickPosY, lastTickPosZ);
        Vector3 diff = thisVec.copy().sub(oldPos);
        Vector3 step = diff.copy().normalize().multiply(0.05);
        int steps = (int)(diff.mag() / step.mag());
        Vector3 particlePos = oldPos.copy();
        Botania.proxy.setSparkleFXCorrupt(evil);
        for (int i = 0; i < steps; ++i) {
            Botania.proxy.sparkleFX(this.field_70170_p, particlePos.x, particlePos.y, particlePos.z, 1.0f, evil ? 0.0f : 0.4f, 1.0f, 0.8f, 2);
            if (this.field_70170_p.field_73012_v.nextInt(steps) <= 1) {
                Botania.proxy.sparkleFX(this.field_70170_p, particlePos.x + (Math.random() - 0.5) * 0.4, particlePos.y + (Math.random() - 0.5) * 0.4, particlePos.z + (Math.random() - 0.5) * 0.4, 1.0f, evil ? 0.0f : 0.4f, 1.0f, 0.8f, 2);
            }
            particlePos.add(step);
        }
        Botania.proxy.setSparkleFXCorrupt(false);
        EntityLivingBase target = this.getTargetEntity();
        if (target != null) {
            if (this.lockY == -1.0) {
                this.lockX = target.field_70165_t;
                this.lockY = target.field_70163_u;
                this.lockZ = target.field_70161_v;
            }
            Vector3 targetVec = evil ? new Vector3(this.lockX, this.lockY, this.lockZ) : Vector3.fromEntityCenter((Entity)target);
            Vector3 diffVec = targetVec.copy().sub(thisVec);
            Vector3 motionVec = diffVec.copy().normalize().multiply(evil ? 0.5 : 0.6);
            this.field_70159_w = motionVec.x;
            this.field_70181_x = motionVec.y;
            if (this.time < 10) {
                this.field_70181_x = Math.abs(this.field_70181_x);
            }
            this.field_70179_y = motionVec.z;
            List targetList = this.field_70170_p.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)(this.field_70165_t - 0.5), (double)(this.field_70163_u - 0.5), (double)(this.field_70161_v - 0.5), (double)(this.field_70165_t + 0.5), (double)(this.field_70163_u + 0.5), (double)(this.field_70161_v + 0.5)));
            if (targetList.contains(target) && target != null) {
                EntityLivingBase thrower = this.func_85052_h();
                if (thrower != null) {
                    EntityPlayer player = thrower instanceof EntityPlayer ? (EntityPlayer)thrower : null;
                    target.func_70097_a(player == null ? DamageSource.func_76358_a((EntityLivingBase)thrower) : DamageSource.func_76365_a((EntityPlayer)player), evil ? 12.0f : 7.0f);
                } else {
                    target.func_70097_a(DamageSource.field_76377_j, evil ? 12.0f : 7.0f);
                }
                this.func_70106_y();
            }
            if (evil && diffVec.mag() < 1.0) {
                this.func_70106_y();
            }
        }
        ++this.time;
    }

    public void func_70014_b(NBTTagCompound cmp) {
        super.func_70014_b(cmp);
        cmp.func_74768_a(TAG_TIME, this.time);
    }

    public void func_70037_a(NBTTagCompound cmp) {
        super.func_70037_a(cmp);
        this.time = cmp.func_74762_e(TAG_TIME);
    }

    public boolean getTarget() {
        EntityLivingBase target = this.getTargetEntity();
        if (target != null && target.func_110143_aJ() > 0.0f && !target.field_70128_L && this.field_70170_p.field_72996_f.contains(target)) {
            return true;
        }
        if (target != null) {
            this.setTarget(null);
        }
        double range = 12.0;
        List entities = this.field_70170_p.func_72872_a(this.isEvil() ? EntityPlayer.class : IMob.class, AxisAlignedBB.func_72330_a((double)(this.field_70165_t - range), (double)(this.field_70163_u - range), (double)(this.field_70161_v - range), (double)(this.field_70165_t + range), (double)(this.field_70163_u + range), (double)(this.field_70161_v + range)));
        while (entities.size() > 0) {
            Entity e = (Entity)entities.get(this.field_70170_p.field_73012_v.nextInt(entities.size()));
            if (!(e instanceof EntityLivingBase) || e.field_70128_L) {
                entities.remove(e);
                continue;
            }
            target = (EntityLivingBase)e;
            this.setTarget(target);
            break;
        }
        return target != null;
    }

    protected void func_70184_a(MovingObjectPosition pos) {
        Block block = this.field_70170_p.func_147439_a(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
        if (!(block instanceof BlockBush || block instanceof BlockLeaves || pos.field_72308_g != null && this.getTargetEntity() != pos.field_72308_g)) {
            this.func_70106_y();
        }
    }
}

