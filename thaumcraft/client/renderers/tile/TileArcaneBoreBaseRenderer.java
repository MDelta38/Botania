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
import thaumcraft.client.renderers.models.ModelBoreBase;
import thaumcraft.common.tiles.TileArcaneBoreBase;

public class TileArcaneBoreBaseRenderer
extends TileEntitySpecialRenderer {
    private ModelBoreBase model = new ModelBoreBase();

    public void renderEntityAt(TileArcaneBoreBase bore, double x, double y, double z, float fq) {
        Minecraft mc = FMLClientHandler.instance().getClient();
        UtilsFX.bindTexture("textures/models/Bore.png");
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y), (float)((float)z + 0.5f));
        GL11.glPushMatrix();
        this.model.render();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        switch (bore.orientation.ordinal()) {
            case 2: {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 3: {
                GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 4: {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 5: {
                GL11.glRotatef((float)0.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            }
        }
        this.model.renderNozzle();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f) {
        this.renderEntityAt((TileArcaneBoreBase)tileentity, d, d1, d2, f);
    }
}

