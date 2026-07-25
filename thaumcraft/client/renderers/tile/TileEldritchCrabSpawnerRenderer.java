/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.model.AdvancedModelLoader
 *  net.minecraftforge.client.model.IModelCustom
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.tiles.TileEldritchCrabSpawner;

@SideOnly(value=Side.CLIENT)
public class TileEldritchCrabSpawnerRenderer
extends TileEntitySpecialRenderer {
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)VENT);
    private static final ResourceLocation VENT = new ResourceLocation("thaumcraft", "textures/models/crabvent.obj");

    public void renderTileEntityAt(TileEldritchCrabSpawner tile, double par2, double par4, double par6, float par8) {
        GL11.glPushMatrix();
        this.translateFromOrientation(par2, par4, par6, tile.getFacing());
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        UtilsFX.bindTexture("textures/models/crabvent.png");
        this.model.renderAll();
        GL11.glPopMatrix();
    }

    private void translateFromOrientation(double x, double y, double z, int orientation) {
        GL11.glTranslated((double)(x + 0.5), (double)(y + 0.5), (double)(z + 0.5));
        if (orientation == 0) {
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        } else if (orientation == 1) {
            GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        } else if (orientation == 2) {
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        } else if (orientation != 3) {
            if (orientation == 4) {
                GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else if (orientation == 5) {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            }
        }
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileEldritchCrabSpawner)par1TileEntity, par2, par4, par6, par8);
    }
}

