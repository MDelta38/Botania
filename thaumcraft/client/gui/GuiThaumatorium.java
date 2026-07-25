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
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.container.ContainerThaumatorium;
import thaumcraft.common.tiles.TileThaumatorium;

@SideOnly(value=Side.CLIENT)
public class GuiThaumatorium
extends GuiContainer {
    private TileThaumatorium inventory;
    private ContainerThaumatorium container = null;
    private int index = 0;
    private int lastSize = 0;
    private EntityPlayer player = null;
    int startAspect = 0;

    public GuiThaumatorium(InventoryPlayer par1InventoryPlayer, TileThaumatorium par2TileEntityFurnace) {
        super((Container)new ContainerThaumatorium(par1InventoryPlayer, par2TileEntityFurnace));
        this.inventory = par2TileEntityFurnace;
        this.container = (ContainerThaumatorium)this.field_147002_h;
        this.container.updateRecipes();
        this.lastSize = this.container.recipes.size();
        this.player = par1InventoryPlayer.field_70458_d;
        this.refreshIndex();
    }

    void refreshIndex() {
        if (this.inventory.recipeHash != null && this.container.recipes.size() > 0) {
            for (int a = 0; a < this.container.recipes.size(); ++a) {
                if (!this.inventory.recipeHash.contains(this.container.recipes.get((int)a).hash)) continue;
                this.index = a;
                break;
            }
        }
        this.startAspect = 0;
    }

    protected void func_146979_b(int par1, int par2) {
    }

    protected void func_146976_a(float par1, int mx, int my) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        UtilsFX.bindTexture("textures/gui/gui_thaumatorium.png");
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        GL11.glEnable((int)3042);
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
        if (this.index >= this.container.recipes.size()) {
            this.index = this.container.recipes.size() - 1;
        }
        if (this.container.recipes.size() > 0) {
            if (this.lastSize != this.container.recipes.size()) {
                this.lastSize = this.container.recipes.size();
                this.refreshIndex();
            }
            if (this.index < 0) {
                this.index = 0;
            }
            if (this.container.recipes.size() > 1) {
                if (this.index > 0) {
                    this.func_73729_b(k + 128, l + 16, 192, 16, 16, 8);
                } else {
                    this.func_73729_b(k + 128, l + 16, 176, 16, 16, 8);
                }
                if (this.index < this.container.recipes.size() - 1) {
                    this.func_73729_b(k + 128, l + 24, 192, 24, 16, 8);
                } else {
                    this.func_73729_b(k + 128, l + 24, 176, 24, 16, 8);
                }
            }
            if (this.container.recipes.get((int)this.index).aspects.size() > 6) {
                if (this.startAspect > 0) {
                    this.func_73729_b(k + 32, l + 40, 192, 32, 8, 16);
                } else {
                    this.func_73729_b(k + 32, l + 40, 176, 32, 8, 16);
                }
                if (this.startAspect < this.container.recipes.get((int)this.index).aspects.size() - 1) {
                    this.func_73729_b(k + 136, l + 40, 200, 32, 8, 16);
                } else {
                    this.func_73729_b(k + 136, l + 40, 184, 32, 8, 16);
                }
            } else {
                this.startAspect = 0;
            }
            if (this.inventory.recipeHash != null && this.inventory.recipeHash.size() > 0) {
                int x = mx - (k + 112);
                int y = my - (l + 16);
                if (x >= 0 && y >= 0 && x < 16 && y < 16 || this.inventory.recipeHash.contains(this.container.recipes.get((int)this.index).hash)) {
                    GL11.glPushMatrix();
                    GL11.glEnable((int)3042);
                    this.func_73729_b(k + 104, l + 8, 176, 96, 48, 48);
                    GL11.glDisable((int)3042);
                    GL11.glPopMatrix();
                }
                GL11.glPushMatrix();
                GL11.glEnable((int)3042);
                float alpha = 0.6f + MathHelper.func_76126_a((float)((float)this.field_146297_k.field_71439_g.field_70173_aa / 5.0f)) * 0.4f + 0.4f;
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
                this.func_73729_b(k + 88, l + 16, 176, 56, 24, 24);
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
            }
            this.drawAspects(k, l);
            this.drawOutput(k, l, mx, my);
            if (this.inventory.maxRecipes > 1) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)(k + 136), (float)(l + 33), (float)0.0f);
                GL11.glScalef((float)0.5f, (float)0.5f, (float)0.0f);
                String text = this.inventory.recipeHash.size() + "/" + this.inventory.maxRecipes;
                int ll = this.field_146289_q.func_78256_a(text) / 2;
                this.field_146289_q.func_78276_b(text, -ll, 0, 0xFFFFFF);
                GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glPopMatrix();
            }
        }
        GL11.glDisable((int)3042);
    }

    private void drawAspects(int k, int l) {
        int count = 0;
        int pos = 0;
        if (this.inventory.recipeHash.contains(this.container.recipes.get((int)this.index).hash)) {
            for (Aspect aspect : this.container.recipes.get((int)this.index).aspects.getAspectsSorted()) {
                if (count >= this.startAspect) {
                    GL11.glPushMatrix();
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    this.func_73729_b(k + 41 + 16 * pos, l + 57, 176, 8, 14, 6);
                    int i1 = (int)((float)this.inventory.essentia.getAmount(aspect) / (float)this.container.recipes.get((int)this.index).aspects.getAmount(aspect) * 12.0f);
                    Color c = new Color(aspect.getColor());
                    GL11.glColor4f((float)((float)c.getRed() / 255.0f), (float)((float)c.getGreen() / 255.0f), (float)((float)c.getBlue() / 255.0f), (float)1.0f);
                    this.func_73729_b(k + 42 + 16 * pos, l + 58, 176, 0, i1, 4);
                    GL11.glPopMatrix();
                    ++pos;
                }
                if (++count >= 6 + this.startAspect) break;
            }
        }
        count = 0;
        pos = 0;
        for (Aspect aspect : this.container.recipes.get((int)this.index).aspects.getAspectsSorted()) {
            if (count >= this.startAspect) {
                UtilsFX.drawTag(k + 40 + 16 * pos, l + 40, aspect, this.container.recipes.get((int)this.index).aspects.getAmount(aspect), 0, this.field_73735_i);
                ++pos;
            }
            if (++count >= 6 + this.startAspect) break;
        }
    }

    private void drawOutput(int x, int y, int mx, int my) {
        GL11.glPushMatrix();
        boolean dull = false;
        if (this.inventory.recipeHash.size() < this.inventory.maxRecipes || this.inventory.recipeHash.contains(this.container.recipes.get((int)this.index).hash)) {
            dull = true;
            float alpha = 0.3f + MathHelper.func_76126_a((float)((float)this.field_146297_k.field_71439_g.field_70173_aa / 4.0f)) * 0.3f + 0.3f;
            GL11.glColor4f((float)0.5f, (float)0.5f, (float)0.5f, (float)alpha);
            GuiThaumatorium.field_146296_j.field_77024_a = false;
        }
        GL11.glEnable((int)2896);
        GL11.glEnable((int)2884);
        GL11.glEnable((int)3042);
        field_146296_j.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, this.container.recipes.get(this.index).getRecipeOutput(), x + 112, y + 16);
        field_146296_j.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, this.container.recipes.get(this.index).getRecipeOutput(), x + 112, y + 16);
        int xx = mx - (x + 112);
        int yy = my - (y + 16);
        if (xx >= 0 && yy >= 0 && xx < 16 && yy < 16) {
            this.func_146285_a(this.container.recipes.get(this.index).getRecipeOutput(), mx, my);
        }
        if (dull) {
            GuiThaumatorium.field_146296_j.field_77024_a = true;
        }
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2896);
        GL11.glPopMatrix();
    }

    protected void func_73864_a(int mx, int my, int par3) {
        super.func_73864_a(mx, my, par3);
        int gx = (this.field_146294_l - this.field_146999_f) / 2;
        int gy = (this.field_146295_m - this.field_147000_g) / 2;
        int x = mx - (gx + 112);
        int y = my - (gy + 16);
        if (this.container.recipes.size() > 0 && this.index >= 0 && this.index < this.container.recipes.size()) {
            if (x >= 0 && y >= 0 && x < 16 && y < 16 && (this.inventory.recipeHash.size() < this.inventory.maxRecipes || this.inventory.recipeHash.contains(this.container.recipes.get((int)this.index).hash))) {
                this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, this.index);
                this.playButtonSelect();
            }
            if (this.container.recipes.size() > 1) {
                if (this.index > 0) {
                    x = mx - (gx + 128);
                    y = my - (gy + 16);
                    if (x >= 0 && y >= 0 && x < 16 && y < 8) {
                        --this.index;
                        this.playButtonClick();
                    }
                }
                if (this.index < this.container.recipes.size() - 1) {
                    x = mx - (gx + 128);
                    y = my - (gy + 24);
                    if (x >= 0 && y >= 0 && x < 16 && y < 8) {
                        ++this.index;
                        this.playButtonClick();
                    }
                }
            }
            if (this.container.recipes.get((int)this.index).aspects.size() > 6) {
                if (this.startAspect > 0) {
                    x = mx - (gx + 32);
                    y = my - (gy + 40);
                    if (x >= 0 && y >= 0 && x < 8 && y < 16) {
                        --this.startAspect;
                        this.playButtonClick();
                    }
                }
                if (this.startAspect < this.container.recipes.get((int)this.index).aspects.size() - 1) {
                    x = mx - (gx + 136);
                    y = my - (gy + 40);
                    if (x >= 0 && y >= 0 && x < 8 && y < 16) {
                        ++this.startAspect;
                        this.playButtonClick();
                    }
                }
            }
        }
    }

    private void playButtonSelect() {
        this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:hhon", 0.3f, 1.0f, false);
    }

    private void playButtonClick() {
        this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:cameraclack", 0.4f, 1.0f, false);
    }
}

