/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.client.renderers.models.ModelJar
 */
package com.kentington.thaumichorizons.client.renderer.tile;

import com.kentington.thaumichorizons.common.tiles.TileSoulJar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelJar;

@SideOnly(value=Side.CLIENT)
public class TileJarTHRenderer
extends TileEntitySpecialRenderer {
    private ModelJar model = new ModelJar();
    static String tx3 = "textures/misc/soul.png";

    public void func_147500_a(TileEntity tile, double x, double y, double z, float f) {
        if (!(tile instanceof TileSoulJar)) {
            return;
        }
        TileSoulJar th = (TileSoulJar)tile;
        if (th.jarTag != null && th.jarTag.func_74767_n("isSoul")) {
            long nt = System.nanoTime();
            UtilsFX.bindTexture((String)"thaumichorizons", (String)tx3);
            GL11.glEnable((int)3042);
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            GL11.glDisable((int)2929);
            GL11.glDisable((int)2884);
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            UtilsFX.renderFacingQuad((double)((double)tile.field_145851_c + 0.5), (double)((double)tile.field_145848_d + 0.4), (double)((double)tile.field_145849_e + 0.5), (float)0.0f, (float)0.1f, (float)0.9f, (int)16, (int)((int)(nt / 40000000L % 16L)), (float)f, (int)0xFFFFFF);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
            GL11.glEnable((int)2884);
            GL11.glEnable((int)2929);
            GL11.glDisable((int)3042);
            return;
        }
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 0.01f), (float)((float)z + 0.5f));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.func_147499_a(th.getTexture());
        if (th.entity != null) {
            float f1 = 0.25f;
            GL11.glScalef((float)f1, (float)f1, (float)f1);
            th.entity.func_70012_b((double)th.field_145851_c + 0.5, (double)th.field_145848_d + 0.5, (double)th.field_145849_e + 0.5, 0.0f, 0.0f);
            Render render = null;
            render = RenderManager.field_78727_a.func_78713_a(th.entity);
            if (render != null) {
                render.func_76986_a(th.entity, 0.0, 0.0, 0.0, 0.0f, f);
            }
        }
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
    }
}

