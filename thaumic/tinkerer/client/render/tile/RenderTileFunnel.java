/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.renderers.block.BlockJarRenderer
 *  thaumcraft.client.renderers.tile.ItemJarFilledRenderer
 */
package thaumic.tinkerer.client.render.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.block.BlockJarRenderer;
import thaumcraft.client.renderers.tile.ItemJarFilledRenderer;
import thaumic.tinkerer.common.block.tile.TileFunnel;

public class RenderTileFunnel
extends TileEntitySpecialRenderer {
    BlockJarRenderer jarRenderer = new BlockJarRenderer();
    ItemJarFilledRenderer jarRenderer1 = new ItemJarFilledRenderer();

    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float f) {
        TileFunnel funnel = (TileFunnel)tileentity;
        ItemStack stack = funnel.func_70301_a(0);
        if (stack != null) {
            GL11.glPushMatrix();
            GL11.glTranslated((double)(d0 + 0.5), (double)(d1 + 0.365), (double)(d2 + 0.5));
            this.jarRenderer1.renderItem(IItemRenderer.ItemRenderType.ENTITY, stack, (Object[])null);
            GL11.glPopMatrix();
        }
    }
}

