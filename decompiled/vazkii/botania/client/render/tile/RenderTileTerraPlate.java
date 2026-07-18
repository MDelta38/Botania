/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.common.block.mana.BlockTerraPlate;
import vazkii.botania.common.block.tile.TileTerraPlate;

public class RenderTileTerraPlate
extends TileEntitySpecialRenderer {
    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float f) {
        TileTerraPlate plate = (TileTerraPlate)tileentity;
        float max = 50000.0f;
        float alphaMod = Math.min(max, (float)plate.getCurrentMana()) / max;
        GL11.glPushMatrix();
        GL11.glTranslated((double)d0, (double)d1, (double)d2);
        GL11.glRotated((double)90.0, (double)1.0, (double)0.0, (double)0.0);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.1885f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3008);
        float alpha = (float)((Math.sin((double)((float)ClientTickHandler.ticksInGame + f) / 8.0) + 1.0) / 5.0 + 0.6) * alphaMod;
        if (ShaderHelper.useShaders()) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
        } else {
            int light = 0xF000F0;
            int lightmapX = light % 65536;
            int lightmapY = light / 65536;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)lightmapX, (float)lightmapY);
            GL11.glColor4f((float)(0.6f + (float)((Math.cos((double)((float)ClientTickHandler.ticksInGame + f) / 6.0) + 1.0) / 5.0)), (float)0.1f, (float)0.9f, (float)alpha);
        }
        Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
        ShaderHelper.useShader(ShaderHelper.terraPlateRune);
        this.renderIcon(0, 0, BlockTerraPlate.overlay, 1, 1, 240);
        ShaderHelper.releaseShader();
        GL11.glEnable((int)3008);
        GL11.glDisable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    public void renderIcon(int par1, int par2, IIcon par3Icon, int par4, int par5, int brightness) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78380_c(brightness);
        tessellator.func_78374_a((double)(par1 + 0), (double)(par2 + par5), 0.0, (double)par3Icon.func_94209_e(), (double)par3Icon.func_94210_h());
        tessellator.func_78374_a((double)(par1 + par4), (double)(par2 + par5), 0.0, (double)par3Icon.func_94212_f(), (double)par3Icon.func_94210_h());
        tessellator.func_78374_a((double)(par1 + par4), (double)(par2 + 0), 0.0, (double)par3Icon.func_94212_f(), (double)par3Icon.func_94206_g());
        tessellator.func_78374_a((double)(par1 + 0), (double)(par2 + 0), 0.0, (double)par3Icon.func_94209_e(), (double)par3Icon.func_94206_g());
        tessellator.func_78381_a();
    }
}

