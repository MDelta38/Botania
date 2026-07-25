/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelBellows;
import thaumcraft.common.tiles.TileBellows;

public class TileBellowsRenderer
extends TileEntitySpecialRenderer {
    private ModelBellows model = new ModelBellows();

    private void translateFromOrientation(double x, double y, double z, int orientation) {
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y - 0.5f), (float)((float)z + 0.5f));
        if (orientation == 0) {
            GL11.glTranslatef((float)0.0f, (float)1.0f, (float)-1.0f);
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        } else if (orientation == 1) {
            GL11.glTranslatef((float)0.0f, (float)1.0f, (float)1.0f);
            GL11.glRotatef((float)270.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        } else if (orientation == 2) {
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        } else if (orientation == 4) {
            GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        } else if (orientation == 5) {
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        }
    }

    public void renderEntityAt(TileBellows bellows, double x, double y, double z, float fq) {
        float scale = 0.0f;
        if (bellows.func_145831_w() == null) {
            EntityClientPlayerMP p = Minecraft.func_71410_x().field_71439_g;
            scale = MathHelper.func_76126_a((float)((float)p.field_70173_aa / 8.0f)) * 0.3f + 0.7f;
            bellows.orientation = (byte)2;
        } else {
            scale = bellows.inflation;
        }
        float tscale = 0.125f + scale * 0.875f;
        Minecraft mc = FMLClientHandler.instance().getClient();
        UtilsFX.bindTexture("textures/models/bellows.png");
        GL11.glPushMatrix();
        GL11.glEnable((int)2977);
        GL11.glEnable((int)3042);
        GL11.glEnable((int)32826);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.translateFromOrientation((float)x, (float)y, (float)z, bellows.orientation);
        GL11.glTranslatef((float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glPushMatrix();
        GL11.glScalef((float)0.5f, (float)((scale + 0.1f) / 2.0f), (float)0.5f);
        this.model.Bag.func_78793_a(0.0f, 0.5f, 0.0f);
        this.model.Bag.func_78785_a(0.0625f);
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
        GL11.glTranslatef((float)0.0f, (float)-1.0f, (float)0.0f);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)(-tscale / 2.0f + 0.5f), (float)0.0f);
        this.model.TopPlank.func_78785_a(0.0625f);
        GL11.glTranslatef((float)0.0f, (float)(tscale / 2.0f - 0.5f), (float)0.0f);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)(tscale / 2.0f - 0.5f), (float)0.0f);
        this.model.BottomPlank.func_78785_a(0.0625f);
        GL11.glTranslatef((float)0.0f, (float)(-tscale / 2.0f + 0.5f), (float)0.0f);
        GL11.glPopMatrix();
        this.model.render();
        GL11.glDisable((int)32826);
        GL11.glDisable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f) {
        this.renderEntityAt((TileBellows)tileentity, d, d1, d2, f);
    }
}

