/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.blocks.BlockBloodCrucible;
import com.emoniph.witchery.client.model.ModelBloodCrucible;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderBloodCrucible
extends TileEntitySpecialRenderer {
    final ModelBloodCrucible model = new ModelBloodCrucible();
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/bloodcrucible.png");

    public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f) {
        BlockBloodCrucible.TileEntityBloodCrucible crucible = (BlockBloodCrucible.TileEntityBloodCrucible)tileEntity;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glTranslatef((float)0.0f, (float)-0.5f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        this.func_147499_a(TEXTURE_URL);
        this.model.func_78088_a(null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
        float percentFilled = crucible.getPercentFilled();
        if (percentFilled > 0.0f) {
            this.func_147499_a(TextureMap.field_110575_b);
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glDisable((int)3008);
            int color = 0xFF0000;
            float red = (float)(color >>> 16 & 0xFF) / 256.0f;
            float green = (float)(color >>> 8 & 0xFF) / 256.0f;
            float blue = (float)(color & 0xFF) / 256.0f;
            GL11.glColor4f((float)red, (float)green, (float)blue, (float)0.9f);
            float w = -0.1875f;
            float depth = -0.1f + -0.2f * percentFilled;
            GL11.glTranslatef((float)w, (float)depth, (float)(-w));
            GL11.glRotatef((float)270.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            float s = 0.0234375f;
            GL11.glScalef((float)0.0234375f, (float)0.0234375f, (float)0.0234375f);
            IIcon icon = Blocks.field_150353_l.func_149691_a(0, 0);
            boolean x = false;
            boolean y = false;
            int u = 16;
            int v = 16;
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            tessellator.func_78380_c(200);
            tessellator.func_78374_a(0.0, 16.0, 0.0, (double)icon.func_94209_e(), (double)icon.func_94210_h());
            tessellator.func_78374_a(16.0, 16.0, 0.0, (double)icon.func_94212_f(), (double)icon.func_94210_h());
            tessellator.func_78374_a(16.0, 0.0, 0.0, (double)icon.func_94212_f(), (double)icon.func_94206_g());
            tessellator.func_78374_a(0.0, 0.0, 0.0, (double)icon.func_94209_e(), (double)icon.func_94206_g());
            tessellator.func_78381_a();
            GL11.glEnable((int)3008);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }
}

