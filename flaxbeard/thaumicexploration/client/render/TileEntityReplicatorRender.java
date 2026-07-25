/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.common.lib.crafting.ThaumcraftCraftingManager
 */
package flaxbeard.thaumicexploration.client.render;

import flaxbeard.thaumicexploration.tile.TileEntityReplicator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;

public class TileEntityReplicatorRender
extends TileEntitySpecialRenderer {
    private static final ResourceLocation largeJarTexture = new ResourceLocation("thaumicexploration:textures/blocks/replicatorRunes.png");

    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float f) {
        float h;
        float ticks;
        EntityItem entityitem;
        TileEntityReplicator replicator = (TileEntityReplicator)tileentity;
        if (replicator.func_70301_a(0) != null && replicator.validLocation() && !replicator.crafting) {
            entityitem = null;
            ticks = (float)Minecraft.func_71410_x().field_71451_h.field_70173_aa + f;
            GL11.glPushMatrix();
            h = MathHelper.func_76126_a((float)(ticks % 32767.0f / 16.0f)) * 0.05f;
            GL11.glTranslatef((float)((float)d0 + 0.5f), (float)((float)d1 + 1.15f + h), (float)((float)d2 + 0.5f));
            GL11.glRotatef((float)(ticks % 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            if (replicator.func_70301_a(0).func_77973_b() instanceof ItemBlock) {
                GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
            } else {
                GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            }
            ItemStack is = replicator.func_70301_a(0).func_77946_l();
            is.field_77994_a = 1;
            entityitem = new EntityItem(replicator.func_145831_w(), 0.0, 0.0, 0.0, is);
            entityitem.field_70290_d = 0.0f;
            if (replicator.func_70301_a((int)0).field_77994_a == 0) {
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)1);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.85f);
            }
            RenderManager.field_78727_a.func_147940_a((Entity)entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
            if (!Minecraft.func_71375_t()) {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                RenderManager.field_78727_a.func_147940_a((Entity)entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
            }
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        }
        if (replicator.func_70301_a(0) != null && replicator.validLocation() && replicator.crafting) {
            entityitem = null;
            ticks = (float)Minecraft.func_71410_x().field_71451_h.field_70173_aa + f;
            GL11.glPushMatrix();
            h = MathHelper.func_76126_a((float)(ticks % 32767.0f / 16.0f)) * 0.05f;
            GL11.glTranslatef((float)((float)d0 + 0.5f), (float)((float)d1 + 1.15f + h), (float)((float)d2 + 0.5f));
            GL11.glRotatef((float)(ticks % 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            float size = (100.0f - (float)replicator.ticksLeft) / 100.0f;
            if (replicator.func_70301_a(0).func_77973_b() instanceof ItemBlock) {
                GL11.glScalef((float)(2.0f * size), (float)(2.0f * size), (float)(2.0f * size));
            } else {
                GL11.glScalef((float)(1.0f * size), (float)(1.0f * size), (float)(1.0f * size));
            }
            ItemStack is = replicator.func_70301_a(0).func_77946_l();
            is.field_77994_a = 1;
            entityitem = new EntityItem(replicator.func_145831_w(), 0.0, 0.0, 0.0, is);
            entityitem.field_70290_d = 0.0f;
            RenderManager.field_78727_a.func_147940_a((Entity)entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
            if (!Minecraft.func_71375_t()) {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                RenderManager.field_78727_a.func_147940_a((Entity)entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
            }
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        }
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glPushMatrix();
        GL11.glTranslated((double)(d0 + 0.5), (double)d1, (double)(d2 + 0.5));
        this.func_147499_a(largeJarTexture);
        if (replicator.crafting && replicator.func_70301_a(0) != null) {
            ItemStack example = replicator.func_70301_a(0).func_77946_l();
            example.field_77994_a = 1;
            AspectList ot = ThaumcraftCraftingManager.getObjectTags((ItemStack)example);
            ot = ThaumcraftCraftingManager.getBonusTags((ItemStack)example, (AspectList)ot);
            ot = ot.copy();
            for (int i = 0; i < 4; ++i) {
                Aspect aspect;
                if (ot.getAspects().length == 1) {
                    aspect = ot.getAspects()[0];
                } else if (ot.getAspects().length == 2) {
                    aspect = ot.getAspects()[i % 2];
                } else if (ot.getAspects().length == 4) {
                    aspect = ot.getAspects()[i];
                } else if (ot.getAspects().length == 3) {
                    Aspect largestAspect = null;
                    int amount = 0;
                    for (Aspect a : ot.getAspects()) {
                        if (ot.getAmount(a) <= amount) continue;
                        largestAspect = a;
                        amount = ot.getAmount(a);
                    }
                    aspect = null;
                    if (i == 1 && (aspect = ot.getAspects()[0]) == largestAspect) {
                        aspect = ot.getAspects()[1];
                    }
                    if (i == 3) {
                        if (ot.getAspects()[0] == largestAspect) {
                            aspect = ot.getAspects()[2];
                        } else {
                            aspect = ot.getAspects()[1];
                            if (aspect == largestAspect) {
                                aspect = ot.getAspects()[2];
                            }
                        }
                    }
                    if (i % 2 == 0) {
                        aspect = largestAspect;
                    }
                } else {
                    aspect = ot.getAspects()[i];
                }
                tessellator.func_78382_b();
                tessellator.func_78380_c(255);
                d0 = 0.0;
                d1 = 0.0;
                d2 = 0.0;
                tessellator.func_78378_d(aspect.getColor());
                float offset = ot.getAmount(aspect) - replicator.recipeEssentia.getAmount(aspect);
                offset /= (float)ot.getAmount(aspect);
                if (i >= 1 && i <= 3) {
                    tessellator.func_78374_a(d0 + 1.0 - 0.5, d1 + (double)offset, d2 - 0.001 - 0.5, 0.0, 1.0 - (double)offset);
                    tessellator.func_78374_a(d0 + 1.0 - 0.5, d1 + 0.0, d2 - 0.001 - 0.5, 0.0, 1.0);
                    tessellator.func_78374_a(d0 + 0.0 - 0.5, d1 + 0.0, d2 - 0.001 - 0.5, 1.0, 1.0);
                    tessellator.func_78374_a(d0 + 0.0 - 0.5, d1 + (double)offset, d2 - 0.001 - 0.5, 1.0, 1.0 - (double)offset);
                }
                tessellator.func_78381_a();
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            }
        }
        GL11.glPopMatrix();
    }
}

