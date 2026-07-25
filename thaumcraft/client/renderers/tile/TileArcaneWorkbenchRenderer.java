/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelArcaneWorkbench;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileArcaneWorkbench;

@SideOnly(value=Side.CLIENT)
public class TileArcaneWorkbenchRenderer
extends TileEntitySpecialRenderer {
    private ModelArcaneWorkbench tableModel = new ModelArcaneWorkbench();

    public void renderTileEntityAt(TileArcaneWorkbench table, double par2, double par4, double par6, float par8) {
        GL11.glPushMatrix();
        UtilsFX.bindTexture("textures/models/worktable.png");
        GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4 + 1.0f), (float)((float)par6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.tableModel.renderAll();
        GL11.glPopMatrix();
        if (table.func_145831_w() != null && table.func_70301_a(10) != null && table.func_70301_a(10).func_77973_b() instanceof ItemWandCasting) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)par2 + 0.65f), (float)((float)par4 + 1.0625f), (float)((float)par6 + 0.25f));
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            ItemStack is = table.func_70301_a(10).func_77946_l();
            is.field_77994_a = 1;
            EntityItem entityitem = new EntityItem(table.func_145831_w(), 0.0, 0.0, 0.0, is);
            entityitem.field_70290_d = 0.0f;
            RenderItem.field_82407_g = true;
            RenderManager.field_78727_a.func_147940_a((Entity)entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
            RenderItem.field_82407_g = false;
            GL11.glPopMatrix();
        }
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileArcaneWorkbench)par1TileEntity, par2, par4, par6, par8);
    }
}

