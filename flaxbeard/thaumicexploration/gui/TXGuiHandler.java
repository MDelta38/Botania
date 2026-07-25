/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.IGuiHandler
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 */
package flaxbeard.thaumicexploration.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import flaxbeard.thaumicexploration.gui.ContainerAutoCrafter;
import flaxbeard.thaumicexploration.gui.ContainerBrainMachine;
import flaxbeard.thaumicexploration.gui.ContainerThinkTank;
import flaxbeard.thaumicexploration.gui.GuiAutoCrafter;
import flaxbeard.thaumicexploration.gui.GuiBrainMachine;
import flaxbeard.thaumicexploration.gui.GuiThinkTank;
import flaxbeard.thaumicexploration.tile.TileEntityAutoCrafter;
import flaxbeard.thaumicexploration.tile.TileEntityAutoSorter;
import flaxbeard.thaumicexploration.tile.TileEntityThinkTank;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class TXGuiHandler
implements IGuiHandler {
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.func_147438_o(x, y, z);
        int side = 5;
        switch (id) {
            case 0: {
                if (entity != null && entity instanceof TileEntityThinkTank) {
                    return new ContainerThinkTank(player.field_71071_by, (TileEntityThinkTank)entity);
                }
                return null;
            }
            case 1: {
                if (entity != null && entity instanceof TileEntityAutoCrafter) {
                    return new ContainerAutoCrafter(player.field_71071_by, (TileEntityAutoCrafter)entity);
                }
                return null;
            }
            case 3: {
                --side;
            }
            case 4: {
                --side;
            }
            case 5: {
                --side;
            }
            case 6: {
                --side;
            }
            case 7: {
                --side;
            }
            case 2: {
                ItemStack stack = player.func_71045_bC();
                TileEntityAutoSorter sorter = (TileEntityAutoSorter)player.field_70170_p.func_147438_o(stack.func_77978_p().func_74762_e("brainx"), stack.func_77978_p().func_74762_e("brainy"), stack.func_77978_p().func_74762_e("brainz"));
                return new ContainerBrainMachine((IInventory)player.field_71071_by, player, sorter, new ChunkCoordinates(x, y, z), side);
            }
        }
        return null;
    }

    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.func_147438_o(x, y, z);
        int side = 5;
        switch (id) {
            case 0: {
                if (entity != null && entity instanceof TileEntityThinkTank) {
                    return new GuiThinkTank(player.field_71071_by, (TileEntityThinkTank)entity);
                }
                return null;
            }
            case 1: {
                if (entity != null && entity instanceof TileEntityAutoCrafter) {
                    return new GuiAutoCrafter(player.field_71071_by, (TileEntityAutoCrafter)entity);
                }
                return null;
            }
            case 3: {
                --side;
            }
            case 4: {
                --side;
            }
            case 5: {
                --side;
            }
            case 6: {
                --side;
            }
            case 7: {
                --side;
            }
            case 2: {
                ItemStack stack = player.func_71045_bC();
                TileEntityAutoSorter sorter = (TileEntityAutoSorter)player.field_70170_p.func_147438_o(stack.func_77978_p().func_74762_e("brainx"), stack.func_77978_p().func_74762_e("brainy"), stack.func_77978_p().func_74762_e("brainz"));
                return new GuiBrainMachine(player.field_71071_by, player, sorter, new ChunkCoordinates(x, y, z), side);
            }
        }
        return null;
    }
}

