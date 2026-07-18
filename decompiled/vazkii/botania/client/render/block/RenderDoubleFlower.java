/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDoublePlant
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 */
package vazkii.botania.client.render.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.client.render.block.RenderSpecialFlower;

public class RenderDoubleFlower
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int p_147774_2_, int p_147774_3_, int p_147774_4_, Block block, int modelId, RenderBlocks renderer) {
        BlockDoublePlant p_147774_1_ = (BlockDoublePlant)block;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78380_c(p_147774_1_.func_149677_c(world, p_147774_2_, p_147774_3_, p_147774_4_));
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        long j1 = (long)(p_147774_2_ * 3129871) ^ (long)p_147774_4_ * 116129781L;
        j1 = j1 * j1 * 42317861L + j1 * 11L;
        int i1 = world.func_72805_g(p_147774_2_, p_147774_3_, p_147774_4_);
        boolean flag1 = BlockDoublePlant.func_149887_c((int)i1);
        if (flag1) {
            if (world.func_147439_a(p_147774_2_, p_147774_3_ - 1, p_147774_4_) != p_147774_1_) {
                return false;
            }
            BlockDoublePlant.func_149890_d((int)world.func_72805_g(p_147774_2_, p_147774_3_ - 1, p_147774_4_));
        } else {
            BlockDoublePlant.func_149890_d((int)i1);
        }
        IIcon icon = renderer.func_147793_a(block, world, p_147774_2_, p_147774_3_, p_147774_4_, 0);
        RenderSpecialFlower.drawCrossedSquares(world, block, icon, p_147774_2_, p_147774_3_, p_147774_4_, p_147774_2_, p_147774_3_, p_147774_4_, 1.0f, renderer);
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return LibRenderIDs.idDoubleFlower;
    }
}

