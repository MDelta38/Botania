/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.blocks.BlockWolfHead;
import com.emoniph.witchery.client.model.ModelWolfHead;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderWolfHead
extends TileEntitySpecialRenderer {
    private static final ResourceLocation WOLF_TEXTURE = new ResourceLocation("textures/entity/wolf/wolf.png");
    private static final ResourceLocation HELLHOUND_TEXTURE = new ResourceLocation("witchery", "textures/entities/hellhound.png");
    public static RenderWolfHead field_147536_b;
    private ModelWolfHead field_147533_g = new ModelWolfHead(0, 0, 64, 32);
    private ModelWolfHead field_147538_h = new ModelWolfHead(0, 0, 64, 64);

    public void renderTileEntityAt(BlockWolfHead.TileEntityWolfHead tile, double x, double y, double z, float partialTicks) {
        this.render((float)x, (float)y, (float)z, tile.func_145832_p() & 7, (float)(tile.getRotation() * 360) / 16.0f, tile.getSkullType());
    }

    public void func_147497_a(TileEntityRendererDispatcher p_147497_1_) {
        super.func_147497_a(p_147497_1_);
        field_147536_b = this;
    }

    public void render(float x, float y, float z, int metadata, float rotation, int skullType) {
        ModelWolfHead modelskeletonhead = this.field_147533_g;
        switch (skullType) {
            default: {
                this.func_147499_a(WOLF_TEXTURE);
                break;
            }
            case 1: {
                this.func_147499_a(HELLHOUND_TEXTURE);
            }
        }
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        if (metadata != 1) {
            switch (metadata) {
                case 2: {
                    GL11.glTranslatef((float)(x + 0.5f), (float)(y + 0.25f), (float)(z + 0.74f));
                    break;
                }
                case 3: {
                    GL11.glTranslatef((float)(x + 0.5f), (float)(y + 0.25f), (float)(z + 0.26f));
                    rotation = 180.0f;
                    break;
                }
                case 4: {
                    GL11.glTranslatef((float)(x + 0.74f), (float)(y + 0.25f), (float)(z + 0.5f));
                    rotation = 270.0f;
                    break;
                }
                default: {
                    GL11.glTranslatef((float)(x + 0.26f), (float)(y + 0.25f), (float)(z + 0.5f));
                    rotation = 90.0f;
                    break;
                }
            }
        } else {
            GL11.glTranslatef((float)(x + 0.5f), (float)y, (float)(z + 0.5f));
        }
        float f4 = 0.0625f;
        GL11.glEnable((int)32826);
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        GL11.glEnable((int)3008);
        modelskeletonhead.func_78088_a(null, 0.0f, 0.0f, 0.0f, rotation, 0.0f, f4);
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity tile, double x, double y, double z, float partialTicks) {
        this.renderTileEntityAt((BlockWolfHead.TileEntityWolfHead)tile, x, y, z, partialTicks);
    }
}

