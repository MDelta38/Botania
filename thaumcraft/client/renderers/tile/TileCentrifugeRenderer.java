/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelCentrifuge;
import thaumcraft.common.tiles.TileCentrifuge;

public class TileCentrifugeRenderer
extends TileEntitySpecialRenderer {
    private ModelCentrifuge model = new ModelCentrifuge();

    public void renderEntityAt(TileCentrifuge cf, double x, double y, double z, float fq) {
        Minecraft mc = FMLClientHandler.instance().getClient();
        UtilsFX.bindTexture("textures/models/centrifuge.png");
        GL11.glPushMatrix();
        GL11.glTranslated((double)(x + 0.5), (double)(y + 0.5), (double)(z + 0.5));
        this.model.renderBoxes();
        GL11.glRotated((double)cf.rotation, (double)0.0, (double)1.0, (double)0.0);
        this.model.renderSpinnyBit();
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f) {
        this.renderEntityAt((TileCentrifuge)tileentity, d, d1, d2, f);
    }
}

