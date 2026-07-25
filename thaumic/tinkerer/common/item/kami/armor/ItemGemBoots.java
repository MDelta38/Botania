/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingJumpEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.event.entity.living.LivingFallEvent
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.item.kami.armor;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
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

public class ItemGemBoots
extends ItemIchorclothArmorAdv {
    public static List<String> playersWith1Step = new ArrayList<String>();

    public ItemGemBoots() {
        super(3);
    }

    @Override
    boolean ticks() {
        return true;
    }

    @Override
    void tickPlayer(EntityPlayer player) {
        ItemStack armor = player.func_82169_q(0);
        if (!ThaumicTinkerer.proxy.armorStatus(player) || armor.func_77960_j() == 1) {
            return;
        }
        player.func_70690_d(new PotionEffect(Potion.field_76422_e.field_76415_H, 2, 1, true));
        if (player.field_70170_p.field_72995_K) {
            float f = player.field_70138_W = player.func_70093_af() ? 0.5f : 1.0f;
        }
        if ((player.field_70122_E || player.field_71075_bZ.field_75100_b) && player.field_70701_bs > 0.0f) {
            player.func_70060_a(0.0f, 1.0f, player.field_71075_bZ.field_75100_b ? 0.075f : 0.15f);
        }
        player.field_70747_aH = player.func_70051_ag() ? 0.05f : 0.04f;
        player.field_70143_R = 0.0f;
        int x = (int)player.field_70165_t;
        int y = (int)player.field_70163_u - 1;
        int z = (int)player.field_70161_v;
        if (player.field_70170_p.func_147439_a(x, y, z) == Blocks.field_150346_d && player.field_70170_p.func_72805_g(x, y, z) == 0) {
            player.field_70170_p.func_147465_d(x, y, z, (Block)Blocks.field_150349_c, 0, 2);
        }
    }

    @Override
    public String getItemName() {
        return "ichorclothBootsGem";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new KamiResearchItem("ICHORCLOTH_BOOTS_GEM", new AspectList().add(Aspect.EARTH, 2).add(Aspect.TRAVEL, 1).add(Aspect.MINE, 1).add(Aspect.PLANT, 1), 15, 10, 5, new ItemStack((Item)this)).setParents(new String[]{"ICHORCLOTH_ARMOR"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("ICHORCLOTH_BOOTS_GEM")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("ICHORCLOTH_BOOTS_GEM", new ItemStack((Item)this), 13, new AspectList().add(Aspect.EARTH, 50).add(Aspect.ARMOR, 32).add(Aspect.MINE, 32).add(Aspect.MOTION, 32).add(Aspect.LIGHT, 64).add(Aspect.PLANT, 16).add(Aspect.TRAVEL, 16), new ItemStack(ThaumicTinkerer.registry.getItemFromClassAndName(ItemIchorclothArmor.class, "ichorclothBoots")), new ItemStack(Items.field_151045_i, 1), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(ConfigItems.itemThaumonomicon), new ItemStack(ConfigItems.itemFocusPrimal), new ItemStack((Item)Items.field_151151_aj), new ItemStack((Block)Blocks.field_150349_c), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 5), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 8), new ItemStack(Items.field_151014_N), new ItemStack(Blocks.field_150325_L), new ItemStack(Items.field_151058_ca));
    }

    @SubscribeEvent
    public void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            boolean hasArmor;
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            boolean bl = hasArmor = player.func_82169_q(0) != null && player.func_82169_q(0).func_77973_b() == this;
            if (hasArmor && ThaumicTinkerer.proxy.armorStatus(player) && player.func_82169_q(0).func_77960_j() == 0) {
                player.field_70181_x += 0.3;
            }
        }
    }

    @SubscribeEvent
    public void onFall(LivingFallEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            boolean hasArmor;
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            boolean bl = hasArmor = player.func_82169_q(0) != null && player.func_82169_q(0).func_77973_b() == this;
            if (hasArmor) {
                event.distance = 0.0f;
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGH)
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayer && event.entityLiving.field_70170_p.field_72995_K) {
            boolean hasHighStep;
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            boolean highStepListed = playersWith1Step.contains(player.func_146103_bH().getName());
            boolean bl = hasHighStep = player.func_82169_q(0) != null && player.func_82169_q(0).func_77973_b() == this;
            if (!highStepListed && hasHighStep && ThaumicTinkerer.proxy.armorStatus(player) && player.func_82169_q(0).func_77960_j() == 0) {
                playersWith1Step.add(player.func_146103_bH().getName());
            }
            if (!(hasHighStep && ThaumicTinkerer.proxy.armorStatus(player) && player.func_82169_q(0).func_77960_j() != 1 || !highStepListed)) {
                playersWith1Step.remove(player.func_146103_bH().getName());
                player.field_70138_W = 0.5f;
            }
        }
    }
}

