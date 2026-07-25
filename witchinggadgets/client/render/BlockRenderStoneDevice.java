/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFenceGate
 *  net.minecraft.block.BlockPane
 *  net.minecraft.block.BlockStairs
 *  net.minecraft.block.BlockWall
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package witchinggadgets.client.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockWall;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.blocks.tiles.TileEntityBlastfurnace;
import witchinggadgets.common.blocks.tiles.TileEntityEtherealWall;
import witchinggadgets.common.blocks.tiles.TileEntityMagicalTileLock;
import witchinggadgets.common.blocks.tiles.TileEntitySarcophagus;
import witchinggadgets.common.blocks.tiles.TileEntityWGBase;

public class BlockRenderStoneDevice
implements ISimpleBlockRenderingHandler {
    public static int renderPass = 0;
    public static int renderID = RenderingRegistry.getNextAvailableRenderId();

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        GL11.glPushMatrix();
        try {
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
            ClientUtilities.drawStandardBlock(block, metadata, renderer);
            GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        }
        catch (Exception e) {
            Tessellator.field_78398_a.func_78381_a();
        }
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        Block blockToRender;
        TileEntityWGBase tile;
        if (world.func_147438_o(x, y, z) instanceof TileEntityEtherealWall) {
            tile = (TileEntityEtherealWall)world.func_147438_o(x, y, z);
            Block block2 = blockToRender = tile.camoID != null ? tile.camoID : block;
            if (!blockToRender.canRenderInPass(renderPass)) {
                return false;
            }
        } else if (renderPass != 0) {
            return false;
        }
        if (world.func_147438_o(x, y, z) instanceof TileEntityEtherealWall) {
            int renderType;
            tile = (TileEntityEtherealWall)world.func_147438_o(x, y, z);
            blockToRender = tile.camoID != null ? tile.camoID : block;
            int metaToRender = tile.camoMeta;
            int n = renderType = blockToRender != null ? blockToRender.func_149645_b() : 0;
            if (tile.camoID == null) {
                return renderer.func_147784_q(block, x, y, z);
            }
            if (renderType == -1) {
                return false;
            }
            blockToRender.func_149719_a(renderer.field_147845_a, x, y, z);
            renderer.func_147775_a(blockToRender);
            int l = block.func_149720_d(world, x, y, z);
            float[] rgb = new float[]{(float)(l >> 16 & 0xFF) / 255.0f, (float)(l >> 8 & 0xFF) / 255.0f, (float)(l & 0xFF) / 255.0f};
            if (EntityRenderer.field_78517_a) {
                float f3 = (rgb[0] * 30.0f + rgb[1] * 59.0f + rgb[2] * 11.0f) / 100.0f;
                float f4 = (rgb[0] * 30.0f + rgb[1] * 70.0f) / 100.0f;
                float f5 = (rgb[0] * 30.0f + rgb[2] * 70.0f) / 100.0f;
                rgb[0] = f3;
                rgb[1] = f4;
                rgb[2] = f5;
            }
            boolean flag = false;
            switch (renderType) {
                case 0: {
                    renderer.func_147751_a(block, x, y, z, rgb[0], rgb[1], rgb[2]);
                    return true;
                }
                case 31: {
                    int i1 = metaToRender & 0xC;
                    if (i1 == 4) {
                        renderer.field_147875_q = 1;
                        renderer.field_147873_r = 1;
                        renderer.field_147867_u = 1;
                        renderer.field_147865_v = 1;
                    } else if (i1 == 8) {
                        renderer.field_147871_s = 1;
                        renderer.field_147869_t = 1;
                    }
                    flag = renderer.func_147784_q(blockToRender, x, y, z);
                    renderer.field_147871_s = 0;
                    renderer.field_147875_q = 0;
                    renderer.field_147873_r = 0;
                    renderer.field_147869_t = 0;
                    renderer.field_147867_u = 0;
                    renderer.field_147865_v = 0;
                    return flag;
                }
                case 39: {
                    if (metaToRender == 3) {
                        renderer.field_147875_q = 1;
                        renderer.field_147873_r = 1;
                        renderer.field_147867_u = 1;
                        renderer.field_147865_v = 1;
                    } else if (metaToRender == 4) {
                        renderer.field_147871_s = 1;
                        renderer.field_147869_t = 1;
                    }
                    flag = renderer.func_147784_q(blockToRender, x, y, z);
                    renderer.field_147871_s = 0;
                    renderer.field_147875_q = 0;
                    renderer.field_147873_r = 0;
                    renderer.field_147869_t = 0;
                    renderer.field_147867_u = 0;
                    renderer.field_147865_v = 0;
                    return flag;
                }
                case 10: {
                    return renderer.func_147722_a((BlockStairs)blockToRender, x, y, z);
                }
                case 32: {
                    return renderer.func_147807_a((BlockWall)blockToRender, x, y, z);
                }
                case 18: {
                    return renderer.func_147767_a((BlockPane)blockToRender, x, y, z);
                }
                case 21: {
                    return renderer.func_147776_a((BlockFenceGate)blockToRender, x, y, z);
                }
            }
            return false;
        }
        if (world.func_147438_o(x, y, z) instanceof TileEntityMagicalTileLock) {
            renderer.func_147782_a(0.0, 0.0, 0.0, 0.25, 1.0, 0.25);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.0, 0.0, 0.75, 0.25, 1.0, 1.0);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.75, 0.0, 0.0, 1.0, 1.0, 0.25);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.75, 0.0, 0.75, 1.0, 1.0, 1.0);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.25, 0.0, 0.0, 0.75, 0.25, 0.25);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.0, 0.0, 0.25, 0.25, 0.25, 0.75);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.25, 0.0, 0.75, 0.75, 0.25, 1.0);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.75, 0.0, 0.25, 1.0, 0.25, 0.75);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.25, 0.75, 0.0, 0.75, 1.0, 0.25);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.0, 0.75, 0.25, 0.25, 1.0, 0.75);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.25, 0.75, 0.75, 0.75, 1.0, 1.0);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.75, 0.75, 0.25, 1.0, 1.0, 0.75);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.0625, 0.0625, 0.0625, 0.9375, 0.9375, 0.9375);
            return renderer.func_147784_q(block, x, y, z);
        }
        if (world.func_147438_o(x, y, z) instanceof TileEntitySarcophagus) {
            tile = (TileEntitySarcophagus)world.func_147438_o(x, y, z);
            switch (((TileEntitySarcophagus)tile).facing) {
                case 2: 
                case 3: {
                    renderer.func_147782_a(((TileEntitySarcophagus)tile).dummyLeft ? 0.0625 : 0.0, 0.0, 0.0625, ((TileEntitySarcophagus)tile).dummyRight ? 0.9375 : 1.0, 0.125, 0.9375);
                    renderer.func_147784_q(block, x, y, z);
                    if (((TileEntitySarcophagus)tile).dummyLeft) {
                        renderer.func_147782_a(0.0, 0.0, 0.0625, 0.0625, 0.75, 0.9375);
                        renderer.func_147784_q(block, x, y, z);
                    } else if (((TileEntitySarcophagus)tile).dummyRight) {
                        renderer.func_147782_a(0.9375, 0.0, 0.0625, 1.0, 0.75, 0.9375);
                        renderer.func_147784_q(block, x, y, z);
                    }
                    renderer.func_147782_a(0.0, 0.0, 0.0, 1.0, 0.75, 0.0625);
                    renderer.func_147784_q(block, x, y, z);
                    renderer.func_147782_a(0.0, 0.0, 0.9375, 1.0, 0.75, 1.0);
                    renderer.func_147784_q(block, x, y, z);
                    if (!((TileEntitySarcophagus)tile).open) {
                        renderer.func_147782_a(((TileEntitySarcophagus)tile).dummyLeft ? 0.0625 : 0.0, 0.75, 0.0625, ((TileEntitySarcophagus)tile).dummyRight ? 0.9375 : 1.0, 1.0, 0.9375);
                        break;
                    }
                    if (((TileEntitySarcophagus)tile).facing == 2) {
                        renderer.func_147782_a(((TileEntitySarcophagus)tile).dummyLeft ? 0.0625 : 0.0, 0.0, 1.0, ((TileEntitySarcophagus)tile).dummyRight ? 0.9375 : 1.0, 0.875, 1.25);
                        break;
                    }
                    renderer.func_147782_a(((TileEntitySarcophagus)tile).dummyLeft ? 0.0625 : 0.0, 0.0, -0.25, ((TileEntitySarcophagus)tile).dummyRight ? 0.9375 : 1.0, 0.875, 0.0);
                    break;
                }
                case 4: 
                case 5: {
                    renderer.func_147782_a(0.0625, 0.0, ((TileEntitySarcophagus)tile).dummyLeft ? 0.0625 : 0.0, 0.9375, 0.125, ((TileEntitySarcophagus)tile).dummyRight ? 0.9375 : 1.0);
                    renderer.func_147784_q(block, x, y, z);
                    if (((TileEntitySarcophagus)tile).dummyLeft) {
                        renderer.func_147782_a(0.0625, 0.0, 0.0, 0.9375, 0.75, 0.0625);
                        renderer.func_147784_q(block, x, y, z);
                    } else if (((TileEntitySarcophagus)tile).dummyRight) {
                        renderer.func_147782_a(0.0625, 0.0, 0.9375, 0.9375, 0.75, 1.0);
                        renderer.func_147784_q(block, x, y, z);
                    }
                    renderer.func_147782_a(0.0, 0.0, 0.0, 0.0625, 0.75, 1.0);
                    renderer.func_147784_q(block, x, y, z);
                    renderer.func_147782_a(0.9375, 0.0, 0.0, 1.0, 0.75, 1.0);
                    renderer.func_147784_q(block, x, y, z);
                    if (!((TileEntitySarcophagus)tile).open) {
                        renderer.func_147782_a(0.0625, 0.75, ((TileEntitySarcophagus)tile).dummyLeft ? 0.0625 : 0.0, 0.9375, 1.0, ((TileEntitySarcophagus)tile).dummyRight ? 0.9375 : 1.0);
                        break;
                    }
                    if (((TileEntitySarcophagus)tile).facing == 4) {
                        renderer.func_147782_a(1.0, 0.0, ((TileEntitySarcophagus)tile).dummyLeft ? 0.0625 : 0.0, 1.25, 0.875, ((TileEntitySarcophagus)tile).dummyRight ? 0.9375 : 1.0);
                        break;
                    }
                    renderer.func_147782_a(-0.25, 0.0, ((TileEntitySarcophagus)tile).dummyLeft ? 0.0625 : 0.0, 0.0, 0.875, ((TileEntitySarcophagus)tile).dummyRight ? 0.9375 : 1.0);
                }
            }
            return renderer.func_147784_q(block, x, y, z);
        }
        if (world.func_72805_g(x, y, z) == 8) {
            byte pos = ((TileEntityBlastfurnace)world.func_147438_o((int)x, (int)y, (int)z)).position;
            if (pos == 22) {
                renderer.func_147782_a(0.0, 0.0, 0.0, 1.0, 0.875, 1.0);
            } else if (pos >= 18) {
                pos = (byte)(pos - 18);
                renderer.func_147782_a(0.0, 0.0, 0.0, 1.0, 0.5, 1.0);
                renderer.func_147784_q(block, x, y, z);
                renderer.func_147782_a(pos % 3 == 0 ? 0.5 : 0.0, 0.5, pos < 3 ? 0.5 : 0.0, (pos + 1) % 3 == 0 ? 0.5 : 1.0, 1.0, pos > 5 ? 0.5 : 1.0);
            } else {
                renderer.func_147782_a(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
            }
        } else {
            block.func_149719_a(world, x, y, z);
            renderer.func_147775_a(block);
            return renderer.func_147784_q(block, x, y, z);
        }
        renderer.func_147784_q(block, x, y, z);
        return false;
    }

    public boolean shouldRender3DInInventory(int modelID) {
        return true;
    }

    public int getRenderId() {
        return renderID;
    }
}

