/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.container.ContainerHandMirror;

@SideOnly(value=Side.CLIENT)
public class GuiHandMirror
extends GuiContainer {
    public GuiHandMirror(InventoryPlayer par1InventoryPlayer, World world, int x, int y, int z) {
        super((Container)new ContainerHandMirror(par1InventoryPlayer, world, x, y, z));
    }

    protected void drawGuiContainerForegroundLayer() {
    }

    protected boolean func_146983_a(int par1) {
        return false;
    }

    protected void func_146976_a(float par1, int par2, int par3) {
        UtilsFX.bindTexture("textures/gui/guihandmirror.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int var5 = (this.field_146294_l - this.field_146999_f) / 2;
        int var6 = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
    }
}

