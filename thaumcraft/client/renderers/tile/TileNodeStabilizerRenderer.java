/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.client.model.AdvancedModelLoader
 *  net.minecraftforge.client.model.IModelCustom
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.tiles.TileNodeStabilizer;

@SideOnly(value=Side.CLIENT)
public class TileNodeStabilizerRenderer
extends TileEntitySpecialRenderer {
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)MODEL);
    private static final ResourceLocation MODEL = new ResourceLocation("thaumcraft", "textures/models/node_stabilizer.obj");

    public void renderTileEntityAt(TileNodeStabilizer tile, double par2, double par4, double par6, float par8) {
        int lock = 1;
        int bright = 20;
        if (tile.func_145831_w() != null) {
            if (tile.func_145831_w().func_72805_g(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e) == 10) {
                lock = 2;
            }
            bright = tile.func_145838_q().func_149677_c((IBlockAccess)tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
        } else {
            lock = tile.lock;
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4), (float)((float)par6 + 0.5f));
        GL11.glRotatef((float)90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        UtilsFX.bindTexture("textures/models/node_stabilizer.png");
        this.model.renderPart("lock");
        for (int a = 0; a < 4; ++a) {
            GL11.glPushMatrix();
            if (tile.func_145831_w() != null) {
                int j = bright;
                int k = j % 65536;
                int l = j / 65536;
                OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
            }
            GL11.glRotatef((float)(90 * a), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)((float)tile.count / 100.0f));
            UtilsFX.bindTexture("textures/models/node_stabilizer.png");
            this.model.renderPart("piston");
            if (lock == 2) {
                GL11.glColor4f((float)1.0f, (float)0.2f, (float)0.2f, (float)1.0f);
            }
            if (tile.func_145831_w() != null) {
                float scale = MathHelper.func_76126_a((float)((float)(Minecraft.func_71410_x().field_71451_h.field_70173_aa + a * 5) / 3.0f)) * 0.1f + 0.9f;
                int j = 50 + (int)(170.0f * ((float)tile.count / 37.0f * scale));
                int k = j % 65536;
                int l = j / 65536;
                OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
            }
            UtilsFX.bindTexture("textures/models/node_stabilizer_over.png");
            this.model.renderPart("piston");
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        if (tile.count > 0) {
            GL11.glPushMatrix();
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)1);
            GL11.glDepthMask((boolean)false);
            float alpha = MathHelper.func_76126_a((float)((float)Minecraft.func_71410_x().field_71451_h.field_70173_aa / 8.0f)) * 0.1f + 0.5f;
            UtilsFX.bindTexture("textures/misc/node_bubble.png");
            UtilsFX.renderFacingQuad((double)tile.field_145851_c + 0.5, (double)tile.field_145848_d + 1.5, (double)tile.field_145849_e + 0.5, 0.0f, 0.9f, (float)tile.count / 37.0f * alpha, 1, 0, par8, lock == 1 ? 0xFFFFFF : 0xFF4444);
            GL11.glDepthMask((boolean)true);
            GL11.glDisable((int)3042);
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glPopMatrix();
        }
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileNodeStabilizer)par1TileEntity, par2, par4, par6, par8);
    }
}

