/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.util.ChunkCoordinates
 *  org.apache.commons.lang3.tuple.MutablePair
 *  thaumcraft.common.container.ContainerGhostSlots
 */
package flaxbeard.thaumicexploration.gui;

import flaxbeard.thaumicexploration.gui.SlotGhostSingular;
import flaxbeard.thaumicexploration.misc.SortingInventory;
import flaxbeard.thaumicexploration.tile.TileEntityAutoSorter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ChunkCoordinates;
import org.apache.commons.lang3.tuple.MutablePair;
import thaumcraft.common.container.ContainerGhostSlots;

public class ContainerBrainMachine
extends ContainerGhostSlots {
    public TileEntityAutoSorter te;
    public ChunkCoordinates cc;
    public int side;
    public SortingInventory inventory = new SortingInventory();

    public ContainerBrainMachine(IInventory pinventory, EntityPlayer player, TileEntityAutoSorter sorter, ChunkCoordinates chunkCoordinates, int s) {
        int j;
        int i;
        this.cc = chunkCoordinates;
        this.te = sorter;
        this.side = s;
        System.out.println("X: " + chunkCoordinates.field_71574_a + " Y: " + chunkCoordinates.field_71572_b + " Z: " + chunkCoordinates.field_71573_c);
        if (!sorter.chestSorts.containsKey(MutablePair.of((Object)chunkCoordinates, (Object)this.side))) {
            sorter.chestSorts.put(MutablePair.of((Object)chunkCoordinates, (Object)this.side), this.inventory);
        } else {
            this.inventory = sorter.chestSorts.get(MutablePair.of((Object)chunkCoordinates, (Object)this.side));
        }
        for (i = 0; i < 2; ++i) {
            for (j = 0; j < 9; ++j) {
                this.func_75146_a((Slot)new SlotGhostSingular(this.inventory, j + i * 9, 8 + j * 18, 36 + i * 18));
            }
        }
        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot(pinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot(pinventory, i, 8 + i * 18, 142));
        }
    }

    public boolean func_75145_c(EntityPlayer entityplayer) {
        return true;
    }

    public int getNumber() {
        return this.inventory.type;
    }
}

