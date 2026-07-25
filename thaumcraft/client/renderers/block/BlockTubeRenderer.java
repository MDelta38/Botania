/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.blocks.BlockTube;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileBellows;
import thaumcraft.common.tiles.TileCentrifuge;
import thaumcraft.common.tiles.TileEssentiaCrystalizer;
import thaumcraft.common.tiles.TileTube;
import thaumcraft.common.tiles.TileTubeFilter;
import thaumcraft.common.tiles.TileTubeValve;

public class BlockTubeRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        try {
            if (metadata == 0 || metadata == 1 || metadata == 3 || metadata == 5 || metadata == 6) {
                block.func_149676_a(W7, 0.0f, W7, W9, 1.0f, W9);
                renderer.func_147775_a(block);
                BlockTubeRenderer.drawFaces(renderer, block, ((BlockTube)block).icon[metadata == 5 ? 6 : 0], true);
            }
            if (metadata == 6) {
                block.func_149676_a(W7 - 0.001f, 0.1f, W7 - 0.001f, W9 + 0.001f, 0.9f, W9 + 0.001f);
                renderer.func_147775_a(block);
                BlockTubeRenderer.drawFaces(renderer, block, ((BlockTube)block).icon[7], true);
            }
            if (metadata == 0 || metadata == 5 || metadata == 6) {
                block.func_149676_a(W7 - 0.03125f, W7 - 0.03125f, W7 - 0.03125f, W9 + 0.03125f, W9 + 0.03125f, W9 + 0.03125f);
                renderer.func_147775_a(block);
                BlockTubeRenderer.drawFaces(renderer, block, ((BlockTube)block).icon[metadata == 5 ? 6 : 2], true);
            }
            if (metadata == 1) {
                block.func_149676_a(W6, W6, W6, W10, W10, W10);
                renderer.func_147775_a(block);
                BlockTubeRenderer.drawFaces(renderer, block, ((BlockTube)block).icon[1], true);
                GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
                TileTubeValve tc = new TileTubeValve();
                tc.facing = ForgeDirection.EAST;
                TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)tc, 0.0, 0.0, 0.0, 0.0f);
                GL11.glEnable((int)32826);
            }
            if (metadata == 2) {
                GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
                TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileCentrifuge(), 0.0, 0.0, 0.0, 0.0f);
                GL11.glEnable((int)32826);
            }
            if (metadata == 3) {
                block.func_149676_a(W6 - 0.03125f, W6 - 0.03125f, W6 - 0.03125f, W10 + 0.03125f, W10 + 0.03125f, W10 + 0.03125f);
                renderer.func_147775_a(block);
                BlockTubeRenderer.drawFaces(renderer, block, ((BlockTube)block).icon[3], false);
                block.func_149676_a(W6 - 0.03125f, W6 - 0.03125f, W6 - 0.03125f, W10 + 0.03125f, W10 + 0.03125f, W10 + 0.03125f);
                renderer.func_147775_a(block);
                BlockTubeRenderer.drawFaces(renderer, block, ((BlockTube)block).icon[4], false);
            }
            if (metadata == 4) {
                block.func_149676_a(W4, W4, W4, W12, W12, W12);
                renderer.func_147775_a(block);
                BlockTubeRenderer.drawFaces(renderer, block, ((BlockTube)block).icon[5], false);
                block.func_149676_a(W7, 0.0f, W7, W9, 1.0f, W9);
                renderer.func_147775_a(block);
                BlockTubeRenderer.drawFaces(renderer, block, ((BlockTube)block).icon[5], false);
            }
            if (metadata == 7) {
                GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
                TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileEssentiaCrystalizer(), 0.0, 0.0, 0.0, 0.0f);
                GL11.glEnable((int)32826);
            }
        }
        catch (Exception e) {
            // empty catch block
        }
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.func_72805_g(x, y, z);
        if (metadata == 0 || metadata == 1 || metadata == 3 || metadata == 4 || metadata == 5 || metadata == 6) {
            TileEntity te;
            renderer.field_152631_f = true;
            float AX_minx = W7;
            float AX_maxx = W9;
            boolean drawX = false;
            float AX_miny = W7;
            float AX_maxy = W9;
            float AX_minz = W7;
            float AX_maxz = W9;
            float AY_minx = W7;
            float AY_maxx = W9;
            boolean drawY = false;
            float AY_miny = W7;
            float AY_maxy = W9;
            float AY_minz = W7;
            float AY_maxz = W9;
            float AZ_minx = W7;
            float AZ_maxx = W9;
            boolean drawZ = false;
            float AZ_miny = W7;
            float AZ_maxy = W9;
            float AZ_minz = W7;
            float AZ_maxz = W9;
            boolean notConduit = false;
            ForgeDirection fd = null;
            IEssentiaTransport tube = null;
            TileEntity tt = world.func_147438_o(x, y, z);
            if (tt instanceof IEssentiaTransport) {
                tube = (IEssentiaTransport)tt;
            }
            block8: for (int side = 0; side < 6; ++side) {
                fd = ForgeDirection.getOrientation((int)side);
                if (tube != null && !tube.isConnectable(fd) || (te = this.getConnectableTile(world, x, y, z, fd)) == null || metadata != 4 && te instanceof TileBellows) continue;
                if (!(te instanceof TileTube)) {
                    notConduit = true;
                }
                switch (side) {
                    case 0: {
                        AY_miny = 0.0f;
                        drawY = true;
                        if (!(te instanceof IEssentiaTransport) || !((IEssentiaTransport)te).renderExtendedTube()) continue block8;
                        AY_miny = -W6;
                        continue block8;
                    }
                    case 1: {
                        AY_maxy = 1.0f;
                        drawY = true;
                        if (!(te instanceof IEssentiaTransport) || !((IEssentiaTransport)te).renderExtendedTube()) continue block8;
                        AY_maxy = 1.0f + W6;
                        continue block8;
                    }
                    case 2: {
                        AZ_minz = 0.0f;
                        drawZ = true;
                        if (!(te instanceof IEssentiaTransport) || !((IEssentiaTransport)te).renderExtendedTube()) continue block8;
                        AZ_minz = -W6;
                        continue block8;
                    }
                    case 3: {
                        AZ_maxz = 1.0f;
                        drawZ = true;
                        if (!(te instanceof IEssentiaTransport) || !((IEssentiaTransport)te).renderExtendedTube()) continue block8;
                        AZ_maxz = 1.0f + W6;
                        continue block8;
                    }
                    case 4: {
                        AX_minx = 0.0f;
                        drawX = true;
                        if (!(te instanceof IEssentiaTransport) || !((IEssentiaTransport)te).renderExtendedTube()) continue block8;
                        AX_minx = -W6;
                        continue block8;
                    }
                    case 5: {
                        AX_maxx = 1.0f;
                        drawX = true;
                        if (!(te instanceof IEssentiaTransport) || !((IEssentiaTransport)te).renderExtendedTube()) continue block8;
                        AX_maxx = 1.0f + W6;
                    }
                }
            }
            int drawn = 0;
            if (drawX) {
                ++drawn;
                block.func_149676_a(AX_minx, AX_miny, AX_minz, AX_maxx, AX_maxy, AX_maxz);
                renderer.func_147775_a(block);
                renderer.func_147784_q(block, x, y, z);
            }
            if (drawY) {
                ++drawn;
                block.func_149676_a(AY_minx, AY_miny, AY_minz, AY_maxx, AY_maxy, AY_maxz);
                renderer.func_147775_a(block);
                renderer.func_147784_q(block, x, y, z);
            }
            if (drawZ) {
                ++drawn;
                block.func_149676_a(AZ_minx, AZ_miny, AZ_minz, AZ_maxx, AZ_maxy, AZ_maxz);
                renderer.func_147775_a(block);
                renderer.func_147784_q(block, x, y, z);
            }
            if (metadata == 3) {
                renderer.field_147840_d = ((BlockTube)block).icon[3];
                block.func_149676_a(W6 - 0.03125f, W6 - 0.03125f, W6 - 0.03125f, W10 + 0.03125f, W10 + 0.03125f, W10 + 0.03125f);
                renderer.func_147775_a(block);
                renderer.func_147784_q(block, x, y, z);
                te = world.func_147438_o(x, y, z);
                float r = 1.0f;
                float g = 1.0f;
                float b = 1.0f;
                if (te != null && te instanceof TileTubeFilter && ((TileTubeFilter)te).aspectFilter != null) {
                    Color c = new Color(((TileTubeFilter)te).aspectFilter.getColor());
                    r = (float)c.getRed() / 255.0f;
                    g = (float)c.getGreen() / 255.0f;
                    b = (float)c.getBlue() / 255.0f;
                }
                renderer.field_147840_d = ((BlockTube)block).icon[4];
                block.func_149676_a(W6 - 0.03125f, W6 - 0.03125f, W6 - 0.03125f, W10 + 0.03125f, W10 + 0.03125f, W10 + 0.03125f);
                renderer.func_147775_a(block);
                renderer.func_147736_d(block, x, y, z, r, g, b);
            } else if (metadata == 4) {
                block.func_149676_a(W4, W4, W4, W12, W12, W12);
                renderer.func_147775_a(block);
                renderer.func_147784_q(block, x, y, z);
            } else if (drawn == 0 || notConduit || metadata == 1) {
                renderer.field_147840_d = ((BlockTube)block).icon[1];
                block.func_149676_a(W6, W6, W6, W10, W10, W10);
                renderer.func_147775_a(block);
                renderer.func_147784_q(block, x, y, z);
            } else {
                if (metadata != 5) {
                    renderer.field_147840_d = ((BlockTube)block).icon[2];
                }
                block.func_149676_a(W7 - 0.03125f, W7 - 0.03125f, W7 - 0.03125f, W9 + 0.03125f, W9 + 0.03125f, W9 + 0.03125f);
                renderer.func_147775_a(block);
                renderer.func_147784_q(block, x, y, z);
            }
            renderer.field_152631_f = false;
        }
        renderer.func_147771_a();
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147775_a(block);
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return ConfigBlocks.blockTubeRI;
    }

    public TileEntity getConnectableTile(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        TileEntity te = world.func_147438_o(x + face.offsetX, y + face.offsetY, z + face.offsetZ);
        if (te instanceof IEssentiaTransport && ((IEssentiaTransport)te).isConnectable(face.getOpposite())) {
            return te;
        }
        if (te instanceof TileBellows && ((TileBellows)te).orientation == face.getOpposite().ordinal()) {
            return te;
        }
        return null;
    }
}

