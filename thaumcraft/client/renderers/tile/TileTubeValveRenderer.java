/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelTubeValve;
import thaumcraft.common.blocks.BlockTube;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileTubeValve;

public class TileTubeValveRenderer
extends TileEntitySpecialRenderer {
    private ModelTubeValve model = new ModelTubeValve();

    public void renderEntityAt(TileTubeValve valve, double x, double y, double z, float fq) {
        UtilsFX.bindTexture("textures/models/valve.png");
        GL11.glPushMatrix();
        GL11.glTranslated((double)(x + 0.5), (double)(y + 0.5), (double)(z + 0.5));
        if (valve.facing.offsetY == 0) {
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        } else {
            GL11.glRotatef((float)90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)90.0f, (float)valve.facing.offsetY, (float)0.0f, (float)0.0f);
        }
        GL11.glRotatef((float)90.0f, (float)valve.facing.offsetX, (float)valve.facing.offsetY, (float)valve.facing.offsetZ);
        GL11.glRotated((double)((double)(-valve.rotation) * 1.5), (double)0.0, (double)1.0, (double)0.0);
        GL11.glTranslated((double)0.0, (double)(-(valve.rotation / 360.0f) * 0.12f), (double)0.0);
        GL11.glPushMatrix();
        this.model.render();
        GL11.glPopMatrix();
        this.renderValve();
        GL11.glPopMatrix();
    }

    void renderValve() {
        GL11.glPushMatrix();
        GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.25f, (float)-0.25f, (float)-0.25f);
        GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
        Tessellator tessellator = Tessellator.field_78398_a;
        IIcon icon = ((BlockTube)ConfigBlocks.blockTube).iconValve;
        float f1 = icon.func_94212_f();
        float f2 = icon.func_94206_g();
        float f3 = icon.func_94209_e();
        float f4 = icon.func_94210_h();
        this.field_147501_a.field_147553_e.func_110577_a(TextureMap.field_110575_b);
        ItemRenderer.func_78439_a((Tessellator)tessellator, (float)f1, (float)f2, (float)f3, (float)f4, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.1f);
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f) {
        this.renderEntityAt((TileTubeValve)tileentity, d, d1, d2, f);
    }
}

