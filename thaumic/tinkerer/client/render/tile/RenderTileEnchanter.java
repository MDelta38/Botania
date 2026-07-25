/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.renderers.item.ItemWandRenderer
 */
package thaumic.tinkerer.client.render.tile;

import java.awt.Color;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.item.ItemWandRenderer;
import thaumic.tinkerer.client.core.helper.ClientHelper;
import thaumic.tinkerer.common.block.tile.TileEnchanter;

public class RenderTileEnchanter
extends TileEntitySpecialRenderer {
    ItemWandRenderer wandRenderer = new ItemWandRenderer();

    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float partTicks) {
        float scale;
        TileEnchanter enchanter = (TileEnchanter)tileentity;
        GL11.glPushMatrix();
        GL11.glTranslated((double)d0, (double)(d1 + 0.75), (double)d2);
        ItemStack item = enchanter.func_70301_a(0);
        if (item != null) {
            GL11.glPushMatrix();
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            scale = 0.7f;
            GL11.glScalef((float)0.7f, (float)0.7f, (float)0.7f);
            GL11.glTranslatef((float)0.6f, (float)-0.2f, (float)0.0f);
            GL11.glRotatef((float)30.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            ClientHelper.minecraft().field_71446_o.func_110577_a(TextureMap.field_110576_c);
            int renderPass = 0;
            do {
                IIcon icon;
                if ((icon = item.func_77973_b().getIcon(item, renderPass)) == null) continue;
                Color color = new Color(item.func_77973_b().func_82790_a(item, renderPass));
                GL11.glColor3ub((byte)((byte)color.getRed()), (byte)((byte)color.getGreen()), (byte)((byte)color.getBlue()));
                float f = icon.func_94209_e();
                float f1 = icon.func_94212_f();
                float f2 = icon.func_94206_g();
                float f3 = icon.func_94210_h();
                ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
                GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            } while (++renderPass < item.func_77973_b().getRenderPasses(item.func_77960_j()));
            GL11.glPopMatrix();
        }
        if ((item = enchanter.func_70301_a(1)) != null) {
            GL11.glPushMatrix();
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            scale = 0.5f;
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            GL11.glTranslatef((float)0.6f, (float)1.5f, (float)-0.1f);
            GL11.glRotatef((float)-70.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            long millis = System.currentTimeMillis();
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)((float)(Math.cos((double)millis / 1000.0) - (double)1.2f) / 10.0f));
            GL11.glTranslatef((float)0.0f, (float)0.325f, (float)0.0f);
            GL11.glRotatef((float)((float)Math.cos((double)millis / 500.0) * 5.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)-0.325f, (float)0.0f);
            this.wandRenderer.renderItem(IItemRenderer.ItemRenderType.ENTITY, item, (Object[])null);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }
}

