/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.gui.crafting;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.gui.crafting.ContainerCraftingHalo;

public class GuiCraftingHalo
extends GuiContainer {
    private static final ResourceLocation craftingTableGuiTextures = new ResourceLocation("textures/gui/container/crafting_table.png");

    public GuiCraftingHalo(InventoryPlayer p_i1084_1_, World p_i1084_2_) {
        super((Container)new ContainerCraftingHalo(p_i1084_1_, p_i1084_2_));
    }

    protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
        this.field_146289_q.func_78276_b(I18n.func_135052_a((String)"container.crafting", (Object[])new Object[0]), 28, 6, 0x404040);
        this.field_146289_q.func_78276_b(I18n.func_135052_a((String)"container.inventory", (Object[])new Object[0]), 8, this.field_147000_g - 96 + 2, 0x404040);
    }

    protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(craftingTableGuiTextures);
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
    }
}

