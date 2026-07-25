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
import thaumcraft.common.tiles.TileTubeBuffer;

public class TileTubeBufferRenderer
extends TileEntitySpecialRenderer {
    private ModelTubeValve model = new ModelTubeValve();

    public void renderEntityAt(TileTubeBuffer buffer, double x, double y, double z, float fq) {
        UtilsFX.bindTexture("textures/models/valve.png");
        if (buffer.func_145831_w() != null) {
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                if (buffer.chokedSides[dir.ordinal()] == 0 || !buffer.openSides[dir.ordinal()] || ThaumcraftApiHelper.getConnectableTile(buffer.func_145831_w(), buffer.field_145851_c, buffer.field_145848_d, buffer.field_145849_e, dir) == null) continue;
                GL11.glPushMatrix();
                GL11.glTranslated((double)(x + 0.5), (double)(y + 0.5), (double)(z + 0.5));
                if (dir.getOpposite().offsetY == 0) {
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                } else {
                    GL11.glRotatef((float)90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
                    GL11.glRotatef((float)90.0f, (float)dir.getOpposite().offsetY, (float)0.0f, (float)0.0f);
                }
                GL11.glRotatef((float)90.0f, (float)dir.getOpposite().offsetX, (float)dir.getOpposite().offsetY, (float)dir.getOpposite().offsetZ);
                GL11.glPushMatrix();
                if (buffer.chokedSides[dir.ordinal()] == 2) {
                    GL11.glColor3f((float)1.0f, (float)0.3f, (float)0.3f);
                } else {
                    GL11.glColor3f((float)0.3f, (float)0.3f, (float)1.0f);
                }
                GL11.glScaled((double)1.2, (double)1.0, (double)1.2);
                GL11.glTranslated((double)0.0, (double)-0.5, (double)0.0);
                this.model.render();
                GL11.glPopMatrix();
                GL11.glPopMatrix();
            }
        }
    }

    public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f) {
        this.renderEntityAt((TileTubeBuffer)tileentity, d, d1, d2, f);
    }
}

