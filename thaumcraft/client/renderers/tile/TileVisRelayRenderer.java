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
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.tiles.TileVisRelay;

@SideOnly(value=Side.CLIENT)
public class TileVisRelayRenderer
extends TileEntitySpecialRenderer {
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)RELAY);
    private static final ResourceLocation RELAY = new ResourceLocation("thaumcraft", "textures/models/vis_relay.obj");

    public void renderTileEntityAt(TileVisRelay tile, double par2, double par4, double par6, float par8) {
        short facing = 1;
        if (tile.func_145831_w() != null) {
            facing = tile.orientation;
        }
        int ticks = Minecraft.func_71410_x().field_71451_h.field_70173_aa;
        GL11.glPushMatrix();
        this.translateFromOrientation(par2, par4, par6, facing);
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)45.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPushMatrix();
        UtilsFX.bindTexture("textures/models/vis_relay.png");
        GL11.glPushMatrix();
        GL11.glScaled((double)0.75, (double)0.75, (double)0.75);
        GL11.glTranslated((double)0.0, (double)0.0, (double)-0.16);
        this.model.renderPart("RingBase");
        GL11.glPopMatrix();
        this.model.renderPart("RingFloat");
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        if (tile.color >= 0) {
            Color c = new Color(TileVisRelay.colors[tile.color]);
            GL11.glColor3f((float)((float)c.getRed() / 200.0f), (float)((float)c.getGreen() / 200.0f), (float)((float)c.getBlue() / 200.0f));
        }
        float scale = MathHelper.func_76126_a((float)(((float)ticks + par8) / 2.0f)) * 0.05f + 0.95f;
        int j = (VisNetHandler.isNodeValid(tile.getParent()) ? 50 : 0) + (int)(150.0f * scale);
        int k = j % 65536;
        int l = j / 65536;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
        this.model.renderPart("Crystal");
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
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileVisRelay)par1TileEntity, par2, par4, par6, par8);
    }
}

