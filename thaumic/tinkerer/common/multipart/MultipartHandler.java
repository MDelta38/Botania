/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.microblock.BlockMicroMaterial
 *  codechicken.microblock.MicroMaterialRegistry
 *  codechicken.microblock.MicroMaterialRegistry$IMicroMaterial
 *  net.minecraft.block.Block
 *  thaumcraft.common.config.ConfigBlocks
 */
package thaumic.tinkerer.common.multipart;

import codechicken.microblock.BlockMicroMaterial;
import codechicken.microblock.MicroMaterialRegistry;
import net.minecraft.block.Block;
import thaumcraft.common.config.ConfigBlocks;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.quartz.BlockDarkQuartz;

public class MultipartHandler {
    public MultipartHandler() {
        ThaumicTinkerer.log.trace("Loading Multipart Handler class");
        MultipartHandler.registerMultipart(ConfigBlocks.blockCustomOre, 0);
        MultipartHandler.registerMultipart(ConfigBlocks.blockCustomOre, 7);
        MultipartHandler.registerMultipart(ConfigBlocks.blockWoodenDevice, 6);
        MultipartHandler.registerMultipart(ConfigBlocks.blockWoodenDevice, 7);
        MultipartHandler.registerMultipartMetadataLine(ConfigBlocks.blockMagicalLog, 1);
        MultipartHandler.registerMultipartMetadataLine(ConfigBlocks.blockMagicalLeaves, 1);
        MultipartHandler.registerMultipartMetadataLine(ConfigBlocks.blockCosmeticOpaque, 1);
        MultipartHandler.registerMultipartMetadataLine(ConfigBlocks.blockCosmeticSolid, 7);
        MultipartHandler.registerMultipartMetadataLine(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockDarkQuartz.class), 2);
    }

    private static void registerMultipartMetadataLine(Block block, int maxMeta) {
        for (int i = 0; i < maxMeta; ++i) {
            MultipartHandler.registerMultipart(block, i);
        }
    }

    private static void registerMultipart(Block block, int meta) {
        MicroMaterialRegistry.registerMaterial((MicroMaterialRegistry.IMicroMaterial)new BlockMicroMaterial(block, meta), (String)(block.func_149739_a() + (meta == 0 ? "" : "_" + meta)));
    }
}

