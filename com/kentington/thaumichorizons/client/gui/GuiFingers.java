/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.lib.crafting.ThaumcraftCraftingManager
 */
package com.kentington.thaumichorizons.client.gui;

import com.kentington.thaumichorizons.common.container.ContainerFingers;
import com.kentington.thaumichorizons.common.container.InventoryFingers;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;

@SideOnly(value=Side.CLIENT)
public class GuiFingers
extends GuiContainer {
    private InventoryPlayer ip;
    private InventoryFingers tileEntity;
    private int[][] aspectLocs = new int[][]{{72, 21}, {24, 43}, {24, 102}, {72, 124}, {120, 102}, {120, 43}};
    ArrayList<Aspect> primals = Aspect.getPrimalAspects();

    public GuiFingers(InventoryPlayer par1InventoryPlayer) {
        super((Container)new ContainerFingers(par1InventoryPlayer));
        this.tileEntity = ((ContainerFingers)this.field_147002_h).tileEntity;
        this.ip = par1InventoryPlayer;
        this.field_147000_g = 234;
        this.field_146999_f = 190;
    }

    protected void drawGuiContainerForegroundLayer() {
    }

    protected void func_146976_a(float par1, int par2, int par3) {
        UtilsFX.bindTexture((String)"thaumichorizons", (String)"textures/gui/guifingers.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        int var5 = (this.field_146294_l - this.field_146999_f) / 2;
        int var6 = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
        GL11.glDisable((int)3042);
        ItemWandCasting wand = null;
        if (this.tileEntity.func_70301_a(10) != null && this.tileEntity.func_70301_a(10).func_77973_b() instanceof ItemWandCasting) {
            wand = (ItemWandCasting)this.tileEntity.func_70301_a(10).func_77973_b();
        }
        AspectList cost = null;
        if (ThaumcraftCraftingManager.findMatchingArcaneRecipe((IInventory)this.tileEntity, (EntityPlayer)this.ip.field_70458_d) != null) {
            cost = ThaumcraftCraftingManager.findMatchingArcaneRecipeAspects((IInventory)this.tileEntity, (EntityPlayer)this.ip.field_70458_d);
            int count = 0;
            for (Aspect primal : this.primals) {
                float amt = cost.getAmount(primal);
                if (cost.getAmount(primal) > 0) {
                    float alpha = 0.5f + (MathHelper.func_76126_a((float)((float)(this.ip.field_70458_d.field_70173_aa + count * 10) / 2.0f)) * 0.2f - 0.2f);
                    if (wand != null && (amt *= wand.getConsumptionModifier(this.tileEntity.func_70301_a(10), this.ip.field_70458_d, primal, true)) * 100.0f <= (float)wand.getVis(this.tileEntity.func_70301_a(10), primal)) {
                        alpha = 1.0f;
                    }
                    UtilsFX.drawTag((int)(var5 + this.aspectLocs[count][0] - 8), (int)(var6 + this.aspectLocs[count][1] - 8), (Aspect)primal, (float)amt, (int)0, (double)this.field_73735_i, (int)771, (float)alpha, (boolean)false);
                }
                if (++count <= 5) continue;
                break;
            }
        }
        if (wand != null && cost != null && !wand.consumeAllVisCrafting(this.tileEntity.func_70301_a(10), this.ip.field_70458_d, cost, false)) {
            GL11.glPushMatrix();
            float var40 = 0.33f;
            GL11.glColor4f((float)var40, (float)var40, (float)var40, (float)0.66f);
            GuiFingers.field_146296_j.field_77024_a = false;
            GL11.glEnable((int)2896);
            GL11.glEnable((int)2884);
            GL11.glEnable((int)3042);
            field_146296_j.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, ThaumcraftCraftingManager.findMatchingArcaneRecipe((IInventory)this.tileEntity, (EntityPlayer)this.ip.field_70458_d), var5 + 160, var6 + 64);
            field_146296_j.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, ThaumcraftCraftingManager.findMatchingArcaneRecipe((IInventory)this.tileEntity, (EntityPlayer)this.ip.field_70458_d), var5 + 160, var6 + 64);
            GuiFingers.field_146296_j.field_77024_a = true;
            GL11.glDisable((int)3042);
            GL11.glDisable((int)2896);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(var5 + 168), (float)(var6 + 46), (float)0.0f);
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.0f);
            String text = "Insufficient vis";
            int ll = this.field_146289_q.func_78256_a(text) / 2;
            this.field_146289_q.func_78276_b(text, -ll, 0, 0xEE6E6E);
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPopMatrix();
        }
    }
}

