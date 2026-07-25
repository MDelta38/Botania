/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package thaumic.tinkerer.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import thaumic.tinkerer.client.gui.button.GuiButtonAT;
import thaumic.tinkerer.client.gui.button.GuiButtonATRadio;
import thaumic.tinkerer.client.gui.button.IRadioButton;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.tile.container.ContainerAnimationTablet;
import thaumic.tinkerer.common.block.tile.tablet.TileAnimationTablet;
import thaumic.tinkerer.common.network.packet.PacketTabletButton;

public class GuiAnimationTablet
extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("ttinkerer:textures/gui/animationTablet.png");
    int x;
    int y;
    TileAnimationTablet tablet;
    List<GuiButtonAT> buttonListAT = new ArrayList<GuiButtonAT>();
    List<IRadioButton> radioButtons = new ArrayList<IRadioButton>();

    public GuiAnimationTablet(TileAnimationTablet tablet, InventoryPlayer playerInv) {
        super((Container)new ContainerAnimationTablet(tablet, playerInv));
        this.tablet = tablet;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.x = (this.field_146294_l - this.field_146999_f) / 2;
        this.y = (this.field_146295_m - this.field_147000_g) / 2;
        this.buttonListAT.clear();
        this.addButton(new GuiButtonAT(0, this.x + this.field_146999_f / 2 - 7, this.y + 60, this.tablet.redstone));
        this.addButton(new GuiButtonATRadio(1, this.x + 52, this.y + 15, this.tablet.leftClick, this.radioButtons));
        this.addButton(new GuiButtonATRadio(2, this.x + 111, this.y + 15, !this.tablet.leftClick, this.radioButtons));
        this.field_146292_n = this.buttonListAT;
    }

    private void addButton(GuiButtonAT button) {
        this.buttonListAT.add(button);
        if (button instanceof IRadioButton) {
            this.radioButtons.add((IRadioButton)((Object)button));
        }
    }

    protected void func_146284_a(GuiButton par1GuiButton) {
        if (par1GuiButton instanceof IRadioButton) {
            ((IRadioButton)par1GuiButton).enableFromClick();
        } else {
            this.buttonListAT.get((int)0).buttonEnabled = !this.buttonListAT.get((int)0).buttonEnabled;
        }
        this.tablet.leftClick = this.buttonListAT.get((int)1).buttonEnabled;
        this.tablet.redstone = this.buttonListAT.get((int)0).buttonEnabled;
        ThaumicTinkerer.netHandler.sendToServer((IMessage)new PacketTabletButton(this.tablet));
    }

    protected void func_146976_a(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(gui);
        this.func_73729_b(this.x, this.y, 0, 0, this.field_146999_f, this.field_147000_g);
        String left = StatCollector.func_74838_a((String)"ttmisc.leftClick");
        String right = StatCollector.func_74838_a((String)"ttmisc.rightClick");
        String redstone = StatCollector.func_74838_a((String)"ttmisc.redstoneControl");
        this.field_146289_q.func_78276_b(left, this.x + 48 - this.field_146289_q.func_78256_a(left), this.y + 18, 0x999999);
        this.field_146289_q.func_78276_b(right, this.x + 128, this.y + 18, 0x999999);
        this.field_146289_q.func_78276_b(redstone, this.x + this.field_146999_f / 2 - this.field_146289_q.func_78256_a(redstone) / 2, this.y + 50, 0x999999);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }
}

