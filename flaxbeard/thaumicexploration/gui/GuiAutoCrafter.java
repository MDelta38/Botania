/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package flaxbeard.thaumicexploration.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.gui.ContainerAutoCrafter;
import flaxbeard.thaumicexploration.tile.TileEntityAutoCrafter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiAutoCrafter
extends GuiContainer {
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("thaumicexploration:textures/gui/AutoCrafter.png");
    private TileEntityAutoCrafter furnaceInventory;

    public GuiAutoCrafter(InventoryPlayer par1InventoryPlayer, TileEntityAutoCrafter par2TileEntityAutoCrafter) {
        super((Container)new ContainerAutoCrafter(par1InventoryPlayer, par2TileEntityAutoCrafter));
        this.furnaceInventory = par2TileEntityAutoCrafter;
    }

    protected void func_146979_b(int par1, int par2) {
    }

    protected void func_146976_a(float par1, int par2, int par3) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(furnaceGuiTextures);
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        GL11.glEnable((int)3042);
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
        int i1 = 0;
        this.func_73729_b(k + 91, l + 24, 198, 11, 32, i1);
        GL11.glDisable((int)3042);
    }
}

