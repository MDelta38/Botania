/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemDye
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.renderers.tile.TileJarRenderer
 *  thaumcraft.common.tiles.TileJar
 */
package flaxbeard.thaumicexploration.client.render;

import flaxbeard.thaumicexploration.client.render.model.ModelJarOverlay;
import flaxbeard.thaumicexploration.tile.TileEntityBoundJar;
import net.minecraft.item.ItemDye;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.tile.TileJarRenderer;
import thaumcraft.common.tiles.TileJar;

public class TileEntityBoundJarRender
extends TileJarRenderer {
    private ModelJarOverlay model = new ModelJarOverlay();
    private static final ResourceLocation overlayn = new ResourceLocation("thaumicexploration:textures/blocks/boundchestoverlaynone.png");
    private static final ResourceLocation overlay0 = new ResourceLocation("thaumicexploration:textures/blocks/boundjaroverlay0.png");
    private static final ResourceLocation seal = new ResourceLocation("thaumicexploration:textures/blocks/boundjaroverlayseal.png");
    private static final ResourceLocation overlay1 = new ResourceLocation("thaumicexploration:textures/blocks/boundjaroverlay1.png");
    private static final ResourceLocation overlay2 = new ResourceLocation("thaumicexploration:textures/blocks/boundjaroverlay2.png");
    private static final ResourceLocation overlay3 = new ResourceLocation("thaumicexploration:textures/blocks/boundjaroverlay3.png");
    private static final ResourceLocation overlay4 = new ResourceLocation("thaumicexploration:textures/blocks/boundjaroverlay4.png");
    private static final ResourceLocation[] overlays = new ResourceLocation[]{overlayn, overlay0, overlay1, overlay2, overlay3, overlay4};

    public void renderTileEntityAt(TileJar tile, double x, double y, double z, float f) {
        super.renderTileEntityAt(tile, x, y, z, f);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x), (float)((float)y + 0.5f), (float)((float)z + 1.0f));
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        int j = ((TileEntityBoundJar)tile).getSealColor();
        float r = (float)(ItemDye.field_150922_c[j] >> 16 & 0xFF) / 255.0f;
        float g = (float)(ItemDye.field_150922_c[j] >> 8 & 0xFF) / 255.0f;
        float b = (float)(ItemDye.field_150922_c[j] & 0xFF) / 255.0f;
        GL11.glColor4f((float)r, (float)g, (float)b, (float)1.0f);
        int ticks = ((TileEntityBoundJar)tile).getAccessTicks();
        if (ticks > 0) {
            double divisor = 13.0001;
            double frame = (double)(ticks - 1) / divisor - 1.0;
            int trueFrame = (int)Math.ceil(frame + 0.5);
            if (trueFrame > 5) {
                trueFrame = 5;
            }
            if (trueFrame < 0) {
                trueFrame = 0;
            }
            this.func_147499_a(overlays[trueFrame]);
        } else {
            this.func_147499_a(overlays[0]);
        }
        this.model.renderAll();
        this.func_147499_a(seal);
        this.model.renderAll();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }
}

