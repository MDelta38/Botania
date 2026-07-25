/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelArcaneWorkbench;
import thaumcraft.common.tiles.TileFocalManipulator;

@SideOnly(value=Side.CLIENT)
public class TileFocalManipulatorRenderer
extends TileEntitySpecialRenderer {
    private ModelArcaneWorkbench tableModel = new ModelArcaneWorkbench();
    EntityItem entityitem = null;

    public void renderTileEntityAt(TileFocalManipulator table, double par2, double par4, double par6, float par8) {
        GL11.glPushMatrix();
        UtilsFX.bindTexture("textures/models/wandtable.png");
        GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4 + 1.0f), (float)((float)par6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.tableModel.renderAll();
        GL11.glPopMatrix();
        if (table.func_145831_w() != null && table.func_70301_a(0) != null && table.func_70301_a(0).func_77973_b() instanceof ItemFocusBasic) {
            float ticks = (float)Minecraft.func_71410_x().field_71451_h.field_70173_aa + par8;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4 + 1.0f), (float)((float)par6 + 0.5f));
            GL11.glRotatef((float)(ticks % 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            ItemStack is = table.func_70301_a(0).func_77946_l();
            this.entityitem = new EntityItem(table.func_145831_w(), 0.0, 0.0, 0.0, is);
            this.entityitem.field_70290_d = MathHelper.func_76126_a((float)(ticks / 14.0f)) * 0.2f + 0.2f;
            RenderItem.field_82407_g = true;
            RenderManager.field_78727_a.func_147940_a((Entity)this.entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
            RenderItem.field_82407_g = false;
            GL11.glPopMatrix();
        }
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileFocalManipulator)par1TileEntity, par2, par4, par6, par8);
    }
}

