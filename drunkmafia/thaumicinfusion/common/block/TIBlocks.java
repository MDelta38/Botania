/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 */
package drunkmafia.thaumicinfusion.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import drunkmafia.thaumicinfusion.common.block.EssentiaBlock;
import net.minecraft.block.Block;

public class TIBlocks {
    public static Block essentiaBlock;
    public static Block aspectInscriber;

    public static void initBlocks() {
        essentiaBlock = new EssentiaBlock();
        GameRegistry.registerBlock((Block)essentiaBlock, (String)"reg_essentia");
    }
}

