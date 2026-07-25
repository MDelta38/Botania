/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.container.ContainerAlchemyFurnace;
import thaumcraft.common.tiles.TileAlchemyFurnace;

@SideOnly(value=Side.CLIENT)
public class GuiAlchemyFurnace
extends GuiContainer {
    private TileAlchemyFurnace furnaceInventory;

    public GuiAlchemyFurnace(InventoryPlayer par1InventoryPlayer, TileAlchemyFurnace par2TileEntityFurnace) {
        super((Container)new ContainerAlchemyFurnace(par1InventoryPlayer, par2TileEntityFurnace));
        this.furnaceInventory = par2TileEntityFurnace;
    }

    protected void func_146979_b(int par1, int par2) {
    }

    protected void func_146976_a(float par1, int par2, int par3) {
        int i1;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        UtilsFX.bindTexture("textures/gui/gui_alchemyfurnace.png");
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        GL11.glEnable((int)3042);
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
        if (this.furnaceInventory.isBurning()) {
            i1 = this.furnaceInventory.getBurnTimeRemainingScaled(20);
            this.func_73729_b(k + 80, l + 26 + 20 - i1, 176, 20 - i1, 16, i1);
        }
        i1 = this.furnaceInventory.getCookProgressScaled(46);
        this.func_73729_b(k + 106, l + 13 + 46 - i1, 216, 46 - i1, 9, i1);
        i1 = this.furnaceInventory.getContentsScaled(48);
        this.func_73729_b(k + 61, l + 12 + 48 - i1, 200, 48 - i1, 8, i1);
        this.func_73729_b(k + 60, l + 8, 232, 0, 10, 55);
        GL11.glDisable((int)3042);
    }
}

