/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.oredict.OreDictionary
 *  net.minecraftforge.oredict.ShapelessOreRecipe
 */
package thaumcraft.common.lib.crafting;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ShapelessNBTOreRecipe
extends ShapelessOreRecipe {
    private ItemStack output = null;
    private ArrayList input = new ArrayList();

    public ShapelessNBTOreRecipe(Block result, Object ... recipe) {
        this(new ItemStack(result), recipe);
    }

    public ShapelessNBTOreRecipe(Item result, Object ... recipe) {
        this(new ItemStack(result), recipe);
    }

    public ShapelessNBTOreRecipe(ItemStack result, Object ... recipe) {
        super(result, recipe);
        this.output = result.func_77946_l();
        for (Object in : recipe) {
            if (in instanceof ItemStack) {
                this.input.add(((ItemStack)in).func_77946_l());
                continue;
            }
            if (in instanceof Item) {
                this.input.add(new ItemStack((Item)in));
                continue;
            }
            if (in instanceof Block) {
                this.input.add(new ItemStack((Block)in));
                continue;
            }
            if (in instanceof String) {
                this.input.add(OreDictionary.getOres((String)((String)in)));
                continue;
            }
            String ret = "Invalid shapeless ore recipe: ";
            for (Object tmp : recipe) {
                ret = ret + tmp + ", ";
            }
            ret = ret + this.output;
            throw new RuntimeException(ret);
        }
    }

    public int func_77570_a() {
        return this.input.size();
    }

    public ItemStack func_77571_b() {
        return this.output;
    }

    public ItemStack func_77572_b(InventoryCrafting var1) {
        return this.output.func_77946_l();
    }

    public boolean func_77569_a(InventoryCrafting var1, World world) {
        ArrayList required = new ArrayList(this.input);
        for (int x = 0; x < var1.func_70302_i_(); ++x) {
            ItemStack slot = var1.func_70301_a(x);
            if (slot == null) continue;
            boolean inRecipe = false;
            Iterator req = required.iterator();
            while (req.hasNext()) {
                boolean match = false;
                Object next = req.next();
                if (next instanceof ItemStack) {
                    match = this.checkItemEquals((ItemStack)next, slot);
                } else if (next instanceof ArrayList) {
                    for (ItemStack item : (ArrayList)next) {
                        match = match || this.checkItemEquals(item, slot);
                    }
                }
                if (!match) continue;
                inRecipe = true;
                required.remove(next);
                break;
            }
            if (inRecipe) continue;
            return false;
        }
        return required.isEmpty();
    }

    private boolean checkItemEquals(ItemStack target, ItemStack input) {
        return target.func_77973_b() == input.func_77973_b() && ItemStack.func_77970_a((ItemStack)target, (ItemStack)input) && (target.func_77960_j() == Short.MAX_VALUE || target.func_77960_j() == input.func_77960_j());
    }

    public ArrayList getInput() {
        return this.input;
    }
}

