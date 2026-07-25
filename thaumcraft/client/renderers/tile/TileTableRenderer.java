/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelTable;
import thaumcraft.common.tiles.TileTable;

@SideOnly(value=Side.CLIENT)
public class TileTableRenderer
extends TileEntitySpecialRenderer {
    private ModelTable tableModel = new ModelTable();

    public void renderTileEntityAt(TileTable table, double par2, double par4, double par6, float par8) {
        int md = 0;
        if (table.func_145831_w() != null) {
            md = table.func_145832_p();
        }
        if (md >= 6) {
            return;
        }
        GL11.glPushMatrix();
        UtilsFX.bindTexture("textures/models/table.png");
        GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4 + 1.0f), (float)((float)par6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        if (md == 1) {
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.tableModel.renderAll();
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileTable)par1TileEntity, par2, par4, par6, par8);
    }
}

