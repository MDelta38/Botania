/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 */
package thaumcraft.common.blocks;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockLootItem
extends ItemBlock {
    public BlockLootItem(Block par1) {
        super(par1);
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    public EnumRarity func_77613_e(ItemStack stack) {
        switch (stack.func_77960_j()) {
            case 1: {
                return EnumRarity.uncommon;
            }
            case 2: {
                return EnumRarity.rare;
            }
        }
        return EnumRarity.common;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        super.func_77624_a(stack, player, list, par4);
        list.add(this.func_77613_e((ItemStack)stack).field_77934_f);
    }
}

