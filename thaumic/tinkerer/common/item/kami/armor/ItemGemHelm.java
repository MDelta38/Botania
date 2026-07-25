/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  thaumcraft.api.IGoggles
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.nodes.IRevealer
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.item.kami.armor;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import thaumcraft.api.IGoggles;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.IRevealer;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;
import thaumic.tinkerer.common.item.kami.armor.ItemIchorclothArmor;
import thaumic.tinkerer.common.item.kami.armor.ItemIchorclothArmorAdv;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.KamiResearchItem;
import thaumic.tinkerer.common.research.ResearchHelper;

public class ItemGemHelm
extends ItemIchorclothArmorAdv
implements IGoggles,
IRevealer {
    public ItemGemHelm() {
        super(0);
    }

    public boolean showNodes(ItemStack paramItemStack, EntityLivingBase paramEntityLivingBase) {
        return true;
    }

    public boolean showIngamePopups(ItemStack paramItemStack, EntityLivingBase paramEntityLivingBase) {
        return true;
    }

    @Override
    boolean ticks() {
        return true;
    }

    @Override
    void tickPlayer(EntityPlayer player) {
        int food;
        PotionEffect effect;
        ItemStack armor = player.func_82169_q(3);
        if (player.func_70055_a(Material.field_151586_h) && ThaumicTinkerer.proxy.armorStatus(player) && armor.func_77960_j() == 0) {
            player.func_70050_g(300);
            effect = player.func_70660_b(Potion.field_76439_r);
            if (effect != null && effect.field_76460_b <= 202) {
                effect.field_76460_b = 202;
            } else {
                player.func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, 202, 0, true));
            }
        }
        if (player.func_70055_a(Material.field_151587_i) && ThaumicTinkerer.proxy.armorStatus(player) && armor.func_77960_j() == 0) {
            player.func_70050_g(300);
            effect = player.func_70660_b(Potion.field_76440_q);
            if (effect != null && effect.field_76460_b <= 202) {
                effect.field_76460_b = 202;
            } else {
                player.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 202, 0, true));
            }
        }
        if ((food = player.func_71024_bL().func_75116_a()) > 0 && food < 18 && player.func_70996_bM() && player.field_70173_aa % 80 == 0) {
            player.func_70691_i(1.0f);
        }
    }

    @Override
    public String getItemName() {
        return "ichorclothHelmGem";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new KamiResearchItem("ICHORCLOTH_HELM_GEM", new AspectList().add(Aspect.WATER, 2).add(Aspect.HEAL, 1).add(Aspect.HUNGER, 1).add(Aspect.AURA, 1), 18, 3, 5, new ItemStack((Item)this)).setParents(new String[]{"ICHORCLOTH_ARMOR"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("ICHORCLOTH_HELM_GEM")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("ICHORCLOTH_HELM_GEM", new ItemStack((Item)this), 13, new AspectList().add(Aspect.WATER, 50).add(Aspect.ARMOR, 32).add(Aspect.HUNGER, 32).add(Aspect.AURA, 32).add(Aspect.LIGHT, 64).add(Aspect.FLESH, 16).add(Aspect.MIND, 16), new ItemStack(ThaumicTinkerer.registry.getItemFromClassAndName(ItemIchorclothArmor.class, "ichorclothHelm")), new ItemStack(Items.field_151045_i, 1), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(ConfigItems.itemThaumonomicon), new ItemStack(ConfigItems.itemFocusPrimal), new ItemStack((Item)Items.field_151169_ag), new ItemStack((Item)Items.field_151068_bn, 1, 8198), new ItemStack(ConfigItems.itemGoggles), new ItemStack(Items.field_151073_bk), new ItemStack(Items.field_151115_aP), new ItemStack(Items.field_151105_aU), new ItemStack(Items.field_151061_bv));
    }
}

