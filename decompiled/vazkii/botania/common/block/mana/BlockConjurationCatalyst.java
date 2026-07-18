/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.mana;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.mana.BlockAlchemyCatalyst;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockConjurationCatalyst
extends BlockAlchemyCatalyst {
    public BlockConjurationCatalyst() {
        super("conjurationCatalyst");
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.conjurationCatalyst;
    }
}

