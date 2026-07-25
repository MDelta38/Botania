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

import com.emoniph.witchery.blocks.BlockCoffin;
import com.emoniph.witchery.client.model.ModelCoffin;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderCoffin
extends TileEntitySpecialRenderer {
    final ModelCoffin model = new ModelCoffin();
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/coffin.png");

    public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        BlockCoffin.TileEntityCoffin tileEntityGoddess = (BlockCoffin.TileEntityCoffin)tileEntity;
        this.renderGoddess(tileEntityGoddess, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e, f);
        GL11.glPopMatrix();
    }

    public void renderGoddess(BlockCoffin.TileEntityCoffin tile, World world, int x, int y, int z, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        this.func_147499_a(TEXTURE_URL);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)0.0f, (float)-0.1f, (float)0.0f);
        if (world != null) {
            int meta = world.func_72805_g(x, y, z);
            int direction = BlockCoffin.getDirection(meta);
            float rotation = 0.0f;
            switch (direction) {
                case 0: {
                    rotation = 0.0f;
                    break;
                }
                case 1: {
                    rotation = 90.0f;
                    break;
                }
                case 2: {
                    rotation = 180.0f;
                    break;
                }
                case 3: {
                    rotation = 270.0f;
                }
            }
            GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
            if (!BlockCoffin.isBlockHeadOfBed(meta)) {
                this.model.sideLeft.field_78798_e = 1.0f;
                this.model.sideRight.field_78798_e = 1.0f;
                this.model.base.field_78798_e = 1.0f;
                this.model.sideEnd.field_78796_g = (float)Math.PI;
                this.model.lidTop.field_78798_e = 2.0f;
                this.model.lidMid.field_78798_e = 1.0f;
                this.model.lid.field_78796_g = 0.0f;
            } else {
                this.model.sideLeft.field_78798_e = 0.0f;
                this.model.sideRight.field_78798_e = 0.0f;
                this.model.sideEnd.field_78798_e = 0.0f;
                this.model.sideEnd.field_78796_g = 0.0f;
                this.model.base.field_78798_e = 0.0f;
                this.model.lidTop.field_78798_e = 0.0f;
                this.model.lidMid.field_78798_e = 0.0f;
            }
            float f1 = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * f;
            f1 = 1.0f - f1;
            f1 = 1.0f - f1 * f1 * f1;
            this.model.lid.field_78808_h = -(f1 * (float)Math.PI / 2.0f);
        }
        this.model.func_78088_a(null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }
}

