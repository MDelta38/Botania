/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.world.WorldSavedData
 *  thaumcraft.api.aspects.AspectList
 */
package flaxbeard.thaumicexploration.data;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.WorldSavedData;
import thaumcraft.api.aspects.AspectList;

public class BoundJarNetworkData
extends WorldSavedData {
    public Map<String, AspectList> networks = new HashMap<String, AspectList>();
    public static String IDENTIFIER = "boundJar";

    public BoundJarNetworkData() {
        super(IDENTIFIER);
    }

    public BoundJarNetworkData(String name) {
        super(name);
    }

    public void func_76184_a(NBTTagCompound nbt) {
        NBTTagList list = nbt.func_150295_c("networks", 10);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound cmp = list.func_150305_b(i);
            String uuid = cmp.func_74779_i("network");
            AspectList aspectList = new AspectList();
            aspectList.readFromNBT(cmp);
            this.networks.put(uuid, aspectList);
        }
    }

    public void func_76187_b(NBTTagCompound nbt) {
        NBTTagList list = new NBTTagList();
        for (Map.Entry<String, AspectList> entry : this.networks.entrySet()) {
            NBTTagCompound cmp = new NBTTagCompound();
            cmp.func_74778_a("network", entry.getKey());
            entry.getValue().writeToNBT(cmp);
            list.func_74742_a((NBTBase)cmp);
        }
        nbt.func_74782_a("networks", (NBTBase)list);
    }
}

