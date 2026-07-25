/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.fx.ParticleEngine
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.config.Config
 */
package com.kentington.thaumichorizons.client.gui;

import com.kentington.thaumichorizons.common.container.ContainerBloodInfuser;
import com.kentington.thaumichorizons.common.tiles.TileBloodInfuser;
import java.awt.Color;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;

public class GuiBloodInfuser
extends GuiContainer {
    TileBloodInfuser tile;
    AspectList aspectsKnown;
    Aspect[] aspectsSelected = new Aspect[8];
    int numSelected = 0;
    int offset = 0;
    boolean scrollLClicked;
    boolean scrollRClicked;
    int flashX;
    int flashY;
    Color flashColor = null;
    int flashTimer = 0;
    int topOut = 2;
    int bottomOut = 2;
    Aspect mousedOver = null;
    HashMap<Integer, Integer> mousedEffects = null;
    NBTTagList cachedEffects = null;

    public GuiBloodInfuser(EntityPlayer p, TileBloodInfuser tile) {
        super((Container)new ContainerBloodInfuser(p, tile));
        for (Aspect asp : tile.aspectsSelected.getAspects()) {
            if (asp == null) continue;
            for (int i = 0; i < tile.aspectsSelected.getAmount(asp); ++i) {
                this.aspectsSelected[this.numSelected] = asp;
                ++this.numSelected;
            }
        }
        this.aspectsKnown = Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(p.func_70005_c_());
        this.tile = tile;
        this.field_146999_f = 176;
        this.field_147000_g = 219;
        this.cachedEffects = tile.getCurrentEffects();
    }

    protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        UtilsFX.bindTexture((ResourceLocation)new ResourceLocation("thaumichorizons", "textures/gui/guibloodinfuser.png"));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int var5 = (this.field_146294_l - this.field_146999_f) / 2;
        int var6 = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    protected void func_146979_b(int par1, int par2) {
        int l;
        Potion potion;
        int j;
        int i;
        this.drawEssentiaSelected();
        this.drawAspectList();
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        UtilsFX.bindTexture((ResourceLocation)new ResourceLocation("thaumichorizons", "textures/gui/guibloodinfuser.png"));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (this.canScrollLeft()) {
            this.func_73729_b(111, 75, 177, 136, 24, 8);
        } else {
            this.func_73729_b(111, 75, 177, 144, 24, 8);
        }
        if (this.canScrollRight()) {
            this.func_73729_b(135, 75, 201, 136, 24, 8);
        } else {
            this.func_73729_b(135, 75, 201, 144, 24, 8);
        }
        if (this.tile.mode == 1) {
            this.func_73729_b(38, 57, 178, 130, 6, 6);
        } else if (this.tile.mode == 2) {
            this.func_73729_b(38, 70, 178, 130, 6, 6);
        }
        int gx = (this.field_146294_l - this.field_146999_f) / 2;
        int gy = (this.field_146295_m - this.field_147000_g) / 2;
        int x = par1 - (gx + 15);
        int y = par2 - (gy + 83);
        if (x >= 0 && y >= 0 && x <= 144 && y <= 34) {
            int ecks = x / 18;
            int why = y / 18 - 1;
            int i2 = ecks * 2 - why;
            Aspect asp = null;
            if (i2 + this.offset < this.aspectsKnown.size()) {
                asp = this.aspectsKnown.getAspectsSorted()[i2 + this.offset];
            }
            if (!(asp == null || this.mousedOver != null && asp.getTag().equals(this.mousedOver.getTag()))) {
                this.mousedOver = asp;
                this.mousedEffects = this.tile.getEffects(this.mousedOver);
            }
        } else {
            x = par1 - (gx + 110);
            y = par2 - (gy + 74);
            if (x >= 0 && y >= 0 && x <= 49 && y <= 9) {
                this.mousedOver = null;
            }
        }
        if (this.mousedOver == null) {
            if (this.bottomOut > 2) {
                this.bottomOut -= 2;
            }
            this.func_73729_b(171, 69, 215 - this.bottomOut, 194, this.bottomOut, 59);
        } else {
            if (this.bottomOut < 38) {
                this.bottomOut += 2;
            }
            this.func_73729_b(171, 69, 215 - this.bottomOut, 194, this.bottomOut, 59);
            i = 0;
            j = 0;
            if (this.bottomOut == 38 && this.mousedEffects != null) {
                for (Integer key : this.mousedEffects.keySet()) {
                    potion = Potion.field_76425_a[key];
                    if (potion.func_76400_d()) {
                        this.field_146297_k.func_110434_K().func_110577_a(field_147001_a);
                        l = potion.func_76392_e();
                        this.func_73729_b(i * 18 + 171, j * 18 + 72, 0 + l % 8 * 18, 198 + l / 8 * 18, 18, 18);
                        this.field_146289_q.func_78276_b("" + this.mousedEffects.get(key), i * 18 + 171, j * 18 + 72, Color.GRAY.getRGB());
                        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                        if (i == 0) {
                            i = 1;
                            continue;
                        }
                        i = 0;
                        ++j;
                        continue;
                    }
                    this.field_146297_k.func_110434_K().func_110577_a(new ResourceLocation("thaumichorizons", "textures/misc/potions.png"));
                    int ecks = 0;
                    int why = 216;
                    if (potion.func_76396_c() == 23) {
                        ecks = 36;
                    } else if (potion.func_76396_c() == Potion.field_76432_h.field_76415_H) {
                        ecks = 0;
                    } else if (potion.func_76396_c() == Potion.field_76433_i.field_76415_H) {
                        ecks = 18;
                    }
                    this.func_73729_b(i * 18 + 171, j * 18 + 72, ecks, why, 18, 18);
                    this.field_146289_q.func_78276_b("" + this.mousedEffects.get(key), i * 18 + 171, j * 18 + 72, Color.GRAY.getRGB());
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    if (i == 0) {
                        i = 1;
                        continue;
                    }
                    i = 0;
                    ++j;
                }
            }
        }
        if (this.cachedEffects != null && this.cachedEffects.func_74745_c() > 0) {
            UtilsFX.bindTexture((ResourceLocation)new ResourceLocation("thaumichorizons", "textures/gui/guibloodinfuser.png"));
            if (this.topOut < 38) {
                this.topOut += 2;
            }
            this.func_73729_b(171, 25, 215 - this.topOut, 152, this.topOut, 41);
            i = 0;
            j = 0;
            if (this.topOut == 38) {
                for (int index = 0; index < this.cachedEffects.func_74745_c(); ++index) {
                    Byte id = this.cachedEffects.func_150305_b(index).func_74771_c("Id");
                    potion = Potion.field_76425_a[id];
                    if (potion == null) continue;
                    this.field_146297_k.func_110434_K().func_110577_a(field_147001_a);
                    l = potion.func_76392_e();
                    this.func_73729_b(i * 18 + 171, j * 18 + 28, 0 + l % 8 * 18, 198 + l / 8 * 18, 18, 18);
                    this.field_146289_q.func_78276_b("" + (this.cachedEffects.func_150305_b(index).func_74771_c("Amplifier") + 1), i * 18 + 171, j * 18 + 28, Color.GRAY.getRGB());
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    if (i == 0) {
                        i = 1;
                        continue;
                    }
                    i = 0;
                    ++j;
                }
            }
        } else {
            if (this.topOut > 2) {
                this.topOut -= 2;
            }
            UtilsFX.bindTexture((ResourceLocation)new ResourceLocation("thaumichorizons", "textures/gui/guibloodinfuser.png"));
            this.func_73729_b(171, 25, 215 - this.topOut, 152, this.topOut, 41);
        }
        if (this.flashTimer > 0) {
            --this.flashTimer;
            this.drawFlash();
        }
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    protected void func_73864_a(int par1, int par2, int par3) {
        int i;
        super.func_73864_a(par1, par2, par3);
        int gx = (this.field_146294_l - this.field_146999_f) / 2;
        int gy = (this.field_146295_m - this.field_147000_g) / 2;
        this.scrollLClicked = false;
        this.scrollRClicked = false;
        int x = par1 - (gx + 38);
        int y = par2 - (gy + 57);
        if (x >= 0 && y >= 0 && x <= 6 && y <= 6) {
            if (this.tile.mode == 1) {
                this.tile.mode = 0;
                this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 0);
            } else {
                this.tile.mode = 1;
                this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 1);
            }
        }
        y = par2 - (gy + 70);
        if (x >= 0 && y >= 0 && x <= 6 && y <= 6) {
            if (this.tile.mode == 2) {
                this.tile.mode = 0;
                this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 0);
            } else {
                this.tile.mode = 2;
                this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 2);
            }
        }
        x = par1 - (gx + 111);
        y = par2 - (gy + 75);
        if (this.canScrollLeft() && x >= 0 && y >= 0 && x <= 24 && y <= 8) {
            this.offset -= 2;
        }
        x = par1 - (gx + 135);
        if (this.canScrollRight() && x >= 0 && y >= 0 && x <= 24 && y <= 8) {
            this.offset += 2;
        }
        for (i = 0; i < 16; ++i) {
            x = par1 - (gx + 14 + 18 * (i / 2));
            y = par2 - (gy + 83 + (i % 2 == 0 ? 18 : 0));
            if (this.offset + i >= this.aspectsKnown.getAspectsSorted().length || this.numSelected >= 8 || x < 0 || x > 16 || y < 0 || y > 16) continue;
            this.aspectsSelected[this.numSelected] = this.aspectsKnown.getAspectsSorted()[this.offset + i];
            ++this.numSelected;
            this.tile.aspectsSelected.add(this.aspectsKnown.getAspectsSorted()[this.offset + i], 1);
            this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, -1 * (i + this.offset) - 1);
            this.flashTimer = 8;
            this.flashColor = new Color(this.aspectsKnown.getAspectsSorted()[this.offset + i].getColor());
            this.flashX = par1 - gx - 8;
            this.flashY = par2 - gy - 8;
            this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:hhoff", 0.2f, 1.0f + this.field_146297_k.field_71451_h.field_70170_p.field_73012_v.nextFloat() * 0.1f, false);
        }
        for (i = 0; i < 8; ++i) {
            x = par1 - (gx + 54 + (i % 2 == 1 ? 17 : 0));
            y = par2 - (gy + 12 + 17 * (i / 2));
            if (this.numSelected <= i || x < 0 || x > 16 || y < 0 || y > 16 || !Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(this.field_146297_k.field_71439_g.func_70005_c_(), this.aspectsSelected[i])) continue;
            this.tile.aspectsSelected.remove(this.aspectsSelected[i], 1);
            this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 3 + this.findInList(this.aspectsSelected[i]));
            this.flashTimer = 8;
            this.flashColor = new Color(this.aspectsSelected[i].getColor());
            this.flashX = par1 - gx - 8;
            this.flashY = par2 - gy - 8;
            this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:hhoff", 0.2f, 1.0f + this.field_146297_k.field_71451_h.field_70170_p.field_73012_v.nextFloat() * 0.1f, false);
            for (int j = i; j < 7; ++j) {
                this.aspectsSelected[j] = this.aspectsSelected[j + 1];
            }
            this.aspectsSelected[7] = null;
            --this.numSelected;
        }
        this.cachedEffects = this.tile.getCurrentEffects();
    }

    private void drawFlash() {
        float red = (float)this.flashColor.getRed() / 255.0f;
        float green = (float)this.flashColor.getGreen() / 255.0f;
        float blue = (float)this.flashColor.getBlue() / 255.0f;
        if (Config.colorBlind) {
            red /= 1.8f;
            green /= 1.8f;
            blue /= 1.8f;
        }
        GL11.glPushMatrix();
        UtilsFX.bindTexture((ResourceLocation)ParticleEngine.particleTexture);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)this.flashX, (double)this.flashY, (double)0.0);
        Tessellator tessellator = Tessellator.field_78398_a;
        int part = this.flashTimer;
        float var8 = 0.5f + (float)part / 8.0f;
        float var9 = var8 + 0.0624375f;
        float var10 = 0.5f;
        float var11 = var10 + 0.0624375f;
        tessellator.func_78382_b();
        tessellator.func_78380_c(240);
        tessellator.func_78369_a(red, green, blue, 1.0f);
        tessellator.func_78374_a(0.0, 16.0, (double)this.field_73735_i, (double)var9, (double)var11);
        tessellator.func_78374_a(16.0, 16.0, (double)this.field_73735_i, (double)var9, (double)var10);
        tessellator.func_78374_a(16.0, 0.0, (double)this.field_73735_i, (double)var8, (double)var10);
        tessellator.func_78374_a(0.0, 0.0, (double)this.field_73735_i, (double)var8, (double)var11);
        tessellator.func_78381_a();
        GL11.glPopMatrix();
    }

    boolean canScrollLeft() {
        return this.offset > 0;
    }

    boolean canScrollRight() {
        return this.offset + 16 < this.aspectsKnown.size();
    }

    void drawAspectList() {
        Aspect[] known = this.aspectsKnown.getAspectsSorted();
        for (int i = 0; i < 16 && this.offset + i < known.length; ++i) {
            Aspect asp = known[this.offset + i];
            UtilsFX.drawTag((int)(14 + 18 * (i / 2)), (int)(83 + (i % 2 == 0 ? 18 : 0)), (Aspect)asp, (float)0.0f, (int)0, (double)this.field_73735_i, (int)771, (float)1.0f, (boolean)false);
        }
    }

    void drawEssentiaSelected() {
        AspectList alreadyUsed = new AspectList();
        for (int i = 0; i < this.numSelected; ++i) {
            Color col;
            Aspect asp = this.aspectsSelected[i];
            alreadyUsed.add(asp, 1);
            if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(this.field_146297_k.field_71439_g.func_70005_c_(), this.aspectsSelected[i])) {
                UtilsFX.drawTag((int)(54 + (i % 2 == 1 ? 17 : 0)), (int)(12 + 17 * (i / 2)), (Aspect)asp, (float)0.0f, (int)0, (double)this.field_73735_i, (int)771, (float)1.0f, (this.tile.aspectsAcquired.getAmount(asp) < alreadyUsed.getAmount(asp) ? 1 : 0) != 0);
                continue;
            }
            if (this.tile.aspectsAcquired.getAmount(asp) < alreadyUsed.getAmount(asp)) {
                col = Color.DARK_GRAY;
                this.drawQuestionMark(54 + (i % 2 == 1 ? 17 : 0), 12 + 17 * (i / 2), col);
                continue;
            }
            col = new Color(asp.getColor());
            this.drawQuestionMark(54 + (i % 2 == 1 ? 17 : 0), 12 + 17 * (i / 2), col);
        }
    }

    void drawQuestionMark(int x, int y, Color color) {
        Minecraft mc = Minecraft.func_71410_x();
        GL11.glPushMatrix();
        GL11.glDisable((int)2896);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glPushMatrix();
        mc.field_71446_o.func_110577_a(new ResourceLocation("thaumcraft", "textures/aspects/_unknown.png"));
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.8f);
        Tessellator var9 = Tessellator.field_78398_a;
        var9.func_78382_b();
        var9.func_78369_a((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, 0.8f);
        var9.func_78374_a((double)x + 0.0, (double)y + 16.0, (double)this.field_73735_i, 0.0, 1.0);
        var9.func_78374_a((double)x + 16.0, (double)y + 16.0, (double)this.field_73735_i, 1.0, 1.0);
        var9.func_78374_a((double)x + 16.0, (double)y + 0.0, (double)this.field_73735_i, 1.0, 0.0);
        var9.func_78374_a((double)x + 0.0, (double)y + 0.0, (double)this.field_73735_i, 0.0, 0.0);
        var9.func_78381_a();
        GL11.glPopMatrix();
        GL11.glDisable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glEnable((int)2896);
        GL11.glPopMatrix();
    }

    int findInList(Aspect asp) {
        Aspect[] aspects = this.aspectsKnown.getAspectsSorted();
        for (int i = 0; i < aspects.length; ++i) {
            if (asp == null || aspects[i] == null || !aspects[i].getTag().equals(asp.getTag())) continue;
            return i;
        }
        return -1;
    }
}

