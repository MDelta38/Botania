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

import com.emoniph.witchery.blocks.BlockMirror;
import com.emoniph.witchery.client.model.ModelMirror;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderMirror
extends TileEntitySpecialRenderer {
    final ModelMirror model = new ModelMirror();
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/mirror.png");
    private static final ResourceLocation TEXTURE2_URL = new ResourceLocation("witchery", "textures/blocks/mirror2.png");

    public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        BlockMirror.TileEntityMirror tileEntityYour = (BlockMirror.TileEntityMirror)tileEntity;
        this.renderMirror(tileEntityYour, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
        GL11.glPopMatrix();
    }

    public void renderMirror(BlockMirror.TileEntityMirror te, World world, int x, int y, int z) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        this.func_147499_a(TEXTURE_URL);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        if (world != null) {
            int meta = world.func_72805_g(x, y, z);
            int facing = BlockMirror.getDirection(meta);
            float rotation = 0.0f;
            switch (facing) {
                case 0: {
                    rotation = 0.0f;
                    break;
                }
                case 1: {
                    rotation = 180.0f;
                    break;
                }
                case 2: {
                    rotation = 270.0f;
                    break;
                }
                case 3: {
                    rotation = 90.0f;
                }
            }
            GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
            if (!BlockMirror.isBlockTopOfMirror(meta)) {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            GL11.glTranslatef((float)0.0f, (float)0.1f, (float)0.42f);
        }
        GL11.glTranslatef((float)0.0f, (float)-0.1f, (float)0.02f);
        this.model.func_78088_a(null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
        if (te.men > 0) {
            this.func_147499_a(TEXTURE2_URL);
            this.model.backMiddle.func_78785_a(0.0625f);
        }
        GL11.glPopMatrix();
    }
}

