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
import thaumcraft.common.container.ContainerFocusPouch;

@SideOnly(value=Side.CLIENT)
public class GuiFocusPouch
extends GuiContainer {
    private int blockSlot;

    public GuiFocusPouch(InventoryPlayer par1InventoryPlayer, World world, int x, int y, int z) {
        super((Container)new ContainerFocusPouch(par1InventoryPlayer, world, x, y, z));
        this.blockSlot = par1InventoryPlayer.field_70461_c;
        this.field_146999_f = 175;
        this.field_147000_g = 232;
    }

    protected void func_146979_b(int par1, int par2) {
        UtilsFX.bindTexture("textures/gui/gui_focuspouch.png");
        float t = this.field_73735_i;
        this.field_73735_i = 200.0f;
        GL11.glEnable((int)3042);
        this.func_73729_b(8 + this.blockSlot * 18, 209, 240, 0, 16, 16);
        GL11.glDisable((int)3042);
        this.field_73735_i = t;
    }

    protected boolean func_146983_a(int par1) {
        return false;
    }

    protected void func_146976_a(float par1, int par2, int par3) {
        if (this.field_146297_k.field_71439_g.field_71071_by.field_70462_a[this.blockSlot] == null) {
            this.field_146297_k.field_71439_g.func_71053_j();
        }
        UtilsFX.bindTexture("textures/gui/gui_focuspouch.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int var5 = (this.field_146294_l - this.field_146999_f) / 2;
        int var6 = (this.field_146295_m - this.field_147000_g) / 2;
        GL11.glEnable((int)3042);
        this.func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
        GL11.glDisable((int)3042);
    }
}

