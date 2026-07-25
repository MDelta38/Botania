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

import com.emoniph.witchery.blocks.BlockChalice;
import com.emoniph.witchery.client.model.ModelChalice;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderChalice
extends TileEntitySpecialRenderer {
    final ModelChalice model = new ModelChalice();
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/chalice.png");

    public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        BlockChalice.TileEntityChalice tileEntityChalice = (BlockChalice.TileEntityChalice)tileEntity;
        this.renderChalice(tileEntityChalice, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
        GL11.glPopMatrix();
    }

    public void renderChalice(BlockChalice.TileEntityChalice tileEntityChalice, World world, int x, int y, int z) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        this.func_147499_a(TEXTURE_URL);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)0.0f, (float)-1.0f, (float)0.0f);
        this.model.render(null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f, tileEntityChalice);
        GL11.glPopMatrix();
    }
}

