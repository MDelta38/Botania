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

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.model.ModelMiniIsland;
import vazkii.botania.common.block.decor.IFloatingFlower;

public class RenderTileFloatingFlower
extends TileEntitySpecialRenderer {
    private static final ModelMiniIsland model = new ModelMiniIsland();

    public void func_147500_a(TileEntity tile, double d0, double d1, double d2, float t) {
        double worldTime;
        IFloatingFlower flower = (IFloatingFlower)tile;
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)d0, (double)d1, (double)d2);
        double d = worldTime = tile.func_145831_w() == null ? 0.0 : (double)((float)ClientTickHandler.ticksInGame + t);
        if (tile.func_145831_w() != null) {
            worldTime += (double)new Random(tile.field_145851_c ^ tile.field_145848_d ^ tile.field_145849_e).nextInt(1000);
        }
        GL11.glTranslatef((float)0.5f, (float)0.0f, (float)0.5f);
        GL11.glRotatef((float)(-((float)worldTime * 0.5f)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)0.0f, (float)-0.5f);
        if (tile.func_145831_w() != null) {
            GL11.glTranslatef((float)0.0f, (float)((float)Math.sin(worldTime * (double)0.05f) * 0.1f), (float)0.0f);
            GL11.glRotatef((float)(4.0f * (float)Math.sin(worldTime * (double)0.04f)), (float)1.0f, (float)0.0f, (float)0.0f);
        }
        Minecraft.func_71410_x().field_71446_o.func_110577_a(flower.getIslandType().getResource());
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.5f, (float)1.4f, (float)0.5f);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        model.render();
        GL11.glPopMatrix();
        ItemStack stack = flower.getDisplayStack();
        IIcon icon = stack.func_77954_c();
        Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
        float f = icon.func_94209_e();
        float f1 = icon.func_94212_f();
        float f2 = icon.func_94206_g();
        float f3 = icon.func_94210_h();
        GL11.glTranslatef((float)0.25f, (float)0.4f, (float)0.5f);
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.03125f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }
}

