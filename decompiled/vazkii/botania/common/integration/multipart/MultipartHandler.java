/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.microblock.BlockMicroMaterial
 *  codechicken.microblock.MicroMaterialRegistry
 *  codechicken.microblock.MicroMaterialRegistry$IMicroMaterial
 *  net.minecraft.block.Block
 */
package vazkii.botania.common.integration.multipart;

import codechicken.microblock.BlockMicroMaterial;
import codechicken.microblock.MicroMaterialRegistry;
import net.minecraft.block.Block;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;

public class MultipartHandler {
    public MultipartHandler() {
        MultipartHandler.registerMultiparts(ModBlocks.livingrock, 0, 4);
        MultipartHandler.registerMultiparts(ModBlocks.livingwood, 0, 5);
        MultipartHandler.registerMultiparts(ModBlocks.storage, 0, 4);
        MultipartHandler.registerMultiparts(ModBlocks.dreamwood, 0, 4);
        MultipartHandler.registerMultiparts(ModBlocks.prismarine, 0, 2);
        MultipartHandler.registerMultiparts(ModBlocks.seaLamp);
        MultipartHandler.registerMultiparts(ModBlocks.reedBlock);
        MultipartHandler.registerMultiparts(ModBlocks.thatch);
        MultipartHandler.registerMultiparts(ModBlocks.customBrick, 0, 15);
        MultipartHandler.registerMultiparts(ModBlocks.elfGlass);
        MultipartHandler.registerMultiparts(ModBlocks.manaGlass);
        MultipartHandler.registerMultiparts(ModBlocks.endStoneBrick, 0, 3);
        MultipartHandler.registerMultiparts(ModBlocks.blazeBlock);
        MultipartHandler.registerMultiparts(ModBlocks.bifrostPerm);
        MultipartHandler.registerMultiparts(ModBlocks.shimmerrock);
        MultipartHandler.registerMultiparts(ModBlocks.shimmerwoodPlanks);
        MultipartHandler.registerMultiparts(ModFluffBlocks.darkQuartz, 0, 2);
        MultipartHandler.registerMultiparts(ModFluffBlocks.manaQuartz, 0, 2);
        MultipartHandler.registerMultiparts(ModFluffBlocks.blazeQuartz, 0, 2);
        MultipartHandler.registerMultiparts(ModFluffBlocks.lavenderQuartz, 0, 2);
        MultipartHandler.registerMultiparts(ModFluffBlocks.redQuartz, 0, 2);
        MultipartHandler.registerMultiparts(ModFluffBlocks.elfQuartz, 0, 2);
        MultipartHandler.registerMultiparts(ModFluffBlocks.sunnyQuartz, 0, 2);
        MultipartHandler.registerMultiparts(ModFluffBlocks.biomeStoneA, 0, 15);
        MultipartHandler.registerMultiparts(ModFluffBlocks.biomeStoneB, 0, 15);
        MultipartHandler.registerMultiparts(ModFluffBlocks.stone, 0, 15);
        MultipartHandler.registerMultiparts(ModFluffBlocks.pavement, 0, 5);
    }

    private static void registerMultiparts(Block block) {
        MultipartHandler.registerMultiparts(block, 0);
    }

    private static void registerMultiparts(Block block, int meta) {
        MicroMaterialRegistry.registerMaterial((MicroMaterialRegistry.IMicroMaterial)new BlockMicroMaterial(block, meta), (String)(block.func_149739_a() + (meta == 0 ? "" : "_" + meta)));
    }

    private static void registerMultiparts(Block block, int metamin, int metamax) {
        for (int i = metamin; i <= metamax; ++i) {
            MultipartHandler.registerMultiparts(block, i);
        }
    }
}

