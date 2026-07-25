/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package witchinggadgets.client.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import witchinggadgets.client.ClientUtilities;

public class BlockRenderRoseVine
implements ISimpleBlockRenderingHandler {
    public static int renderID = RenderingRegistry.getNextAvailableRenderId();

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        boolean invertXZ = true;
        boolean mirror = false;
        GL11.glPushMatrix();
        GL11.glRotated((double)-65.0, (double)0.0, (double)1.0, (double)0.0);
        GL11.glScaled((double)1.5, (double)1.5, (double)1.5);
        GL11.glTranslated((double)0.0, (double)-0.0, (double)-0.6);
        ClientUtilities.drawSubBlockInInventory(0.375, 0.0, 0.375, 0.5, 0.1875, 0.5, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.4375, 0.1875, 0.5, 0.5625, 0.3125, 0.5625, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.5, 0.1875, 0.5625, 0.625, 0.3125, 0.625, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.5625, 0.125, 0.625, 0.625, 0.25, 0.6875, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.625, 0.0625, 0.6875, 0.6875, 0.1875, 0.75, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.6875, 0.0, 0.75, 0.75, 0.125, 0.8125, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.75, 0.0, 0.625, 0.8125, 0.125, 0.6875, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.75, 0.125, 0.6875, 0.8125, 0.1875, 0.75, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.8125, 0.125, 0.6875, 0.875, 0.25, 0.75, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.25, 0.0, 0.625, 0.3125, 0.125, 0.6875, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.25, 0.125, 0.6875, 0.3125, 0.1875, 0.75, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.1875, 0.125, 0.6875, 0.25, 0.25, 0.75, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.125, 0.125, 0.75, 0.1875, 0.1875, 0.8125, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.0625, 0.0, 0.75, 0.125, 0.125, 0.8125, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.125, 0.0, 0.125, 0.25, 0.125, 0.25, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.1875, 0.125, 0.1875, 0.3125, 0.1875, 0.3125, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.25, 0.1875, 0.1875, 0.375, 0.25, 0.3125, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.375, 0.25, 0.1875, 0.625, 0.3125, 0.3125, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.625, 0.1875, 0.25, 0.75, 0.25, 0.375, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.75, 0.0625, 0.25, 0.8125, 0.1875, 0.375, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.0625, 0.0, 0.4375, 0.1875, 0.1875, 0.5625, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.125, 0.1875, 0.5, 0.25, 0.25, 0.625, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.1875, 0.25, 0.5625, 0.375, 0.3125, 0.6875, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.3125, 0.1875, 0.625, 0.5, 0.25, 0.75, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.4375, 0.0625, 0.6875, 0.5625, 0.1875, 0.8125, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.5, 0.0, 0.75, 0.625, 0.0625, 0.875, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.625, 0.0, 0.0625, 0.75, 0.125, 0.125, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.625, 0.125, 0.125, 0.75, 0.25, 0.1875, invertXZ, mirror, block, renderer);
        ClientUtilities.drawSubBlockInInventory(0.625, 0.25, 0.125, 0.6875, 0.3125, 0.1875, invertXZ, mirror, block, renderer);
        GL11.glPopMatrix();
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int growthState = world.func_72805_g(x, y, z);
        if (world.isSideSolid(x, y - 1, z, ForgeDirection.UP, false)) {
            this.drawFloorVines(x, y, z, block, growthState, renderer);
        }
        if (world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, false)) {
            this.drawWallVines(x, y, z, block, growthState, renderer, false, false);
        }
        if (world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, false)) {
            this.drawWallVines(x, y, z, block, growthState, renderer, false, true);
        }
        if (world.isSideSolid(x - 1, y, z, ForgeDirection.WEST, false)) {
            this.drawWallVines(x, y, z, block, growthState, renderer, true, false);
        }
        if (world.isSideSolid(x + 1, y, z, ForgeDirection.EAST, false)) {
            this.drawWallVines(x, y, z, block, growthState, renderer, true, true);
        }
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return renderID;
    }

    private void drawWallVines(int x, int y, int z, Block block, int growthState, RenderBlocks renderer, boolean invertXZ, boolean mirror) {
        if (growthState == 0) {
            ClientUtilities.drawSubBlock(0.125, 0.0, 0.0, 0.1875, 0.125, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.1875, 0.125, 0.0, 0.25, 0.1875, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.3125, 0.0, 0.0, 0.375, 0.1875, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.375, 0.1875, 0.0, 0.4375, 0.3125, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
        } else if (growthState == 1) {
            ClientUtilities.drawSubBlock(0.125, 0.0, 0.0, 0.1875, 0.125, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.1875, 0.125, 0.0, 0.25, 0.1875, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.3125, 0.0, 0.0, 0.375, 0.125, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.375, 0.125, 0.0625, 0.4375, 0.375, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.4375, 0.3125, 0.0, 0.5, 0.4375, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.0, 0.0, 0.8125, 0.125, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.6875, 0.125, 0.0, 0.75, 0.25, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
        } else if (growthState == 2) {
            ClientUtilities.drawSubBlock(0.125, 0.0, 0.0625, 0.1875, 0.125, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.1875, 0.125, 0.0625, 0.25, 0.25, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.1875, 0.25, 0.0, 0.25, 0.3125, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.3125, 0.0, 0.0, 0.375, 0.125, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.375, 0.125, 0.0625, 0.4375, 0.375, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.4375, 0.3125, 0.0, 0.5, 0.4375, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.0, 0.0, 0.8125, 0.125, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.6875, 0.125, 0.0625, 0.75, 0.375, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.6875, 0.375, 0.0, 0.75, 0.5, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
        } else if (growthState == 3) {
            ClientUtilities.drawSubBlock(0.125, 0.0, 0.0625, 0.1875, 0.125, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.125, 0.125, 0.0625, 0.1875, 0.25, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.0625, 0.25, 0.0, 0.125, 0.4375, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.3125, 0.0, 0.0, 0.375, 0.125, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.375, 0.125, 0.0625, 0.4375, 0.375, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.25, 0.3125, 0.0, 0.375, 0.5, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.0, 0.0, 0.8125, 0.125, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.6875, 0.125, 0.0625, 0.75, 0.375, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.6875, 0.375, 0.0, 0.75, 0.5, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.625, 0.5, 0.0, 0.6875, 0.625, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
        } else if (growthState == 4) {
            ClientUtilities.drawSubBlock(0.125, 0.0, 0.0625, 0.1875, 0.125, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.125, 0.125, 0.0625, 0.1875, 0.25, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.0625, 0.25, 0.0, 0.125, 0.4375, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.125, 0.4375, 0.0, 0.1875, 0.5625, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.3125, 0.0, 0.0, 0.375, 0.125, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.375, 0.0625, 0.0625, 0.4375, 0.4375, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.25, 0.3125, 0.0, 0.375, 0.5, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.4375, 0.4375, 0.0625, 0.5, 0.5, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.5, 0.5, 0.0625, 0.5625, 0.75, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.0, 0.0, 0.8125, 0.25, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.6875, 0.25, 0.125, 0.75, 0.4375, 0.1875, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.6875, 0.4375, 0.0625, 0.75, 0.625, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.625, 0.625, 0.0, 0.6875, 0.75, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.5625, 0.75, 0.0, 0.625, 0.8125, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
        } else if (growthState == 5) {
            ClientUtilities.drawSubBlock(0.125, 0.0, 0.125, 0.1875, 0.125, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.125, 0.0625, 0.0625, 0.1875, 0.25, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.0625, 0.25, 0.0, 0.125, 0.4375, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.125, 0.4375, 0.0, 0.1875, 0.5625, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.0625, 0.5625, 0.0, 0.125, 0.75, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.125, 0.75, 0.0, 0.1875, 0.8125, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.1875, 0.8125, 0.0, 0.25, 0.9375, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.125, 0.9375, 0.0, 0.1875, 1.0, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.25, 0.9375, 0.0, 0.375, 1.0, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.3125, 0.0, 0.0, 0.375, 0.125, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.375, 0.125, 0.0625, 0.4375, 0.375, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.375, 0.25, 0.0625, 0.4375, 0.4375, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.25, 0.3125, 0.0, 0.375, 0.5, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.4375, 0.4375, 0.0625, 0.5, 0.5, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.5, 0.5, 0.0625, 0.5625, 0.75, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.3125, 0.5, 0.0625, 0.375, 0.625, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.25, 0.625, 0.0, 0.3125, 0.8125, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.0, 0.0, 0.8125, 0.25, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.6875, 0.25, 0.0625, 0.75, 0.4375, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.6875, 0.4375, 0.0625, 0.75, 0.625, 0.125, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.625, 0.625, 0.0, 0.6875, 0.75, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.5625, 0.75, 0.0, 0.625, 0.8125, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.6875, 0.75, 0.0, 0.75, 0.875, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.8125, 0.0, 0.8125, 1.0, 0.0625, invertXZ, mirror, x, y, z, block, renderer);
        }
    }

    private void drawFloorVines(int x, int y, int z, Block block, int growthState, RenderBlocks renderer) {
        if (growthState == 0) {
            ClientUtilities.drawSubBlock(0.375, 0.0, 0.375, 0.5, 0.1875, 0.5, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.375, 0.1875, 0.375, 0.4375, 0.3125, 0.4375, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.375, 0.3125, 0.4375, 0.5, 0.375, 0.5, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.0, 0.625, 0.8125, 0.125, 0.6875, false, false, x, y, z, block, renderer);
        } else if (growthState == 1) {
            ClientUtilities.drawSubBlock(0.375, 0.0, 0.3705, 0.5, 0.1875, 0.5, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.4375, 0.1875, 0.5, 0.5625, 0.3125, 0.5625, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.5, 0.1875, 0.5625, 0.625, 0.3125, 0.625, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.5625, 0.1875, 0.625, 0.625, 0.3125, 0.6875, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.0, 0.625, 0.8125, 0.125, 0.6875, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.125, 0.6875, 0.8125, 0.1875, 0.75, false, false, x, y, z, block, renderer);
        } else if (growthState == 2) {
            ClientUtilities.drawSubBlock(0.375, 0.0, 0.375, 0.5, 0.1875, 0.5, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.4375, 0.1875, 0.5, 0.5625, 0.3125, 0.5625, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.5, 0.1875, 0.5625, 0.625, 0.3125, 0.625, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.5625, 0.125, 0.625, 0.625, 0.25, 0.6875, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.625, 0.0625, 0.6875, 0.6875, 0.1875, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.6875, 0.0, 0.75, 0.75, 0.125, 0.8125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.0, 0.625, 0.8125, 0.125, 0.6875, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.125, 0.6875, 0.8125, 0.1875, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.8125, 0.125, 0.6875, 0.875, 0.25, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.25, 0.0, 0.625, 0.3125, 0.125, 0.6875, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.25, 0.125, 0.6875, 0.3125, 0.1875, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.1875, 0.125, 0.6875, 0.25, 0.25, 0.75, false, false, x, y, z, block, renderer);
        } else if (growthState == 3) {
            ClientUtilities.drawSubBlock(0.375, 0.0, 0.375, 0.5, 0.1875, 0.5, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.4375, 0.1875, 0.5, 0.5625, 0.3125, 0.5625, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.5, 0.1875, 0.5625, 0.625, 0.3125, 0.625, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.5625, 0.125, 0.625, 0.625, 0.25, 0.6875, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.625, 0.0625, 0.6875, 0.6875, 0.1875, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.6875, 0.0, 0.75, 0.75, 0.125, 0.8125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.0, 0.625, 0.8125, 0.125, 0.6875, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.125, 0.6875, 0.8125, 0.1875, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.8125, 0.125, 0.6875, 0.875, 0.25, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.25, 0.0, 0.625, 0.3125, 0.125, 0.6875, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.25, 0.125, 0.6875, 0.3125, 0.1875, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.1875, 0.125, 0.6875, 0.25, 0.25, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.125, 0.125, 0.75, 0.1875, 0.1875, 0.8125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.0625, 0.0, 0.75, 0.125, 0.125, 0.8125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.125, 0.0, 0.125, 0.25, 0.125, 0.25, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.1875, 0.125, 0.1875, 0.3125, 0.1875, 0.3125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.25, 0.1875, 0.1875, 0.375, 0.25, 0.3125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.375, 0.25, 0.1875, 0.625, 0.3125, 0.3125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.625, 0.1875, 0.25, 0.75, 0.25, 0.375, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.0625, 0.25, 0.8125, 0.1875, 0.375, false, false, x, y, z, block, renderer);
        } else if (growthState == 4) {
            ClientUtilities.drawSubBlock(0.375, 0.0, 0.375, 0.5, 0.1875, 0.5, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.4375, 0.1875, 0.5, 0.5625, 0.3125, 0.5625, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.5, 0.1875, 0.5625, 0.625, 0.3125, 0.625, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.5625, 0.125, 0.625, 0.625, 0.25, 0.6875, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.625, 0.0625, 0.6875, 0.6875, 0.1875, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.6875, 0.0, 0.75, 0.75, 0.125, 0.8125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.0, 0.625, 0.8125, 0.125, 0.6875, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.125, 0.6875, 0.8125, 0.1875, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.8125, 0.125, 0.6875, 0.875, 0.25, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.25, 0.0, 0.625, 0.3125, 0.125, 0.6875, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.25, 0.125, 0.6875, 0.3125, 0.1875, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.1875, 0.125, 0.6875, 0.25, 0.25, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.125, 0.125, 0.75, 0.1875, 0.1875, 0.8125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.0625, 0.0, 0.75, 0.125, 0.125, 0.8125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.125, 0.0, 0.125, 0.25, 0.125, 0.25, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.1875, 0.125, 0.1875, 0.3125, 0.1875, 0.3125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.25, 0.1875, 0.1875, 0.375, 0.25, 0.3125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.375, 0.25, 0.1875, 0.625, 0.3125, 0.3125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.625, 0.1875, 0.25, 0.75, 0.25, 0.375, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.0625, 0.25, 0.8125, 0.1875, 0.375, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.0625, 0.0, 0.4375, 0.1875, 0.1875, 0.5625, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.125, 0.1875, 0.5, 0.25, 0.25, 0.625, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.1875, 0.25, 0.5625, 0.375, 0.3125, 0.6875, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.3125, 0.1875, 0.625, 0.5, 0.25, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.4375, 0.0625, 0.6875, 0.5625, 0.1875, 0.8125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.5, 0.0, 0.75, 0.625, 0.0625, 0.875, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.625, 0.0, 0.0625, 0.75, 0.125, 0.125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.6875, 0.125, 0.0625, 0.75, 0.25, 0.125, false, false, x, y, z, block, renderer);
        } else if (growthState == 5) {
            ClientUtilities.drawSubBlock(0.375, 0.0, 0.375, 0.5, 0.1875, 0.5, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.4375, 0.1875, 0.5, 0.5625, 0.3125, 0.5625, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.5, 0.1875, 0.5625, 0.625, 0.3125, 0.625, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.5625, 0.125, 0.625, 0.625, 0.25, 0.6875, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.625, 0.0625, 0.6875, 0.6875, 0.1875, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.6875, 0.0, 0.75, 0.75, 0.125, 0.8125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.0, 0.625, 0.8125, 0.125, 0.6875, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.125, 0.6875, 0.8125, 0.1875, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.8125, 0.125, 0.6875, 0.875, 0.25, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.25, 0.0, 0.625, 0.3125, 0.125, 0.6875, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.25, 0.125, 0.6875, 0.3125, 0.1875, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.1875, 0.125, 0.6875, 0.25, 0.25, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.125, 0.125, 0.75, 0.1875, 0.1875, 0.8125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.0625, 0.0, 0.75, 0.125, 0.125, 0.8125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.125, 0.0, 0.125, 0.25, 0.125, 0.25, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.1875, 0.125, 0.1875, 0.3125, 0.1875, 0.3125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.25, 0.1875, 0.1875, 0.375, 0.25, 0.3125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.375, 0.25, 0.1875, 0.625, 0.3125, 0.3125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.625, 0.1875, 0.25, 0.75, 0.25, 0.375, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.75, 0.0625, 0.25, 0.8125, 0.1875, 0.375, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.0625, 0.0, 0.4375, 0.1875, 0.1875, 0.5625, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.125, 0.1875, 0.5, 0.25, 0.25, 0.625, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.1875, 0.25, 0.5625, 0.375, 0.3125, 0.6875, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.3125, 0.1875, 0.625, 0.5, 0.25, 0.75, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.4375, 0.0625, 0.6875, 0.5625, 0.1875, 0.8125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.5, 0.0, 0.75, 0.625, 0.0625, 0.875, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.625, 0.0, 0.0625, 0.75, 0.125, 0.125, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.625, 0.125, 0.125, 0.75, 0.25, 0.1875, false, false, x, y, z, block, renderer);
            ClientUtilities.drawSubBlock(0.625, 0.25, 0.125, 0.6875, 0.3125, 0.1875, false, false, x, y, z, block, renderer);
        }
    }
}

