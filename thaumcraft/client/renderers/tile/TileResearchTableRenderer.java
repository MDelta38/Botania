/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.IScribeTools;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelResearchTable;
import thaumcraft.common.blocks.BlockTable;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.research.ResearchNoteData;
import thaumcraft.common.tiles.TileResearchTable;

@SideOnly(value=Side.CLIENT)
public class TileResearchTableRenderer
extends TileEntitySpecialRenderer {
    private ModelResearchTable tableModel = new ModelResearchTable();

    public void renderTileEntityAt(TileResearchTable table, double par2, double par4, double par6, float par8) {
        int md = 0;
        if (table.func_145831_w() != null) {
            md = table.func_145832_p();
        }
        GL11.glPushMatrix();
        UtilsFX.bindTexture("textures/models/restable.png");
        GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4 + 1.0f), (float)((float)par6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        switch (md) {
            case 2: {
                GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 3: {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 4: {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            }
        }
        this.tableModel.renderAll();
        if (table.func_70301_a(0) != null && table.func_70301_a(0).func_77973_b() instanceof IScribeTools) {
            this.tableModel.renderInkwell();
            GL11.glPushMatrix();
            GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.17f, (float)0.1f, (float)-0.15f);
            GL11.glRotatef((float)15.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            IIcon icon = ((BlockTable)ConfigBlocks.blockTable).iconQuill;
            float f1 = icon.func_94212_f();
            float f2 = icon.func_94206_g();
            float f3 = icon.func_94209_e();
            float f4 = icon.func_94210_h();
            Tessellator tessellator = Tessellator.field_78398_a;
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            this.field_147501_a.field_147553_e.func_110577_a(TextureMap.field_110575_b);
            ItemRenderer.func_78439_a((Tessellator)tessellator, (float)f1, (float)f2, (float)f3, (float)f4, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.025f);
            GL11.glPopMatrix();
        }
        for (int a = 0; a < 6; ++a) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.1f, (float)(-0.01f - (float)a * 0.015f), (float)0.35f);
            GL11.glRotatef((float)90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(15 + a % 3 * 2), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glScalef((float)0.5f, (float)0.6f, (float)0.6f);
            UtilsFX.renderQuad("textures/misc/parchment.png", 771, 1.0f);
            GL11.glPopMatrix();
        }
        if (table.func_70301_a(1) != null && table.func_70301_a(1).func_77973_b() == ConfigItems.itemResearchNotes) {
            UtilsFX.bindTexture("textures/models/restable2.png");
            ResearchNoteData rd = ResearchManager.getData(table.func_70301_a(1));
            int color = 0x999999;
            if (rd != null) {
                color = rd.color;
            }
            this.tableModel.renderScroll(color);
        }
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileResearchTable)par1TileEntity, par2, par4, par6, par8);
    }
}

