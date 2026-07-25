/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 */
package thaumic.tinkerer.common.item.kami;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.proxy.TTCommonProxy;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;
import thaumic.tinkerer.common.item.kami.tool.IAdvancedTool;
import thaumic.tinkerer.common.item.kami.tool.ToolHandler;
import thaumic.tinkerer.common.registry.ItemKamiBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.KamiResearchItem;
import thaumic.tinkerer.common.research.ResearchHelper;

public class ItemProtoclay
extends ItemKamiBase {
    public ItemProtoclay() {
        this.func_77625_d(1);
    }

    public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        Block block;
        if (!(par3Entity instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer)par3Entity;
        ItemStack currentStack = player.func_71045_bC();
        if (currentStack == null || !(currentStack.func_77973_b() instanceof IAdvancedTool)) {
            return;
        }
        IAdvancedTool tool = (IAdvancedTool)currentStack.func_77973_b();
        if (tool.getType().equals("sword")) {
            return;
        }
        MovingObjectPosition pos = ToolHandler.raytraceFromEntity(par2World, par3Entity, true, 4.5);
        String typeToFind = "";
        if (player.field_82175_bq && pos != null && (block = par2World.func_147439_a(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d)) != null) {
            Material mat = block.func_149688_o();
            if (ToolHandler.isRightMaterial(mat, ToolHandler.materialsPick)) {
                typeToFind = "pick";
            } else if (ToolHandler.isRightMaterial(mat, ToolHandler.materialsShovel)) {
                typeToFind = "shovel";
            } else if (ToolHandler.isRightMaterial(mat, ToolHandler.materialsAxe)) {
                typeToFind = "axe";
            }
        }
        if (tool.getType().equals(typeToFind) || typeToFind.isEmpty()) {
            return;
        }
        for (int i = 0; i < player.field_71071_by.func_70302_i_(); ++i) {
            IAdvancedTool toolInSlot;
            ItemStack stackInSlot = player.field_71071_by.func_70301_a(i);
            if (stackInSlot == null || !(stackInSlot.func_77973_b() instanceof IAdvancedTool) || stackInSlot == currentStack || !(toolInSlot = (IAdvancedTool)stackInSlot.func_77973_b()).getType().equals(typeToFind)) continue;
            player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, stackInSlot);
            player.field_71071_by.func_70299_a(i, currentStack);
            break;
        }
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return TTCommonProxy.kamiRarity;
    }

    @Override
    public String getItemName() {
        return "protoclay";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new KamiResearchItem("PROTOCLAY", new AspectList().add(Aspect.TOOL, 2).add(Aspect.MINE, 1).add(Aspect.MAN, 1).add(Aspect.MECHANISM, 1), 12, 17, 5, new ItemStack((Item)this)).setParents(new String[]{"ICHOR_PICK_GEM"}).setParentsHidden(new String[]{"ICHOR_SHOVEL_GEM"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("PROTOCLAY")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("PROTOCLAY", new ItemStack((Item)this), 4, new AspectList().add(Aspect.MINE, 16).add(Aspect.TOOL, 16), new ItemStack(Items.field_151119_aD), new ItemStack(Blocks.field_150346_d), new ItemStack(Blocks.field_150348_b), new ItemStack(Blocks.field_150364_r), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 7));
    }
}

