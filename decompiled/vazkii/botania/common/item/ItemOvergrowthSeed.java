/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.oredict.OreDictionary
 */
package vazkii.botania.common.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ItemMod;

public class ItemOvergrowthSeed
extends ItemMod {
    public ItemOvergrowthSeed() {
        this.func_77655_b("overgrowthSeed");
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int s, float xs, float ys, float zs) {
        int[] ids;
        Block block = world.func_147439_a(x, y, z);
        ItemStack blockStack = new ItemStack(block);
        for (int i : ids = OreDictionary.getOreIDs((ItemStack)blockStack)) {
            String name = OreDictionary.getOreName((int)i);
            if (!name.equals("grass")) continue;
            world.func_72926_e(2001, x, y, z, Block.func_149682_b((Block)block));
            world.func_147449_b(x, y, z, ModBlocks.enchantedSoil);
            --stack.field_77994_a;
            return true;
        }
        return false;
    }
}

