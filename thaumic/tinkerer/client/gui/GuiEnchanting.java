/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.Aspect
 */
package thaumic.tinkerer.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumic.tinkerer.client.core.helper.ClientHelper;
import thaumic.tinkerer.client.gui.button.GuiButtonEnchant;
import thaumic.tinkerer.client.gui.button.GuiButtonEnchanterLevel;
import thaumic.tinkerer.client.gui.button.GuiButtonEnchantment;
import thaumic.tinkerer.client.gui.button.GuiButtonFramedEnchantment;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.tile.TileEnchanter;
import thaumic.tinkerer.common.block.tile.container.ContainerEnchanter;
import thaumic.tinkerer.common.enchantment.core.EnchantmentManager;
import thaumic.tinkerer.common.lib.LibFeatures;
import thaumic.tinkerer.common.network.packet.PacketEnchanterAddEnchant;
import thaumic.tinkerer.common.network.packet.PacketEnchanterStartWorking;

public class GuiEnchanting
extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("ttinkerer:textures/gui/enchanter.png");
    public TileEnchanter enchanter;
    public List<String> tooltip = new ArrayList<String>();
    int x;
    int y;
    GuiButtonEnchantment[] enchantButtons = new GuiButtonEnchantment[16];
    List<Integer> lastTickEnchants;
    List<Integer> lastTickLevels;
    ItemStack lastTickStack;
    ItemStack currentStack;

    public GuiEnchanting(TileEnchanter enchanter, InventoryPlayer inv) {
        super((Container)new ContainerEnchanter(enchanter, inv));
        this.enchanter = enchanter;
        this.lastTickStack = enchanter.func_70301_a(0);
        this.currentStack = enchanter.func_70301_a(0);
        this.lastTickEnchants = new ArrayList<Integer>(enchanter.enchantments);
        this.lastTickLevels = new ArrayList<Integer>(enchanter.levels);
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.x = (this.field_146294_l - this.field_146999_f) / 2;
        this.y = (this.field_146295_m - this.field_147000_g) / 2;
        this.buildButtonList();
    }

    public void buildButtonList() {
        int i;
        this.field_146292_n.clear();
        GuiButtonEnchant enchantButton = new GuiButtonEnchant(this, this.enchanter, 0, this.x + 151, this.y + 33);
        this.field_146292_n.add(enchantButton);
        enchantButton.field_146124_l = !this.enchanter.enchantments.isEmpty();
        for (i = 0; i < 16; ++i) {
            GuiButtonEnchantment button;
            int z = -24;
            if (i > 7 || this.enchantButtons[8] == null || !this.enchantButtons[8].field_146124_l) {
                z = 0;
            }
            this.enchantButtons[i] = button = new GuiButtonEnchantment(this, 1 + i, this.x + 34 + i % 8 * 16, this.y + 54 + z);
            this.field_146292_n.add(button);
        }
        this.asignEnchantButtons();
        i = 0;
        for (Integer enchant : this.enchanter.enchantments) {
            GuiButtonFramedEnchantment button = new GuiButtonFramedEnchantment(this, 17 + i * 3, this.x + this.field_146999_f + 4, this.y + i * 26);
            button.enchant = Enchantment.field_77331_b[enchant];
            this.field_146292_n.add(button);
            this.field_146292_n.add(new GuiButtonEnchanterLevel(17 + i * 3 + 1, this.x + this.field_146999_f + 24, this.y + i * 26 - 4, false));
            this.field_146292_n.add(new GuiButtonEnchanterLevel(17 + i * 3 + 2, this.x + this.field_146999_f + 31, this.y + i * 26 - 4, true));
            ++i;
        }
    }

    public void asignEnchantButtons() {
        for (int i = 0; i < 16; ++i) {
            this.enchantButtons[i].enchant = null;
            this.enchantButtons[i].field_146124_l = false;
        }
        if (this.currentStack == null || this.currentStack.func_77948_v()) {
            return;
        }
        int it = 0;
        for (int enchant : EnchantmentManager.enchantmentData.keySet()) {
            if (this.currentStack.func_77973_b().func_77619_b() == 0 || !EnchantmentManager.canApply(this.currentStack, Enchantment.field_77331_b[enchant], this.enchanter.enchantments) || !EnchantmentManager.canEnchantmentBeUsed(ClientHelper.clientPlayer().func_146103_bH().getName(), Enchantment.field_77331_b[enchant])) continue;
            this.enchantButtons[it].enchant = Enchantment.field_77331_b[enchant];
            this.enchantButtons[it].field_146124_l = true;
            if (++it < 16) continue;
            break;
        }
    }

    protected void func_146284_a(GuiButton par1GuiButton) {
        if (par1GuiButton.field_146127_k == 0) {
            ThaumicTinkerer.netHandler.sendToServer((IMessage)new PacketEnchanterStartWorking(this.enchanter));
        } else if (par1GuiButton.field_146127_k <= 16) {
            GuiButtonEnchantment button = this.enchantButtons[par1GuiButton.field_146127_k - 1];
            if (button != null && button.enchant != null) {
                ThaumicTinkerer.netHandler.sendToServer((IMessage)new PacketEnchanterAddEnchant(this.enchanter, button.enchant.field_77352_x, 0));
            }
        } else {
            int type = (par1GuiButton.field_146127_k - 17) % 3;
            int index = (par1GuiButton.field_146127_k - 17) / 3;
            if (index >= this.enchanter.enchantments.size() || index >= this.enchanter.levels.size()) {
                return;
            }
            int level = this.enchanter.levels.get(index);
            Enchantment enchant = Enchantment.field_77331_b[this.enchanter.enchantments.get(index)];
            switch (type) {
                case 0: {
                    ThaumicTinkerer.netHandler.sendToServer((IMessage)new PacketEnchanterAddEnchant(this.enchanter, enchant.field_77352_x, -1));
                    break;
                }
                case 1: {
                    ThaumicTinkerer.netHandler.sendToServer((IMessage)new PacketEnchanterAddEnchant(this.enchanter, enchant.field_77352_x, level == 1 ? -1 : level - 1));
                    break;
                }
                case 2: {
                    ThaumicTinkerer.netHandler.sendToServer((IMessage)new PacketEnchanterAddEnchant(this.enchanter, enchant.field_77352_x, level + 1));
                }
            }
        }
        this.buildButtonList();
    }

    public void func_73876_c() {
        this.currentStack = this.enchanter.func_70301_a(0);
        this.buildButtonList();
        if (this.currentStack != this.lastTickStack || !this.lastTickEnchants.equals(this.enchanter.enchantments) || !this.lastTickLevels.equals(this.enchanter.levels)) {
            this.buildButtonList();
        }
        this.lastTickStack = this.currentStack;
        this.lastTickEnchants = new ArrayList<Integer>(this.enchanter.enchantments);
        this.lastTickLevels = new ArrayList<Integer>(this.enchanter.enchantments);
    }

    protected void func_146976_a(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(gui);
        this.func_73729_b(this.x, this.y, 0, 0, this.field_146999_f, this.field_147000_g);
        ItemStack itemToEnchant = this.enchanter.func_70301_a(0);
        if (this.enchantButtons[8].field_146124_l && itemToEnchant != null && !itemToEnchant.func_77948_v()) {
            this.func_73729_b(this.x + 30, this.y + 26, 0, this.field_147000_g, 147, 24);
        }
        if (itemToEnchant != null && !itemToEnchant.func_77948_v()) {
            this.func_73729_b(this.x + 30, this.y + 50, 0, this.field_147000_g, 147, 24);
        }
        if (!this.enchanter.enchantments.isEmpty()) {
            int x = this.x + 40;
            GL11.glEnable((int)3042);
            int xo = 15;
            int z = 50;
            if (this.enchantButtons[8].field_146124_l) {
                z = 26;
            }
            for (Aspect aspect : LibFeatures.PRIMAL_ASPECTS) {
                this.drawAspectBar(aspect, x + xo, this.y + z, i, j);
                xo += 15;
            }
            GL11.glDisable((int)3042);
        }
    }

    protected void func_146979_b(int par1, int par2) {
        if (!this.tooltip.isEmpty()) {
            ClientHelper.renderTooltip(par1 - this.x, par2 - this.y, this.tooltip);
        }
        this.tooltip.clear();
    }

    private void drawAspectBar(Aspect aspect, int x, int y, int mx, int my) {
        int size;
        int totalCost = this.enchanter.totalAspects.getAmount(aspect);
        int current = this.enchanter.currentAspects.getAmount(aspect);
        int n = size = totalCost == 0 ? 11 : 59;
        if (totalCost == 0) {
            this.func_73729_b(x, y - size, 200, 0, 10, 4);
            this.func_73729_b(x, y - size + 4, 200, 52, 10, 10);
        } else {
            int pixels = (int)(48.0 * ((double)current / (double)totalCost));
            Color color = new Color(aspect.getColor());
            GL11.glColor3ub((byte)((byte)color.getRed()), (byte)((byte)color.getGreen()), (byte)((byte)color.getBlue()));
            this.func_73729_b(x + 1, y - size + 4 + 48 - pixels, 210, 48 - pixels, 8, pixels);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            this.func_73729_b(x, y - size, 200, 0, 10, size);
        }
        if (mx > x && mx <= x + 10 && my > y - size && my <= y) {
            ArrayList<String> tooltip = new ArrayList<String>();
            tooltip.add('\u00a7' + aspect.getChatcolor() + aspect.getName());
            tooltip.add(current + "/" + totalCost);
            this.tooltip = tooltip;
        }
    }
}

