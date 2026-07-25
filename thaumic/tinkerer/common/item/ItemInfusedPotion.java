/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemPotion
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 */
package thaumic.tinkerer.common.item;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.item.ItemInfusedGrain;
import thaumic.tinkerer.common.item.ItemInfusedSeeds;
import thaumic.tinkerer.common.registry.ITTinkererItem;
import thaumic.tinkerer.common.registry.ThaumicTinkererCrucibleRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipeMulti;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class ItemInfusedPotion
extends ItemPotion
implements ITTinkererItem {
    private IIcon[] icons;

    public static int getMetaForAspect(Aspect aspect) {
        for (PRIMAL_ASPECT_ENUM e : PRIMAL_ASPECT_ENUM.values()) {
            if (aspect != e.aspect) continue;
            return e.ordinal();
        }
        return 0;
    }

    public String func_77653_i(ItemStack par1ItemStack) {
        String s = "item.infusedPotion." + this.getAspect(par1ItemStack).getName() + ".name";
        return StatCollector.func_74838_a((String)s).trim();
    }

    public List func_77832_l(ItemStack par1ItemStack) {
        return this.func_77834_f(par1ItemStack.func_77960_j());
    }

    public List func_77834_f(int par1) {
        ArrayList<PotionEffect> r = new ArrayList<PotionEffect>();
        switch (par1) {
            case 0: {
                r.add(new PotionEffect(ConfigHandler.potionAirId, 3600));
                break;
            }
            case 1: {
                r.add(new PotionEffect(ConfigHandler.potionFireId, 3600));
                break;
            }
            case 2: {
                r.add(new PotionEffect(ConfigHandler.potionEarthId, 3600));
                break;
            }
            case 3: {
                r.add(new PotionEffect(ConfigHandler.potionWaterId, 3600));
            }
        }
        return r;
    }

    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[4];
        this.icons[0] = IconHelper.forName(par1IconRegister, "potion_aer");
        this.icons[1] = IconHelper.forName(par1IconRegister, "potion_ignis");
        this.icons[2] = IconHelper.forName(par1IconRegister, "potion_terra");
        this.icons[3] = IconHelper.forName(par1IconRegister, "potion_aqua");
    }

    public IIcon func_77617_a(int par1) {
        return this.icons[par1];
    }

    public IIcon func_77618_c(int par1, int par2) {
        return this.icons[par1];
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
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
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getItemName() {
        return "infusedPotion";
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
        if (!ConfigHandler.enableFire) {
            return (TTResearchItem)new TTResearchItem("INFUSED_POTIONS", new AspectList().add(Aspect.WATER, 5).add(Aspect.ENTROPY, 5), 7, -5, 2, ItemInfusedSeeds.getStackFromAspect(Aspect.FIRE), new ResearchPage[0]).setParentsHidden(new String[]{"INFUSION"}).setConcealed().setPages(new ResearchPage[]{new ResearchPage("0"), new ResearchPage("1"), new ResearchPage("2"), new ResearchPage("3"), new ResearchPage("4"), new ResearchPage("5"), ResearchHelper.infusionPage("INFUSED_POTIONS", 4), ResearchHelper.crucibleRecipePage("INFUSED_POTIONSPOT0"), ResearchHelper.crucibleRecipePage("INFUSED_POTIONSPOT1"), ResearchHelper.crucibleRecipePage("INFUSED_POTIONSPOT2"), ResearchHelper.crucibleRecipePage("INFUSED_POTIONSPOT3")});
        }
        return (TTResearchItem)new TTResearchItem("INFUSED_POTIONS", new AspectList().add(Aspect.WATER, 5).add(Aspect.ENTROPY, 5), 7, -5, 2, ItemInfusedSeeds.getStackFromAspect(Aspect.FIRE), new ResearchPage[0]).setParents(new String[]{"FIRE_PERDITIO", "FIRE_ORDO", "FIRE_IGNIS", "FIRE_TERRA", "FIRE_AER", "FIRE_AQUA"}).setParentsHidden(new String[]{"INFUSION"}).setConcealed().setPages(new ResearchPage[]{new ResearchPage("0"), new ResearchPage("1"), new ResearchPage("2"), new ResearchPage("3"), new ResearchPage("4"), new ResearchPage("5"), ResearchHelper.infusionPage("INFUSED_POTIONS", 4), ResearchHelper.crucibleRecipePage("INFUSED_POTIONSPOT0"), ResearchHelper.crucibleRecipePage("INFUSED_POTIONSPOT1"), ResearchHelper.crucibleRecipePage("INFUSED_POTIONSPOT2"), ResearchHelper.crucibleRecipePage("INFUSED_POTIONSPOT3")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererRecipeMulti(new ThaumicTinkererCrucibleRecipe("INFUSED_POTIONSPOT0", new ItemStack((Item)this, 1, 0), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemInfusedGrain.class), 1, 0), new AspectList().add(Aspect.AURA, 5).add(Aspect.AIR, 5)), new ThaumicTinkererCrucibleRecipe("INFUSED_POTIONSPOT1", new ItemStack((Item)this, 1, 1), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemInfusedGrain.class), 1, 1), new AspectList().add(Aspect.AURA, 5).add(Aspect.FIRE, 5)), new ThaumicTinkererCrucibleRecipe("INFUSED_POTIONSPOT2", new ItemStack((Item)this, 1, 2), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemInfusedGrain.class), 1, 2), new AspectList().add(Aspect.AURA, 5).add(Aspect.EARTH, 5)), new ThaumicTinkererCrucibleRecipe("INFUSED_POTIONSPOT3", new ItemStack((Item)this, 1, 3), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemInfusedGrain.class), 1, 3), new AspectList().add(Aspect.AURA, 5).add(Aspect.WATER, 5)));
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

