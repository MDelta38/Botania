/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelTubeValve;
import thaumcraft.common.tiles.TileTubeOneway;

public class TileTubeOnewayRenderer
extends TileEntitySpecialRenderer {
    private ModelTubeValve model = new ModelTubeValve();
    ForgeDirection fd = null;

    public void renderEntityAt(TileTubeOneway valve, double x, double y, double z, float fq) {
        UtilsFX.bindTexture("textures/models/valve.png");
        if (valve.func_145831_w() != null && ThaumcraftApiHelper.getConnectableTile(valve.func_145831_w(), valve.field_145851_c, valve.field_145848_d, valve.field_145849_e, valve.facing.getOpposite()) == null) {
            return;
        }
        GL11.glPushMatrix();
        this.fd = valve.facing;
        GL11.glTranslated((double)(x + 0.5), (double)(y + 0.5), (double)(z + 0.5));
        if (this.fd.offsetY == 0) {
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        } else {
            GL11.glRotatef((float)90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)90.0f, (float)this.fd.offsetY, (float)0.0f, (float)0.0f);
        }
        GL11.glRotatef((float)90.0f, (float)this.fd.offsetX, (float)this.fd.offsetY, (float)this.fd.offsetZ);
        GL11.glPushMatrix();
        GL11.glColor3f((float)0.45f, (float)0.5f, (float)1.0f);
        GL11.glScaled((double)1.1, (double)0.5, (double)1.1);
        GL11.glTranslated((double)0.0, (double)-0.5, (double)0.0);
        this.model.render();
        GL11.glTranslated((double)0.0, (double)-0.25, (double)0.0);
        this.model.render();
        GL11.glTranslated((double)0.0, (double)-0.25, (double)0.0);
        this.model.render();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f) {
        this.renderEntityAt((TileTubeOneway)tileentity, d, d1, d2, f);
    }
}

