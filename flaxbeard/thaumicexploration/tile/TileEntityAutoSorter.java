/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  org.apache.commons.lang3.tuple.MutablePair
 */
package flaxbeard.thaumicexploration.tile;

import flaxbeard.thaumicexploration.misc.SortingInventory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import org.apache.commons.lang3.tuple.MutablePair;

public class TileEntityAutoSorter
extends TileEntity
implements IInventory {
    public ArrayList<MutablePair> chests = new ArrayList();
    public HashMap<MutablePair, SortingInventory> chestSorts = new HashMap();

    public void func_145845_h() {
        ArrayList<MutablePair> chestsToRemove = new ArrayList<MutablePair>();
        for (MutablePair loc : this.chests) {
            ChunkCoordinates chest = (ChunkCoordinates)loc.left;
            if (this.field_145850_b.func_147438_o(chest.field_71574_a, chest.field_71572_b, chest.field_71573_c) != null) continue;
            chestsToRemove.add(loc);
        }
        for (MutablePair chest : chestsToRemove) {
            this.chests.remove(chest);
        }
        ArrayList<MutablePair<int[], MutablePair>> accessible = new ArrayList<MutablePair<int[], MutablePair>>();
        chestsToRemove = new ArrayList();
        for (MutablePair mutablePair : this.chests) {
            int[] accessibleSlots;
            ChunkCoordinates chest = (ChunkCoordinates)mutablePair.left;
            TileEntity te = this.field_145850_b.func_147438_o(chest.field_71574_a, chest.field_71572_b, chest.field_71573_c);
            if (te instanceof ISidedInventory) {
                accessibleSlots = ((ISidedInventory)te).func_94128_d(((Integer)mutablePair.right).intValue());
                if (this.arrayContains(accessible, (MutablePair<int[], MutablePair>)MutablePair.of((Object)accessibleSlots, (Object)mutablePair))) {
                    chestsToRemove.add(mutablePair);
                    continue;
                }
                accessible.add((MutablePair<int[], MutablePair>)MutablePair.of((Object)accessibleSlots, (Object)mutablePair));
                continue;
            }
            accessibleSlots = this.numsUpTo(((IInventory)te).func_70302_i_());
            String test = "";
            if (this.arrayContains(accessible, (MutablePair<int[], MutablePair>)MutablePair.of((Object)accessibleSlots, (Object)mutablePair))) {
                chestsToRemove.add(mutablePair);
                continue;
            }
            accessible.add((MutablePair<int[], MutablePair>)MutablePair.of((Object)accessibleSlots, (Object)mutablePair));
        }
        for (MutablePair mutablePair : chestsToRemove) {
            this.chests.remove(mutablePair);
        }
        for (MutablePair mutablePair : accessible) {
            MutablePair loc = (MutablePair)mutablePair.right;
            System.out.println(this.chestSorts.keySet().contains(loc));
            SortingInventory sort = this.chestSorts.get(loc);
            if (sort == null || sort.type != 1) continue;
            ChunkCoordinates chest = (ChunkCoordinates)((MutablePair)mutablePair.right).left;
            TileEntity te = this.field_145850_b.func_147438_o(chest.field_71574_a, chest.field_71572_b, chest.field_71573_c);
            for (int i = 0; i < ((int[])mutablePair.left).length; ++i) {
                ISidedInventory tile;
                if (te instanceof ISidedInventory) {
                    tile = (ISidedInventory)te;
                    tile.func_70299_a(((int[])mutablePair.left)[i], null);
                    continue;
                }
                tile = (IInventory)te;
                tile.func_70299_a(((int[])mutablePair.left)[i], null);
            }
        }
    }

    private boolean arrayContains(ArrayList<MutablePair<int[], MutablePair>> array, MutablePair<int[], MutablePair> pair) {
        boolean contains = false;
        for (MutablePair<int[], MutablePair> item : array) {
            if (!Arrays.equals((int[])pair.left, (int[])item.left) || !((MutablePair)pair.right).equals(item.right)) continue;
            contains = true;
        }
        return contains;
    }

    private int[] numsUpTo(int x) {
        int[] test = new int[x];
        for (int i = 0; i < x; ++i) {
            test[i] = i;
        }
        return test;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean areStacksEqual(ItemStack stack1, ItemStack stack2) {
        ItemStack stack1c = stack1.func_77946_l();
        stack1c.field_77994_a = 1;
        ItemStack stack2c = stack2.func_77946_l();
        stack2c.field_77994_a = 1;
        if (!ItemStack.func_77989_b((ItemStack)stack1c, (ItemStack)stack2c)) return false;
        if (!ItemStack.func_77970_a((ItemStack)stack1c, (ItemStack)stack2c)) return false;
        return true;
    }

    public int func_70302_i_() {
        return 0;
    }

    public ItemStack func_70301_a(int i) {
        return null;
    }

    public ItemStack func_70298_a(int i, int j) {
        return null;
    }

    public ItemStack func_70304_b(int i) {
        return null;
    }

    public void func_70299_a(int i, ItemStack itemstack) {
    }

    public int func_70297_j_() {
        return 0;
    }

    public boolean func_70300_a(EntityPlayer entityplayer) {
        return false;
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return false;
    }

    public String func_145825_b() {
        return null;
    }

    public boolean func_145818_k_() {
        return false;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }
}

