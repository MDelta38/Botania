/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 */
package thaumic.tinkerer.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockGas;
import thaumic.tinkerer.common.item.ItemGas;
import thaumic.tinkerer.common.item.quartz.ItemDarkQuartz;
import thaumic.tinkerer.common.registry.ItemBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class ItemGasRemover
extends ItemBase {
    public ItemGasRemover() {
        this.func_77625_d(1);
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        TTResearchItem research = (TTResearchItem)new TTResearchItem("GAS_REMOVER", new AspectList().add(Aspect.DARKNESS, 2).add(Aspect.LIGHT, 2), -2, -7, 0, new ItemStack((Item)this), new ResearchPage[0]).setRound().setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.arcaneRecipePage("GAS_REMOVER")}).setParents(new String[]{"GASEOUS_SHADOW"});
        return research;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererArcaneRecipe("GAS_REMOVER", "GAS_REMOVER", new ItemStack((Item)this), new AspectList().add(Aspect.AIR, 2).add(Aspect.ORDER, 2), "DDD", "T G", "QQQ", Character.valueOf('D'), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemDarkQuartz.class)), Character.valueOf('T'), new ItemStack(ThaumicTinkerer.registry.getItemFromClass(ItemGas.class).get(0)), Character.valueOf('G'), new ItemStack(ThaumicTinkerer.registry.getItemFromClass(ItemGas.class).get(1)), Character.valueOf('Q'), new ItemStack(Items.field_151128_bU));
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par3EntityPlayer.func_70093_af()) {
            int xs = (int)par3EntityPlayer.field_70165_t;
            int ys = (int)par3EntityPlayer.field_70163_u;
            int zs = (int)par3EntityPlayer.field_70161_v;
            for (int x = xs - 3; x < xs + 3; ++x) {
                for (int y = ys - 3; y < ys + 3; ++y) {
                    for (int z = zs - 3; z < zs + 3; ++z) {
                        Block block = par2World.func_147439_a(x, y, z);
                        if (block == null || !(block instanceof BlockGas)) continue;
                        BlockGas gas = (BlockGas)block;
                        gas.placeParticle(par2World, x, y, z);
                        par2World.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 3);
                    }
                }
            }
            par2World.func_72956_a((Entity)par3EntityPlayer, "thaumcraft.wand", 0.2f, 1.0f);
        }
        return par1ItemStack;
    }

    @SideOnly(value=Side.CLIENT)
    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.uncommon;
    }

    @Override
    public String getItemName() {
        return "gasRemover";
    }
}

