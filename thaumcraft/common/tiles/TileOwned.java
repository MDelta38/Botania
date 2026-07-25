/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package thaumcraft.common.tiles;

import java.util.ArrayList;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import thaumcraft.api.TileThaumcraft;

public class TileOwned
extends TileThaumcraft {
    public String owner = "";
    public ArrayList<String> accessList = new ArrayList();
    public boolean safeToRemove = false;

    public boolean canUpdate() {
        return false;
    }

    @Override
    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.owner = nbttagcompound.func_74779_i("owner");
        NBTTagList var2 = nbttagcompound.func_150295_c("access", 10);
        this.accessList = new ArrayList();
        for (int var3 = 0; var3 < var2.func_74745_c(); ++var3) {
            NBTTagCompound var4 = var2.func_150305_b(var3);
            this.accessList.add(var4.func_74779_i("name"));
        }
    }

    @Override
    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.accessList.size(); ++var3) {
            NBTTagCompound var4 = new NBTTagCompound();
            var4.func_74778_a("name", this.accessList.get(var3));
            var2.func_74742_a((NBTBase)var4);
        }
        nbttagcompound.func_74782_a("access", (NBTBase)var2);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.owner = nbttagcompound.func_74779_i("owner");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74778_a("owner", this.owner);
    }
}

