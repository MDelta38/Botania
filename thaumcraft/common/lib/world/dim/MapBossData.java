/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.WorldSavedData
 */
package thaumcraft.common.lib.world.dim;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class MapBossData
extends WorldSavedData {
    public int bossCount;

    public MapBossData(String p_i2140_1_) {
        super(p_i2140_1_);
    }

    public void func_76184_a(NBTTagCompound p_76184_1_) {
        this.bossCount = p_76184_1_.func_74762_e("bossCount");
    }

    public void func_76187_b(NBTTagCompound p_76187_1_) {
        p_76187_1_.func_74768_a("bossCount", this.bossCount);
    }
}

