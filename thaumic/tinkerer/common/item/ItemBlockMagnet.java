/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 */
package thaumic.tinkerer.common.item;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import thaumic.tinkerer.common.registry.ITTinkererItem;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class ItemBlockMagnet
extends ItemBlock
implements ITTinkererItem {
    public ItemBlockMagnet(Block block) {
        super(block);
        this.func_77627_a(true);
    }

    public int func_77647_b(int par1) {
        return par1 == 0 ? 0 : 2;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return "tile." + (par1ItemStack.func_77960_j() == 0 ? "magnet" : "mobMagnet");
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getItemName() {
        return "magnet";
    }

    @Override
    public boolean shouldRegister() {
        return false;
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return null;
    }
}

