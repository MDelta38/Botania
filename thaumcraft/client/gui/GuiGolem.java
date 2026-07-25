/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.fluids.FluidContainerRegistry
 *  net.minecraftforge.fluids.FluidStack
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.glu.GLU
 */
package thaumcraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.entity.RenderGolemBase;
import thaumcraft.client.renderers.models.entities.ModelGolem;
import thaumcraft.common.container.SlotGhostFluid;
import thaumcraft.common.entities.golems.ContainerGolem;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.ItemGolemCore;
import thaumcraft.common.lib.utils.Utils;

@SideOnly(value=Side.CLIENT)
public class GuiGolem
extends GuiContainer {
    private float xSize_lo;
    private float ySize_lo;
    private EntityGolemBase golem;
    private int threat = -1;
    RenderLiving rgb = new RenderGolemBase(new ModelGolem(false));
    private static ModelGolem model = new ModelGolem(true);
    private Slot theSlot;

    public GuiGolem(EntityPlayer player, EntityGolemBase e) {
        super((Container)new ContainerGolem(player.field_71071_by, e.inventory));
        this.golem = e;
        if (this.golem.advanced && this.golem.field_70170_p.field_73012_v.nextInt(4) == 0) {
            this.threat = this.golem.field_70170_p.field_73012_v.nextInt(9);
        }
    }

    protected void func_146979_b(int par1, int par2) {
        GL11.glPushMatrix();
        GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
        if (this.threat >= 0) {
            this.field_146289_q.func_78279_b(StatCollector.func_74838_a((String)("golemthreat." + this.threat + ".text")), 80, 22, 110, 0xDDDDDD);
        } else {
            this.field_146289_q.func_78279_b(StatCollector.func_74838_a((String)("golemblurb." + this.golem.getCore() + ".text")), 80, 22, 110, 0xDDDDDD);
        }
        if (((ContainerGolem)this.field_147002_h).maxScroll > 0) {
            this.field_146289_q.func_78276_b(((ContainerGolem)this.field_147002_h).currentScroll + 1 + "/" + (((ContainerGolem)this.field_147002_h).maxScroll + 1), 323, 140, 0xDDDDDD);
        }
        GL11.glPopMatrix();
    }

    public void func_73863_a(int par1, int par2, float par3) {
        super.func_73863_a(par1, par2, par3);
        this.xSize_lo = par1;
        this.ySize_lo = par2;
        int baseX = this.field_147003_i;
        int baseY = this.field_147009_r;
        int slots = this.golem.inventory.slotCount;
        int typeLoc = this.golem.getGolemType().ordinal() * 24;
        if (this.golem.getCore() > -1 && ItemGolemCore.hasInventory(this.golem.getCore()) && this.golem.getUpgradeAmount(5) > 0) {
            for (int a = 0; a < Math.min(6, slots); ++a) {
                int mposx = par1 - (baseX + 96 + a / 2 * 28);
                int mposy = par2 - (baseY + 4 + a % 2 * 31);
                if (mposx < 0 || mposy < 0 || mposx >= 24 || mposy >= 12) continue;
                String text = "Any color";
                if (this.golem.getColors(a + ((ContainerGolem)this.field_147002_h).currentScroll * 6) >= 0) {
                    text = Utils.colorNames[this.golem.getColors(a + ((ContainerGolem)this.field_147002_h).currentScroll * 6)];
                }
                int size = this.field_146289_q.func_78256_a(text);
                this.field_146289_q.func_78276_b(text, baseX + 133 - size / 2, baseY - 6, 0xFDFDFD);
            }
        }
    }

    protected void func_146976_a(float par1, int par2, int par3) {
        int baseX = this.field_147003_i;
        int baseY = this.field_147009_r;
        int mposx = par2 - (baseX + 139);
        int mposy = par3 - (baseY + 10);
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        UtilsFX.bindTexture("textures/gui/guigolem.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.func_73729_b(baseX, baseY, 0, 0, this.field_146999_f, this.field_147000_g);
        int slots = this.golem.inventory.slotCount;
        int typeLoc = this.golem.getGolemType().ordinal() * 24;
        IIcon icon = null;
        if (this.golem.getCore() > -1 && ItemGolemCore.hasInventory(this.golem.getCore())) {
            for (int a = 0; a < Math.min(6, slots); ++a) {
                FluidStack fluid;
                this.func_73729_b(baseX + 96 + a / 2 * 28, baseY + 12 + a % 2 * 31, 184, typeLoc, 24, 24);
                if (this.golem.getUpgradeAmount(4) > 0) {
                    this.func_73729_b(baseX + 96 + a / 2 * 28, baseY + 4 + a % 2 * 31, 72, 168, 24, 12);
                    short color = this.golem.getColors(a + ((ContainerGolem)this.field_147002_h).currentScroll * 6);
                    if (color > -1) {
                        Color c = new Color(Utils.colors[color]);
                        float r = (float)c.getRed() / 255.0f;
                        float g = (float)c.getGreen() / 255.0f;
                        float b = (float)c.getBlue() / 255.0f;
                        GL11.glColor4f((float)r, (float)g, (float)b, (float)1.0f);
                        this.func_73729_b(baseX + 105 + a / 2 * 28, baseY + 7 + a % 2 * 31, 0, 176, 6, 6);
                        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    }
                }
                if (this.golem.getCore() != 5 || (fluid = FluidContainerRegistry.getFluidForFilledItem((ItemStack)this.golem.inventory.func_70301_a(a + ((ContainerGolem)this.field_147002_h).currentScroll * 6))) == null || (icon = fluid.getFluid().getIcon()) == null) continue;
                GL11.glPushMatrix();
                GL11.glTranslatef((float)(baseX + 108 + a / 2 * 28), (float)(baseY + 24 + a % 2 * 31), (float)0.0f);
                UtilsFX.renderQuadCenteredFromIcon(true, icon, 16.0f, 1.0f, 1.0f, 1.0f, 200, 771, 1.0f);
                GL11.glPopMatrix();
                UtilsFX.bindTexture("textures/gui/guigolem.png");
            }
            if (slots > 6) {
                if (((ContainerGolem)this.field_147002_h).currentScroll > 0) {
                    this.func_73729_b(baseX + 111, baseY + 68, 0, 200, 24, 8);
                } else {
                    this.func_73729_b(baseX + 111, baseY + 68, 0, 208, 24, 8);
                }
                if (((ContainerGolem)this.field_147002_h).currentScroll < ((ContainerGolem)this.field_147002_h).maxScroll) {
                    this.func_73729_b(baseX + 135, baseY + 68, 24, 200, 24, 8);
                } else {
                    this.func_73729_b(baseX + 135, baseY + 68, 24, 208, 24, 8);
                }
            }
        }
        if (this.golem.getCore() == 4 && this.golem.getUpgradeAmount(4) > 0) {
            this.func_73729_b(baseX + 104, baseY + 5, 8, 168, 8, 8);
            this.func_73729_b(baseX + 104, baseY + 21, 8, 168, 8, 8);
            this.func_73729_b(baseX + 104, baseY + 37, 8, 168, 8, 8);
            this.func_73729_b(baseX + 104, baseY + 53, 8, 168, 8, 8);
            if (this.golem.canAttackHostiles()) {
                this.func_73729_b(baseX + 104, baseY + 5, 8, 176, 8, 8);
            }
            if (this.golem.canAttackAnimals()) {
                this.func_73729_b(baseX + 104, baseY + 21, 8, 176, 8, 8);
            }
            if (this.golem.canAttackPlayers()) {
                this.func_73729_b(baseX + 104, baseY + 37, 8, 176, 8, 8);
            }
            if (this.golem.canAttackCreepers()) {
                this.func_73729_b(baseX + 104, baseY + 53, 8, 176, 8, 8);
            }
            this.field_146289_q.func_78276_b("Monsters", baseX + 122, baseY + 6, 0xFFCCCC);
            this.field_146289_q.func_78276_b("Animals", baseX + 122, baseY + 22, 0xFFFFCC);
            this.field_146289_q.func_78276_b("Players", baseX + 122, baseY + 38, 0xCCCCFF);
            this.field_146289_q.func_78276_b("Creepers", baseX + 122, baseY + 54, 0xCCFFCC);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
        if (this.golem.getCore() == 0) {
            this.func_73729_b(baseX + 62, baseY + 54, 8, 168, 8, 8);
            String text = "Precise amount";
            if (!this.golem.getToggles()[0]) {
                this.func_73729_b(baseX + 62, baseY + 54, 8, 176, 8, 8);
            } else {
                text = "Any amount";
            }
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(baseX + 66), (float)(baseY + 48), (float)0.0f);
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.0f);
            int size = this.field_146289_q.func_78256_a(text);
            this.field_146289_q.func_78276_b(text, -size / 2, 0, 0xFDFDFD);
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPopMatrix();
        }
        if (this.golem.getCore() == 8) {
            this.func_73729_b(baseX + 42, baseY + 40, 8, 168, 8, 8);
            String text1 = "Block";
            if (!this.golem.getToggles()[0]) {
                this.func_73729_b(baseX + 42, baseY + 40, 8, 176, 8, 8);
            } else {
                text1 = "Empty space";
            }
            this.func_73729_b(baseX + 42, baseY + 50, 8, 168, 8, 8);
            String text2 = "Right click";
            if (!this.golem.getToggles()[1]) {
                this.func_73729_b(baseX + 42, baseY + 50, 8, 176, 8, 8);
            } else {
                text2 = "Left click";
            }
            this.func_73729_b(baseX + 42, baseY + 60, 8, 168, 8, 8);
            String text3 = "Not sneaking";
            if (!this.golem.getToggles()[2]) {
                this.func_73729_b(baseX + 42, baseY + 60, 8, 176, 8, 8);
            } else {
                text3 = "Sneaking";
            }
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(baseX + 53), (float)(baseY + 42), (float)0.0f);
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.0f);
            this.field_146289_q.func_78276_b(text1, 0, 0, 0xFDFDFD);
            this.field_146289_q.func_78276_b(text2, 0, 20, 0xFDFDFD);
            this.field_146289_q.func_78276_b(text3, 0, 40, 0xFDFDFD);
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPopMatrix();
        }
        if (this.golem.getUpgradeAmount(5) > 0 && ItemGolemCore.canSort(this.golem.getCore())) {
            int shiftx = this.golem.getCore() == 10 ? 66 : 180;
            int shifty = this.golem.getCore() == 10 ? 12 : 0;
            this.func_73729_b(baseX + shiftx, baseY + 24 + shifty, 8, 168, 8, 8);
            String text1 = "Use Ore dictionary";
            if (this.golem.checkOreDict()) {
                this.func_73729_b(baseX + shiftx, baseY + 24 + shifty, 8, 176, 8, 8);
            }
            this.func_73729_b(baseX + shiftx, baseY + 34 + shifty, 8, 168, 8, 8);
            String text2 = "Ignore item damage";
            if (this.golem.ignoreDamage()) {
                this.func_73729_b(baseX + shiftx, baseY + 34 + shifty, 8, 176, 8, 8);
            }
            this.func_73729_b(baseX + shiftx, baseY + 44 + shifty, 8, 168, 8, 8);
            String text3 = "Ignore NBT values";
            if (this.golem.ignoreNBT()) {
                this.func_73729_b(baseX + shiftx, baseY + 44 + shifty, 8, 176, 8, 8);
            }
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(baseX + shiftx + 10), (float)(baseY + 26 + shifty), (float)0.0f);
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.0f);
            this.field_146289_q.func_78276_b(text1, 0, 0, this.golem.checkOreDict() ? 0xFDFDFD : 0x666666);
            this.field_146289_q.func_78276_b(text2, 0, 20, this.golem.ignoreDamage() ? 0xFDFDFD : 0x666666);
            this.field_146289_q.func_78276_b(text3, 0, 40, this.golem.ignoreNBT() ? 0xFDFDFD : 0x666666);
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
        this.drawGolem(this.field_146297_k, baseX + 51, baseY + 75, 30, (float)(baseX + 51) - this.xSize_lo, (float)(baseY + 75 - 50) - this.ySize_lo);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public void drawGolem(Minecraft mc, int par1, int par2, int par3, float par4, float par5) {
        GL11.glEnable((int)2903);
        GL11.glPushMatrix();
        GL11.glMatrixMode((int)5889);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        ScaledResolution var7 = new ScaledResolution(Minecraft.func_71410_x(), this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        GL11.glViewport((int)((var7.func_78326_a() - 320) / 2 * var7.func_78325_e()), (int)((var7.func_78328_b() - 240) / 2 * var7.func_78325_e()), (int)(320 * var7.func_78325_e()), (int)(240 * var7.func_78325_e()));
        GL11.glTranslatef((float)-0.34f, (float)0.23f, (float)0.0f);
        GLU.gluPerspective((float)90.0f, (float)1.3333334f, (float)9.0f, (float)80.0f);
        float var8 = 1.0f;
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        RenderHelper.func_74519_b();
        GL11.glTranslatef((float)-1.5f, (float)-1.0f, (float)-12.0f);
        GL11.glScalef((float)var8, (float)var8, (float)var8);
        float var9 = 5.0f;
        GL11.glScalef((float)var9, (float)var9, (float)var9);
        float f2 = this.golem.field_70761_aq;
        float f3 = this.golem.field_70177_z;
        float f4 = this.golem.field_70125_A;
        float f5 = this.golem.field_70758_at;
        float f6 = this.golem.field_70759_as;
        this.golem.field_70761_aq = -20.0f;
        this.golem.field_70177_z = 0.0f;
        this.golem.field_70125_A = 0.0f;
        this.golem.field_70758_at = -5.0f;
        this.golem.field_70759_as = -5.0f;
        RenderManager.field_78727_a.func_147940_a((Entity)this.golem, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        this.golem.field_70761_aq = f2;
        this.golem.field_70177_z = f3;
        this.golem.field_70125_A = f4;
        this.golem.field_70758_at = f5;
        this.golem.field_70759_as = f6;
        GL11.glDisable((int)32826);
        RenderHelper.func_74518_a();
        GL11.glMatrixMode((int)5889);
        GL11.glViewport((int)0, (int)0, (int)this.field_146297_k.field_71443_c, (int)this.field_146297_k.field_71440_d);
        GL11.glPopMatrix();
        GL11.glMatrixMode((int)5888);
        RenderHelper.func_74518_a();
        GL11.glPopMatrix();
        GL11.glDisable((int)32826);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
        GL11.glDisable((int)3553);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
    }

    protected void func_73864_a(int par1, int par2, int par3) {
        int var8;
        int var7;
        int a;
        super.func_73864_a(par1, par2, par3);
        int baseX = (this.field_146294_l - this.field_146999_f) / 2;
        int baseY = (this.field_146295_m - this.field_147000_g) / 2;
        int slots = this.golem.inventory.slotCount;
        int typeLoc = this.golem.getGolemType().ordinal() * 24;
        if (this.golem.getCore() > -1 && ItemGolemCore.hasInventory(this.golem.getCore())) {
            for (a = 0; a < Math.min(6, slots); ++a) {
                if (this.golem.getUpgradeAmount(4) <= 0) continue;
                var7 = par1 - (baseX + 96 + a / 2 * 28);
                var8 = par2 - (baseY + 4 + a % 2 * 31);
                if (var7 >= 0 && var8 >= 0 && var7 < 8 && var8 < 12) {
                    this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, a + ((ContainerGolem)this.field_147002_h).currentScroll * 6);
                    return;
                }
                var7 = par1 - (baseX + 96 + 16 + a / 2 * 28);
                var8 = par2 - (baseY + 4 + a % 2 * 31);
                if (var7 < 0 || var8 < 0 || var7 >= 8 || var8 >= 12) continue;
                this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, a + slots + ((ContainerGolem)this.field_147002_h).currentScroll * 6);
                return;
            }
            if (slots > 6) {
                var7 = par1 - (baseX + 111);
                var8 = par2 - (baseY + 68);
                if (var7 >= 0 && var8 >= 0 && var7 < 24 && var8 < 8 && ((ContainerGolem)this.field_147002_h).currentScroll > 0) {
                    this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 66);
                    --((ContainerGolem)this.field_147002_h).currentScroll;
                    ((ContainerGolem)this.field_147002_h).refreshInventory();
                    return;
                }
                var7 = par1 - (baseX + 135);
                var8 = par2 - (baseY + 68);
                if (var7 >= 0 && var8 >= 0 && var7 < 24 && var8 < 8 && ((ContainerGolem)this.field_147002_h).currentScroll < ((ContainerGolem)this.field_147002_h).maxScroll) {
                    this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 67);
                    ++((ContainerGolem)this.field_147002_h).currentScroll;
                    ((ContainerGolem)this.field_147002_h).refreshInventory();
                    return;
                }
            }
        }
        if (this.golem.getCore() == 4) {
            for (a = 0; a < 4; ++a) {
                var7 = par1 - (baseX + 104);
                var8 = par2 - (baseY + 5 + 16 * a);
                if (var7 < 0 || var8 < 0 || var7 >= 8 || var8 >= 8) continue;
                this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 51 + a);
                return;
            }
        }
        if (this.golem.getCore() == 0) {
            var7 = par1 - (baseX + 62);
            var8 = par2 - (baseY + 54);
            if (var7 >= 0 && var8 >= 0 && var7 < 8 && var8 < 8) {
                this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 50);
                return;
            }
        }
        if (this.golem.getCore() == 8) {
            var7 = par1 - (baseX + 42);
            var8 = par2 - (baseY + 40);
            if (var7 >= 0 && var8 >= 0 && var7 < 8 && var8 < 8) {
                this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 50);
                return;
            }
            var7 = par1 - (baseX + 42);
            var8 = par2 - (baseY + 50);
            if (var7 >= 0 && var8 >= 0 && var7 < 8 && var8 < 8) {
                this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 51);
                return;
            }
            var7 = par1 - (baseX + 42);
            var8 = par2 - (baseY + 60);
            if (var7 >= 0 && var8 >= 0 && var7 < 8 && var8 < 8) {
                this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 52);
                return;
            }
        }
        if (this.golem.getUpgradeAmount(5) > 0 && ItemGolemCore.canSort(this.golem.getCore())) {
            int shiftx = this.golem.getCore() == 10 ? 66 : 180;
            int shifty = this.golem.getCore() == 10 ? 12 : 0;
            for (int a2 = 0; a2 < 3; ++a2) {
                var7 = par1 - (baseX + shiftx);
                var8 = par2 - (baseY + 24 + a2 * 10 + shifty);
                if (var7 < 0 || var8 < 0 || var7 >= 64 || var8 >= 8) continue;
                this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 55 + a2);
                return;
            }
        }
    }

    protected void func_146285_a(ItemStack par1ItemStack, int par2, int par3) {
        if (this.golem.getCore() != 5 || !(this.theSlot instanceof SlotGhostFluid)) {
            super.func_146285_a(par1ItemStack, par2, par3);
        } else {
            ArrayList<String> list = new ArrayList<String>();
            FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem((ItemStack)par1ItemStack);
            if (fluid != null) {
                list.add(fluid.getFluid().getLocalizedName(fluid));
                FontRenderer font = par1ItemStack.func_77973_b().getFontRenderer(par1ItemStack);
                this.drawHoveringText(list, par2, par3, font == null ? this.field_146289_q : font);
            }
        }
    }
}

