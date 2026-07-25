/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
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
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.tiles.TileCrystal;

@SideOnly(value=Side.CLIENT)
public class TileEldritchCrystalRenderer
extends TileEntitySpecialRenderer {
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)CRYSTAL);
    private static final ResourceLocation CRYSTAL = new ResourceLocation("thaumcraft", "textures/models/vcrystal.obj");

    public void renderTileEntityAt(TileCrystal tile, double par2, double par4, double par6, float par8) {
        GL11.glPushMatrix();
        this.translateFromOrientation(par2, par4, par6, tile.orientation, (tile.func_145831_w() == null ? 0 : ((Object)((Object)tile)).hashCode()) % 4);
        EntityClientPlayerMP p = Minecraft.func_71410_x().field_71439_g;
        UtilsFX.bindTexture("textures/blocks/crust.png");
        this.model.renderPart("Base");
        float shade = MathHelper.func_76126_a((float)((float)p.field_70173_aa / 6.0f)) * 0.075f + 0.925f;
        int var19 = (int)(210.0f * shade);
        int var20 = var19 % 65536;
        int var21 = var19 / 65536;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)var20 / 1.0f), (float)((float)var21 / 1.0f));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.7f);
        UtilsFX.bindTexture("textures/models/vcrystal.png");
        this.model.renderPart("Crystal");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    private void translateFromOrientation(double x, double y, double z, int orientation, int r) {
        GL11.glTranslated((double)(x + 0.5), (double)(y + 0.5), (double)(z + 0.5));
        if (orientation == 0) {
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        } else if (orientation == 1) {
            GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        } else if (orientation == 2) {
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        } else if (orientation != 3) {
            if (orientation == 4) {
                GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else if (orientation == 5) {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            }
        }
        GL11.glTranslated((double)0.0, (double)0.0, (double)-0.5);
        GL11.glRotatef((float)(90.0f * (float)r), (float)0.0f, (float)0.0f, (float)1.0f);
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileCrystal)par1TileEntity, par2, par4, par6, par8);
    }
}

