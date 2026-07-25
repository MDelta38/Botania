/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.common.tiles.TileBellows
 */
package witchinggadgets.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.tiles.TileBellows;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.blocks.tiles.TileEntityEssentiaPump;

public class TileRenderEssentiaPump
extends TileEntitySpecialRenderer {
    static TileBellows bellow = new TileBellows();

    public void func_147500_a(TileEntity tileentity, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
        Tessellator tes = Tessellator.field_78398_a;
        ClientUtilities.bindTexture("thaumcraft:textures/models/alembic.png");
        TileEntityEssentiaPump tile = (TileEntityEssentiaPump)tileentity;
        switch (tile.facing) {
            case NORTH: {
                break;
            }
            case SOUTH: {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-1.0f, (float)0.0f, (float)-1.0f);
                break;
            }
            case EAST: {
                GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-1.0f);
                break;
            }
            case WEST: {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-1.0f, (float)0.0f, (float)0.0f);
                break;
            }
        }
        tes.func_78371_b(4);
        tes.func_78375_b(0.0f, 0.0f, 1.0f);
        tes.func_78374_a(0.375, 0.75, 0.25, 0.921875, 0.65625);
        tes.func_78374_a(0.375, 0.25, 0.25, 0.921875, 0.34375);
        tes.func_78374_a(0.25, 0.5, 0.25, 1.0, 0.5);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78375_b(0.0f, 0.0f, 1.0f);
        tes.func_78374_a(0.375, 0.75, 0.25, 0.921875, 0.34375);
        tes.func_78374_a(0.625, 0.75, 0.25, 0.703125, 0.34375);
        tes.func_78374_a(0.625, 0.25, 0.25, 0.703125, 0.65625);
        tes.func_78374_a(0.375, 0.25, 0.25, 0.921875, 0.65625);
        tes.func_78381_a();
        tes.func_78371_b(4);
        tes.func_78375_b(0.0f, 0.0f, 1.0f);
        tes.func_78374_a(0.625, 0.75, 0.25, 0.703125, 0.65625);
        tes.func_78374_a(0.75, 0.5, 0.25, 0.625, 0.5);
        tes.func_78374_a(0.625, 0.25, 0.25, 0.703125, 0.34375);
        tes.func_78381_a();
        tes.func_78371_b(4);
        tes.func_78375_b(0.0f, 0.0f, 1.0f);
        tes.func_78374_a(0.0, 0.5, 0.75, 1.0, 0.5);
        tes.func_78374_a(0.25, 0.0, 0.75, 0.921875, 0.34375);
        tes.func_78374_a(0.25, 1.0, 0.75, 0.921875, 0.65625);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78375_b(0.0f, 0.0f, 1.0f);
        tes.func_78374_a(0.75, 1.0, 0.75, 0.703125, 0.34375);
        tes.func_78374_a(0.25, 1.0, 0.75, 0.921875, 0.34375);
        tes.func_78374_a(0.25, 0.0, 0.75, 0.921875, 0.65625);
        tes.func_78374_a(0.75, 0.0, 0.75, 0.703125, 0.65625);
        tes.func_78381_a();
        tes.func_78371_b(4);
        tes.func_78375_b(0.0f, 0.0f, 1.0f);
        tes.func_78374_a(0.75, 0.0, 0.75, 0.703125, 0.34375);
        tes.func_78374_a(1.0, 0.5, 0.75, 0.625, 0.5);
        tes.func_78374_a(0.75, 1.0, 0.75, 0.703125, 0.65625);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78375_b(0.0f, 1.0f, 0.0f);
        tes.func_78374_a(0.25, 1.0, 0.75, 0.28125, 0.71875);
        tes.func_78374_a(0.75, 1.0, 0.75, 0.28125, 0.375);
        tes.func_78374_a(0.625, 0.75, 0.25, 0.46875, 0.46875);
        tes.func_78374_a(0.375, 0.75, 0.25, 0.46875, 0.625);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78375_b(0.0f, -1.0f, 0.0f);
        tes.func_78374_a(0.75, 0.0, 0.75, 0.28125, 0.375);
        tes.func_78374_a(0.25, 0.0, 0.75, 0.28125, 0.71875);
        tes.func_78374_a(0.375, 0.25, 0.25, 0.46875, 0.625);
        tes.func_78374_a(0.625, 0.25, 0.25, 0.46875, 0.46875);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78375_b(-1.0f, 1.0f, 0.0f);
        tes.func_78374_a(0.0, 0.5, 0.75, 0.28125, 0.71875);
        tes.func_78374_a(0.25, 1.0, 0.75, 0.28125, 0.375);
        tes.func_78374_a(0.375, 0.75, 0.25, 0.46875, 0.46875);
        tes.func_78374_a(0.25, 0.5, 0.25, 0.46875, 0.625);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78375_b(-1.0f, -1.0f, 0.0f);
        tes.func_78374_a(0.25, 0.0, 0.75, 0.28125, 0.375);
        tes.func_78374_a(0.0, 0.5, 0.75, 0.28125, 0.71875);
        tes.func_78374_a(0.25, 0.5, 0.25, 0.46875, 0.625);
        tes.func_78374_a(0.375, 0.25, 0.25, 0.46875, 0.46875);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78375_b(1.0f, 1.0f, 0.0f);
        tes.func_78374_a(0.75, 1.0, 0.75, 0.28125, 0.71875);
        tes.func_78374_a(1.0, 0.5, 0.75, 0.28125, 0.375);
        tes.func_78374_a(0.75, 0.5, 0.25, 0.46875, 0.46875);
        tes.func_78374_a(0.625, 0.75, 0.25, 0.46875, 0.625);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78375_b(1.0f, -1.0f, 0.0f);
        tes.func_78374_a(1.0, 0.5, 0.75, 0.28125, 0.375);
        tes.func_78374_a(0.75, 0.0, 0.75, 0.28125, 0.71875);
        tes.func_78374_a(0.625, 0.25, 0.25, 0.46875, 0.625);
        tes.func_78374_a(0.75, 0.5, 0.25, 0.46875, 0.46875);
        tes.func_78381_a();
        ClientUtilities.bindTexture("thaumcraft:textures/blocks/alchemyblock.png");
        tes.func_78382_b();
        tes.func_78375_b(1.0f, 1.0f, 0.0f);
        tes.func_78374_a(0.5, 0.75, 0.25, 0.0, 1.0);
        tes.func_78374_a(0.75, 0.5, 0.25, 1.0, 1.0);
        tes.func_78374_a(0.75, 0.5, 0.0, 1.0, 0.0);
        tes.func_78374_a(0.5, 0.75, 0.0, 0.0, 0.0);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78375_b(-1.0f, -1.0f, 0.0f);
        tes.func_78374_a(0.5, 0.25, 0.25, 1.0, 1.0);
        tes.func_78374_a(0.25, 0.5, 0.25, 0.0, 1.0);
        tes.func_78374_a(0.25, 0.5, 0.0, 0.0, 0.0);
        tes.func_78374_a(0.5, 0.25, 0.0, 1.0, 0.0);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78375_b(0.0f, 0.0f, -1.0f);
        tes.func_78374_a(0.5, 0.75, 0.0, 1.0, 0.0);
        tes.func_78374_a(0.75, 0.5, 0.0, 0.0, 0.0);
        tes.func_78374_a(0.5, 0.25, 0.0, 0.0, 1.0);
        tes.func_78374_a(0.25, 0.5, 0.0, 1.0, 1.0);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78375_b(-1.0f, 1.0f, 0.0f);
        tes.func_78374_a(0.25, 0.5, 0.0, 0.0, 1.0);
        tes.func_78374_a(0.25, 0.5, 0.25, 1.0, 1.0);
        tes.func_78374_a(0.5, 0.75, 0.25, 1.0, 0.0);
        tes.func_78374_a(0.5, 0.75, 0.0, 0.0, 0.0);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78375_b(1.0f, -1.0f, 0.0f);
        tes.func_78374_a(0.5, 0.25, 0.25, 0.0, 1.0);
        tes.func_78374_a(0.5, 0.25, 0.0, 1.0, 1.0);
        tes.func_78374_a(0.75, 0.5, 0.0, 1.0, 0.0);
        tes.func_78374_a(0.75, 0.5, 0.25, 0.0, 0.0);
        tes.func_78381_a();
        try {
            GL11.glScaled((double)0.375, (double)0.375, (double)0.375);
            GL11.glTranslated((double)0.625, (double)-0.0625, (double)-0.125);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-1.0625f);
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)bellow, 0.0, 0.0, 0.0, 0.0f);
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)1.0625f);
            GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)0.9375f, (float)0.875f);
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)bellow, 0.0, 0.0, 0.0, 0.0f);
            GL11.glTranslatef((float)0.0f, (float)-0.9375f, (float)-0.875f);
            GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)-1.0f, (float)1.8125f);
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)bellow, 0.0, 0.0, 0.0, 0.0f);
            GL11.glTranslatef((float)0.0f, (float)1.0f, (float)-1.8125f);
            GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)-1.9375f, (float)-0.125f);
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)bellow, 0.0, 0.0, 0.0, 0.0f);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        GL11.glPopMatrix();
    }
}

