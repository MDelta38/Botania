/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package witchinggadgets.client.render;

import java.util.List;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.blocks.tiles.TileEntityWallMirror;

public class TileRenderWallMirror
extends TileEntitySpecialRenderer {
    static int[] n = new int[]{-1, -1, -1};
    static int[] g01 = new int[]{243, 184, 56};
    static int[] g02 = new int[]{99, 69, 6};
    static int[] g03 = new int[]{220, 155, 14};
    static int[] g04 = new int[]{156, 109, 9};
    static int[] g05 = new int[]{132, 92, 8};
    static int[] g06 = new int[]{241, 170, 14};
    static int[] g07 = new int[]{179, 126, 11};
    static int[] g08 = new int[]{241, 176, 36};
    static int[] g09 = new int[]{135, 94, 7};
    static int[] g10 = new int[]{203, 143, 13};
    static int[] g11 = new int[]{190, 134, 12};
    static int[] g12 = new int[]{242, 181, 48};
    static int[] w = new int[]{208, 224, 248};
    public static int[][][] shape = new int[][][]{new int[][]{n, n, n, n, n, g11, g11, g10}, new int[][]{n, g04, g09, n, g07, g01, g10, n}, new int[][]{g10, w, w, g05, g07, n, n, n}, new int[][]{g06, w, g01, g03, g01, n, n, n}, new int[][]{n, g04, g04, g11, n, n, n, n}, new int[][]{n, n, g05, g11, n, n, n, n}, new int[][]{n, n, g11, g04, n, n, n, n}, new int[][]{n, n, g04, g11, n, n, n, n}, new int[][]{n, n, g03, g04, n, n, n, n}, new int[][]{n, n, g03, g01, n, n, n, n}, new int[][]{n, n, g04, g03, n, n, n, n}, new int[][]{n, g03, g04, n, n, n, n, n}, new int[][]{n, g03, g10, n, n, n, n, n}, new int[][]{n, g01, g10, n, n, n, n, n}, new int[][]{n, g08, g12, n, n, n, n, n}, new int[][]{g08, g08, g12, n, n, n, n, n}};

    public void renderTileEntityAt(TileEntityWallMirror tile, double x, double y, double z, float f) {
        if (tile.isDummy) {
            return;
        }
        double animation = tile.animation;
        double activationAnimation = tile.activationAnimation;
        boolean active = tile.isActive;
        boolean activating = tile.temp_isActivating;
        boolean deactivating = tile.temp_isDeActivating;
        GL11.glPushMatrix();
        Tessellator tes = Tessellator.field_78398_a;
        GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
        switch (tile.facing) {
            case 2: {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-1.0f, (float)0.0f, (float)0.0f);
                break;
            }
            case 3: {
                GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-1.0f);
                break;
            }
            case 4: {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-1.0f, (float)0.0f, (float)-1.0f);
            }
        }
        double glassUmin = 0.0;
        double glassUmax = 0.03125;
        double glassVmin = 0.0;
        double glassVmax = 0.5;
        if (active && !deactivating) {
            glassUmin = animation * 0.03125;
            glassUmax = (animation + 1.0) * 0.03125;
        }
        if (activating || deactivating) {
            glassUmin = activationAnimation * 0.03125;
            glassUmax = (activationAnimation + 1.0) * 0.03125;
        }
        ClientUtilities.bindTexture("witchinggadgets:textures/models/glass.png");
        if (activating || deactivating || !active) {
            tes.func_78382_b();
            tes.func_78374_a(5.0E-4, 0.0, 0.0, glassUmax, glassVmax);
            tes.func_78374_a(5.0E-4, 2.0, 0.0, glassUmax, glassVmin);
            tes.func_78374_a(5.0E-4, 2.0, 1.0, glassUmin, glassVmin);
            tes.func_78374_a(5.0E-4, 0.0, 1.0, glassUmin, glassVmax);
            tes.func_78381_a();
        }
        ClientUtilities.bindTexture("witchinggadgets:textures/blocks/white.png");
        for (int i = 0; i < shape.length; ++i) {
            for (int j = 0; j < shape[i].length; ++j) {
                if (shape[i][j][0] == -1) continue;
                double r = (double)shape[i][j][0] / 256.0;
                double g = (double)shape[i][j][1] / 256.0;
                double b = (double)shape[i][j][2] / 256.0;
                GL11.glColor3d((double)r, (double)g, (double)b);
                ClientUtilities.renderPixelBlock(tes, 0.0, i, j, 0.0625, 0.0, 0.0, 1.0, 1.0);
                ClientUtilities.renderPixelBlock(tes, 0.0, i, 15 - j, 0.0625, 0.0, 0.0, 1.0, 1.0);
                ClientUtilities.renderPixelBlock(tes, 0.0, 15 - i + 16, j, 0.0625, 0.0, 0.0, 1.0, 1.0);
                ClientUtilities.renderPixelBlock(tes, 0.0, 15 - i + 16, 15 - j, 0.0625, 0.0, 0.0, 1.0, 1.0);
                GL11.glColor3d((double)1.0, (double)1.0, (double)1.0);
            }
        }
        List<EntityPlayer> players = tile.getMirroredPlayers();
        for (EntityPlayer pp : players) {
            double distance = Math.sqrt(((double)tile.field_145851_c - pp.field_70165_t) * ((double)tile.field_145851_c - pp.field_70165_t) + ((double)tile.field_145849_e - pp.field_70161_v) * ((double)tile.field_145849_e - pp.field_70161_v));
            float distanceScaling = 0.8125f * (float)(9.0 - distance) / 9.0f;
            float hOffset = (float)(tile.facing == 2 || tile.facing == 3 ? pp.field_70165_t - ((double)tile.field_145851_c + 0.5) : pp.field_70161_v - ((double)tile.field_145849_e + 0.5));
            float vOffset = (float)(pp.field_70163_u - (double)pp.field_70129_M - (double)tile.field_145848_d);
            this.drawPlayer((EntityLivingBase)pp, distanceScaling, tile.facing, hOffset /= 4.0f, vOffset);
        }
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float f) {
        this.renderTileEntityAt((TileEntityWallMirror)tileentity, d0, d1, d2, f);
    }

    private void drawPlayer(EntityLivingBase player, float scale, int facing, float hOffset, float vOffset) {
        GL11.glEnable((int)2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.01875f, (float)0.875f, (float)0.5f);
        int cuttingAngle = facing == 3 || facing == 4 ? (hOffset > 0.0f ? 1 : -1) : (hOffset > 0.0f ? -1 : 1);
        GL11.glRotatef((float)((float)cuttingAngle * 2.875f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glScalef((float)-0.0048f, (float)1.0f, (float)1.0f);
        GL11.glScalef((float)1.0f, (float)scale, (float)scale);
        GL11.glTranslatef((float)0.5f, (float)0.0f, (float)0.0f);
        switch (facing) {
            case 2: {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)-1.0f, (float)0.0f);
                break;
            }
            case 3: {
                GL11.glRotatef((float)270.0f, (float)0.0f, (float)-1.0f, (float)0.0f);
                break;
            }
            case 4: {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)-1.0f, (float)0.0f);
            }
        }
        if (!player.equals((Object)RenderManager.field_78727_a.field_78734_h)) {
            GL11.glTranslatef((float)0.0f, (float)-0.75f, (float)0.0f);
        }
        GL11.glTranslatef((float)0.0f, (float)(player.field_70129_M / 2.0f), (float)0.0f);
        GL11.glTranslatef((float)(facing == 2 || facing == 3 ? hOffset : 0.0f), (float)vOffset, (float)(facing == 2 || facing == 3 ? 0.0f : hOffset));
        ItemStack cc = player.func_70694_bm();
        player.func_70062_b(0, null);
        RenderManager.field_78727_a.func_147940_a((Entity)player, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        player.func_70062_b(0, cc);
        GL11.glPopMatrix();
    }
}

