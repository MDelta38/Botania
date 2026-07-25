/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.fluids.FluidStack
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.container.ContainerSpa;
import thaumcraft.common.tiles.TileSpa;

@SideOnly(value=Side.CLIENT)
public class GuiSpa
extends GuiContainer {
    private TileSpa spa;
    private float xSize_lo;
    private float ySize_lo;

    public GuiSpa(InventoryPlayer par1InventoryPlayer, TileSpa teSpa) {
        super((Container)new ContainerSpa(par1InventoryPlayer, teSpa));
        this.spa = teSpa;
    }

    public void func_73863_a(int par1, int par2, float par3) {
        ArrayList<String> list;
        super.func_73863_a(par1, par2, par3);
        this.xSize_lo = par1;
        this.ySize_lo = par2;
        int baseX = this.field_147003_i;
        int baseY = this.field_147009_r;
        int mposx = par1 - (baseX + 104);
        int mposy = par2 - (baseY + 10);
        if (mposx >= 0 && mposy >= 0 && mposx < 10 && mposy < 55) {
            list = new ArrayList<String>();
            FluidStack fluid = this.spa.tank.getFluid();
            if (fluid != null) {
                list.add(fluid.getFluid().getLocalizedName(fluid));
                list.add(fluid.amount + " mb");
                this.drawHoveringText(list, par1, par2, this.field_146289_q);
            }
        }
        mposx = par1 - (baseX + 88);
        mposy = par2 - (baseY + 34);
        if (mposx >= 0 && mposy >= 0 && mposx < 10 && mposy < 10) {
            list = new ArrayList();
            if (this.spa.getMix()) {
                list.add(StatCollector.func_74838_a((String)"text.spa.mix.true"));
            } else {
                list.add(StatCollector.func_74838_a((String)"text.spa.mix.false"));
            }
            this.drawHoveringText(list, par1, par2, this.field_146289_q);
        }
    }

    protected void func_146976_a(float par1, int par2, int par3) {
        IIcon icon;
        FluidStack fluid;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        UtilsFX.bindTexture("textures/gui/gui_spa.png");
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
        if (this.spa.getMix()) {
            this.func_73729_b(k + 89, l + 35, 208, 16, 8, 8);
        } else {
            this.func_73729_b(k + 89, l + 35, 208, 32, 8, 8);
        }
        if (this.spa.tank.getFluidAmount() > 0 && (fluid = this.spa.tank.getFluid()) != null && (icon = fluid.getFluid().getIcon()) != null) {
            float bar = (float)this.spa.tank.getFluidAmount() / (float)this.spa.tank.getCapacity();
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(this.field_147003_i + 107), (float)(this.field_147009_r + 15), (float)0.0f);
            GuiSpa.renderFluid(icon);
            GL11.glPopMatrix();
            UtilsFX.bindTexture("textures/gui/gui_spa.png");
            this.func_73729_b(k + 107, l + 15, 107, 15, 10, (int)(48.0f - 48.0f * bar));
        }
        this.func_73729_b(k + 106, l + 11, 232, 0, 10, 55);
        GL11.glDisable((int)3042);
    }

    public static void renderFluid(IIcon icon) {
        Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
        Tessellator tessellator = Tessellator.field_78398_a;
        float f1 = icon.func_94212_f();
        float f2 = icon.func_94206_g();
        float f3 = icon.func_94209_e();
        float f4 = icon.func_94210_h();
        GL11.glScalef((float)8.0f, (float)8.0f, (float)8.0f);
        for (int a = 0; a < 6; ++a) {
            tessellator.func_78382_b();
            tessellator.func_78369_a(1.0f, 1.0f, 1.0f, 1.0f);
            tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
            tessellator.func_78374_a(0.0, (double)(1 + a), 0.0, (double)f1, (double)f4);
            tessellator.func_78374_a(1.0, (double)(1 + a), 0.0, (double)f3, (double)f4);
            tessellator.func_78374_a(1.0, (double)(0 + a), 0.0, (double)f3, (double)f2);
            tessellator.func_78374_a(0.0, (double)(0 + a), 0.0, (double)f1, (double)f2);
            tessellator.func_78381_a();
        }
    }

    protected void func_73864_a(int mx, int my, int par3) {
        super.func_73864_a(mx, my, par3);
        int gx = (this.field_146294_l - this.field_146999_f) / 2;
        int gy = (this.field_146295_m - this.field_147000_g) / 2;
        int var7 = mx - (gx + 89);
        int var8 = my - (gy + 35);
        if (var7 >= 0 && var8 >= 0 && var7 < 8 && var8 < 8) {
            this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 1);
            this.playButtonClick();
            return;
        }
    }

    private void playButtonClick() {
        this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:cameraclack", 0.4f, 1.0f, false);
    }
}

