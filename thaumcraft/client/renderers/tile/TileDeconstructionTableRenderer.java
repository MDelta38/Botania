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
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelArcaneWorkbench;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.tiles.TileDeconstructionTable;

@SideOnly(value=Side.CLIENT)
public class TileDeconstructionTableRenderer
extends TileEntitySpecialRenderer {
    private ModelArcaneWorkbench tableModel = new ModelArcaneWorkbench();
    ItemStack tm = new ItemStack(ConfigItems.itemThaumometer);

    public void renderTileEntityAt(TileDeconstructionTable table, double par2, double par4, double par6, float par8) {
        GL11.glPushMatrix();
        UtilsFX.bindTexture("textures/models/decontable.png");
        GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4 + 1.0f), (float)((float)par6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.tableModel.renderAll();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4 + 0.92f), (float)((float)par6 + 0.5f));
        GL11.glScaled((double)0.8, (double)0.8, (double)0.8);
        EntityItem entityitem = new EntityItem(table.func_145831_w(), 0.0, 0.0, 0.0, this.tm);
        entityitem.field_70290_d = 0.0f;
        RenderItem.field_82407_g = true;
        RenderManager.field_78727_a.func_147940_a((Entity)entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
        RenderItem.field_82407_g = false;
        GL11.glPopMatrix();
        float ticks = (float)Minecraft.func_71410_x().field_71451_h.field_70173_aa + par8;
        if (table != null && table.func_145831_w() != null && table.func_70301_a(0) != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4 + 1.15f), (float)((float)par6 + 0.5f));
            GL11.glRotatef((float)(ticks % 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)1);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.75f);
            ItemStack is = table.func_70301_a(0).func_77946_l();
            is.field_77994_a = 1;
            entityitem = new EntityItem(table.func_145831_w(), 0.0, 0.0, 0.0, is);
            entityitem.field_70290_d = MathHelper.func_76126_a((float)(ticks / 14.0f)) * 0.2f + 0.2f;
            RenderItem.field_82407_g = true;
            RenderManager.field_78727_a.func_147940_a((Entity)entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
            RenderItem.field_82407_g = false;
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        }
        if (table != null && table.func_145831_w() != null && table.aspect != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4 + 1.081f), (float)((float)par6 + 0.5f));
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(ticks % 360.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glScaled((double)0.024, (double)0.024, (double)0.024);
            UtilsFX.drawTag(-8, -8, table.aspect, 0.0f, 0, 0.0, 1, 0.8f, false);
            GL11.glPopMatrix();
        }
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileDeconstructionTable)par1TileEntity, par2, par4, par6, par8);
    }
}

