/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.tiles.TilePedestal;

@SideOnly(value=Side.CLIENT)
public class TilePedestalRenderer
extends TileEntitySpecialRenderer {
    public void renderTileEntityAt(TilePedestal ped, double par2, double par4, double par6, float par8) {
        if (ped != null && ped.func_145831_w() != null && ped.func_70301_a(0) != null) {
            EntityItem entityitem = null;
            float ticks = (float)Minecraft.func_71410_x().field_71451_h.field_70173_aa + par8;
            GL11.glPushMatrix();
            float h = MathHelper.func_76126_a((float)(ticks % 32767.0f / 16.0f)) * 0.05f;
            GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4 + 1.15f + h), (float)((float)par6 + 0.5f));
            GL11.glRotatef((float)(ticks % 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            if (ped.func_70301_a(0).func_77973_b() instanceof ItemBlock) {
                GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
            } else {
                GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            }
            ItemStack is = ped.func_70301_a(0).func_77946_l();
            is.field_77994_a = 1;
            entityitem = new EntityItem(ped.func_145831_w(), 0.0, 0.0, 0.0, is);
            entityitem.field_70290_d = 0.0f;
            RenderManager.field_78727_a.func_147940_a((Entity)entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
            if (!Minecraft.func_71375_t()) {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                RenderManager.field_78727_a.func_147940_a((Entity)entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
            }
            GL11.glPopMatrix();
        }
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TilePedestal)par1TileEntity, par2, par4, par6, par8);
    }
}

