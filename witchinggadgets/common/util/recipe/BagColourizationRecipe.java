/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockColored
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 */
package witchinggadgets.common.util.recipe;

import java.util.ArrayList;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import witchinggadgets.common.items.tools.ItemBag;
import witchinggadgets.common.util.Utilities;

public class BagColourizationRecipe
implements IRecipe {
    public boolean func_77569_a(InventoryCrafting par1InventoryCrafting, World par2World) {
        ItemStack itemstack = null;
        ArrayList<ItemStack> arraylist = new ArrayList<ItemStack>();
        for (int i = 0; i < par1InventoryCrafting.func_70302_i_(); ++i) {
            ItemStack itemstack1 = par1InventoryCrafting.func_70301_a(i);
            if (itemstack1 == null) continue;
            if (itemstack1.func_77973_b() instanceof ItemBag) {
                Item itembag = itemstack1.func_77973_b();
                if (!(itembag instanceof ItemBag) || itemstack != null) {
                    return false;
                }
                itemstack = itemstack1;
                continue;
            }
            if (!Utilities.isDye(itemstack1)) {
                return false;
            }
            arraylist.add(itemstack1);
        }
        return itemstack != null;
    }

    public ItemStack func_77572_b(InventoryCrafting par1InventoryCrafting) {
        float f1;
        float f;
        int l;
        int k;
        ItemStack itemstack = null;
        int[] aint = new int[3];
        int i = 0;
        int j = 0;
        ItemBag itembag = null;
        boolean revert = true;
        for (k = 0; k < par1InventoryCrafting.func_70302_i_(); ++k) {
            ItemStack itemstack1 = par1InventoryCrafting.func_70301_a(k);
            if (itemstack1 == null) continue;
            if (itemstack1.func_77973_b() instanceof ItemBag) {
                if (itemstack != null) {
                    return null;
                }
                itembag = (ItemBag)itemstack1.func_77973_b();
                itemstack = itemstack1.func_77946_l();
                itemstack.field_77994_a = 1;
                l = itembag.getBagColorFromItemStack(itemstack, 0);
                f = (float)(l >> 16 & 0xFF) / 255.0f;
                f1 = (float)(l >> 8 & 0xFF) / 255.0f;
                float f2 = (float)(l & 0xFF) / 255.0f;
                i = (int)((float)i + Math.max(f, Math.max(f1, f2)) * 255.0f);
                aint[0] = (int)((float)aint[0] + f * 255.0f);
                aint[1] = (int)((float)aint[1] + f1 * 255.0f);
                aint[2] = (int)((float)aint[2] + f2 * 255.0f);
                ++j;
                continue;
            }
            if (!Utilities.isDye(itemstack1)) {
                return null;
            }
            if (revert) {
                revert = false;
            }
            float[] afloat = EntitySheep.field_70898_d[BlockColored.func_150032_b((int)Utilities.getDamageForDye(itemstack1))];
            int j1 = (int)(afloat[0] * 255.0f);
            int k1 = (int)(afloat[1] * 255.0f);
            int i1 = (int)(afloat[2] * 255.0f);
            i += Math.max(j1, Math.max(k1, i1));
            aint[0] = aint[0] + j1;
            aint[1] = aint[1] + k1;
            aint[2] = aint[2] + i1;
            ++j;
        }
        if (revert) {
            itembag.modifyColorOnItemStack(itemstack, ItemBag.getDefaultBagColour(itemstack.func_77960_j()));
            return itemstack;
        }
        if (itembag == null) {
            return null;
        }
        k = aint[0] / j;
        int l1 = aint[1] / j;
        l = aint[2] / j;
        f = i / j;
        f1 = Math.max(k, Math.max(l1, l));
        k = (int)((float)k * f / f1);
        l1 = (int)((float)l1 * f / f1);
        l = (int)((float)l * f / f1);
        int i1 = (k << 8) + l1;
        i1 = (i1 << 8) + l;
        itembag.modifyColorOnItemStack(itemstack, i1);
        return itemstack;
    }

    public int func_77570_a() {
        return 10;
    }

    public ItemStack func_77571_b() {
        return null;
    }
}

