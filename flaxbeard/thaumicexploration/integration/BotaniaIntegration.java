/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.tileentity.TileEntity
 *  vazkii.botania.common.block.ModBlocks
 *  vazkii.botania.common.block.tile.TileAltar
 */
package flaxbeard.thaumicexploration.integration;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileAltar;

public class BotaniaIntegration {
    public static Block getAltar() {
        return ModBlocks.altar;
    }

    public static boolean needsWater(TileEntity tile) {
        TileAltar altar = (TileAltar)tile;
        return !altar.hasWater;
    }

    public static void fillWater(TileEntity tile) {
        TileAltar altar = (TileAltar)tile;
        altar.hasWater = true;
        altar.func_145831_w().func_147453_f(altar.field_145851_c, altar.field_145848_d, altar.field_145849_e, altar.func_145838_q());
    }
}

