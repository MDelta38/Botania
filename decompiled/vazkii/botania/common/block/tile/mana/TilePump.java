/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.tile.mana;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.common.block.tile.TileMod;

public class TilePump
extends TileMod {
    private static final String TAG_ACTIVE = "active";
    public float innerRingPos;
    public boolean active = false;
    public boolean hasCart = false;
    public boolean hasCartOnTop = false;
    public float moving = 0.0f;
    public int comparator;
    public boolean hasRedstone = false;
    int lastComparator = 0;

    public void func_145845_h() {
        this.hasRedstone = false;
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            int redstoneSide = this.field_145850_b.func_72878_l(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ, dir.ordinal());
            if (redstoneSide <= 0) continue;
            this.hasRedstone = true;
            break;
        }
        float max = 8.0f;
        float min = 0.0f;
        float incr = max / 10.0f;
        if (this.innerRingPos < max && this.active && this.moving >= 0.0f) {
            this.innerRingPos += incr;
            this.moving = incr;
            if (this.innerRingPos >= max) {
                this.innerRingPos = Math.min(max, this.innerRingPos);
                this.moving = 0.0f;
                for (int x = 0; x < 2; ++x) {
                    this.field_145850_b.func_72869_a("explode", (double)this.field_145851_c + Math.random(), (double)this.field_145848_d + Math.random(), (double)this.field_145849_e + Math.random(), 0.0, 0.0, 0.0);
                }
            }
        } else if (this.innerRingPos > min) {
            this.innerRingPos -= incr * 2.0f;
            this.moving = -incr * 2.0f;
            if (this.innerRingPos <= min) {
                this.innerRingPos = Math.max(min, this.innerRingPos);
                this.moving = 0.0f;
            }
        }
        if (!this.hasCartOnTop) {
            this.comparator = 0;
        }
        if (!this.hasCart && this.active) {
            this.setActive(false);
        }
        if (this.active && this.hasRedstone) {
            this.setActive(false);
        }
        this.hasCart = false;
        this.hasCartOnTop = false;
        if (this.comparator != this.lastComparator) {
            this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
        }
        this.lastComparator = this.comparator;
        super.func_145845_h();
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        cmp.func_74757_a(TAG_ACTIVE, this.active);
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        this.active = cmp.func_74767_n(TAG_ACTIVE);
    }

    public void setActive(boolean active) {
        if (!this.field_145850_b.field_72995_K) {
            boolean diff = this.active != active;
            this.active = active;
            if (diff) {
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }
    }
}

