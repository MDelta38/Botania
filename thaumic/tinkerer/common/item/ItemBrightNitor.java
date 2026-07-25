/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockNitorGas;
import thaumic.tinkerer.common.registry.ItemBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererCrucibleRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class ItemBrightNitor
extends ItemBase {
    public static int meta = 0;

    public ItemBrightNitor() {
        this.func_77625_d(1);
    }

    public static void setBlock(int x, int y, int z, World world) {
        if (!(world.func_147439_a(x, y, z) != Blocks.field_150350_a && world.func_147439_a(x, y, z) != ThaumicTinkerer.registry.getFirstBlockFromClass(BlockNitorGas.class) || world.field_72995_K)) {
            world.func_147465_d(x, y, z, ThaumicTinkerer.registry.getFirstBlockFromClass(BlockNitorGas.class), meta, 2);
        }
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (TTResearchItem)new TTResearchItem("BRIGHT_NITOR", new AspectList().add(Aspect.LIGHT, 2).add(Aspect.FIRE, 1).add(Aspect.ENERGY, 1).add(Aspect.AIR, 1), 1, -5, 2, new ItemStack((Item)this), new ResearchPage[0]).setParents(new String[]{"GASEOUS_LIGHT"}).setConcealed().setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.crucibleRecipePage("BRIGHT_NITOR")}).setSecondary();
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererCrucibleRecipe("BRIGHT_NITOR", new ItemStack((Item)this), new ItemStack(ConfigItems.itemResource, 1, 1), new AspectList().add(Aspect.ENERGY, 25).add(Aspect.LIGHT, 25).add(Aspect.AIR, 10).add(Aspect.FIRE, 10));
    }

    public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        int x = (int)Math.floor(par3Entity.field_70165_t);
        int y = (int)par3Entity.field_70163_u + 1;
        int z = (int)Math.floor(par3Entity.field_70161_v);
        ItemBrightNitor.setBlock(x, y, z, par2World);
    }

    @Override
    public String getItemName() {
        return "brightNitor";
    }
}

