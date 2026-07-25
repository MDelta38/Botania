/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.Aspect
 */
package witchinggadgets.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import witchinggadgets.client.ClientProxy;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.blocks.tiles.TileEntityTerraformer;

public class TileRenderTerraformer
extends TileEntitySpecialRenderer {
    public void func_147500_a(TileEntity tile, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y), (float)((float)z + 0.5f));
        if (ClientProxy.terraformerModel != null) {
            Aspect a;
            ClientUtilities.bindTexture("witchinggadgets:textures/models/terraformer.png");
            ClientProxy.terraformerModel.renderPart("main_01");
            if (tile != null && tile.func_145831_w() != null && (a = ((TileEntityTerraformer)tile).getSuctionType(null)) != null) {
                GL11.glColor3f((float)((float)(a.getColor() >> 16 & 0xFF) / 255.0f), (float)((float)(a.getColor() >> 8 & 0xFF) / 255.0f), (float)((float)(a.getColor() & 0xFF) / 255.0f));
            }
            ClientProxy.terraformerModel.renderPart("crystals_02");
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        }
        GL11.glPopMatrix();
    }
}

