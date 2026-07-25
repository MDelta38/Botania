/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.client.FMLClientHandler;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelManaPod;
import thaumcraft.common.tiles.TileManaPod;

public class TileManaPodRenderer
extends TileEntitySpecialRenderer {
    private ModelManaPod model = new ModelManaPod();
    private static final ResourceLocation pod0tex = new ResourceLocation("thaumcraft", "textures/models/manapod_0.png");
    private static final ResourceLocation pod2tex = new ResourceLocation("thaumcraft", "textures/models/manapod_2.png");

    public void renderEntityAt(TileManaPod pod, double x, double y, double z, float fq) {
        int meta = 0;
        int bright = 20;
        Aspect aspect = Aspect.PLANT;
        if (pod.func_145831_w() == null) {
            meta = 5;
        } else {
            meta = pod.func_145832_p();
            if (pod.aspect != null) {
                aspect = pod.aspect;
            }
            bright = pod.func_145838_q().func_149677_c((IBlockAccess)pod.func_145831_w(), pod.field_145851_c, pod.field_145848_d, pod.field_145849_e);
        }
        if (meta > 1) {
            float br = 0.14509805f;
            float bg = 0.6156863f;
            float bb = 0.45882353f;
            float fr = br;
            float fg = bg;
            float fb = bb;
            if (pod.aspect != null) {
                Color color = new Color(aspect.getColor());
                float ar = (float)color.getRed() / 255.0f;
                float ag = (float)color.getGreen() / 255.0f;
                float ab = (float)color.getBlue() / 255.0f;
                if (meta == 7) {
                    fr = ar;
                    fg = ag;
                    fb = ab;
                } else {
                    float m = meta - 2;
                    fr = (br + ar * m) / (m + 1.0f);
                    fg = (bg + ag * m) / (m + 1.0f);
                    fb = (bb + ab * m) / (m + 1.0f);
                }
            }
            Minecraft mc = FMLClientHandler.instance().getClient();
            GL11.glPushMatrix();
            GL11.glEnable((int)2977);
            GL11.glEnable((int)3042);
            GL11.glEnable((int)32826);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glTranslated((double)(x + 0.5), (double)(y + 0.75), (double)(z + 0.5));
            GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            if (meta > 2) {
                EntityClientPlayerMP p = Minecraft.func_71410_x().field_71439_g;
                float scale = MathHelper.func_76126_a((float)((float)(p.field_70173_aa + pod.hashCode() % 100) / 8.0f)) * 0.1f + 0.9f;
                GL11.glPushMatrix();
                float bs = MathHelper.func_76126_a((float)((float)(p.field_70173_aa + pod.hashCode() % 100) / 8.0f)) * 0.3f + 0.7f;
                int j = meta * 10 + (int)(150.0f * scale);
                int k = j % 65536;
                int l = j / 65536;
                OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
                GL11.glTranslated((double)0.0, (double)0.1, (double)0.0);
                GL11.glScaled((double)(0.125 * (double)meta * (double)scale), (double)(0.125 * (double)meta * (double)scale), (double)(0.125 * (double)meta * (double)scale));
                UtilsFX.bindTexture(pod0tex);
                this.model.pod0.func_78785_a(0.0625f);
                GL11.glPopMatrix();
            }
            GL11.glScaled((double)(0.15 * (double)meta), (double)(0.15 * (double)meta), (double)(0.15 * (double)meta));
            GL11.glColor4f((float)fr, (float)fg, (float)fb, (float)0.9f);
            UtilsFX.bindTexture(pod2tex);
            this.model.pod2.func_78785_a(0.0625f);
            GL11.glDisable((int)32826);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        }
    }

    public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f) {
        this.renderEntityAt((TileManaPod)tileentity, d, d1, d2, f);
    }
}

