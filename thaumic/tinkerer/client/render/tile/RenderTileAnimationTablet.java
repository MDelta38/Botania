/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumic.tinkerer.client.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumic.tinkerer.client.core.helper.ClientHelper;
import thaumic.tinkerer.common.block.tile.tablet.TileAnimationTablet;

public class RenderTileAnimationTablet
extends TileEntitySpecialRenderer {
    private static final ResourceLocation overlayCenter = new ResourceLocation("ttinkerer:textures/misc/atCenter.png");
    private static final ResourceLocation overlayLeft = new ResourceLocation("ttinkerer:textures/misc/atLeft.png");
    private static final ResourceLocation overlayRight = new ResourceLocation("ttinkerer:textures/misc/atRight.png");
    private static final ResourceLocation overlayIndent = new ResourceLocation("ttinkerer:textures/misc/atFacingIndent.png");
    private static final float[][] TRANSLATIONS = new float[][]{{0.0f, 0.0f, -1.0f}, {-1.0f, 0.0f, 0.0f}, {0.0f, 0.0f, 0.0f}, {-1.0f, 0.0f, -1.0f}};

    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float partialTicks) {
        TileAnimationTablet tile = (TileAnimationTablet)tileentity;
        int meta = tile.func_145832_p() & 7;
        if (meta < 2) {
            meta = 2;
        }
        int rotation = meta == 2 ? 270 : (meta == 3 ? 90 : (meta == 4 ? 0 : 180));
        GL11.glPushMatrix();
        GL11.glTranslated((double)d0, (double)d1, (double)d2);
        this.renderOverlay(tile, overlayCenter, -1, false, false, 0.65, 0.13f, 0.0f);
        if (tile.leftClick) {
            this.renderOverlay(tile, overlayLeft, 1, false, true, 1.0, 0.13f, 0.0f);
        } else {
            this.renderOverlay(tile, overlayRight, 1, false, true, 1.0, 0.131f, 0.0f);
        }
        this.renderOverlay(tile, overlayIndent, 0, false, false, 0.5, 0.13f, (float)rotation + 90.0f);
        GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslated((double)0.1, (double)(0.2 + Math.cos((double)System.currentTimeMillis() / 600.0) / 18.0), (double)0.5);
        float[] translations = TRANSLATIONS[meta - 2];
        GL11.glTranslatef((float)translations[0], (float)translations[1], (float)translations[2]);
        GL11.glScalef((float)0.8f, (float)0.8f, (float)0.8f);
        GL11.glTranslatef((float)0.5f, (float)0.0f, (float)0.5f);
        GL11.glRotatef((float)tile.swingProgress, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)-0.5f, (float)0.0f, (float)-0.5f);
        GL11.glTranslatef((float)((float)(-tile.swingProgress) / 250.0f), (float)((float)tile.swingProgress / 1000.0f), (float)0.0f);
        GL11.glRotatef((float)((float)Math.cos((float)System.currentTimeMillis() / 400.0f) * 5.0f), (float)1.0f, (float)0.0f, (float)1.0f);
        this.renderItem(tile);
        GL11.glPopMatrix();
    }

    private void renderItem(TileAnimationTablet tablet) {
        ItemStack stack = tablet.func_70301_a(0);
        if (stack != null) {
            EntityItem entityitem = new EntityItem(tablet.func_145831_w(), 0.0, 0.0, 0.0, stack);
            entityitem.func_92059_d().field_77994_a = 1;
            entityitem.field_70290_d = 0.0f;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.5f, (float)0.55f, (float)0.0f);
            if (stack.func_77973_b() instanceof ItemBlock) {
                GL11.glScalef((float)2.5f, (float)2.5f, (float)2.5f);
            } else {
                GL11.glScalef((float)1.5f, (float)1.5f, (float)1.5f);
            }
            RenderItem.field_82407_g = true;
            RenderManager.field_78727_a.func_147940_a((Entity)entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
            RenderItem.field_82407_g = false;
            GL11.glPopMatrix();
        }
    }

    private void renderOverlay(TileAnimationTablet tablet, ResourceLocation texture, int rotationMod, boolean useLighting, boolean useBlend, double size, float height, float forceDeg) {
        Minecraft mc = ClientHelper.minecraft();
        mc.field_71446_o.func_110577_a(texture);
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)false);
        if (!useLighting) {
            GL11.glDisable((int)2896);
        }
        if (useBlend) {
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
        }
        GL11.glTranslatef((float)0.5f, (float)height, (float)0.5f);
        float deg = rotationMod == 0 ? forceDeg : (float)(tablet.ticksExisted * (double)rotationMod % 360.0);
        GL11.glRotatef((float)deg, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Tessellator tess = Tessellator.field_78398_a;
        double size1 = size / 2.0;
        double size2 = -size1;
        tess.func_78382_b();
        tess.func_78374_a(size2, 0.0, size1, 0.0, 1.0);
        tess.func_78374_a(size1, 0.0, size1, 1.0, 1.0);
        tess.func_78374_a(size1, 0.0, size2, 1.0, 0.0);
        tess.func_78374_a(size2, 0.0, size2, 0.0, 0.0);
        tess.func_78381_a();
        GL11.glDepthMask((boolean)true);
        if (!useLighting) {
            GL11.glEnable((int)2896);
        }
        GL11.glPopMatrix();
    }
}

