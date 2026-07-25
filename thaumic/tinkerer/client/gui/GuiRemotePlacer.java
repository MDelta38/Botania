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
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.Aspect
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
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumic.tinkerer.client.gui.button.GuiButtonRP;
import thaumic.tinkerer.client.gui.button.GuiButtonRPRadio;
import thaumic.tinkerer.client.gui.button.IRadioButton;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.tile.TileRPlacer;
import thaumic.tinkerer.common.block.tile.container.ContainerRemotePlacer;
import thaumic.tinkerer.common.network.packet.PacketPlacerButton;

public class GuiRemotePlacer
extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("ttinkerer:textures/gui/aspectAnalyzer.png");
    int x;
    int y;
    List<IRadioButton> radioButtons = new ArrayList<IRadioButton>();
    List<GuiButtonRP> buttonListRP = new ArrayList<GuiButtonRP>();
    TileRPlacer placer;
    Aspect aspectHovered = null;

    public GuiRemotePlacer(TileRPlacer placer, InventoryPlayer inv) {
        super((Container)new ContainerRemotePlacer(placer, inv));
        this.placer = placer;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.x = (this.field_146294_l - this.field_146999_f) / 2;
        this.y = (this.field_146295_m - this.field_147000_g) / 2;
        this.buttonListRP.clear();
        this.addButton(new GuiButtonRPRadio(0, this.x + 100, this.y + 0, this.placer.blocks == 1, this.radioButtons));
        this.addButton(new GuiButtonRPRadio(1, this.x + 100, this.y + 13, this.placer.blocks == 2, this.radioButtons));
        this.addButton(new GuiButtonRPRadio(2, this.x + 100, this.y + 26, this.placer.blocks == 3, this.radioButtons));
        this.addButton(new GuiButtonRPRadio(3, this.x + 100, this.y + 39, this.placer.blocks == 4, this.radioButtons));
        this.field_146292_n = this.buttonListRP;
    }

    private void addButton(GuiButtonRP button) {
        this.buttonListRP.add(button);
        if (button instanceof IRadioButton) {
            this.radioButtons.add((IRadioButton)((Object)button));
        }
    }

    protected void func_146284_a(GuiButton par1GuiButton) {
        if (par1GuiButton instanceof IRadioButton) {
            ((IRadioButton)par1GuiButton).enableFromClick();
        } else {
            this.buttonListRP.get((int)0).buttonEnabled = !this.buttonListRP.get((int)0).buttonEnabled;
        }
        this.placer.blocks = par1GuiButton.field_146127_k + 1;
        ThaumicTinkerer.netHandler.sendToServer((IMessage)new PacketPlacerButton(this.placer));
    }

    protected void func_146976_a(float var1, int var2, int var3) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(gui);
        this.func_73729_b(this.x, this.y, 0, 0, this.field_146999_f, this.field_147000_g);
        this.field_146289_q.func_78276_b("1", this.x + 120, this.y + 2, 0x999999);
        this.field_146289_q.func_78276_b("2", this.x + 120, this.y + 15, 0x999999);
        this.field_146289_q.func_78276_b("3", this.x + 120, this.y + 28, 0x999999);
        this.field_146289_q.func_78276_b("4", this.x + 120, this.y + 41, 0x999999);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }
}

