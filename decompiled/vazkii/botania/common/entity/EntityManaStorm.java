/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package vazkii.botania.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.item.ModItems;

public class EntityManaStorm
extends Entity {
    private static final String TAG_TIME = "time";
    private static final String TAG_BURSTS_FIRED = "burstsFired";
    private static final String TAG_DEATH_TIME = "deathTime";
    public static final int TOTAL_BURSTS = 250;
    public static final int DEATH_TIME = 200;
    public int liveTime;
    public int burstsFired;
    public int deathTime;

    public EntityManaStorm(World p_i1582_1_) {
        super(p_i1582_1_);
    }

    protected void func_70088_a() {
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        ++this.liveTime;
        int diffTime = Math.max(1, 30 - (int)((float)this.liveTime / 45.0f));
        if (this.burstsFired < 250 && this.liveTime % diffTime == 0) {
            if (!this.field_70170_p.field_72995_K) {
                this.spawnBurst();
            }
            ++this.burstsFired;
        }
        if (this.burstsFired >= 250) {
            ++this.deathTime;
            if (this.deathTime >= 200) {
                this.func_70106_y();
                this.field_70170_p.func_72885_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 8.0f, true, true);
            }
        }
    }

    public void spawnBurst() {
        EntityManaBurst burst = new EntityManaBurst(this.field_70170_p);
        burst.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        float motionModifier = 0.5f;
        burst.setColor(0x20FF20);
        burst.setMana(120);
        burst.setStartingMana(340);
        burst.setMinManaLoss(50);
        burst.setManaLossPerTick(1.0f);
        burst.setGravity(0.0f);
        ItemStack lens = new ItemStack(ModItems.lens, 1, 5000);
        burst.setSourceLens(lens);
        Vector3 motion = new Vector3(Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5).normalize().multiply(motionModifier);
        burst.setMotion(motion.x, motion.y, motion.z);
        this.field_70170_p.func_72838_d((Entity)burst);
    }

    protected void func_70037_a(NBTTagCompound cmp) {
        this.liveTime = cmp.func_74762_e(TAG_TIME);
        this.burstsFired = cmp.func_74762_e(TAG_BURSTS_FIRED);
        this.deathTime = cmp.func_74762_e(TAG_DEATH_TIME);
    }

    protected void func_70014_b(NBTTagCompound cmp) {
        cmp.func_74768_a(TAG_TIME, this.liveTime);
        cmp.func_74768_a(TAG_BURSTS_FIRED, this.burstsFired);
        cmp.func_74768_a(TAG_DEATH_TIME, this.deathTime);
    }
}

