/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldSavedData
 */
package flaxbeard.thaumicexploration.data;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class TXWorldData
extends WorldSavedData {
    private static final String IDENTIFIER = "teWorldData";
    private int nextBoundChestID = 1;

    public TXWorldData() {
        super(IDENTIFIER);
    }

    public TXWorldData(String identifier) {
        super(identifier);
    }

    public void func_76184_a(NBTTagCompound nbt) {
        this.nextBoundChestID = nbt.func_74762_e("nextChestID");
    }

    public void func_76187_b(NBTTagCompound nbt) {
        nbt.func_74768_a("nextChestID", this.nextBoundChestID);
    }

    public int getNextBoundChestID() {
        this.func_76185_a();
        return this.nextBoundChestID++;
    }

    public static TXWorldData get(World world) {
        TXWorldData data = (TXWorldData)world.func_72943_a(TXWorldData.class, IDENTIFIER);
        if (data == null) {
            data = new TXWorldData();
            world.func_72823_a(IDENTIFIER, (WorldSavedData)data);
        }
        return data;
    }
}

