/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.aspects.IEssentiaContainerItem
 */
package thaumic.tinkerer.common.item;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.registry.ItemBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class ItemInfusedGrain
extends ItemBase
implements IEssentiaContainerItem {
    private IIcon[] icons;

    public static int getMetaForAspect(Aspect aspect) {
        for (PRIMAL_ASPECT_ENUM e : PRIMAL_ASPECT_ENUM.values()) {
            if (aspect != e.aspect) continue;
            return e.ordinal();
        }
        return 0;
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.func_77624_a(par1ItemStack, par2EntityPlayer, par3List, par4);
        par3List.add(this.getAspect(par1ItemStack).getName());
    }

    public Aspect getAspect(ItemStack stack) {
        return PRIMAL_ASPECT_ENUM.values()[stack.func_77960_j()].aspect;
    }

    public boolean func_77614_k() {
        return true;
    }

    public void func_150895_a(Item item, CreativeTabs tab, List l) {
        for (PRIMAL_ASPECT_ENUM primal : PRIMAL_ASPECT_ENUM.values()) {
            l.add(new ItemStack(item, 1, primal.ordinal()));
        }
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[4];
        this.icons[0] = IconHelper.forName(par1IconRegister, "fruit_aer");
        this.icons[1] = IconHelper.forName(par1IconRegister, "fruit_ignis");
        this.icons[2] = IconHelper.forName(par1IconRegister, "fruit_terra");
        this.icons[3] = IconHelper.forName(par1IconRegister, "fruit_aqua");
    }

    public IIcon func_77617_a(int par1) {
        return this.icons[par1];
    }

    @Override
    public String getItemName() {
        return "infusedGrain";
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return null;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    public AspectList getAspects(ItemStack itemStack) {
        return new AspectList().add(this.getAspect(itemStack), 1).add(Aspect.CROP, 1);
    }

    public void setAspects(ItemStack itemStack, AspectList aspectList) {
    }

    private static enum PRIMAL_ASPECT_ENUM {
        AIR(Aspect.AIR),
        FIRE(Aspect.FIRE),
        EARTH(Aspect.EARTH),
        WATER(Aspect.WATER);

        Aspect aspect;

        private PRIMAL_ASPECT_ENUM(Aspect a) {
            this.aspect = a;
        }
    }
}

