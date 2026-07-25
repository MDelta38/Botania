/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.fluids.BlockFluidBase
 */
package thaumcraft.client.renderers.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidBase;
import thaumcraft.common.blocks.BlockFluxGas;
import thaumcraft.common.config.ConfigBlocks;

public class BlockGasRenderer
implements ISimpleBlockRenderingHandler {
    public static BlockGasRenderer instance = new BlockGasRenderer();
    static final float LIGHT_Y_NEG = 0.5f;
    static final float LIGHT_Y_POS = 1.0f;
    static final float LIGHT_XZ_NEG = 0.8f;
    static final float LIGHT_XZ_POS = 0.6f;
    static final double RENDER_OFFSET = (double)0.001f;

    public float getFluidHeightAverage(float[] flow) {
        float total = 0.0f;
        int count = 0;
        float end = 0.0f;
        for (int i = 0; i < flow.length; ++i) {
            if (flow[i] >= 0.875f && end != 1.0f) {
                end = flow[i];
            }
            if (!(flow[i] >= 0.0f)) continue;
            total += flow[i];
            ++count;
        }
        if (end == 0.0f) {
            end = total / (float)count;
        }
        return end;
    }

    public float getFluidHeightForRender(IBlockAccess world, int x, int y, int z, BlockFluxGas block) {
        if (world.func_147439_a(x, y, z) == block) {
            if (world.func_147439_a(x, y - block.getDensityDir(), z).func_149688_o().func_76224_d()) {
                return 1.0f;
            }
            if (world.func_72805_g(x, y, z) == block.getMaxRenderHeightMeta()) {
                return 0.875f;
            }
        }
        return !world.func_147439_a(x, y, z).func_149688_o().func_76220_a() && world.func_147439_a(x, y - block.getDensityDir(), z) == block ? 1.0f : block.getQuantaPercentage(world, x, y, z) * 0.875f;
    }

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        boolean rises;
        double heightNE;
        double heightSE;
        double heightSW;
        double heightNW;
        if (!(block instanceof BlockFluxGas)) {
            return false;
        }
        Tessellator tessellator = Tessellator.field_78398_a;
        int color = block.func_149720_d(world, x, y, z);
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float green = (float)(color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(color & 0xFF) / 255.0f;
        BlockFluxGas theFluid = (BlockFluxGas)block;
        int bMeta = world.func_72805_g(x, y, z);
        if (!world.isSideSolid(x, y + theFluid.getDensityDir(), z, ForgeDirection.DOWN, false)) {
            tessellator.func_78380_c(block.func_149677_c(world, x, y, z));
            tessellator.func_78386_a(1.0f * red, 1.0f * green, 1.0f * blue);
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147771_a();
            renderer.func_147775_a(block);
            return true;
        }
        boolean renderTop = world.func_147439_a(x, y - theFluid.getDensityDir(), z) != theFluid;
        boolean renderBottom = block.func_149646_a(world, x, y + theFluid.getDensityDir(), z, 0) && world.func_147439_a(x, y + theFluid.getDensityDir(), z) != theFluid;
        boolean[] renderSides = new boolean[]{block.func_149646_a(world, x, y, z - 1, 2), block.func_149646_a(world, x, y, z + 1, 3), block.func_149646_a(world, x - 1, y, z, 4), block.func_149646_a(world, x + 1, y, z, 5)};
        if (!(renderTop || renderBottom || renderSides[0] || renderSides[1] || renderSides[2] || renderSides[3])) {
            return false;
        }
        boolean rendered = false;
        float flow11 = this.getFluidHeightForRender(world, x, y, z, theFluid);
        if (flow11 != 1.0f) {
            float flow00 = this.getFluidHeightForRender(world, x - 1, y, z - 1, theFluid);
            float flow01 = this.getFluidHeightForRender(world, x - 1, y, z, theFluid);
            float flow02 = this.getFluidHeightForRender(world, x - 1, y, z + 1, theFluid);
            float flow10 = this.getFluidHeightForRender(world, x, y, z - 1, theFluid);
            float flow12 = this.getFluidHeightForRender(world, x, y, z + 1, theFluid);
            float flow20 = this.getFluidHeightForRender(world, x + 1, y, z - 1, theFluid);
            float flow21 = this.getFluidHeightForRender(world, x + 1, y, z, theFluid);
            float flow22 = this.getFluidHeightForRender(world, x + 1, y, z + 1, theFluid);
            heightNW = this.getFluidHeightAverage(new float[]{flow00, flow01, flow10, flow11});
            heightSW = this.getFluidHeightAverage(new float[]{flow01, flow02, flow12, flow11});
            heightSE = this.getFluidHeightAverage(new float[]{flow12, flow21, flow22, flow11});
            heightNE = this.getFluidHeightAverage(new float[]{flow10, flow20, flow21, flow11});
        } else {
            heightNW = flow11;
            heightSW = flow11;
            heightSE = flow11;
            heightNE = flow11;
        }
        boolean bl = rises = theFluid.getDensityDir() == 1;
        if (renderer.field_147837_f || renderTop) {
            double v3;
            double u3;
            double v4;
            double u4;
            double v1;
            double u1;
            double v2;
            double u2;
            rendered = true;
            IIcon iconStill = block.func_149691_a(1, bMeta);
            float flowDir = (float)BlockFluidBase.getFlowDirection((IBlockAccess)world, (int)x, (int)y, (int)z);
            if (flowDir > -999.0f) {
                iconStill = block.func_149691_a(2, bMeta);
            }
            heightNW -= (double)0.001f;
            heightSW -= (double)0.001f;
            heightSE -= (double)0.001f;
            heightNE -= (double)0.001f;
            if (flowDir < -999.0f) {
                u2 = iconStill.func_94214_a(0.0);
                v2 = iconStill.func_94207_b(0.0);
                u1 = u2;
                v1 = iconStill.func_94207_b(16.0);
                u4 = iconStill.func_94214_a(16.0);
                v4 = v1;
                u3 = u4;
                v3 = v2;
            } else {
                float xFlow = MathHelper.func_76126_a((float)flowDir) * 0.25f;
                float zFlow = MathHelper.func_76134_b((float)flowDir) * 0.25f;
                u2 = iconStill.func_94214_a((double)(8.0f + (-zFlow - xFlow) * 16.0f));
                v2 = iconStill.func_94207_b((double)(8.0f + (-zFlow + xFlow) * 16.0f));
                u1 = iconStill.func_94214_a((double)(8.0f + (-zFlow + xFlow) * 16.0f));
                v1 = iconStill.func_94207_b((double)(8.0f + (zFlow + xFlow) * 16.0f));
                u4 = iconStill.func_94214_a((double)(8.0f + (zFlow + xFlow) * 16.0f));
                v4 = iconStill.func_94207_b((double)(8.0f + (zFlow - xFlow) * 16.0f));
                u3 = iconStill.func_94214_a((double)(8.0f + (zFlow - xFlow) * 16.0f));
                v3 = iconStill.func_94207_b((double)(8.0f + (-zFlow - xFlow) * 16.0f));
            }
            tessellator.func_78380_c(block.func_149677_c(world, x, y, z));
            tessellator.func_78386_a(1.0f * red, 1.0f * green, 1.0f * blue);
            if (!rises) {
                tessellator.func_78374_a((double)(x + 0), (double)y + heightNW, (double)(z + 0), u2, v2);
                tessellator.func_78374_a((double)(x + 0), (double)y + heightSW, (double)(z + 1), u1, v1);
                tessellator.func_78374_a((double)(x + 1), (double)y + heightSE, (double)(z + 1), u4, v4);
                tessellator.func_78374_a((double)(x + 1), (double)y + heightNE, (double)(z + 0), u3, v3);
            } else {
                tessellator.func_78374_a((double)(x + 1), (double)(y + 1) - heightNE, (double)(z + 0), u3, v3);
                tessellator.func_78374_a((double)(x + 1), (double)(y + 1) - heightSE, (double)(z + 1), u4, v4);
                tessellator.func_78374_a((double)(x + 0), (double)(y + 1) - heightSW, (double)(z + 1), u1, v1);
                tessellator.func_78374_a((double)(x + 0), (double)(y + 1) - heightNW, (double)(z + 0), u2, v2);
            }
        }
        if (renderer.field_147837_f || renderBottom) {
            rendered = true;
            tessellator.func_78380_c(block.func_149677_c(world, x, y - 1, z));
            if (!rises) {
                tessellator.func_78386_a(0.5f * red, 0.5f * green, 0.5f * blue);
                renderer.func_147768_a(block, (double)x, (double)y + (double)0.001f, (double)z, block.func_149691_a(0, bMeta));
            } else {
                tessellator.func_78386_a(1.0f * red, 1.0f * green, 1.0f * blue);
                renderer.func_147806_b(block, (double)x, (double)y + (double)0.001f, (double)z, block.func_149691_a(1, bMeta));
            }
        }
        for (int side = 0; side < 4; ++side) {
            double tz2;
            double tz1;
            double tx2;
            double tx1;
            double ty2;
            double ty1;
            int x2 = x;
            int z2 = z;
            switch (side) {
                case 0: {
                    --z2;
                    break;
                }
                case 1: {
                    ++z2;
                    break;
                }
                case 2: {
                    --x2;
                    break;
                }
                case 3: {
                    ++x2;
                }
            }
            IIcon iconFlow = block.func_149691_a(side + 2, bMeta);
            if (!renderer.field_147837_f && !renderSides[side]) continue;
            rendered = true;
            if (side == 0) {
                ty1 = heightNW;
                ty2 = heightNE;
                tx1 = x;
                tx2 = x + 1;
                tz1 = (double)z + (double)0.001f;
                tz2 = (double)z + (double)0.001f;
            } else if (side == 1) {
                ty1 = heightSE;
                ty2 = heightSW;
                tx1 = x + 1;
                tx2 = x;
                tz1 = (double)(z + 1) - (double)0.001f;
                tz2 = (double)(z + 1) - (double)0.001f;
            } else if (side == 2) {
                ty1 = heightSW;
                ty2 = heightNW;
                tx1 = (double)x + (double)0.001f;
                tx2 = (double)x + (double)0.001f;
                tz1 = z + 1;
                tz2 = z;
            } else {
                ty1 = heightNE;
                ty2 = heightSE;
                tx1 = (double)(x + 1) - (double)0.001f;
                tx2 = (double)(x + 1) - (double)0.001f;
                tz1 = z;
                tz2 = z + 1;
            }
            float u1Flow = iconFlow.func_94214_a(0.0);
            float u2Flow = iconFlow.func_94214_a(8.0);
            float v1Flow = iconFlow.func_94207_b((1.0 - ty1) * 16.0 * 0.5);
            float v2Flow = iconFlow.func_94207_b((1.0 - ty2) * 16.0 * 0.5);
            float v3Flow = iconFlow.func_94207_b(8.0);
            tessellator.func_78380_c(block.func_149677_c(world, x2, y, z2));
            float sideLighting = 1.0f;
            sideLighting = side < 2 ? 0.8f : 0.6f;
            tessellator.func_78386_a(1.0f * sideLighting * red, 1.0f * sideLighting * green, 1.0f * sideLighting * blue);
            if (!rises) {
                tessellator.func_78374_a(tx1, (double)y + ty1, tz1, (double)u1Flow, (double)v1Flow);
                tessellator.func_78374_a(tx2, (double)y + ty2, tz2, (double)u2Flow, (double)v2Flow);
                tessellator.func_78374_a(tx2, (double)(y + 0), tz2, (double)u2Flow, (double)v3Flow);
                tessellator.func_78374_a(tx1, (double)(y + 0), tz1, (double)u1Flow, (double)v3Flow);
                continue;
            }
            tessellator.func_78374_a(tx1, (double)(y + 1 - 0), tz1, (double)u1Flow, (double)v3Flow);
            tessellator.func_78374_a(tx2, (double)(y + 1 - 0), tz2, (double)u2Flow, (double)v3Flow);
            tessellator.func_78374_a(tx2, (double)(y + 1) - ty2, tz2, (double)u2Flow, (double)v2Flow);
            tessellator.func_78374_a(tx1, (double)(y + 1) - ty1, tz1, (double)u1Flow, (double)v1Flow);
        }
        renderer.field_147855_j = 0.0;
        renderer.field_147857_k = 1.0;
        return rendered;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return ConfigBlocks.blockFluxGasRI;
    }
}

