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
import thaumcraft.common.tiles.TileInfusionPillar;

@SideOnly(value=Side.CLIENT)
public class TileInfusionPillarRenderer
extends TileEntitySpecialRenderer {
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)PILLAR);
    private static final ResourceLocation PILLAR = new ResourceLocation("thaumcraft", "textures/models/pillar.obj");

    public void renderTileEntityAt(TileInfusionPillar tile, double par2, double par4, double par6, float par8) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4), (float)((float)par6 + 0.5f));
        GL11.glRotatef((float)90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        UtilsFX.bindTexture("textures/models/pillar.png");
        if (tile.orientation == 3) {
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        } else if (tile.orientation == 4) {
            GL11.glRotatef((float)270.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        } else if (tile.orientation == 5) {
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        this.model.renderAll();
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileInfusionPillar)par1TileEntity, par2, par4, par6, par8);
    }
}

