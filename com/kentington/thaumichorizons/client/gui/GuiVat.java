/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.common.lib.research.ResearchManager
 */
package com.kentington.thaumichorizons.client.gui;

import com.kentington.thaumichorizons.common.container.ContainerVat;
import com.kentington.thaumichorizons.common.lib.EntityInfusionProperties;
import com.kentington.thaumichorizons.common.tiles.TileVat;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.lib.research.ResearchManager;

public class GuiVat
extends GuiContainer {
    TileVat tile;
    EntityPlayer player;

    public GuiVat(EntityPlayer p, TileVat t) {
        super((Container)new ContainerVat(p, t));
        this.tile = t;
        this.player = p;
        this.field_146999_f = 176;
        this.field_147000_g = 209;
    }

    protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        int[] infusions;
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        UtilsFX.bindTexture((ResourceLocation)new ResourceLocation("thaumichorizons", "textures/gui/guivat.png"));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int var5 = (this.field_146294_l - this.field_146999_f) / 2;
        int var6 = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
        if (!ResearchManager.isResearchComplete((String)this.player.func_70005_c_(), (String)"incarnationVat")) {
            this.func_73729_b(var5 + 58, var6 + 30, 176, 163, 57, 20);
        }
        if (this.tile.getEntity() != null) {
            EntityLivingBase critter = this.tile.getEntity();
            float health = critter.func_110143_aJ() / 2.0f;
            float max = critter.func_110138_aP() / 2.0f;
            for (int i = 0; i < (int)max; ++i) {
                if (health >= (float)i) {
                    this.func_73729_b(var5 + 56 + 7 * i - 63 * (i / 9), var6 + 12 + 7 * (i / 9), 176, 126, 7, 6);
                    continue;
                }
                this.func_73729_b(var5 + 56 + 7 * i - 63 * (i / 9), var6 + 12 + 7 * (i / 9), 176, 120, 7, 6);
                if (!(health >= (float)i - 0.5f)) continue;
                this.func_73729_b(var5 + 56 + 7 * i - 63 * (i / 9), var6 + 12 + 7 * (i / 9), 176, 126, 4, 6);
            }
        } else if (this.tile.mode == 4 || this.tile.mode == 2) {
            float health = this.tile.selfInfusionHealth / 2.0f;
            float max = 10.0f;
            for (int i = 0; i < (int)max; ++i) {
                if (health >= (float)i) {
                    this.func_73729_b(var5 + 56 + 7 * i - 63 * (i / 9), var6 + 12 + 7 * (i / 9), 176, 126, 7, 6);
                    continue;
                }
                this.func_73729_b(var5 + 56 + 7 * i - 63 * (i / 9), var6 + 12 + 7 * (i / 9), 176, 120, 7, 6);
                if (!(health >= (float)i - 0.5f)) continue;
                this.func_73729_b(var5 + 56 + 7 * i - 63 * (i / 9), var6 + 12 + 7 * (i / 9), 176, 126, 4, 6);
            }
        }
        if (this.tile.getEntity() != null && (infusions = ((EntityInfusionProperties)this.tile.getEntity().getExtendedProperties("CreatureInfusion")).getInfusions())[0] != 0) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            for (int i = 0; i < 12 && infusions[i] != 0; ++i) {
                this.func_73729_b(var5 + 55 + 16 * (i % 4), var6 + 56 + 17 * (i / 4), (infusions[i] - 1) * 16, 209, 16, 16);
            }
        }
        if (this.tile.mode == 4 || this.tile.selfInfusions[0] != 0) {
            infusions = this.tile.selfInfusions;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            for (int i = 0; i < 12 && infusions[i] != 0; ++i) {
                this.func_73729_b(var5 + 55 + 16 * (i % 4), var6 + 56 + 17 * (i / 4), (infusions[i] - 1) * 16, 225, 16, 16);
            }
        }
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    protected void func_146979_b(int par1, int par2) {
    }
}

