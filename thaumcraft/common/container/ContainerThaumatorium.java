/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package thaumcraft.common.container;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.tiles.TileThaumatorium;

public class ContainerThaumatorium
extends Container {
    private TileThaumatorium thaumatorium;
    private EntityPlayer player = null;
    public ArrayList<CrucibleRecipe> recipes = new ArrayList();

    public ContainerThaumatorium(InventoryPlayer par1InventoryPlayer, TileThaumatorium tileEntity) {
        int i;
        this.player = par1InventoryPlayer.field_70458_d;
        this.thaumatorium = tileEntity;
        this.thaumatorium.eventHandler = this;
        this.func_75146_a(new Slot((IInventory)tileEntity, 0, 48, 16));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)par1InventoryPlayer, i, 8 + i * 18, 142));
        }
        this.func_75130_a((IInventory)this.thaumatorium);
    }

    public void func_75130_a(IInventory par1iInventory) {
        super.func_75130_a(par1iInventory);
        this.updateRecipes();
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
        if (!this.thaumatorium.func_145831_w().field_72995_K) {
            this.thaumatorium.eventHandler = null;
        }
    }

    public void updateRecipes() {
        this.recipes.clear();
        if (this.thaumatorium.inputStack != null || this.thaumatorium.recipeHash != null) {
            block0: for (Object r : ThaumcraftApi.getCraftingRecipes()) {
                if (!(r instanceof CrucibleRecipe)) continue;
                if (ResearchManager.isResearchComplete(this.player.func_70005_c_(), ((CrucibleRecipe)r).key) && ((CrucibleRecipe)r).catalystMatches(this.thaumatorium.inputStack)) {
                    this.recipes.add((CrucibleRecipe)r);
                    continue;
                }
                if (this.thaumatorium.recipeHash == null || this.thaumatorium.recipeHash.size() <= 0) continue;
                for (Integer hash : this.thaumatorium.recipeHash) {
                    if (((CrucibleRecipe)r).hash != hash) continue;
                    this.recipes.add((CrucibleRecipe)r);
                    continue block0;
                }
            }
        }
    }

    public boolean func_75140_a(EntityPlayer par1EntityPlayer, int button) {
        if (this.recipes.size() > 0 && button >= 0 && button < this.recipes.size()) {
            boolean found = false;
            for (int a = 0; a < this.thaumatorium.recipeHash.size(); ++a) {
                if (this.recipes.get((int)button).hash != this.thaumatorium.recipeHash.get(a)) continue;
                found = true;
                this.thaumatorium.recipeEssentia.remove(a);
                this.thaumatorium.recipePlayer.remove(a);
                this.thaumatorium.recipeHash.remove(a);
                this.thaumatorium.currentCraft = -1;
                break;
            }
            if (!found) {
                this.thaumatorium.recipeEssentia.add(this.recipes.get((int)button).aspects.copy());
                this.thaumatorium.recipePlayer.add(par1EntityPlayer.func_70005_c_());
                this.thaumatorium.recipeHash.add(this.recipes.get((int)button).hash);
            }
            this.thaumatorium.func_70296_d();
            this.thaumatorium.func_145831_w().func_147471_g(this.thaumatorium.field_145851_c, this.thaumatorium.field_145848_d, this.thaumatorium.field_145849_e);
            return true;
        }
        return false;
    }

    public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
        return this.thaumatorium.func_70300_a(par1EntityPlayer);
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(par2);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (par2 != 0) {
                if (!this.func_75135_a(itemstack1, 0, 1, false)) {
                    return null;
                }
            } else if (par2 >= 1 && par2 < 28) {
                if (!this.func_75135_a(itemstack1, 28, 37, false)) {
                    return null;
                }
            } else {
                if (par2 >= 28 && par2 < 37 && !this.func_75135_a(itemstack1, 1, 28, false)) {
                    return null;
                }
                if (!this.func_75135_a(itemstack1, 1, 37, false)) {
                    return null;
                }
            }
            if (itemstack1.field_77994_a == 0) {
                slot.func_75215_d((ItemStack)null);
            } else {
                slot.func_75218_e();
            }
            if (itemstack1.field_77994_a == itemstack.field_77994_a) {
                return null;
            }
            slot.func_82870_a(par1EntityPlayer, itemstack1);
        }
        return itemstack;
    }
}

