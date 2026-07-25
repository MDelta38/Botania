/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelCube;
import thaumcraft.client.renderers.tile.TileNodeRenderer;
import thaumcraft.common.blocks.BlockCustomPlant;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileEtherealBloom;

@SideOnly(value=Side.CLIENT)
public class TileEtherealBloomRenderer
extends TileEntitySpecialRenderer {
    String tx2 = "textures/models/crystalcapacitor.png";
    BlockCustomPlant block;
    private ModelCube model = new ModelCube();

    public TileEtherealBloomRenderer() {
        this.block = (BlockCustomPlant)ConfigBlocks.blockCustomPlant;
    }

    public void func_147500_a(TileEntity tile, double x, double y, double z, float par8) {
        int a;
        float rc1;
        float rc2 = rc1 = (float)((TileEtherealBloom)tile).growthCounter + par8;
        float rc3 = rc1 - 33.0f;
        float rc4 = rc1 - 66.0f;
        if (rc1 > 100.0f) {
            rc1 = 100.0f;
        }
        if (rc2 > 50.0f) {
            rc2 = 50.0f;
        }
        if (rc3 < 0.0f) {
            rc3 = 0.0f;
        }
        if (rc3 > 33.0f) {
            rc3 = 33.0f;
        }
        if (rc4 < 0.0f) {
            rc4 = 0.0f;
        }
        if (rc4 > 33.0f) {
            rc4 = 33.0f;
        }
        float scale1 = rc1 / 100.0f;
        float scale2 = rc2 / 60.0f + 0.1666666f;
        float scale3 = rc3 / 33.0f;
        float scale4 = rc4 / 33.0f * 0.7f;
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glPushMatrix();
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)false);
        GL11.glDisable((int)2884);
        int i = ((TileEtherealBloom)tile).counter % 32;
        UtilsFX.bindTexture(TileNodeRenderer.nodetex);
        UtilsFX.renderFacingStrip((double)tile.field_145851_c + 0.5, (float)tile.field_145848_d + scale1, (double)tile.field_145849_e + 0.5, 0.0f, scale1, 1.0f, 32, 6, i, par8, 0xAADDFF);
        GL11.glEnable((int)2884);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated((double)(x + 0.5 - (double)(scale4 / 8.0f)), (double)(y + (double)scale1 - (double)(scale4 / 6.0f)), (double)(z + 0.5 - (double)(scale4 / 8.0f)));
        GL11.glScaled((double)(scale4 / 4.0f), (double)(scale4 / 3.0f), (double)(scale4 / 4.0f));
        UtilsFX.bindTexture(this.tx2);
        this.model.render();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated((double)(x + 0.5), (double)(y + 0.25), (double)(z + 0.5));
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        for (a = 0; a < 4; ++a) {
            GL11.glPushMatrix();
            GL11.glScaled((double)scale3, (double)scale1, (double)scale3);
            GL11.glRotatef((float)(90 * a), (float)0.0f, (float)1.0f, (float)0.0f);
            UtilsFX.renderQuadCenteredFromIcon(true, this.block.iconLeaves, 1.0f, 1.0f, 1.0f, 1.0f, 200, 771, 1.0f);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated((double)(x + 0.5), (double)(y + 0.6), (double)(z + 0.5));
        GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        for (a = 0; a < 4; ++a) {
            GL11.glPushMatrix();
            GL11.glScaled((double)scale4, (double)(scale1 * 0.7f), (double)scale4);
            GL11.glRotatef((float)(90 * a), (float)0.0f, (float)1.0f, (float)0.0f);
            UtilsFX.renderQuadCenteredFromIcon(true, this.block.iconLeaves, 1.0f, 1.0f, 1.0f, 1.0f, 200, 771, 1.0f);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated((double)(x + 0.5), (double)(y + 0.5), (double)(z + 0.5));
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        for (a = 0; a < 4; ++a) {
            GL11.glPushMatrix();
            GL11.glTranslated((double)0.0, (double)((1.0f - scale1) / 2.0f), (double)0.0);
            GL11.glScaled((double)scale2, (double)scale1, (double)scale2);
            GL11.glRotatef((float)(90 * a), (float)0.0f, (float)1.0f, (float)0.0f);
            UtilsFX.renderQuadCenteredFromIcon(true, this.block.iconStalk, 1.0f, 1.0f, 1.0f, 1.0f, 200, 771, 1.0f);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
    }
}

