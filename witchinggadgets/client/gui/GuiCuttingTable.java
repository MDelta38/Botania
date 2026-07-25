/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.client.lib.UtilsFX
 */
package witchinggadgets.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.lib.UtilsFX;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.blocks.tiles.TileEntityCuttingTable;
import witchinggadgets.common.gui.ContainerCuttingTable;
import witchinggadgets.common.items.ItemInfusedGem;
import witchinggadgets.common.util.network.message.MessageTileUpdate;

public class GuiCuttingTable
extends GuiContainer {
    private TileEntityCuttingTable tile;

    public GuiCuttingTable(InventoryPlayer inventoryPlayer, TileEntityCuttingTable tileEntity) {
        super((Container)new ContainerCuttingTable(inventoryPlayer, tileEntity));
        this.tile = tileEntity;
    }

    protected void func_146976_a(float par1, int mX, int mY) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ClientUtilities.bindTexture("witchinggadgets:textures/gui/cuttingTable.png");
        this.func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
        ClientUtilities.bindTexture("witchinggadgets:textures/gui/research/gemcuts_transparent.png");
        int xOff = this.tile.targetGemCut == 0 ? 0 : 80;
        int yOff = this.tile.targetGemCut == 0 ? 0 : 80;
        Tessellator tes = Tessellator.field_78398_a;
        GL11.glEnable((int)3042);
        tes.func_78382_b();
        tes.func_78374_a((double)(this.field_147003_i + 73), (double)(this.field_147009_r + 17), 0.0, (double)((float)xOff / 255.0f), (double)((float)yOff / 255.0f));
        tes.func_78374_a((double)(this.field_147003_i + 73), (double)(this.field_147009_r + 47), 0.0, (double)((float)xOff / 255.0f), (double)((float)(yOff + 80) / 255.0f));
        tes.func_78374_a((double)(this.field_147003_i + 103), (double)(this.field_147009_r + 47), 0.0, (double)((float)(xOff + 80) / 255.0f), (double)((float)(yOff + 80) / 255.0f));
        tes.func_78374_a((double)(this.field_147003_i + 103), (double)(this.field_147009_r + 17), 0.0, (double)((float)(xOff + 80) / 255.0f), (double)((float)yOff / 255.0f));
        tes.func_78381_a();
        UtilsFX.drawTag((int)(this.field_147003_i + 118), (int)(this.field_147009_r + 41), (Aspect)this.tile.getInfusingAspect(), (float)0.0f, (int)0, (double)this.field_73735_i);
    }

    protected void func_73864_a(int mX, int mY, int button) {
        super.func_73864_a(mX, mY, button);
        mX -= (this.field_146294_l - this.field_146999_f) / 2;
        if ((mY -= (this.field_146295_m - this.field_147000_g) / 2) > 12 && mY < 24) {
            byte old = this.tile.targetGemCut;
            if (mX > 106 && mX < 117) {
                this.tile.targetGemCut = (byte)(this.tile.targetGemCut + 1);
            }
            if (mX > 59 && mX < 70) {
                this.tile.targetGemCut = (byte)(this.tile.targetGemCut - 1);
            }
            if (this.tile.targetGemCut < 0) {
                this.tile.targetGemCut = (byte)(ItemInfusedGem.GemCut.values().length - 1);
            } else if (this.tile.targetGemCut >= ItemInfusedGem.GemCut.values().length) {
                this.tile.targetGemCut = 0;
            }
            if (this.tile.targetGemCut != old) {
                WitchingGadgets.packetHandler.sendToServer((IMessage)new MessageTileUpdate(this.tile));
            }
        }
    }
}

