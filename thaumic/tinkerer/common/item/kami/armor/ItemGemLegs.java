/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.codechicken.lib.vec.Vector3
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.item.kami.armor;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.codechicken.lib.vec.Vector3;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.item.ItemBrightNitor;
import thaumic.tinkerer.common.item.foci.ItemFocusSmelt;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;
import thaumic.tinkerer.common.item.kami.armor.ItemIchorclothArmor;
import thaumic.tinkerer.common.item.kami.armor.ItemIchorclothArmorAdv;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.KamiResearchItem;
import thaumic.tinkerer.common.research.ResearchHelper;

public class ItemGemLegs
extends ItemIchorclothArmorAdv {
    public ItemGemLegs() {
        super(2);
    }

    @Override
    boolean ticks() {
        return true;
    }

    @Override
    void tickPlayer(EntityPlayer player) {
        ItemStack armor = player.func_82169_q(1);
        if (armor.func_77960_j() == 1 || !ThaumicTinkerer.proxy.armorStatus(player)) {
            return;
        }
        PotionEffect effect = player.func_70660_b(Potion.field_76426_n);
        if (effect != null && effect.field_76460_b <= 202) {
            effect.field_76460_b = 202;
        } else {
            player.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 202, 10, true));
        }
        ItemBrightNitor.meta = 1;
        ((ItemBrightNitor)ThaumicTinkerer.registry.getFirstItemFromClass(ItemBrightNitor.class)).func_77663_a(null, player.field_70170_p, (Entity)player, 0, false);
        ItemBrightNitor.meta = 0;
        int x = (int)Math.floor(player.field_70165_t);
        int y = (int)player.field_70163_u + 1;
        int z = (int)Math.floor(player.field_70161_v);
        float yaw = MathHelper.func_76142_g((float)(player.field_70177_z + 90.0f)) * (float)Math.PI / 180.0f;
        Vector3 lookVector = new Vector3(Math.cos(yaw), Math.sin(yaw), 0.0).normalize();
        Vector3 newVector = new Vector3(lookVector.x, lookVector.y, 0.0);
        for (int i = 0; i < 5; ++i) {
            newVector = newVector.add(lookVector);
            int x1 = x + (int)newVector.x;
            int z1 = z + (int)newVector.y;
            ItemBrightNitor.setBlock(x1, y, z1, player.field_70170_p);
        }
    }

    @Override
    public String getItemName() {
        return "ichorclothLegsGem";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new KamiResearchItem("ICHORCLOTH_LEGS_GEM", new AspectList().add(Aspect.FIRE, 2).add(Aspect.HEAL, 1).add(Aspect.GREED, 1).add(Aspect.ENERGY, 1), 17, 9, 5, new ItemStack((Item)this)).setParents(new String[]{"ICHORCLOTH_ARMOR"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("ICHORCLOTH_LEGS_GEM"), new ResearchPage("1")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("ICHORCLOTH_LEGS_GEM", new ItemStack((Item)this), 13, new AspectList().add(Aspect.FIRE, 50).add(Aspect.ARMOR, 32).add(Aspect.HEAL, 32).add(Aspect.ENERGY, 32).add(Aspect.LIGHT, 64).add(Aspect.GREED, 16).add(Aspect.ELDRITCH, 16), new ItemStack(ThaumicTinkerer.registry.getItemFromClassAndName(ItemIchorclothArmor.class, "ichorclothLegs")), new ItemStack(Items.field_151045_i, 1), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(ConfigItems.itemFocusPrimal), new ItemStack(ConfigItems.itemThaumonomicon), new ItemStack((Item)Items.field_151171_ah), new ItemStack((Item)Items.field_151068_bn, 1, 8195), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemFocusSmelt.class)), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemBrightNitor.class)), new ItemStack(Items.field_151129_at), new ItemStack(Items.field_151059_bz), new ItemStack(Items.field_151072_bj));
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onDamageTaken(LivingHurtEvent event) {
        EntityPlayer player;
        if (event.entityLiving instanceof EntityPlayer && (player = (EntityPlayer)event.entityLiving).func_82169_q(1) != null && player.func_82169_q(1).func_77973_b() == this && event.source.func_76347_k() && ThaumicTinkerer.proxy.armorStatus(player)) {
            event.setCanceled(true);
            player.func_70691_i(event.ammount);
        }
    }
}

