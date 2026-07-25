/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.Aspect
 */
package thaumic.tinkerer.client.render.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumic.tinkerer.client.lib.LibRenderIDs;
import thaumic.tinkerer.common.block.BlockInfusedGrain;

public class RenderInfusedCrops
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        Aspect aspect = BlockInfusedGrain.getAspect(world, x, y, z);
        GL11.glPushAttrib((int)16384);
        if (aspect != null && !aspect.isPrimal()) {
            float r = (float)(aspect.getColor() >> 16 & 0xFF) / 255.0f;
            float g = (float)(aspect.getColor() >> 8 & 0xFF) / 255.0f;
            float b = (float)(aspect.getColor() & 0xFF) / 255.0f;
            GL11.glColor4f((float)r, (float)g, (float)b, (float)1.0f);
            Tessellator.field_78398_a.func_78384_a(aspect.getColor(), 255);
        }
        renderer.func_147757_a(block.func_149673_e(world, x, y, z, world.func_72805_g(x, y, z)));
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78380_c(block.func_149677_c(world, x, y, z));
        renderer.func_147795_a(block, world.func_72805_g(x, y, z), (double)x, (double)((float)y - 0.0625f), (double)z);
        renderer.func_147771_a();
        GL11.glPopAttrib();
        GL11.glPopMatrix();
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return LibRenderIDs.idGrain;
    }
}

