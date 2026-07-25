/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package witchinggadgets.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.client.render.TileRenderWallMirror;

public class ItemRenderWallMirror
implements IItemRenderer {
    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)2896);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        switch (type) {
            case ENTITY: {
                float scale = 1.0f;
                GL11.glScalef((float)scale, (float)scale, (float)scale);
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.5f);
                break;
            }
            case EQUIPPED: {
                float scale = 0.8f;
                GL11.glRotatef((float)5.0f, (float)0.0f, (float)-1.0f, (float)0.0f);
                GL11.glScalef((float)scale, (float)scale, (float)scale);
                GL11.glTranslatef((float)-0.8f, (float)-0.25f, (float)0.2f);
                break;
            }
            case INVENTORY: {
                float scale = 0.9f;
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)scale, (float)scale, (float)scale);
                GL11.glTranslatef((float)0.0f, (float)-1.0f, (float)-0.5f);
                break;
            }
            case EQUIPPED_FIRST_PERSON: {
                float scale = 2.0f;
                GL11.glScalef((float)scale, (float)scale, (float)scale);
                GL11.glRotatef((float)60.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)30.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)-2.0f, (float)-1.0f, (float)0.5f);
                break;
            }
        }
        Tessellator tes = Tessellator.field_78398_a;
        double glassUmin = 0.0;
        double glassUmax = 0.03125;
        double glassVmin = 0.0;
        double glassVmax = 0.5;
        ClientUtilities.bindTexture("witchinggadgets:textures/models/glass.png");
        tes.func_78382_b();
        tes.func_78374_a(0.03125, 0.0, 0.0, glassUmax, glassVmax);
        tes.func_78374_a(0.03125, 2.0, 0.0, glassUmax, glassVmin);
        tes.func_78374_a(0.03125, 2.0, 1.0, glassUmin, glassVmin);
        tes.func_78374_a(0.03125, 0.0, 1.0, glassUmin, glassVmax);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78374_a(0.03125, 0.0, 0.0, glassUmin, glassVmax);
        tes.func_78374_a(0.03125, 0.0, 1.0, glassUmax, glassVmax);
        tes.func_78374_a(0.03125, 2.0, 1.0, glassUmax, glassVmin);
        tes.func_78374_a(0.03125, 2.0, 0.0, glassUmin, glassVmin);
        tes.func_78381_a();
        ClientUtilities.bindTexture("witchinggadgets:textures/blocks/white.png");
        int[][][] shape = TileRenderWallMirror.shape;
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
        GL11.glEnable((int)2896);
        GL11.glPopMatrix();
    }
}

