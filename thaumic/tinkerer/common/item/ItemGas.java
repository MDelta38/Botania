/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.item;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockGaseousLight;
import thaumic.tinkerer.common.block.BlockGaseousShadow;
import thaumic.tinkerer.common.registry.ItemBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererCrucibleRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class ItemGas
extends ItemBase {
    private Block setBlock;

    public ItemGas(Block setBlock) {
        this.setBlock = setBlock;
    }

    public ItemGas() {
        this(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockGaseousShadow.class));
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        ArrayList<Object> result = new ArrayList<Object>();
        result.add(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockGaseousLight.class));
        return result;
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        if (this.setBlock == ThaumicTinkerer.registry.getFirstBlockFromClass(BlockGaseousShadow.class)) {
            TTResearchItem research = (TTResearchItem)new TTResearchItem("GASEOUS_SHADOW", new AspectList().add(Aspect.DARKNESS, 2).add(Aspect.AIR, 1).add(Aspect.MOTION, 4), -1, -5, 2, new ItemStack((Item)this), new ResearchPage[0]).setSecondary().setParents(new String[]{"GASEOUS_LIGHT"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.crucibleRecipePage("GASEOUS_SHADOW")});
            return research;
        }
        if (this.setBlock == ThaumicTinkerer.registry.getFirstBlockFromClass(BlockGaseousLight.class)) {
            TTResearchItem research = (TTResearchItem)new TTResearchItem("GASEOUS_LIGHT", new AspectList().add(Aspect.LIGHT, 2).add(Aspect.AIR, 1), 0, -3, 1, new ItemStack((Item)this), new ResearchPage[0]).setParents(new String[]{"NITOR"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.crucibleRecipePage("GASEOUS_LIGHT")});
            return research;
        }
        return null;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        if (this.setBlock == ThaumicTinkerer.registry.getFirstBlockFromClass(BlockGaseousLight.class)) {
            return new ThaumicTinkererCrucibleRecipe("GASEOUS_LIGHT", new ItemStack((Item)this), new ItemStack(ConfigItems.itemEssence, 1, 0), new AspectList().add(Aspect.LIGHT, 16).add(Aspect.AIR, 10).add(Aspect.MOTION, 8));
        }
        if (this.setBlock == ThaumicTinkerer.registry.getFirstBlockFromClass(BlockGaseousShadow.class)) {
            return new ThaumicTinkererCrucibleRecipe("GASEOUS_SHADOW", new ItemStack((Item)this), new ItemStack(ConfigItems.itemEssence, 1, 0), new AspectList().add(Aspect.DARKNESS, 16).add(Aspect.AIR, 10).add(Aspect.MOTION, 8));
        }
        return null;
    }

    @Override
    public String getItemName() {
        return this.setBlock == ThaumicTinkerer.registry.getFirstBlockFromClass(BlockGaseousShadow.class) ? "gaseousShadowItem" : "gaseousLightItem";
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        int x = (int)par3EntityPlayer.field_70165_t;
        int y = (int)par3EntityPlayer.field_70163_u + 1;
        int z = (int)par3EntityPlayer.field_70161_v;
        boolean air = par2World.func_147437_c(x, y, z);
        if (!par3EntityPlayer.field_71075_bZ.field_75098_d) {
            --par1ItemStack.field_77994_a;
        }
        par2World.func_72956_a((Entity)par3EntityPlayer, "random.pop", 0.5f, 0.4f / (field_77697_d.nextFloat() * 0.4f + 0.8f));
        if (air) {
            if (!par2World.field_72995_K) {
                par2World.func_147465_d(x, y, z, this.setBlock, 4, 2);
            } else {
                par3EntityPlayer.func_71038_i();
            }
            par2World.func_147464_a(x, y, z, this.setBlock, 10);
        }
        return par1ItemStack;
    }
}

