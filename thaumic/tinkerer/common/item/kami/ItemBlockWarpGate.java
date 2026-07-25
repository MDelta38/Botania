/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 */
package thaumic.tinkerer.common.item.kami;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import thaumic.tinkerer.common.core.proxy.TTCommonProxy;
import thaumic.tinkerer.common.registry.ITTinkererItem;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class ItemBlockWarpGate
extends ItemBlock
implements ITTinkererItem {
    public ItemBlockWarpGate(Block par1) {
        super(par1);
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return TTCommonProxy.kamiRarity;
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getItemName() {
        return "warpGate";
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

