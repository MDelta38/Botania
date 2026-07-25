/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.tile.TileNodeRenderer;
import thaumcraft.common.tiles.TileNodeEnergized;

@SideOnly(value=Side.CLIENT)
public class TileNodeEnergizedRenderer
extends TileEntitySpecialRenderer {
    static String tx1 = "textures/items/lightningringv.png";

    public void func_147500_a(TileEntity tile, double x, double y, double z, float partialTicks) {
        EntityLivingBase viewer = Minecraft.func_71410_x().field_71451_h;
        TileNodeRenderer.renderNode(viewer, 64.0, true, false, 1.0f, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, partialTicks, ((TileNodeEnergized)tile).getAuraBase(), ((TileNodeEnergized)tile).getNodeType(), ((TileNodeEnergized)tile).getNodeModifier());
        GL11.glPushMatrix();
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        long nt = System.nanoTime();
        UtilsFX.bindTexture(tx1);
        int frames = UtilsFX.getTextureAnimationSize(tx1);
        int i = (int)(((double)(nt / 40000000L) + x) % (double)frames);
        UtilsFX.renderFacingQuad((double)tile.field_145851_c + 0.5, (double)tile.field_145848_d + 0.5, (double)tile.field_145849_e + 0.5, 0.0f, 0.33f, 0.9f, frames, i, partialTicks, 0xFFFFFF);
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
    }
}

