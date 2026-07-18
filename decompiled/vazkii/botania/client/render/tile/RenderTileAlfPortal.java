/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.common.block.BlockAlfPortal;
import vazkii.botania.common.block.tile.TileAlfPortal;

public class RenderTileAlfPortal
extends TileEntitySpecialRenderer {
    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float f) {
        TileAlfPortal portal = (TileAlfPortal)tileentity;
        int meta = portal.func_145832_p();
        if (meta == 0) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glTranslated((double)d0, (double)d1, (double)d2);
        GL11.glTranslatef((float)-1.0f, (float)1.0f, (float)0.25f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3008);
        GL11.glEnable((int)2884);
        float alpha = (float)Math.min(1.0, (Math.sin((double)((float)ClientTickHandler.ticksInGame + f) / 8.0) + 1.0) / 7.0 + 0.6) * ((float)Math.min(60, portal.ticksOpen) / 60.0f) * 0.5f;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
        if (meta == 2) {
            GL11.glTranslatef((float)1.25f, (float)0.0f, (float)1.75f);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        GL11.glDisable((int)2884);
        GL11.glDisable((int)2896);
        this.renderIcon(0, 0, BlockAlfPortal.portalTex, 3, 3, 240);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glTranslated((double)0.0, (double)0.0, (double)0.5);
        this.renderIcon(0, 0, BlockAlfPortal.portalTex, 3, 3, 240);
        GL11.glEnable((int)2884);
        GL11.glEnable((int)3008);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2896);
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

