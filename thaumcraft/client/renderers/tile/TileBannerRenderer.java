/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelBanner;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.tiles.TileBanner;

@SideOnly(value=Side.CLIENT)
public class TileBannerRenderer
extends TileEntitySpecialRenderer {
    private ModelBanner model = new ModelBanner();

    public void renderTileEntityAt(TileBanner banner, double par2, double par4, double par6, float par8) {
        float rx;
        boolean flag = banner.func_145831_w() != null;
        long k = flag ? banner.func_145831_w().func_82737_E() : 0L;
        GL11.glPushMatrix();
        if (banner.getAspect() == null && banner.getColor() == -1) {
            UtilsFX.bindTexture("textures/models/banner_cultist.png");
        } else {
            UtilsFX.bindTexture("textures/models/banner_blank.png");
        }
        GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4 + 1.5f), (float)((float)par6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (banner.func_145831_w() != null) {
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            float f2 = (float)(banner.getFacing() * 360) / 16.0f;
            GL11.glRotatef((float)f2, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        if (!banner.getWall()) {
            this.model.renderPole();
        } else {
            GL11.glTranslated((double)0.0, (double)0.0, (double)-0.4125);
        }
        this.model.renderBeam();
        if (banner.getColor() != -1) {
            Color c = new Color(Utils.colors[banner.getColor()]);
            GL11.glColor4f((float)((float)c.getRed() / 255.0f), (float)((float)c.getGreen() / 255.0f), (float)((float)c.getBlue() / 255.0f), (float)1.0f);
        }
        this.model.renderTabs();
        float f3 = (float)(banner.field_145851_c * 7 + banner.field_145848_d * 9 + banner.field_145849_e * 13) + (float)k + par8;
        this.model.Banner.field_78795_f = rx = (0.005f + 0.005f * MathHelper.func_76134_b((float)(f3 * (float)Math.PI * 0.02f))) * (float)Math.PI;
        this.model.renderBanner();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (banner.getAspect() != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.05001f);
            GL11.glScaled((double)0.0375, (double)0.0375, (double)0.0375);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(-rx * 57.295776f * 2.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            UtilsFX.drawTag(-8, 4, banner.getAspect(), 0.0f, 0, 0.0, 771, 0.75f, false);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileBanner)par1TileEntity, par2, par4, par6, par8);
    }
}

