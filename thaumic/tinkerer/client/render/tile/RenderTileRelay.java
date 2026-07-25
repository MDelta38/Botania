/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumic.tinkerer.client.render.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumic.tinkerer.client.core.helper.ClientHelper;
import thaumic.tinkerer.client.model.ModelRelay;

public class RenderTileRelay
extends TileEntitySpecialRenderer {
    private static final ResourceLocation texture = new ResourceLocation("ttinkerer:textures/model/relay.png");
    ModelRelay model = new ModelRelay();

    public void func_147500_a(TileEntity tileentity, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
        ClientHelper.minecraft().field_71446_o.func_110577_a(texture);
        GL11.glTranslatef((float)0.5f, (float)1.5f, (float)0.5f);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        this.model.render();
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
    }
}

