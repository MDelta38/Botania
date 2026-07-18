/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.common.item.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;

public class ItemBlockPool
extends ItemBlockWithMetadataAndName {
    public ItemBlockPool(Block par2Block) {
        super(par2Block);
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (par1ItemStack.func_77960_j() == 1) {
            for (int i = 0; i < 2; ++i) {
                par3List.add(StatCollector.func_74838_a((String)("botaniamisc.creativePool" + i)));
            }
        }
    }
}

