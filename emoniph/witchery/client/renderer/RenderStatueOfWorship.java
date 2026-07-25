/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.blocks.BlockStatueOfWorship;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderStatueOfWorship
extends TileEntitySpecialRenderer {
    private final ModelBiped model = new ModelBiped(0.0f);
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/statueofworship.png");

    public void func_147500_a(TileEntity tile, double x, double y, double z, float var8) {
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        this.model.field_78091_s = true;
        this.model.field_78119_l = 1;
        this.model.field_78120_m = 1;
        BlockStatueOfWorship.TileEntityStatueOfWorship statue = tile != null ? (BlockStatueOfWorship.TileEntityStatueOfWorship)tile : null;
        World world = statue != null ? statue.func_145831_w() : null;
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)0.0f, (float)-1.0f, (float)0.0f);
        if (world != null && statue != null) {
            int meta = world.func_72805_g(statue.field_145851_c, statue.field_145848_d, statue.field_145849_e);
            float rotation = 0.0f;
            switch (meta) {
                case 2: {
                    rotation = 0.0f;
                    break;
                }
                case 3: {
                    rotation = 180.0f;
                    break;
                }
                case 4: {
                    rotation = 270.0f;
                    break;
                }
                case 5: {
                    rotation = 90.0f;
                }
            }
            GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        this.func_147499_a(statue.getLocationSkin());
        GL11.glColor3f((float)0.7f, (float)0.7f, (float)0.7f);
        this.model.func_78088_a(null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
        GL11.glPushAttrib((int)16448);
        GL11.glShadeModel((int)7424);
        GL11.glDisable((int)3008);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        this.func_147499_a(TEXTURE_URL);
        GL11.glColor3f((float)0.8f, (float)0.8f, (float)0.8f);
        this.model.func_78088_a(null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
}

