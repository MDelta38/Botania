/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 */
package witchinggadgets.asm.pouch;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import witchinggadgets.asm.pouch.ContainerPatchedFocusPouch;

public class GuiPatchedFocusPouch
extends GuiContainer {
    public GuiPatchedFocusPouch(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
        super((Container)new ContainerPatchedFocusPouch(inventoryPlayer, world, x, y, z));
        this.field_146999_f = 175;
        this.field_147000_g = 232;
    }

    protected boolean func_146983_a(int par1) {
        return false;
    }

    protected void func_146976_a(float par1, int par2, int par3) {
        UtilsFX.bindTexture((String)"textures/gui/gui_focuspouch.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int var5 = (this.field_146294_l - this.field_146999_f) / 2;
        int var6 = (this.field_146295_m - this.field_147000_g) / 2;
        GL11.glEnable((int)3042);
        this.func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
        GL11.glDisable((int)3042);
    }
}

