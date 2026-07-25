/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.ThaumcraftApiHelper
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.client.lib.UtilsFX
 */
package witchinggadgets.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.lib.UtilsFX;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.blocks.tiles.TileEntityLabelLibrary;
import witchinggadgets.common.gui.ContainerLabelLibrary;
import witchinggadgets.common.util.network.message.MessageTileUpdate;

public class GuiLabelLibrary
extends GuiContainer {
    private TileEntityLabelLibrary tile;
    private EntityPlayer player;

    public GuiLabelLibrary(InventoryPlayer inventoryPlayer, TileEntityLabelLibrary tileEntity) {
        super((Container)new ContainerLabelLibrary(inventoryPlayer, tileEntity));
        this.tile = tileEntity;
        this.player = inventoryPlayer.field_70458_d;
    }

    protected void func_146976_a(float par1, int mX, int mY) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ClientUtilities.bindTexture("witchinggadgets:textures/gui/labelLibrary.png");
        this.func_73729_b(this.field_147003_i, this.field_147009_r - 5, 0, 0, this.field_146999_f + 18, this.field_147000_g + 5);
        float scale = 0.65f;
        GL11.glScalef((float)scale, (float)scale, (float)1.0f);
        int i = 0;
        int x = (int)Math.floor((float)(this.field_147003_i + 36) / scale);
        int y = (int)Math.floor((float)(this.field_147009_r + 8) / scale);
        int row = (int)(8.0f / scale);
        for (Aspect a : Aspect.aspects.values()) {
            if (!ThaumcraftApiHelper.hasDiscoveredAspect((String)this.player.func_70005_c_(), (Aspect)a)) continue;
            UtilsFX.drawTag((int)(x + 17 * (i % row)), (int)(y + 17 * (i / row)), (Aspect)a, (float)0.0f, (int)(this.tile.aspect == a ? 1 : 0), (double)this.field_73735_i);
            ++i;
        }
        GL11.glScalef((float)(1.0f / scale), (float)(1.0f / scale), (float)1.0f);
        UtilsFX.drawTag((int)(this.field_147003_i + 8), (int)(this.field_147009_r + 30), (Aspect)this.tile.aspect, (float)0.0f, (int)0, (double)this.field_73735_i);
    }

    protected void func_73864_a(int mX, int mY, int button) {
        super.func_73864_a(mX, mY, button);
        mX -= (this.field_146294_l - this.field_146999_f) / 2 + 36;
        mY -= (this.field_146295_m - this.field_147000_g) / 2 + 8;
        float scale = 0.65f;
        int i = 0;
        int row = (int)(8.0f / scale);
        int size = (int)Math.floor(17.0f * scale);
        Aspect old = this.tile.aspect;
        for (Aspect a : Aspect.aspects.values()) {
            if (!ThaumcraftApiHelper.hasDiscoveredAspect((String)this.player.func_70005_c_(), (Aspect)a)) continue;
            if (mX >= size * (i % row) && mX < size * (i % row + 1) && mY >= size * (i / row) && mY < size * ((i + row) / row)) {
                this.tile.aspect = a;
            }
            ++i;
        }
        if (this.tile.aspect != old) {
            WitchingGadgets.packetHandler.sendToServer((IMessage)new MessageTileUpdate(this.tile));
        }
    }
}

