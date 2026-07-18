/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package vazkii.botania.api.lexicon.multiblock;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import vazkii.botania.api.lexicon.multiblock.Multiblock;

public class MultiblockSet {
    private final Multiblock[] mbs;

    public MultiblockSet(Multiblock[] mbs) {
        this.mbs = mbs;
    }

    public MultiblockSet(Multiblock mb) {
        this(mb.createRotations());
    }

    public Multiblock getForEntity(Entity e) {
        return this.getForRotation(e.field_70177_z);
    }

    public Multiblock getForRotation(double rotation) {
        int facing = MathHelper.func_76128_c((double)(rotation * 4.0 / 360.0 + 0.5)) & 3;
        return this.getForIndex(facing);
    }

    public Multiblock getForIndex(int index) {
        return this.mbs[Math.min(this.mbs.length - 1, index)];
    }
}

