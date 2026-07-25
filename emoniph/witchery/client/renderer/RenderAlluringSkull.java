/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelSkeletonHead
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.blocks.BlockAlluringSkull;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderAlluringSkull
extends TileEntitySpecialRenderer {
    private static final ResourceLocation field_110642_c = new ResourceLocation("witchery", "textures/blocks/alluringSkull.png");
    private static final ResourceLocation field_110640_d = new ResourceLocation("witchery", "textures/blocks/alluringSkull2.png");
    private ModelSkeletonHead field_82396_c = new ModelSkeletonHead(0, 0, 64, 32);

    public void renderTileEntityAlluringSkullAt(BlockAlluringSkull.TileEntityAlluringSkull par1TileEntitySkull, double par2, double par4, double par6, float par8) {
        this.func_82393_a((float)par2, (float)par4, (float)par6, par1TileEntitySkull.func_145832_p() & 7, (float)(par1TileEntitySkull.func_82119_b() * 360) / 16.0f, par1TileEntitySkull.getSkullType());
    }

    public void func_82393_a(float par1, float par2, float par3, int par4, float par5, int par6) {
        ModelSkeletonHead modelskeletonhead = this.field_82396_c;
        switch (par6) {
            default: {
                this.func_147499_a(field_110642_c);
                break;
            }
            case 1: {
                this.func_147499_a(field_110640_d);
            }
        }
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        if (par4 != 1) {
            switch (par4) {
                case 2: {
                    GL11.glTranslatef((float)(par1 + 0.5f), (float)(par2 + 0.25f), (float)(par3 + 0.74f));
                    break;
                }
                case 3: {
                    GL11.glTranslatef((float)(par1 + 0.5f), (float)(par2 + 0.25f), (float)(par3 + 0.26f));
                    par5 = 180.0f;
                    break;
                }
                case 4: {
                    GL11.glTranslatef((float)(par1 + 0.74f), (float)(par2 + 0.25f), (float)(par3 + 0.5f));
                    par5 = 270.0f;
                    break;
                }
                default: {
                    GL11.glTranslatef((float)(par1 + 0.26f), (float)(par2 + 0.25f), (float)(par3 + 0.5f));
                    par5 = 90.0f;
                    break;
                }
            }
        } else {
            GL11.glTranslatef((float)(par1 + 0.5f), (float)par2, (float)(par3 + 0.5f));
        }
        float f4 = 0.0625f;
        GL11.glEnable((int)32826);
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        GL11.glEnable((int)3008);
        modelskeletonhead.func_78088_a((Entity)null, 0.0f, 0.0f, 0.0f, par5, 0.0f, f4);
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAlluringSkullAt((BlockAlluringSkull.TileEntityAlluringSkull)par1TileEntity, par2, par4, par6, par8);
    }
}

