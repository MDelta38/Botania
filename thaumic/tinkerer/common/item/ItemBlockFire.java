/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.util.IIcon
 */
package thaumic.tinkerer.common.item;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.block.fire.BlockFireAir;
import thaumic.tinkerer.common.block.fire.BlockFireChaos;
import thaumic.tinkerer.common.block.fire.BlockFireEarth;
import thaumic.tinkerer.common.block.fire.BlockFireIgnis;
import thaumic.tinkerer.common.block.fire.BlockFireOrder;
import thaumic.tinkerer.common.block.fire.BlockFireWater;
import thaumic.tinkerer.common.registry.ITTinkererBlock;
import thaumic.tinkerer.common.registry.ITTinkererItem;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class ItemBlockFire
extends ItemBlock
implements ITTinkererItem {
    private IIcon icon;

    public ItemBlockFire(Block block) {
        super(block);
    }

    public int func_77647_b(int par1) {
        return 0;
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getItemName() {
        return ((ITTinkererBlock)this.field_150939_a).getBlockName();
    }

    @Override
    public boolean shouldRegister() {
        return true;
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

    public void func_94581_a(IIconRegister par1IconRegister) {
        String s = "";
        if (this.field_150939_a instanceof BlockFireAir) {
            s = "aer";
        }
        if (this.field_150939_a instanceof BlockFireEarth) {
            s = "terra";
        }
        if (this.field_150939_a instanceof BlockFireWater) {
            s = "aqua";
        }
        if (this.field_150939_a instanceof BlockFireIgnis) {
            s = "ignis";
        }
        if (this.field_150939_a instanceof BlockFireOrder) {
            s = "ordo";
        }
        if (this.field_150939_a instanceof BlockFireChaos) {
            s = "perditio";
        }
        s = s + "Fire";
        this.icon = IconHelper.forName(par1IconRegister, s);
    }
}

