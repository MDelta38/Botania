/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelEnderCrystal
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.block.tile.corporea.TileCorporeaIndex;

public class RenderTileCorporeaIndex
extends TileEntitySpecialRenderer {
    private static final ResourceLocation texture = new ResourceLocation("botania:textures/model/corporeaIndex.png");
    ModelEnderCrystal crystal = new ModelEnderCrystal(0.0f, false);
    public static boolean move = true;

    public void func_147500_a(TileEntity tile, double x, double y, double z, float partticks) {
        TileCorporeaIndex index = (TileCorporeaIndex)tile;
        GL11.glPushMatrix();
        GL11.glTranslated((double)(x + 0.5), (double)y, (double)(z + 0.5));
        Minecraft.func_71410_x().field_71446_o.func_110577_a(texture);
        float translation = move ? (float)((Math.cos(((float)index.ticksWithCloseby + (index.hasCloseby ? partticks : 0.0f)) / 10.0f) * 0.5 + 0.5) * 0.25) : 0.0f;
        float rotation = move ? (float)(index.ticks * 2) + partticks : 0.0f;
        float scale = 0.6f;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        this.crystal.func_78088_a(null, 0.0f, rotation, translation, 0.0f, 0.0f, 0.0625f);
        GL11.glScalef((float)(1.0f / scale), (float)(1.0f / scale), (float)(1.0f / scale));
        if (index.closeby > 0.0f) {
            float starScale = 0.02f;
            float starRadius = 2.5f * index.closeby + (index.closeby == 1.0f ? 0.0f : (index.hasCloseby ? partticks : -partticks)) * 0.2f;
            double rads = (double)(((float)index.ticksWithCloseby + partticks) * 2.0f) * Math.PI / 180.0;
            double starX = Math.cos(rads) * (double)starRadius;
            double starZ = Math.sin(rads) * (double)starRadius;
            int color = 0xFF00FF;
            int seed = index.field_145851_c ^ index.field_145848_d ^ index.field_145849_e;
            GL11.glTranslated((double)starX, (double)0.3, (double)starZ);
            RenderHelper.renderStar(color, starScale, starScale, starScale, seed);
            GL11.glTranslated((double)(-starX * 2.0), (double)0.0, (double)(-starZ * 2.0));
            RenderHelper.renderStar(color, starScale, starScale, starScale, seed);
            GL11.glTranslated((double)starX, (double)0.0, (double)starZ);
            rads = -rads;
            starX = Math.cos(rads) * (double)starRadius;
            starZ = Math.sin(rads) * (double)starRadius;
            GL11.glTranslated((double)starX, (double)0.0, (double)starZ);
            RenderHelper.renderStar(color, starScale, starScale, starScale, seed);
            GL11.glTranslated((double)(-starX * 2.0), (double)0.0, (double)(-starZ * 2.0));
            RenderHelper.renderStar(color, starScale, starScale, starScale, seed);
            GL11.glTranslated((double)starX, (double)0.0, (double)starZ);
        }
        GL11.glPopMatrix();
    }
}

