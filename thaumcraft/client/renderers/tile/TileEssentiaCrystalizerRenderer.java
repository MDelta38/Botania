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
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.tiles.TileEssentiaCrystalizer;

@SideOnly(value=Side.CLIENT)
public class TileEssentiaCrystalizerRenderer
extends TileEntitySpecialRenderer {
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)RELAY);
    private IModelCustom model2 = AdvancedModelLoader.loadModel((ResourceLocation)CRYSTAL);
    private static final ResourceLocation RELAY = new ResourceLocation("thaumcraft", "textures/models/crystalizer.obj");
    private static final ResourceLocation CRYSTAL = new ResourceLocation("thaumcraft", "textures/models/vis_relay.obj");

    public void renderTileEntityAt(TileEssentiaCrystalizer tile, double par2, double par4, double par6, float par8) {
        int facing = tile.facing.ordinal();
        int ticks = Minecraft.func_71410_x().field_71451_h.field_70173_aa;
        GL11.glPushMatrix();
        this.translateFromOrientation(par2, par4, par6, facing);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPushMatrix();
        UtilsFX.bindTexture("textures/models/crystalizer.png");
        this.model.renderAll();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        UtilsFX.bindTexture("textures/models/vis_relay.png");
        GL11.glColor3f((float)tile.cr, (float)tile.cg, (float)tile.cb);
        for (int q = 0; q < 4; ++q) {
            GL11.glPushMatrix();
            GL11.glScaled((double)0.75, (double)0.75, (double)0.75);
            float glow = MathHelper.func_76126_a((float)(((float)ticks + par8 + (float)(q * 10)) / 2.0f)) * 0.05f + 0.95f;
            int j = 50 + (int)(150.0f * glow);
            int k = j % 65536;
            int l = j / 65536;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
            GL11.glRotatef((float)(90 * q), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslated((double)0.34, (double)0.0, (double)1.2125);
            GL11.glRotatef((float)(tile.spin + tile.spinInc * par8), (float)0.0f, (float)0.0f, (float)1.0f);
            this.model2.renderPart("Crystal");
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    private void translateFromOrientation(double x, double y, double z, int orientation) {
        GL11.glTranslated((double)(x + 0.5), (double)(y + 0.5), (double)(z + 0.5));
        if (orientation == 0) {
            GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        } else if (orientation == 1) {
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        } else if (orientation != 2) {
            if (orientation == 3) {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else if (orientation == 4) {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else if (orientation == 5) {
                GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            }
        }
        GL11.glTranslated((double)0.0, (double)0.0, (double)-0.5);
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileEssentiaCrystalizer)par1TileEntity, par2, par4, par6, par8);
    }
}

