/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import java.awt.Color;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelCrystal;
import thaumcraft.common.blocks.BlockCustomOreItem;
import thaumcraft.common.tiles.TileCrystal;

public class TileCrystalRenderer
extends TileEntitySpecialRenderer {
    private ModelCrystal model = new ModelCrystal();

    private void translateFromOrientation(float x, float y, float z, int orientation) {
        if (orientation == 0) {
            GL11.glTranslatef((float)(x + 0.5f), (float)(y + 1.3f), (float)(z + 0.5f));
            GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        } else if (orientation == 1) {
            GL11.glTranslatef((float)(x + 0.5f), (float)(y - 0.3f), (float)(z + 0.5f));
        } else if (orientation == 2) {
            GL11.glTranslatef((float)(x + 0.5f), (float)(y + 0.5f), (float)(z + 1.3f));
            GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        } else if (orientation == 3) {
            GL11.glTranslatef((float)(x + 0.5f), (float)(y + 0.5f), (float)(z - 0.3f));
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        } else if (orientation == 4) {
            GL11.glTranslatef((float)(x + 1.3f), (float)(y + 0.5f), (float)(z + 0.5f));
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        } else if (orientation == 5) {
            GL11.glTranslatef((float)(x - 0.3f), (float)(y + 0.5f), (float)(z + 0.5f));
            GL11.glRotatef((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        }
    }

    private void drawCrystal(int ori, float x, float y, float z, float a1, float a2, Random rand, int color, float size) {
        EntityClientPlayerMP p = Minecraft.func_71410_x().field_71439_g;
        float shade = MathHelper.func_76126_a((float)((float)(p.field_70173_aa + rand.nextInt(10)) / (5.0f + rand.nextFloat()))) * 0.075f + 0.925f;
        Color c = new Color(color);
        float r = (float)c.getRed() / 220.0f;
        float g = (float)c.getGreen() / 220.0f;
        float b = (float)c.getBlue() / 220.0f;
        GL11.glPushMatrix();
        GL11.glEnable((int)2977);
        GL11.glEnable((int)3042);
        GL11.glEnable((int)32826);
        GL11.glBlendFunc((int)770, (int)771);
        this.translateFromOrientation(x, y, z, ori);
        GL11.glRotatef((float)a1, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)a2, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glScalef((float)((0.15f + rand.nextFloat() * 0.075f) * size), (float)((0.5f + rand.nextFloat() * 0.1f) * size), (float)((0.15f + rand.nextFloat() * 0.05f) * size));
        int var19 = (int)(210.0f * shade);
        int var20 = var19 % 65536;
        int var21 = var19 / 65536;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)var20 / 1.0f), (float)((float)var21 / 1.0f));
        GL11.glColor4f((float)r, (float)g, (float)b, (float)1.0f);
        this.model.render();
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)32826);
        GL11.glDisable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity te, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        TileCrystal tco = (TileCrystal)te;
        int md = tco.func_145832_p();
        int color = BlockCustomOreItem.colors[5];
        if (md != 6) {
            color = BlockCustomOreItem.colors[md + 1];
        }
        UtilsFX.bindTexture("textures/models/crystal.png");
        Random rand = new Random(tco.func_145832_p() + tco.field_145851_c + tco.field_145848_d * tco.field_145849_e);
        this.drawCrystal(tco.orientation, (float)x, (float)y, (float)z, (rand.nextFloat() - rand.nextFloat()) * 5.0f, (rand.nextFloat() - rand.nextFloat()) * 5.0f, rand, color, 1.1f);
        for (int a = 1; a < 6; ++a) {
            if (md == 6) {
                color = BlockCustomOreItem.colors[a == 5 ? 6 : a];
            }
            int angle1 = rand.nextInt(36) + 72 * a;
            int angle2 = 15 + rand.nextInt(15);
            this.drawCrystal(tco.orientation, (float)x, (float)y, (float)z, angle1, angle2, rand, color, 0.8f);
        }
        GL11.glPopMatrix();
        GL11.glDisable((int)3042);
    }
}

