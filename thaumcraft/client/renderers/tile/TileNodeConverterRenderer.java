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
import thaumcraft.common.tiles.TileNodeConverter;

@SideOnly(value=Side.CLIENT)
public class TileNodeConverterRenderer
extends TileEntitySpecialRenderer {
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)MODEL);
    private static final ResourceLocation MODEL = new ResourceLocation("thaumcraft", "textures/models/node_stabilizer.obj");

    public void renderTileEntityAt(TileNodeConverter tile, double par2, double par4, double par6, float par8) {
        int l;
        int k;
        int j;
        int bright = 20;
        if (tile.func_145831_w() != null) {
            bright = tile.func_145838_q().func_149677_c((IBlockAccess)tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4 + 1.0f), (float)((float)par6 + 0.5f));
        UtilsFX.bindTexture("textures/models/node_converter.png");
        GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        float v = (float)Math.min(50, tile.count) / 137.0f;
        this.model.renderPart("lock");
        if (tile.status == 2) {
            GL11.glColor4f((float)1.0f, (float)0.0f, (float)0.3f, (float)1.0f);
        } else if (tile.status == 1) {
            GL11.glColor4f((float)1.0f, (float)0.6f, (float)0.1f, (float)1.0f);
        } else {
            GL11.glColor4f((float)0.5f, (float)1.0f, (float)0.5f, (float)1.0f);
        }
        if (tile.func_145831_w() != null) {
            float scale = MathHelper.func_76126_a((float)((float)Minecraft.func_71410_x().field_71451_h.field_70173_aa / 3.0f)) * 0.1f + 0.9f;
            j = 50 + (int)(170.0f * (v * 2.5f * scale));
            k = j % 65536;
            l = j / 65536;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
        }
        UtilsFX.bindTexture("textures/models/node_converter_over.png");
        this.model.renderPart("lock");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        for (int a = 0; a < 4; ++a) {
            GL11.glPushMatrix();
            if (tile.func_145831_w() != null) {
                j = bright;
                k = j % 65536;
                l = j / 65536;
                OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
            }
            GL11.glRotatef((float)(90 * a), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)v);
            UtilsFX.bindTexture("textures/models/node_converter.png");
            this.model.renderPart("piston");
            if (tile.status == 2) {
                GL11.glColor4f((float)1.0f, (float)0.0f, (float)0.3f, (float)1.0f);
            } else if (tile.status == 1) {
                GL11.glColor4f((float)1.0f, (float)0.6f, (float)0.1f, (float)1.0f);
            } else {
                GL11.glColor4f((float)0.5f, (float)1.0f, (float)0.5f, (float)1.0f);
            }
            if (tile.func_145831_w() != null) {
                float scale = MathHelper.func_76126_a((float)((float)(Minecraft.func_71410_x().field_71451_h.field_70173_aa + a * 5) / 3.0f)) * 0.1f + 0.9f;
                int j2 = 50 + (int)(170.0f * (v * 2.5f * scale));
                int k2 = j2 % 65536;
                int l2 = j2 / 65536;
                OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k2 / 1.0f), (float)((float)l2 / 1.0f));
            }
            UtilsFX.bindTexture("textures/models/node_converter_over.png");
            this.model.renderPart("piston");
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileNodeConverter)par1TileEntity, par2, par4, par6, par8);
    }
}

