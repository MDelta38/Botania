/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.ItemStack
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.container.ContainerArcaneBore;
import thaumcraft.common.items.equipment.ItemElementalPickaxe;
import thaumcraft.common.items.wands.foci.ItemFocusExcavation;
import thaumcraft.common.tiles.TileArcaneBore;

@SideOnly(value=Side.CLIENT)
public class GuiArcaneBore
extends GuiContainer {
    private TileArcaneBore bore;

    public GuiArcaneBore(InventoryPlayer par1InventoryPlayer, TileArcaneBore e) {
        super((Container)new ContainerArcaneBore(par1InventoryPlayer, e));
        this.bore = e;
        this.field_146999_f = 176;
        this.field_147000_g = 141;
    }

    protected void drawGuiContainerForegroundLayer() {
    }

    protected void func_146976_a(float par1, int par2, int par3) {
        UtilsFX.bindTexture("textures/gui/gui_arcanebore.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int var5 = (this.field_146294_l - this.field_146999_f) / 2;
        int var6 = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
        if (this.bore.func_70301_a(1) != null && this.bore.func_70301_a(1).func_77960_j() + 1 >= this.bore.func_70301_a(1).func_77958_k()) {
            this.func_73729_b(var5 + 74, var6 + 18, 184, 0, 16, 16);
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(var5 + 112), (float)(var6 + 8), (float)505.0f);
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.0f);
        String text = "Width: " + (1 + (this.bore.area + this.bore.maxRadius) * 2);
        this.field_146289_q.func_78261_a(text, 0, 0, 0xFFFFFF);
        text = "Speed: +" + this.bore.speed;
        this.field_146289_q.func_78261_a(text, 0, 10, 0xFFFFFF);
        text = "Other properties:";
        this.field_146289_q.func_78261_a(text, 0, 24, 0xFFFFFF);
        int base = 0;
        if (this.bore.func_70301_a(1) != null && this.bore.func_70301_a(1).func_77973_b() instanceof ItemElementalPickaxe || this.bore.func_70301_a(0) != null && this.bore.func_70301_a(0).func_77973_b() instanceof ItemFocusBasic && ((ItemFocusBasic)this.bore.func_70301_a(0).func_77973_b()).isUpgradedWith(this.bore.func_70301_a(0), ItemFocusExcavation.dowsing)) {
            text = "Native Clusters";
            this.field_146289_q.func_78261_a(text, 4, 34 + base, 0xC0C0C0);
            base += 9;
        }
        if (this.bore.fortune > 0) {
            text = "Fortune " + this.bore.fortune;
            this.field_146289_q.func_78261_a(text, 4, 34 + base, 15648330);
            base += 9;
        }
        if (this.bore.func_70301_a(1) != null && EnchantmentHelper.func_77506_a((int)Enchantment.field_77348_q.field_77352_x, (ItemStack)this.bore.func_70301_a(1)) > 0 || this.bore.func_70301_a(0) != null && this.bore.func_70301_a(0).func_77973_b() instanceof ItemFocusBasic && ((ItemFocusBasic)this.bore.func_70301_a(0).func_77973_b()).isUpgradedWith(this.bore.func_70301_a(0), FocusUpgradeType.silktouch)) {
            text = "Silk Touch";
            this.field_146289_q.func_78261_a(text, 4, 34 + base, 0x8080FF);
            base += 9;
        }
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }
}

