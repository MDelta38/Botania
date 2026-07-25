/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.client.model.AdvancedModelLoader
 *  net.minecraftforge.client.model.IModelCustom
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.blocks.BlockJar;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileEssentiaReservoir;

@SideOnly(value=Side.CLIENT)
public class TileEssentiaReservoirRenderer
extends TileEntitySpecialRenderer {
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)RELAY);
    private static final ResourceLocation RELAY = new ResourceLocation("thaumcraft", "textures/models/reservoir.obj");

    public void renderTileEntityAt(TileEssentiaReservoir tile, double par2, double par4, double par6, float par8) {
        int facing = tile.facing.ordinal();
        GL11.glPushMatrix();
        this.translateFromOrientation(par2, par4, par6, facing);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        UtilsFX.bindTexture("textures/models/reservoir.png");
        this.model.renderAll();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated((double)par2, (double)(par4 - 0.5), (double)par6);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.renderLiquid(tile, par2, par4, par6, par8);
        GL11.glPopMatrix();
    }

    public void renderLiquid(TileEssentiaReservoir te, double x, double y, double z, float f) {
        if (this.field_147501_a.field_147553_e == null || te.displayAspect == null || te.essentia.visSize() == 0) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        World world = te.func_145831_w();
        RenderBlocks renderBlocks = new RenderBlocks();
        GL11.glDisable((int)2896);
        float level = (float)te.essentia.visSize() / (float)te.maxAmount;
        Tessellator t = Tessellator.field_78398_a;
        renderBlocks.func_147782_a((double)BlockRenderer.W3, (double)BlockRenderer.W3, (double)BlockRenderer.W3, (double)BlockRenderer.W13, (double)(BlockRenderer.W3 + BlockRenderer.W10 * level), (double)BlockRenderer.W13);
        t.func_78382_b();
        t.func_78369_a(te.cr, te.cg, te.cb, 0.9f);
        int bright = 200;
        t.func_78380_c(200);
        IIcon icon = ((BlockJar)ConfigBlocks.blockJar).iconLiquid;
        this.field_147501_a.field_147553_e.func_110577_a(TextureMap.field_110575_b);
        renderBlocks.func_147768_a(ConfigBlocks.blockEssentiaReservoir, 0.0, 0.5, 0.0, icon);
        renderBlocks.func_147806_b(ConfigBlocks.blockEssentiaReservoir, 0.0, 0.5, 0.0, icon);
        renderBlocks.func_147761_c(ConfigBlocks.blockEssentiaReservoir, 0.0, 0.5, 0.0, icon);
        renderBlocks.func_147734_d(ConfigBlocks.blockEssentiaReservoir, 0.0, 0.5, 0.0, icon);
        renderBlocks.func_147798_e(ConfigBlocks.blockEssentiaReservoir, 0.0, 0.5, 0.0, icon);
        renderBlocks.func_147764_f(ConfigBlocks.blockEssentiaReservoir, 0.0, 0.5, 0.0, icon);
        t.func_78381_a();
        GL11.glEnable((int)2896);
        GL11.glDisable((int)3042);
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
        this.renderTileEntityAt((TileEssentiaReservoir)par1TileEntity, par2, par4, par6, par8);
    }
}

