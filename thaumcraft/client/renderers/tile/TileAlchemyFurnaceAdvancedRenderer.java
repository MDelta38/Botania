/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.model.AdvancedModelLoader
 *  net.minecraftforge.client.model.IModelCustom
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileAlchemyFurnaceAdvanced;

@SideOnly(value=Side.CLIENT)
public class TileAlchemyFurnaceAdvancedRenderer
extends TileEntitySpecialRenderer {
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)FURNACE);
    private static final ResourceLocation FURNACE = new ResourceLocation("thaumcraft", "textures/models/adv_alch_furnace.obj");

    public void renderTileEntityAt(TileAlchemyFurnaceAdvanced tile, double par2, double par4, double par6, float par8) {
        int a;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4), (float)((float)par6 + 0.5f));
        GL11.glRotatef((float)90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (tile.heat <= 100) {
            UtilsFX.bindTexture("textures/models/alch_furnace.png");
        } else {
            UtilsFX.bindTexture("textures/models/alch_furnace_on.png");
        }
        this.model.renderPart("Base");
        if (tile.vis <= 0) {
            UtilsFX.bindTexture("textures/models/alch_furnace_tank.png");
        } else {
            UtilsFX.bindTexture("textures/models/alch_furnace_tank_on.png");
        }
        for (a = 0; a < 4; ++a) {
            GL11.glPushMatrix();
            GL11.glRotatef((float)(90 * a), (float)0.0f, (float)0.0f, (float)1.0f);
            this.model.renderPart("Tank");
            GL11.glPopMatrix();
        }
        if (tile.vis > 0) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.5f, (float)-0.5f, (float)1.1f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            this.renderQuadCenteredFromIcon(ConfigBlocks.FLUXGOO.getIcon(), 190, 0.0f);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            float f = 1.0f - (float)tile.vis / (float)tile.maxVis;
            for (int a2 = 0; a2 < 4; ++a2) {
                GL11.glPushMatrix();
                GL11.glPushMatrix();
                GL11.glRotatef((float)(90 * a2), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)0.85f, (float)-1.8f, (float)-1.4f);
                GL11.glScaled((double)0.3, (double)0.6, (double)1.0);
                this.renderQuadCenteredFromIcon(tile.func_145838_q().func_149691_a(0, 0), 150, 0.0f);
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.01f);
                this.renderQuadCenteredFromIcon(ConfigBlocks.FLUXGOO.getIcon(), 190, f);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glRotatef((float)(90 * a2), (float)0.0f, (float)0.0f, (float)-1.0f);
                GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)1.15f, (float)1.8f, (float)-1.4f);
                GL11.glScaled((double)-0.3, (double)-0.6, (double)-1.0);
                this.renderQuadCenteredFromIcon(tile.func_145838_q().func_149691_a(0, 0), 150, 0.0f);
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.01f);
                this.renderQuadCenteredFromIcon(ConfigBlocks.FLUXGOO.getIcon(), 190, f);
                GL11.glPopMatrix();
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
        }
        if (tile.heat > 100) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)1.0f);
            for (a = 0; a < 4; ++a) {
                GL11.glPushMatrix();
                GL11.glRotatef((float)(90 * a), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)135.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.5f, (float)0.0f, (float)-1.0f);
                GL11.glPushMatrix();
                this.renderQuadCenteredFromIcon(Blocks.field_150480_ab.func_149691_a(0, 0), 220, 1.0f - Math.min(1.0f, (float)tile.heat / (float)tile.maxPower));
                GL11.glPopMatrix();
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.05f);
                this.renderQuadCenteredFromIcon(tile.func_145838_q().func_149691_a(0, 0), 150, 0.0f);
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    public void renderQuadCenteredFromIcon(IIcon icon, int brightness, float width) {
        Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
        RenderHelper.func_74518_a();
        Tessellator tessellator = Tessellator.field_78398_a;
        float f1 = icon.func_94212_f();
        float f2 = icon.func_94206_g();
        float f3 = icon.func_94209_e();
        float f4 = icon.func_94210_h();
        GL11.glEnable((int)32826);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        tessellator.func_78382_b();
        tessellator.func_78380_c(brightness);
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        tessellator.func_78374_a(0.0, 1.0, 0.0, (double)f1, (double)f4);
        tessellator.func_78374_a(1.0, 1.0, 0.0, (double)f3, (double)f4);
        tessellator.func_78374_a(1.0, (double)width, 0.0, (double)f3, (double)f2);
        tessellator.func_78374_a(0.0, (double)width, 0.0, (double)f1, (double)f2);
        tessellator.func_78381_a();
        GL11.glDisable((int)3042);
        GL11.glDisable((int)32826);
        RenderHelper.func_74519_b();
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileAlchemyFurnaceAdvanced)par1TileEntity, par2, par4, par6, par8);
    }
}

