/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.mana.ILens;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.render.item.RenderLens;
import vazkii.botania.common.block.tile.mana.TilePrism;

public class RenderTilePrism
extends TileEntitySpecialRenderer {
    public void func_147500_a(TileEntity tile, double x, double y, double z, float partTicks) {
        TilePrism prism = (TilePrism)tile;
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        float pos = (float)Math.sin(((float)ClientTickHandler.ticksInGame + partTicks) * 0.05f) * 0.5f * 0.9375f - 0.5f;
        ItemStack stack = prism.func_70301_a(0);
        if (stack != null) {
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
            if (stack.func_77973_b() instanceof ILens) {
                ILens lens = (ILens)stack.func_77973_b();
                GL11.glPushMatrix();
                GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)pos);
                RenderLens.render(stack, lens.getLensColor(stack));
                GL11.glPopMatrix();
            }
        }
        GL11.glPopMatrix();
    }
}

