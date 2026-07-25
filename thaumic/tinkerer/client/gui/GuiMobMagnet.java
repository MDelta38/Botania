/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.ItemStack
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
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import thaumic.tinkerer.client.gui.button.GuiButtonMM;
import thaumic.tinkerer.client.gui.button.GuiButtonMMRadio;
import thaumic.tinkerer.client.gui.button.IRadioButton;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.tile.TileMobMagnet;
import thaumic.tinkerer.common.block.tile.container.ContainerMobMagnet;
import thaumic.tinkerer.common.item.ItemSoulMould;
import thaumic.tinkerer.common.network.packet.PacketMobMagnetButton;

public class GuiMobMagnet
extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("ttinkerer:textures/gui/mobMagnet.png");
    int x;
    int y;
    ItemStack stack = null;
    TileMobMagnet mobMagnet;
    List<GuiButtonMM> buttonListMM = new ArrayList<GuiButtonMM>();
    List<IRadioButton> radioButtons = new ArrayList<IRadioButton>();

    public GuiMobMagnet(TileMobMagnet tile, InventoryPlayer playerInv) {
        super((Container)new ContainerMobMagnet(tile, playerInv));
        this.mobMagnet = tile;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.x = (this.field_146294_l - this.field_146999_f) / 2;
        this.y = (this.field_146295_m - this.field_147000_g) / 2;
        this.buttonListMM.clear();
        this.addButton(new GuiButtonMMRadio(0, this.x + 100, this.y + 28, this.mobMagnet.adult, this.radioButtons));
        this.addButton(new GuiButtonMMRadio(1, this.x + 100, this.y + 48, !this.mobMagnet.adult, this.radioButtons));
        this.field_146292_n = this.buttonListMM;
    }

    private void addButton(GuiButtonMM button) {
        this.buttonListMM.add(button);
        if (button instanceof IRadioButton) {
            this.radioButtons.add((IRadioButton)((Object)button));
        }
    }

    protected void func_146284_a(GuiButton par1GuiButton) {
        if (par1GuiButton instanceof IRadioButton) {
            ((IRadioButton)par1GuiButton).enableFromClick();
        } else {
            this.buttonListMM.get((int)0).field_146124_l = !this.buttonListMM.get((int)0).field_146124_l;
        }
        this.mobMagnet.adult = this.buttonListMM.get((int)0).field_146124_l;
        ThaumicTinkerer.netHandler.sendToServer((IMessage)new PacketMobMagnetButton(this.mobMagnet));
    }

    protected void func_146976_a(float f, int i, int j) {
        String name;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(gui);
        this.func_73729_b(this.x, this.y, 0, 0, this.field_146999_f, this.field_147000_g);
        String adult = StatCollector.func_74838_a((String)"ttmisc.adult");
        String child = StatCollector.func_74838_a((String)"ttmisc.child");
        this.stack = this.mobMagnet.func_70301_a(0);
        String filter = this.stack != null ? ((name = ItemSoulMould.getPatternName(this.stack)).isEmpty() ? StatCollector.func_74838_a((String)"ttmisc.none") : StatCollector.func_74838_a((String)("entity." + name + ".name"))) : StatCollector.func_74838_a((String)"ttmisc.all");
        this.field_146289_q.func_78276_b(filter, this.x + this.field_146999_f / 2 - this.field_146289_q.func_78256_a(filter) / 2 - 26, this.y + 16, 0x999999);
        this.field_146289_q.func_78276_b(adult, this.x + 120, this.y + 30, 0x999999);
        this.field_146289_q.func_78276_b(child, this.x + 120, this.y + 50, 0x999999);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }
}

