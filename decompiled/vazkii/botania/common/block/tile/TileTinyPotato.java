/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package vazkii.botania.common.block.tile;

import net.minecraft.nbt.NBTTagCompound;
import vazkii.botania.common.block.tile.TileMod;

public class TileTinyPotato
extends TileMod {
    private static final String TAG_NAME = "name";
    public int jumpTicks = 0;
    public String name = "";
    public int nextDoIt = 0;

    public void interact() {
        this.jump();
        if (this.name.equalsIgnoreCase("shia labeouf") && !this.field_145850_b.field_72995_K && this.nextDoIt == 0) {
            this.nextDoIt = 40;
            this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "botania:doit", 1.0f, 1.0f);
        }
    }

    public void jump() {
        if (this.jumpTicks == 0) {
            this.jumpTicks = 20;
        }
    }

    public void func_145845_h() {
        if (this.field_145850_b.field_73012_v.nextInt(100) == 0) {
            this.jump();
        }
        if (this.jumpTicks > 0) {
            --this.jumpTicks;
        }
        if (this.nextDoIt > 0) {
            --this.nextDoIt;
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        cmp.func_74778_a(TAG_NAME, this.name);
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        this.name = cmp.func_74779_i(TAG_NAME);
    }
}

