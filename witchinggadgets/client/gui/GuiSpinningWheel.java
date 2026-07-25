/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  org.lwjgl.opengl.GL11
 */
package witchinggadgets.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import org.lwjgl.opengl.GL11;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.blocks.tiles.TileEntitySpinningWheel;
import witchinggadgets.common.gui.ContainerSpinningWheel;

public class GuiSpinningWheel
extends GuiContainer {
    private TileEntitySpinningWheel tile;

    public GuiSpinningWheel(InventoryPlayer inventoryPlayer, TileEntitySpinningWheel tileEntity) {
        super((Container)new ContainerSpinningWheel(inventoryPlayer, tileEntity));
        this.tile = tileEntity;
        this.field_146999_f = 184;
        this.field_147000_g = 233;
    }

    protected void func_146979_b(int param1, int param2) {
    }

    protected void func_146976_a(float par1, int par2, int par3) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ClientUtilities.bindTexture("witchinggadgets:textures/gui/spinningwheel.png");
        int x = (this.field_146294_l - this.field_146999_f) / 2;
        int y = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(x, y + 144, 0, 144, 184, 90);
        this.func_73729_b(x, y, 0, 0, 38, 144);
        this.func_73729_b(x + 126, y, 126, 0, 48, 144);
        int var7 = this.tile.getProgressScaled(88);
        this.func_73729_b(x + 38, y, 38, 0, var7, 144);
    }
}

