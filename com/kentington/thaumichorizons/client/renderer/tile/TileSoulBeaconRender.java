/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 */
package com.kentington.thaumichorizons.client.renderer.tile;

import com.kentington.thaumichorizons.client.renderer.model.ModelSoulBeacon;
import com.kentington.thaumichorizons.common.tiles.TileSoulBeacon;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class TileSoulBeaconRender
extends TileEntitySpecialRenderer {
    static String tx1 = "textures/models/soulbeacon.png";
    private ModelSoulBeacon base = new ModelSoulBeacon();
    private static final ResourceLocation field_147523_b = new ResourceLocation("textures/entity/beacon_beam.png");
    private static final String __OBFID = "CL_00000962";

    public void renderTileEntityAt(TileSoulBeacon tco, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)p_147500_2_ + 0.5f), (float)((float)p_147500_4_ + 1.5f), (float)((float)p_147500_6_ + 0.5f));
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        UtilsFX.bindTexture((String)"thaumichorizons", (String)tx1);
        this.base.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
        if (tco.func_145831_w() == null || !tco.func_145831_w().func_72937_j(tco.field_145851_c, tco.field_145848_d, tco.field_145849_e)) {
            return;
        }
        float f1 = tco.func_146002_i();
        GL11.glAlphaFunc((int)516, (float)0.1f);
        if (f1 > 0.0f) {
            Tessellator tessellator = Tessellator.field_78398_a;
            this.func_147499_a(field_147523_b);
            GL11.glTexParameterf((int)3553, (int)10242, (float)10497.0f);
            GL11.glTexParameterf((int)3553, (int)10243, (float)10497.0f);
            GL11.glDisable((int)2896);
            GL11.glDisable((int)2884);
            GL11.glDisable((int)3042);
            GL11.glDepthMask((boolean)true);
            OpenGlHelper.func_148821_a((int)770, (int)1, (int)1, (int)0);
            float f2 = (float)tco.func_145831_w().func_82737_E() + p_147500_8_;
            float f3 = -f2 * 0.2f - (float)MathHelper.func_76141_d((float)(-f2 * 0.1f));
            boolean b0 = true;
            double d3 = (double)f2 * 0.025 * (1.0 - (double)(b0 & true) * 2.5);
            tessellator.func_78382_b();
            tessellator.func_78370_a(255, 255, 255, 32);
            double d5 = (double)b0 * 0.2;
            double d7 = 0.5 + Math.cos(d3 + 2.356194490192345) * d5;
            double d9 = 0.5 + Math.sin(d3 + 2.356194490192345) * d5;
            double d11 = 0.5 + Math.cos(d3 + 0.7853981633974483) * d5;
            double d13 = 0.5 + Math.sin(d3 + 0.7853981633974483) * d5;
            double d15 = 0.5 + Math.cos(d3 + 3.9269908169872414) * d5;
            double d17 = 0.5 + Math.sin(d3 + 3.9269908169872414) * d5;
            double d19 = 0.5 + Math.cos(d3 + 5.497787143782138) * d5;
            double d21 = 0.5 + Math.sin(d3 + 5.497787143782138) * d5;
            double d23 = 256.0f * f1;
            double d25 = 0.0;
            double d27 = 1.0;
            double d28 = -1.0f + f3;
            double d29 = (double)(256.0f * f1) * (0.5 / d5) + d28;
            tessellator.func_78374_a(p_147500_2_ + d7, p_147500_4_ + d23, p_147500_6_ + d9, d27, d29);
            tessellator.func_78374_a(p_147500_2_ + d7, p_147500_4_, p_147500_6_ + d9, d27, d28);
            tessellator.func_78374_a(p_147500_2_ + d11, p_147500_4_, p_147500_6_ + d13, d25, d28);
            tessellator.func_78374_a(p_147500_2_ + d11, p_147500_4_ + d23, p_147500_6_ + d13, d25, d29);
            tessellator.func_78374_a(p_147500_2_ + d19, p_147500_4_ + d23, p_147500_6_ + d21, d27, d29);
            tessellator.func_78374_a(p_147500_2_ + d19, p_147500_4_, p_147500_6_ + d21, d27, d28);
            tessellator.func_78374_a(p_147500_2_ + d15, p_147500_4_, p_147500_6_ + d17, d25, d28);
            tessellator.func_78374_a(p_147500_2_ + d15, p_147500_4_ + d23, p_147500_6_ + d17, d25, d29);
            tessellator.func_78374_a(p_147500_2_ + d11, p_147500_4_ + d23, p_147500_6_ + d13, d27, d29);
            tessellator.func_78374_a(p_147500_2_ + d11, p_147500_4_, p_147500_6_ + d13, d27, d28);
            tessellator.func_78374_a(p_147500_2_ + d19, p_147500_4_, p_147500_6_ + d21, d25, d28);
            tessellator.func_78374_a(p_147500_2_ + d19, p_147500_4_ + d23, p_147500_6_ + d21, d25, d29);
            tessellator.func_78374_a(p_147500_2_ + d15, p_147500_4_ + d23, p_147500_6_ + d17, d27, d29);
            tessellator.func_78374_a(p_147500_2_ + d15, p_147500_4_, p_147500_6_ + d17, d27, d28);
            tessellator.func_78374_a(p_147500_2_ + d7, p_147500_4_, p_147500_6_ + d9, d25, d28);
            tessellator.func_78374_a(p_147500_2_ + d7, p_147500_4_ + d23, p_147500_6_ + d9, d25, d29);
            tessellator.func_78381_a();
            GL11.glEnable((int)3042);
            OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
            GL11.glDepthMask((boolean)false);
            tessellator.func_78382_b();
            tessellator.func_78370_a(255, 255, 255, 32);
            double d30 = 0.2;
            double d4 = 0.2;
            double d6 = 0.8;
            double d8 = 0.2;
            double d10 = 0.2;
            double d12 = 0.8;
            double d14 = 0.8;
            double d16 = 0.8;
            double d18 = 256.0f * f1;
            double d20 = 0.0;
            double d22 = 1.0;
            double d24 = -1.0f + f3;
            double d26 = (double)(256.0f * f1) + d24;
            tessellator.func_78374_a(p_147500_2_ + d30, p_147500_4_ + d18, p_147500_6_ + d4, d22, d26);
            tessellator.func_78374_a(p_147500_2_ + d30, p_147500_4_, p_147500_6_ + d4, d22, d24);
            tessellator.func_78374_a(p_147500_2_ + d6, p_147500_4_, p_147500_6_ + d8, d20, d24);
            tessellator.func_78374_a(p_147500_2_ + d6, p_147500_4_ + d18, p_147500_6_ + d8, d20, d26);
            tessellator.func_78374_a(p_147500_2_ + d14, p_147500_4_ + d18, p_147500_6_ + d16, d22, d26);
            tessellator.func_78374_a(p_147500_2_ + d14, p_147500_4_, p_147500_6_ + d16, d22, d24);
            tessellator.func_78374_a(p_147500_2_ + d10, p_147500_4_, p_147500_6_ + d12, d20, d24);
            tessellator.func_78374_a(p_147500_2_ + d10, p_147500_4_ + d18, p_147500_6_ + d12, d20, d26);
            tessellator.func_78374_a(p_147500_2_ + d6, p_147500_4_ + d18, p_147500_6_ + d8, d22, d26);
            tessellator.func_78374_a(p_147500_2_ + d6, p_147500_4_, p_147500_6_ + d8, d22, d24);
            tessellator.func_78374_a(p_147500_2_ + d14, p_147500_4_, p_147500_6_ + d16, d20, d24);
            tessellator.func_78374_a(p_147500_2_ + d14, p_147500_4_ + d18, p_147500_6_ + d16, d20, d26);
            tessellator.func_78374_a(p_147500_2_ + d10, p_147500_4_ + d18, p_147500_6_ + d12, d22, d26);
            tessellator.func_78374_a(p_147500_2_ + d10, p_147500_4_, p_147500_6_ + d12, d22, d24);
            tessellator.func_78374_a(p_147500_2_ + d30, p_147500_4_, p_147500_6_ + d4, d20, d24);
            tessellator.func_78374_a(p_147500_2_ + d30, p_147500_4_ + d18, p_147500_6_ + d4, d20, d26);
            tessellator.func_78381_a();
            GL11.glEnable((int)2896);
            GL11.glEnable((int)3553);
            GL11.glDepthMask((boolean)true);
        }
    }

    public void func_147500_a(TileEntity te, double x, double y, double z, float f) {
        this.renderTileEntityAt((TileSoulBeacon)te, x, y, z, f);
    }
}

