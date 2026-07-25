/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.blocks.BlockLeechChest;
import com.emoniph.witchery.client.model.ModelLeechChest;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderLeechChest
extends TileEntitySpecialRenderer {
    private static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation("witchery", "textures/blocks/leechchest.png");
    private ModelLeechChest model = new ModelLeechChest();

    public void renderLeechChest(BlockLeechChest.TileEntityLeechChest chestEntity, double par2, double par4, double par6, float par8) {
        int i = 0;
        if (chestEntity.func_145830_o()) {
            i = chestEntity.func_145832_p();
        }
        this.func_147499_a(RESOURCE_LOCATION);
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslatef((float)((float)par2), (float)((float)par4 + 1.0f), (float)((float)par6 + 1.0f));
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        int short1 = 0;
        if (i == 2) {
            short1 = 180;
        }
        if (i == 3) {
            short1 = 0;
        }
        if (i == 4) {
            short1 = 90;
        }
        if (i == 5) {
            short1 = -90;
        }
        GL11.glRotatef((float)short1, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        float f1 = chestEntity.prevLidAngle + (chestEntity.lidAngle - chestEntity.prevLidAngle) * par8;
        f1 = 1.0f - f1;
        f1 = 1.0f - f1 * f1 * f1;
        this.model.chestLidBL.field_78795_f = -(f1 * (float)Math.PI / 2.0f) / 1.5f;
        this.model.chestLidBL.field_78796_g = f1 * (float)Math.PI / 2.0f / 3.0f;
        this.model.chestLidBL.field_78808_h = f1 * (float)Math.PI / 2.0f / 1.5f;
        this.model.chestLidBR.field_78795_f = -(f1 * (float)Math.PI / 2.0f) / 1.5f;
        this.model.chestLidBR.field_78796_g = -(f1 * (float)Math.PI / 2.0f) / 3.0f;
        this.model.chestLidBR.field_78808_h = -(f1 * (float)Math.PI / 2.0f) / 1.5f;
        this.model.chestLidFL.field_78795_f = f1 * (float)Math.PI / 2.0f / 1.5f;
        this.model.chestLidFL.field_78796_g = -(f1 * (float)Math.PI / 2.0f) / 3.0f;
        this.model.chestLidFL.field_78808_h = f1 * (float)Math.PI / 2.0f / 1.5f;
        this.model.chestLidFR.field_78795_f = f1 * (float)Math.PI / 2.0f / 1.5f;
        this.model.chestLidFR.field_78796_g = f1 * (float)Math.PI / 2.0f / 3.0f;
        this.model.chestLidFR.field_78808_h = -(f1 * (float)Math.PI / 2.0f) / 1.5f;
        this.model.renderAll(chestEntity.players.size());
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderLeechChest((BlockLeechChest.TileEntityLeechChest)par1TileEntity, par2, par4, par6, par8);
    }
}

