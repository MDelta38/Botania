/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.blocks.BlockBeartrap;
import com.emoniph.witchery.client.model.ModelBeartrap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderBeartrap
extends TileEntitySpecialRenderer {
    final ModelBeartrap model = new ModelBeartrap();
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/beartrap.png");

    public void func_147500_a(TileEntity tile, double x, double y, double z, float t) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
        BlockBeartrap.TileEntityBeartrap mantrap = (BlockBeartrap.TileEntityBeartrap)tile;
        this.renderTileEntityAt(mantrap, tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(BlockBeartrap.TileEntityBeartrap tile, World world, int x, int y, int z) {
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)0.0f, (float)-1.0f, (float)0.0f);
        if (world != null) {
            int meta = world.func_72805_g(x, y, z);
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
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)3008);
        this.func_147499_a(TEXTURE_URL);
        this.model.render(null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f, tile);
        GL11.glEnable((int)3008);
        GL11.glDisable((int)3042);
    }
}

