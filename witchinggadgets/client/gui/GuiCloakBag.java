/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package witchinggadgets.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.gui.ContainerCloak;

public class GuiCloakBag
extends GuiContainer {
    InventoryPlayer test;

    public GuiCloakBag(InventoryPlayer inventoryPlayer, World world, ItemStack cloak) {
        super((Container)new ContainerCloak(inventoryPlayer, world, cloak));
        this.test = inventoryPlayer;
        this.field_146999_f = 176;
        this.field_147000_g = 166;
    }

    protected void func_146979_b(int par1, int par2) {
        this.field_146289_q.func_78276_b(I18n.func_135052_a((String)"container.inventory", (Object[])new Object[0]), 4, this.field_147000_g - 92, 0x404040);
    }

    protected void func_146976_a(float par1, int par2, int par3) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ClientUtilities.bindTexture("witchinggadgets:textures/gui/bagCloak.png");
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
    }
}

