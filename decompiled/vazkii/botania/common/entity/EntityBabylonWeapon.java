/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package vazkii.botania.common.entity;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityThrowableCopy;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.item.relic.ItemKingKey;

public class EntityBabylonWeapon
extends EntityThrowableCopy {
    private static final String TAG_CHARGING = "charging";
    private static final String TAG_VARIETY = "variety";
    private static final String TAG_CHARGE_TICKS = "chargeTicks";
    private static final String TAG_LIVE_TICKS = "liveTicks";
    private static final String TAG_DELAY = "delay";
    private static final String TAG_ROTATION = "rotation";

    public EntityBabylonWeapon(World world) {
        super(world);
    }

    public EntityBabylonWeapon(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    @Override
    protected void func_70088_a() {
        this.func_70105_a(0.0f, 0.0f);
        this.field_70180_af.func_75682_a(20, (Object)0);
        this.field_70180_af.func_75682_a(21, (Object)0);
        this.field_70180_af.func_75682_a(22, (Object)0);
        this.field_70180_af.func_75682_a(23, (Object)0);
        this.field_70180_af.func_75682_a(24, (Object)0);
        this.field_70180_af.func_75682_a(25, (Object)Float.valueOf(0.0f));
        this.field_70180_af.func_82708_h(20);
        this.field_70180_af.func_82708_h(21);
        this.field_70180_af.func_82708_h(22);
        this.field_70180_af.func_82708_h(23);
        this.field_70180_af.func_82708_h(24);
        this.field_70180_af.func_82708_h(25);
    }

    @Override
    public void func_70071_h_() {
        EntityLivingBase thrower = this.getThrower();
        if (!(this.field_70170_p.field_72995_K || thrower != null && thrower instanceof EntityPlayer && !thrower.field_70128_L)) {
            this.func_70106_y();
            return;
        }
        EntityPlayer player = (EntityPlayer)thrower;
        boolean charging = this.isCharging();
        if (!this.field_70170_p.field_72995_K) {
            boolean newCharging;
            ItemStack stack = player == null ? null : player.func_71045_bC();
            boolean bl = newCharging = stack != null && stack.func_77973_b() == ModItems.kingKey && ItemKingKey.isCharging(stack);
            if (charging != newCharging) {
                this.setCharging(newCharging);
                charging = newCharging;
            }
        }
        double x = this.field_70159_w;
        double y = this.field_70181_x;
        double z = this.field_70179_y;
        int liveTime = this.getLiveTicks();
        int delay = this.getDelay();
        if (charging &= liveTime == 0) {
            this.field_70159_w = 0.0;
            this.field_70181_x = 0.0;
            this.field_70179_y = 0.0;
            int chargeTime = this.getChargeTicks();
            this.setChargeTicks(chargeTime + 1);
            if (this.field_70170_p.field_73012_v.nextInt(20) == 0) {
                this.field_70170_p.func_72956_a((Entity)this, "botania:babylonSpawn", 0.1f, 1.0f + this.field_70170_p.field_73012_v.nextFloat() * 3.0f);
            }
        } else {
            if (liveTime < delay) {
                this.field_70159_w = 0.0;
                this.field_70181_x = 0.0;
                this.field_70179_y = 0.0;
            } else if (liveTime == delay && player != null) {
                Vector3 playerLook = null;
                MovingObjectPosition lookat = ToolCommons.raytraceFromEntity(this.field_70170_p, (Entity)player, true, 64.0);
                playerLook = lookat == null ? new Vector3(player.func_70040_Z()).multiply(64.0).add(Vector3.fromEntity((Entity)player)) : new Vector3((double)lookat.field_72311_b + 0.5, (double)lookat.field_72312_c + 0.5, (double)lookat.field_72309_d + 0.5);
                Vector3 thisVec = Vector3.fromEntityCenter(this);
                Vector3 motionVec = playerLook.sub(thisVec).normalize().multiply(2.0);
                x = motionVec.x;
                y = motionVec.y;
                z = motionVec.z;
                this.field_70170_p.func_72956_a((Entity)this, "botania:babylonAttack", 2.0f, 0.1f + this.field_70170_p.field_73012_v.nextFloat() * 3.0f);
            }
            this.setLiveTicks(liveTime + 1);
            if (!this.field_70170_p.field_72995_K) {
                AxisAlignedBB axis = AxisAlignedBB.func_72330_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v, (double)this.field_70142_S, (double)this.field_70137_T, (double)this.field_70136_U).func_72314_b(2.0, 2.0, 2.0);
                List entities = this.field_70170_p.func_72872_a(EntityLivingBase.class, axis);
                for (EntityLivingBase living : entities) {
                    if (living == thrower || living.field_70737_aN != 0) continue;
                    if (player != null) {
                        living.func_70097_a(DamageSource.func_76365_a((EntityPlayer)player), 20.0f);
                    } else {
                        living.func_70097_a(DamageSource.field_76377_j, 20.0f);
                    }
                    this.onImpact(new MovingObjectPosition((Entity)living));
                    return;
                }
            }
        }
        super.func_70071_h_();
        this.field_70159_w = x;
        this.field_70181_x = y;
        this.field_70179_y = z;
        if (liveTime > delay) {
            Botania.proxy.wispFX(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0f, 1.0f, 0.0f, 0.3f, 0.0f);
        }
        if (liveTime > 200 + delay) {
            this.func_70106_y();
        }
    }

    @Override
    protected void onImpact(MovingObjectPosition pos) {
        EntityLivingBase thrower = this.getThrower();
        if (pos.field_72308_g == null || pos.field_72308_g != thrower) {
            this.field_70170_p.func_72876_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 3.0f, false);
            this.func_70106_y();
        }
    }

    @Override
    public void func_70014_b(NBTTagCompound cmp) {
        super.func_70014_b(cmp);
        cmp.func_74757_a(TAG_CHARGING, this.isCharging());
        cmp.func_74768_a(TAG_VARIETY, this.getVariety());
        cmp.func_74768_a(TAG_CHARGE_TICKS, this.getChargeTicks());
        cmp.func_74768_a(TAG_LIVE_TICKS, this.getLiveTicks());
        cmp.func_74768_a(TAG_DELAY, this.getDelay());
        cmp.func_74776_a(TAG_ROTATION, this.getRotation());
    }

    @Override
    public void func_70037_a(NBTTagCompound cmp) {
        super.func_70037_a(cmp);
        this.setCharging(cmp.func_74767_n(TAG_CHARGING));
        this.setVariety(cmp.func_74762_e(TAG_VARIETY));
        this.setChargeTicks(cmp.func_74762_e(TAG_CHARGE_TICKS));
        this.setLiveTicks(cmp.func_74762_e(TAG_LIVE_TICKS));
        this.setDelay(cmp.func_74762_e(TAG_DELAY));
        this.setRotation(cmp.func_74760_g(TAG_ROTATION));
    }

    public boolean isCharging() {
        return this.field_70180_af.func_75683_a(20) == 1;
    }

    public void setCharging(boolean charging) {
        this.field_70180_af.func_75692_b(20, (Object)((byte)(charging ? 1 : 0)));
    }

    public int getVariety() {
        return this.field_70180_af.func_75679_c(21);
    }

    public void setVariety(int var) {
        this.field_70180_af.func_75692_b(21, (Object)var);
    }

    public int getChargeTicks() {
        return this.field_70180_af.func_75679_c(22);
    }

    public void setChargeTicks(int ticks) {
        this.field_70180_af.func_75692_b(22, (Object)ticks);
    }

    public int getLiveTicks() {
        return this.field_70180_af.func_75679_c(23);
    }

    public void setLiveTicks(int ticks) {
        this.field_70180_af.func_75692_b(23, (Object)ticks);
    }

    public int getDelay() {
        return this.field_70180_af.func_75679_c(24);
    }

    public void setDelay(int delay) {
        this.field_70180_af.func_75692_b(24, (Object)delay);
    }

    public float getRotation() {
        return this.field_70180_af.func_111145_d(25);
    }

    public void setRotation(float rot) {
        this.field_70180_af.func_75692_b(25, (Object)Float.valueOf(rot));
    }
}

