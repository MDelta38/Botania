/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ContainerChest
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiMagicBox
extends GuiContainer {
    private static final ResourceLocation field_147017_u = new ResourceLocation("textures/gui/container/generic_54.png");
    private IInventory playerInventory;
    private IInventory lowerChestInventory;
    private int inventoryRows;

    public GuiMagicBox(IInventory par1IInventory, IInventory par2IInventory) {
        super((Container)new ContainerChest(par1IInventory, par2IInventory));
        this.playerInventory = par1IInventory;
        this.lowerChestInventory = par2IInventory;
        this.field_146291_p = false;
        int short1 = 222;
        int i = short1 - 108;
        this.inventoryRows = par2IInventory.func_70302_i_() / 9;
        this.field_147000_g = i + 54;
    }

    protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
        this.field_146289_q.func_78276_b(this.lowerChestInventory.func_145818_k_() ? this.lowerChestInventory.func_145825_b() : I18n.func_135052_a((String)this.lowerChestInventory.func_145825_b(), (Object[])new Object[0]), 8, 6, 0x404040);
        this.field_146289_q.func_78276_b(this.playerInventory.func_145818_k_() ? this.playerInventory.func_145825_b() : I18n.func_135052_a((String)this.playerInventory.func_145825_b(), (Object[])new Object[0]), 8, this.field_147000_g - 96 + 2, 0x404040);
    }

    protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(field_147017_u);
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, 71);
        this.func_73729_b(k, l + 54 + 17, 0, 126, this.field_146999_f, 96);
    }
}

