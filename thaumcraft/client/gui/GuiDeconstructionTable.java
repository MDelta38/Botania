/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.container.ContainerDeconstructionTable;
import thaumcraft.common.tiles.TileDeconstructionTable;

@SideOnly(value=Side.CLIENT)
public class GuiDeconstructionTable
extends GuiContainer {
    private TileDeconstructionTable tableInventory;

    public GuiDeconstructionTable(InventoryPlayer par1InventoryPlayer, TileDeconstructionTable par2TileEntityFurnace) {
        super((Container)new ContainerDeconstructionTable(par1InventoryPlayer, par2TileEntityFurnace));
        this.tableInventory = par2TileEntityFurnace;
    }

    protected void func_146979_b(int par1, int par2) {
    }

    protected void func_146976_a(float par1, int par2, int par3) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        UtilsFX.bindTexture("textures/gui/gui_decontable.png");
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        GL11.glEnable((int)3042);
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
        if (this.tableInventory.breaktime > 0) {
            int i1 = this.tableInventory.getBreakTimeScaled(46);
            this.func_73729_b(k + 93, l + 15 + 46 - i1, 176, 46 - i1, 9, i1);
        }
        if (this.tableInventory.aspect != null) {
            UtilsFX.drawTag(k + 64, l + 48, this.tableInventory.aspect, 0.0f, 0, this.field_73735_i);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderHelper.func_74518_a();
            int var7 = par2 - (k + 64);
            int var8 = par3 - (l + 48);
            if (var7 >= 0 && var8 >= 0 && var7 < 16 && var8 < 16) {
                UtilsFX.drawCustomTooltip((GuiScreen)this, field_146296_j, this.field_146289_q, Arrays.asList(this.tableInventory.aspect.getName(), this.tableInventory.aspect.getLocalizedDescription()), par2, par3 - 8, 11);
                return;
            }
        }
        GL11.glDisable((int)3042);
    }

    protected void func_73864_a(int mx, int my, int par3) {
        super.func_73864_a(mx, my, par3);
        int gx = (this.field_146294_l - this.field_146999_f) / 2;
        int gy = (this.field_146295_m - this.field_147000_g) / 2;
        int var7 = mx - (gx + 64);
        int var8 = my - (gy + 48);
        if (var7 >= 0 && var8 >= 0 && var7 < 16 && var8 < 16 && this.tableInventory.aspect != null) {
            this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 1);
            this.playButtonAspect();
            return;
        }
    }

    private void playButtonAspect() {
        this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:hhoff", 0.2f, 1.0f + this.field_146297_k.field_71451_h.field_70170_p.field_73012_v.nextFloat() * 0.1f, false);
    }
}

