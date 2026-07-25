/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 */
package com.kentington.thaumichorizons.client.renderer.tile;

import com.kentington.thaumichorizons.common.tiles.TileVat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class TileVatSlaveRender
extends TileEntitySpecialRenderer {
    ModelBiped corpse = new ModelBiped();
    static String tx1 = "textures/models/corpseeffigy.png";
    static String tx2 = "textures/models/corpseeffigyrevived.png";
    EntityItem stack = null;

    public void func_147500_a(TileEntity te, double x, double y, double z, float f) {
        if (te.func_145832_p() == 0 && te.func_145831_w().func_72805_g(te.field_145851_c, te.field_145848_d + 1, te.field_145849_e) == 7) {
            TileVat tco = (TileVat)te.func_145831_w().func_147438_o(te.field_145851_c, te.field_145848_d + 1, te.field_145849_e);
            GL11.glPushMatrix();
            if (tco.getEntityContained() != null && !(tco.getEntityContained() instanceof EntityPlayer)) {
                if (tco.mode == 1) {
                    float f2 = tco.CLONE_TIME - tco.progress;
                    tco.getClass();
                    float scale = f2 / 800.0f;
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)scale);
                    EntityLivingBase p_147936_1_ = tco.getEntityContained();
                    double d0 = p_147936_1_.field_70142_S + (p_147936_1_.field_70165_t - p_147936_1_.field_70142_S) * (double)f;
                    double d1 = p_147936_1_.field_70137_T + (p_147936_1_.field_70163_u - p_147936_1_.field_70137_T) * (double)f;
                    double d2 = p_147936_1_.field_70136_U + (p_147936_1_.field_70161_v - p_147936_1_.field_70136_U) * (double)f;
                    float f1 = p_147936_1_.field_70126_B + (p_147936_1_.field_70177_z - p_147936_1_.field_70126_B) * f;
                    RenderManager.field_78727_a.func_147939_a((Entity)p_147936_1_, d0 - RenderManager.field_78725_b, d1 - RenderManager.field_78726_c, d2 - RenderManager.field_78723_d, f1, f, false);
                } else {
                    GL11.glTranslatef((float)0.0f, (float)(0.1f * (float)Math.cos(Math.toRadians(Minecraft.func_71410_x().field_71439_g.field_70173_aa))), (float)0.0f);
                    RenderManager.field_78727_a.func_147937_a((Entity)tco.getEntityContained(), f);
                }
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glDisable((int)3042);
            } else if (tco.mode == 3) {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)((float)(-x) - 0.5f), (float)((float)(-y) - 1.5f + 0.1f * (float)Math.cos(Math.toRadians(Minecraft.func_71410_x().field_71439_g.field_70173_aa))), (float)((float)z + 0.5f));
                UtilsFX.bindTexture((String)"thaumichorizons", (String)tx1);
                this.corpse.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.1f);
            } else if (tco.mode == 4 || tco.mode == 2 && tco.recipeType == 1) {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)((float)(-x) - 0.5f), (float)((float)(-y) - 1.5f + 0.1f * (float)Math.cos(Math.toRadians(Minecraft.func_71410_x().field_71439_g.field_70173_aa))), (float)((float)z + 0.5f));
                UtilsFX.bindTexture((String)"thaumichorizons", (String)tx2);
                this.corpse.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.1f);
            } else if (tco.sample != null) {
                if (this.stack == null || tco.sample.func_77973_b() != this.stack.func_92059_d().func_77973_b()) {
                    this.stack = new EntityItem(tco.func_145831_w(), (double)tco.field_145851_c + 0.5, (double)tco.field_145848_d - 1.0, (double)tco.field_145849_e + 0.5, tco.sample);
                }
                GL11.glTranslatef((float)0.0f, (float)(0.1f * (float)Math.cos(Math.toRadians(Minecraft.func_71410_x().field_71439_g.field_70173_aa))), (float)0.0f);
                RenderManager.field_78727_a.func_147937_a((Entity)this.stack, f);
            }
            GL11.glEnable((int)32826);
            GL11.glPopMatrix();
        }
    }
}

