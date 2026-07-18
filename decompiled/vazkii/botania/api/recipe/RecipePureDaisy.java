/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.oredict.OreDictionary
 */
package vazkii.botania.api.recipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.subtile.SubTileEntity;

public class RecipePureDaisy {
    private static final Map<String, List<ItemStack>> oreMap = new HashMap<String, List<ItemStack>>();
    Object input;
    Block output;
    int outputMeta;

    public RecipePureDaisy(Object input, Block output, int outputMeta) {
        this.input = input;
        this.output = output;
        this.outputMeta = outputMeta;
        if (input != null && !(input instanceof String) && !(input instanceof Block)) {
            throw new IllegalArgumentException("input must be an oredict String or a Block.");
        }
    }

    public boolean matches(World world, int x, int y, int z, SubTileEntity pureDaisy, Block block, int meta) {
        if (this.input instanceof Block) {
            return block == this.input;
        }
        ItemStack stack = new ItemStack(block, 1, meta);
        String oredict = (String)this.input;
        return this.isOreDict(stack, oredict);
    }

    public boolean isOreDict(ItemStack stack, String entry) {
        List<Object> ores;
        if (stack == null || stack.func_77973_b() == null) {
            return false;
        }
        if (oreMap.containsKey(entry)) {
            ores = oreMap.get(entry);
        } else {
            ores = OreDictionary.getOres((String)entry);
            oreMap.put(entry, (List<ItemStack>)ores);
        }
        for (ItemStack itemStack : ores) {
            ItemStack cstack = itemStack.func_77946_l();
            if (cstack.func_77960_j() == Short.MAX_VALUE) {
                cstack.func_77964_b(stack.func_77960_j());
            }
            if (!stack.func_77969_a(cstack)) continue;
            return true;
        }
        return false;
    }

    public boolean set(World world, int x, int y, int z, SubTileEntity pureDaisy) {
        if (!world.field_72995_K) {
            world.func_147465_d(x, y, z, this.output, this.outputMeta, 3);
        }
        return true;
    }

    public Object getInput() {
        return this.input;
    }

    public Block getOutput() {
        return this.output;
    }

    public int getOutputMeta() {
        return this.outputMeta;
    }
}

