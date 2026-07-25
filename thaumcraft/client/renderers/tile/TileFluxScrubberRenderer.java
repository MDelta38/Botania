/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.model.AdvancedModelLoader
 *  net.minecraftforge.client.model.IModelCustom
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.tiles.TileFluxScrubber;

public class TileFluxScrubberRenderer
extends TileEntitySpecialRenderer {
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)CAP);
    private static final ResourceLocation CAP = new ResourceLocation("thaumcraft", "textures/models/obelisk_cap.obj");

    public void func_147500_a(TileEntity te, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        int facing = ((TileFluxScrubber)te).facing.ordinal();
        this.translateFromOrientation(x, y, z, facing);
        UtilsFX.bindTexture("textures/models/fluxscrubber.png");
        this.model.renderPart("Cap");
        float q = (float)Minecraft.func_71410_x().field_71451_h.field_70173_aa + f + (float)((TileFluxScrubber)te).count;
        float bob = MathHelper.func_76126_a((float)(q / 8.0f)) * 0.075f + 0.075f;
        GL11.glTranslated((double)0.0, (double)0.0, (double)(-bob));
        this.model.renderPart("Tip");
        GL11.glPopMatrix();
    }

    private void translateFromOrientation(double x, double y, double z, int orientation) {
        GL11.glTranslated((double)(x + 0.5), (double)(y + 0.5), (double)(z + 0.5));
        if (orientation == 0) {
            GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        } else if (orientation == 1) {
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        } else if (orientation != 2) {
            if (orientation == 3) {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else if (orientation == 4) {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else if (orientation == 5) {
                GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            }
        }
        GL11.glTranslated((double)0.0, (double)0.0, (double)-0.5);
    }
}

