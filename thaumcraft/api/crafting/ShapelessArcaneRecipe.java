/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.oredict.OreDictionary
 */
package thaumcraft.api.crafting;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.IArcaneRecipe;

public class ShapelessArcaneRecipe
implements IArcaneRecipe {
    private ItemStack output = null;
    private ArrayList input = new ArrayList();
    public AspectList aspects = null;
    public String research;

    public ShapelessArcaneRecipe(String research, Block result, AspectList aspects, Object ... recipe) {
        this(research, new ItemStack(result), aspects, recipe);
    }

    public ShapelessArcaneRecipe(String research, Item result, AspectList aspects, Object ... recipe) {
        this(research, new ItemStack(result), aspects, recipe);
    }

    public ShapelessArcaneRecipe(String research, ItemStack result, AspectList aspects, Object ... recipe) {
        this.output = result.func_77946_l();
        this.research = research;
        this.aspects = aspects;
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

    @Override
    public int getRecipeSize() {
        return this.input.size();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.output;
    }

    @Override
    public ItemStack getCraftingResult(IInventory var1) {
        return this.output.func_77946_l();
    }

    @Override
    public boolean matches(IInventory var1, World world, EntityPlayer player) {
        if (this.research.length() > 0 && !ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), this.research)) {
            return false;
        }
        ArrayList required = new ArrayList(this.input);
        for (int x = 0; x < 9; ++x) {
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
        if (input == null && target != null || input != null && target == null) {
            return false;
        }
        return !(target.func_77973_b() != input.func_77973_b() || target.func_77942_o() && !ThaumcraftApiHelper.areItemStackTagsEqualForCrafting(input, target) || target.func_77960_j() != Short.MAX_VALUE && target.func_77960_j() != input.func_77960_j());
    }

    public ArrayList getInput() {
        return this.input;
    }

    @Override
    public AspectList getAspects() {
        return this.aspects;
    }

    @Override
    public AspectList getAspects(IInventory inv) {
        return this.aspects;
    }

    @Override
    public String getResearch() {
        return this.research;
    }
}

