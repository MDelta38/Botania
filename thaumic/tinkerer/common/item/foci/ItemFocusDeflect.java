/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IProjectile
 *  net.minecraft.entity.item.EntityExpBottle
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.codechicken.lib.vec.Vector3
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package thaumic.tinkerer.common.item.foci;

import cpw.mods.fml.common.Loader;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.codechicken.lib.vec.Vector3;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.compat.BloodMagic;
import thaumic.tinkerer.common.compat.BotaniaFunctions;
import thaumic.tinkerer.common.core.helper.ProjectileHelper;
import thaumic.tinkerer.common.item.foci.ItemFocusFlight;
import thaumic.tinkerer.common.item.foci.ItemModFocus;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class ItemFocusDeflect
extends ItemModFocus {
    public static List<Class<?>> DeflectBlacklist = new ArrayList();
    AspectList visUsage = new AspectList().add(Aspect.ORDER, 8).add(Aspect.AIR, 4);

    public static void setupBlackList() {
        DeflectBlacklist.add(EntityExpBottle.class);
        if (Loader.isModLoaded((String)"BloodMagic")) {
            BloodMagic.setupClass();
        }
    }

    public static void protectFromProjectiles(EntityPlayer p) {
        List projectiles = p.field_70170_p.func_72872_a(IProjectile.class, AxisAlignedBB.func_72330_a((double)(p.field_70165_t - 4.0), (double)(p.field_70163_u - 4.0), (double)(p.field_70161_v - 4.0), (double)(p.field_70165_t + 3.0), (double)(p.field_70163_u + 3.0), (double)(p.field_70161_v + 3.0)));
        for (Entity e : projectiles) {
            if (ItemFocusDeflect.CheckBlackList(e) || ProjectileHelper.getOwner(e) == p) continue;
            Vector3 motionVec = new Vector3(e.field_70159_w, e.field_70181_x, e.field_70179_y).normalize().multiply(Math.sqrt((e.field_70165_t - p.field_70165_t) * (e.field_70165_t - p.field_70165_t) + (e.field_70163_u - p.field_70163_u) * (e.field_70163_u - p.field_70163_u) + (e.field_70161_v - p.field_70161_v) * (e.field_70161_v - p.field_70161_v)) * 2.0);
            for (int i = 0; i < 6; ++i) {
                ThaumicTinkerer.tcProxy.sparkle((float)e.field_70165_t, (float)e.field_70163_u, (float)e.field_70161_v, 6);
            }
            e.field_70165_t += motionVec.x;
            e.field_70163_u += motionVec.y;
            e.field_70161_v += motionVec.z;
        }
    }

    private static boolean CheckBlackList(Entity entity) {
        Class<?> aClass = entity.getClass();
        if (DeflectBlacklist.contains(aClass)) {
            return true;
        }
        if (Loader.isModLoaded((String)"Botania")) {
            return BotaniaFunctions.isEntityHarmless(entity);
        }
        for (Class<?> testClass : DeflectBlacklist) {
            if (!testClass.isInterface() || !testClass.isAssignableFrom(aClass)) continue;
            return true;
        }
        return false;
    }

    @Override
    public void onUsingFocusTick(ItemStack stack, EntityPlayer p, int ticks) {
        ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
        if (wand.consumeAllVis(stack, p, this.getVisCost(stack), true, false)) {
            ItemFocusDeflect.protectFromProjectiles(p);
        }
    }

    @Override
    public String getSortingHelper(ItemStack paramItemStack) {
        return "DEFLECT";
    }

    @Override
    public boolean isVisCostPerTick(ItemStack stack) {
        return true;
    }

    public int getFocusColor(ItemStack stack) {
        return 0xFFFFFF;
    }

    public AspectList getVisCost(ItemStack stack) {
        return this.visUsage;
    }

    @Override
    public String getItemName() {
        return "focusDeflect";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        if (!Config.allowMirrors) {
            return null;
        }
        return (TTResearchItem)new TTResearchItem("FOCUS_DEFLECT", new AspectList().add(Aspect.MOTION, 2).add(Aspect.AIR, 1).add(Aspect.ORDER, 1).add(Aspect.DEATH, 1), -4, -3, 3, new ItemStack((Item)this), new ResearchPage[0]).setConcealed().setParents(new String[]{"FOCUS_SMELT"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("FOCUS_DEFLECT")}).setSecondary();
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("FOCUS_DEFLECT", new ItemStack((Item)this), 5, new AspectList().add(Aspect.AIR, 15).add(Aspect.ARMOR, 5).add(Aspect.ORDER, 20), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemFocusFlight.class)), new ItemStack(ConfigItems.itemResource, 1, 10), new ItemStack(ConfigItems.itemResource, 1, 10), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 3), new ItemStack(ConfigItems.itemShard, 1, 4));
    }
}

