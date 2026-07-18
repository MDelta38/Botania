/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.item.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.recipe.IElvenItem;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;

public class ItemBlockDreamwood
extends ItemBlockWithMetadataAndName
implements IElvenItem {
    public ItemBlockDreamwood(Block block) {
        super(block);
    }

    @Override
    public boolean isElvenItem(ItemStack stack) {
        return true;
    }
}

