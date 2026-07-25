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
package thaumic.tinkerer.client.gui.kami;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import thaumic.tinkerer.client.gui.button.kami.GuiButtonWG;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.tile.container.kami.ContainerWarpGate;
import thaumic.tinkerer.common.block.tile.kami.TileWarpGate;
import thaumic.tinkerer.common.network.packet.kami.PacketWarpGateButton;

public class GuiWarpGate
extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("ttinkerer:textures/gui/warpGate.png");
    TileWarpGate warpGate;
    int x;
    int y;

    public GuiWarpGate(TileWarpGate warpGate, InventoryPlayer inv) {
        super((Container)new ContainerWarpGate(warpGate, inv));
        this.warpGate = warpGate;
    }

    protected void func_146284_a(GuiButton par1GuiButton) {
        ((GuiButtonWG)par1GuiButton).field_146124_l = !((GuiButtonWG)par1GuiButton).field_146124_l;
        this.warpGate.locked = ((GuiButtonWG)par1GuiButton).field_146124_l;
        ThaumicTinkerer.netHandler.sendToServer((IMessage)new PacketWarpGateButton(this.warpGate));
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.x = (this.field_146294_l - this.field_146999_f) / 2;
        this.y = (this.field_146295_m - this.field_147000_g) / 2;
        this.field_146292_n.clear();
        this.field_146292_n.add(new GuiButtonWG(0, this.x + 5, this.y + 5, this.warpGate.locked));
    }

    protected void func_146976_a(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(gui);
        this.func_73729_b(this.x, this.y, 0, 0, this.field_146999_f, this.field_147000_g);
        this.field_146289_q.func_78261_a(StatCollector.func_74838_a((String)"ttmisc.lockedGate"), this.x + 20, this.y + 7, 0x999999);
    }
}

