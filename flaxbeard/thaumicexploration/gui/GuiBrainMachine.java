/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.ResourceLocation
 *  org.apache.commons.lang3.tuple.MutablePair
 *  org.lwjgl.opengl.GL11
 */
package flaxbeard.thaumicexploration.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.gui.ContainerBrainMachine;
import flaxbeard.thaumicexploration.gui.GuiButtonSelector;
import flaxbeard.thaumicexploration.misc.SortingInventory;
import flaxbeard.thaumicexploration.packet.TXClientPacketHandler;
import flaxbeard.thaumicexploration.tile.TileEntityAutoSorter;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.MutablePair;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiBrainMachine
extends GuiContainer {
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("thaumicexploration:textures/gui/brains.png");
    private static final ResourceLocation slotOverlay = new ResourceLocation("thaumicexploration:textures/gui/brainsOverlayy.png");
    List<GuiButton> buttonListP = new ArrayList<GuiButton>();

    public GuiBrainMachine(InventoryPlayer par1InventoryPlayer, EntityPlayer player, TileEntityAutoSorter sorter, ChunkCoordinates chunkCoordinates, int side) {
        super((Container)new ContainerBrainMachine((IInventory)par1InventoryPlayer, player, sorter, chunkCoordinates, side));
    }

    protected void func_146976_a(float par1, int par2, int par3) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(furnaceGuiTextures);
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        GL11.glEnable((int)3042);
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
        GL11.glDisable((int)3042);
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        this.buttonListP.clear();
        ContainerBrainMachine container = (ContainerBrainMachine)this.field_147002_h;
        this.buttonListP.add(new GuiButtonSelector(1, k + 53, l + 14, container.getNumber() != 0, 0));
        this.buttonListP.add(new GuiButtonSelector(1, k + 89, l + 14, container.getNumber() != 1, 1));
        this.buttonListP.add(new GuiButtonSelector(1, k + 125, l + 14, container.getNumber() != 2, 2));
        this.field_146292_n = this.buttonListP;
    }

    protected void func_146284_a(GuiButton par1GuiButton) {
        if (par1GuiButton instanceof GuiButtonSelector) {
            GuiButtonSelector button = (GuiButtonSelector)par1GuiButton;
            ContainerBrainMachine container = (ContainerBrainMachine)this.field_147002_h;
            TXClientPacketHandler.sendTypeChangePacket(container.te, container.cc, button.myID, container.side);
            TileEntityAutoSorter switcher = container.te;
            SortingInventory inv = switcher.chestSorts.get(MutablePair.of((Object)container.cc, (Object)container.side));
            inv.type = button.myID;
            switcher.chestSorts.put(MutablePair.of((Object)container.cc, (Object)container.side), inv);
            for (GuiButton item : this.buttonListP) {
                ((GuiButtonSelector)item).field_146124_l = ((GuiButtonSelector)item).myID != button.myID;
            }
        }
    }
}

