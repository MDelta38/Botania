/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.tile;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;
import vazkii.botania.common.block.tile.TileSparkChanger;

public class RenderTileSparkChanger
extends TileEntitySpecialRenderer {
    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float pticks) {
        GL11.glPushMatrix();
        GL11.glTranslated((double)d0, (double)d1, (double)d2);
        GL11.glRotated((double)90.0, (double)1.0, (double)0.0, (double)0.0);
        GL11.glTranslatef((float)0.8f, (float)0.2f, (float)-0.22f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ItemStack stack = ((TileSparkChanger)tileentity).func_70301_a(0);
        if (stack != null) {
            GL11.glPushMatrix();
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
            float s = 0.6f;
            GL11.glScalef((float)s, (float)s, (float)s);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            int renderPass = 0;
            do {
                IIcon icon;
                if ((icon = stack.func_77973_b().getIcon(stack, renderPass)) == null) continue;
                Color color = new Color(stack.func_77973_b().func_82790_a(stack, renderPass));
                GL11.glColor3ub((byte)((byte)color.getRed()), (byte)((byte)color.getGreen()), (byte)((byte)color.getBlue()));
                float f = icon.func_94209_e();
                float f1 = icon.func_94212_f();
                float f2 = icon.func_94206_g();
                float f3 = icon.func_94210_h();
                ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
                GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            } while (++renderPass < stack.func_77973_b().getRenderPasses(stack.func_77960_j()));
            GL11.glPopMatrix();
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }
}

