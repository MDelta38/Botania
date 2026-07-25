/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.api.wands.FocusUpgradeType
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package thaumic.tinkerer.common.item.foci;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.item.foci.ItemModFocus;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class ItemFocusFlight
extends ItemModFocus {
    private static final AspectList visUsage = new AspectList().add(Aspect.AIR, 15);

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition movingobjectposition) {
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        if (!ConfigHandler.enableFlight) {
            return itemstack;
        }
        if (wand.consumeAllVis(itemstack, p, this.getVisCost(itemstack), true, false)) {
            Vec3 vec = p.func_70040_Z();
            double force = 0.6666666666666666 * (1.0 + (double)this.getUpgradeLevel(itemstack, FocusUpgradeType.potency) * 0.2);
            p.field_70159_w = vec.field_72450_a * force;
            p.field_70181_x = vec.field_72448_b * force;
            p.field_70179_y = vec.field_72449_c * force;
            p.field_70143_R = 0.0f;
            if (p instanceof EntityPlayerMP) {
                ((EntityPlayerMP)p).field_71135_a.field_147365_f = 0;
            }
            for (int i = 0; i < 5; ++i) {
                ThaumicTinkerer.tcProxy.smokeSpiral(world, p.field_70165_t, p.field_70163_u - p.field_70181_x, p.field_70161_v, 2.0f, (int)(Math.random() * 360.0), (int)p.field_70163_u, 647935);
            }
            world.func_72956_a((Entity)p, "thaumcraft:wind", 0.4f, 1.0f);
        }
        if (world.field_72995_K) {
            p.func_71038_i();
        }
        return itemstack;
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "FLIGHT";
    }

    public int getFocusColor(ItemStack stack) {
        return 10416895;
    }

    @Override
    protected boolean hasOrnament() {
        return true;
    }

    public AspectList getVisCost(ItemStack stack) {
        return visUsage;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemStack, int i) {
        return new FocusUpgradeType[]{FocusUpgradeType.treasure, FocusUpgradeType.potency};
    }

    @Override
    public String getItemName() {
        return "focusFlight";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (TTResearchItem)new TTResearchItem("FOCUS_FLIGHT", new AspectList().add(Aspect.MOTION, 1).add(Aspect.MAGIC, 1).add(Aspect.AIR, 2), -3, -4, 2, new ItemStack((Item)this), new ResearchPage[0]).setParents(new String[]{"FOCUS_SMELT"}).setConcealed().setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("FOCUS_FLIGHT")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("FOCUS_FLIGHT", new ItemStack((Item)this), 3, new AspectList().add(Aspect.AIR, 15).add(Aspect.MOTION, 20).add(Aspect.TRAVEL, 10), new ItemStack(Items.field_151079_bi), new ItemStack(Items.field_151128_bU), new ItemStack(Items.field_151128_bU), new ItemStack(Items.field_151128_bU), new ItemStack(Items.field_151128_bU), new ItemStack(Items.field_151008_G), new ItemStack(Items.field_151008_G), new ItemStack(ConfigItems.itemShard, 1, 0));
    }
}

