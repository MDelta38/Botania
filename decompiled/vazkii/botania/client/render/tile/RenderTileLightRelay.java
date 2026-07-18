/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.common.block.BlockLightRelay;

public class RenderTileLightRelay
extends TileEntitySpecialRenderer {
    public void func_147500_a(TileEntity tile, double x, double y, double z, float pticks) {
        Minecraft mc = Minecraft.func_71410_x();
        IIcon iicon = tile.func_145832_p() > 0 ? BlockLightRelay.worldIconRed : BlockLightRelay.worldIcon;
        GL11.glPushMatrix();
        GL11.glTranslated((double)(x + 0.5), (double)(y + 0.3), (double)(z + 0.5));
        GL11.glEnable((int)32826);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glAlphaFunc((int)516, (float)0.05f);
        double time = (float)ClientTickHandler.ticksInGame + pticks;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        float scale = 0.75f;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glPushMatrix();
        float r = 180.0f - RenderManager.field_78727_a.field_78735_i;
        GL11.glRotatef((float)r, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-RenderManager.field_78727_a.field_78732_j), (float)1.0f, (float)0.0f, (float)0.0f);
        float off = 0.25f;
        GL11.glTranslatef((float)0.0f, (float)off, (float)0.0f);
        GL11.glRotated((double)time, (double)0.0, (double)0.0, (double)1.0);
        GL11.glTranslatef((float)0.0f, (float)(-off), (float)0.0f);
        mc.field_71446_o.func_110577_a(TextureMap.field_110575_b);
        ShaderHelper.useShader(ShaderHelper.halo);
        this.func_77026_a(tessellator, iicon);
        ShaderHelper.releaseShader();
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }

    private void func_77026_a(Tessellator p_77026_1_, IIcon p_77026_2_) {
        float f = p_77026_2_.func_94209_e();
        float f1 = p_77026_2_.func_94212_f();
        float f2 = p_77026_2_.func_94206_g();
        float f3 = p_77026_2_.func_94210_h();
        float size = f1 - f;
        float pad = size / 8.0f;
        f += pad;
        f1 -= pad;
        f2 += pad;
        f3 -= pad;
        float f4 = 1.0f;
        float f5 = 0.5f;
        float f6 = 0.25f;
        p_77026_1_.func_78382_b();
        p_77026_1_.func_78375_b(0.0f, 1.0f, 0.0f);
        p_77026_1_.func_78380_c(240);
        p_77026_1_.func_78374_a((double)(0.0f - f5), (double)(0.0f - f6), 0.0, (double)f, (double)f3);
        p_77026_1_.func_78374_a((double)(f4 - f5), (double)(0.0f - f6), 0.0, (double)f1, (double)f3);
        p_77026_1_.func_78374_a((double)(f4 - f5), (double)(f4 - f6), 0.0, (double)f1, (double)f2);
        p_77026_1_.func_78374_a((double)(0.0f - f5), (double)(f4 - f6), 0.0, (double)f, (double)f2);
        p_77026_1_.func_78381_a();
    }
}

