/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldSavedData
 */
package flaxbeard.thaumicexploration.data;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class BoundChestWorldData
extends WorldSavedData {
    private static final String IDENTIFIER = "teBoundChest";
    private ItemStack[] chestContents = new ItemStack[36];
    private int myColor = 0;

    public BoundChestWorldData() {
        super(IDENTIFIER);
    }

    public BoundChestWorldData(String identifier) {
        super(identifier);
    }

    public void func_76184_a(NBTTagCompound nbt) {
        if (nbt.func_74764_b("myColor")) {
            this.myColor = nbt.func_74762_e("myColor");
        }
        NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
        this.chestContents = new ItemStack[36];
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            int j = nbttagcompound1.func_74771_c("Slot") & 0xFF;
            if (j < 0 || j >= this.chestContents.length) continue;
            this.chestContents[j] = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
        }
    }

    public void func_76187_b(NBTTagCompound nbt) {
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.chestContents.length; ++i) {
            if (this.chestContents[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.func_74774_a("Slot", (byte)i);
            this.chestContents[i].func_77955_b(nbttagcompound1);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        }
        nbt.func_74782_a("Items", (NBTBase)nbttaglist);
        nbt.func_74768_a("myColor", this.myColor);
    }

    public void updateChestContents(ItemStack[] chestItems) {
        this.chestContents = chestItems;
        this.func_76185_a();
    }

    public ItemStack[] getChestContents() {
        return this.chestContents;
    }

    public int getSealColor() {
        return this.myColor;
    }

    public void setSealColor(int color) {
        this.myColor = color;
    }

    public static BoundChestWorldData get(World world, String ident, int color) {
        BoundChestWorldData data = (BoundChestWorldData)world.field_72988_C.func_75742_a(BoundChestWorldData.class, ident);
        if (data == null) {
            System.out.println("Making new Bound Chest world data for " + ident);
            data = new BoundChestWorldData(ident);
            data.setSealColor(color);
            data.func_76185_a();
            world.field_72988_C.func_75745_a(ident, (WorldSavedData)data);
        }
        return data;
    }
}

