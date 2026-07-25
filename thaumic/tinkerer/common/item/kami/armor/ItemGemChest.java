/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.item.kami.armor;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.client.model.kami.ModelWings;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.item.foci.ItemFocusDeflect;
import thaumic.tinkerer.common.item.foci.ItemFocusFlight;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;
import thaumic.tinkerer.common.item.kami.armor.ItemIchorclothArmor;
import thaumic.tinkerer.common.item.kami.armor.ItemIchorclothArmorAdv;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.KamiResearchItem;
import thaumic.tinkerer.common.research.ResearchHelper;

public class ItemGemChest
extends ItemIchorclothArmorAdv {
    public static List<String> playersWithFlight = new ArrayList<String>();

    public ItemGemChest() {
        super(1);
    }

    public static String playerStr(EntityPlayer player) {
        return player.func_146103_bH().getName() + ":" + player.field_70170_p.field_72995_K;
    }

    private static boolean shouldPlayerHaveFlight(EntityPlayer player) {
        ItemStack armor = player.func_82169_q(2);
        return armor != null && armor.func_77973_b() == ThaumicTinkerer.registry.getFirstItemFromClass(ItemGemChest.class) && ThaumicTinkerer.proxy.armorStatus(player) && armor.func_77960_j() == 0 && ConfigHandler.enableFlight;
    }

    @SideOnly(value=Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        return new ModelWings();
    }

    @Override
    boolean ticks() {
        return true;
    }

    @Override
    void tickPlayer(EntityPlayer player) {
        ItemStack armor = player.func_82169_q(2);
        if (armor.func_77960_j() == 1 || !ThaumicTinkerer.proxy.armorStatus(player)) {
            return;
        }
        ItemFocusDeflect.protectFromProjectiles(player);
    }

    @Override
    public String getItemName() {
        return "ichorclothChestGem";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new KamiResearchItem("ICHORCLOTH_CHEST_GEM", new AspectList().add(Aspect.AIR, 2).add(Aspect.MOTION, 1).add(Aspect.FLIGHT, 1).add(Aspect.ELDRITCH, 1), 17, 7, 5, new ItemStack((Item)this)).setParents(new String[]{"ICHORCLOTH_ARMOR"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("ICHORCLOTH_CHEST_GEM")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("ICHORCLOTH_CHEST_GEM", new ItemStack((Item)this), 13, new AspectList().add(Aspect.AIR, 50).add(Aspect.ARMOR, 32).add(Aspect.FLIGHT, 32).add(Aspect.ORDER, 32).add(Aspect.LIGHT, 64).add(Aspect.ELDRITCH, 16).add(Aspect.SENSES, 16), new ItemStack(ThaumicTinkerer.registry.getItemFromClassAndName(ItemIchorclothArmor.class, "ichorclothChest")), new ItemStack(Items.field_151045_i, 1), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(ConfigItems.itemFocusPrimal), new ItemStack(ConfigItems.itemThaumonomicon), new ItemStack((Item)Items.field_151171_ah), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemFocusFlight.class)), new ItemStack(ConfigItems.itemHoverHarness), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemFocusDeflect.class)), new ItemStack(Items.field_151008_G), new ItemStack(Items.field_151073_bk), new ItemStack(Items.field_151032_g));
    }

    @SubscribeEvent
    public void updatePlayerFlyStatus(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            ItemStack armor = player.func_82169_q(3 - this.field_77881_a);
            if (armor != null && armor.func_77973_b() == this) {
                this.tickPlayer(player);
            }
            if (playersWithFlight.contains(ItemGemChest.playerStr(player))) {
                if (ItemGemChest.shouldPlayerHaveFlight(player)) {
                    player.field_71075_bZ.field_75101_c = true;
                } else {
                    if (!player.field_71075_bZ.field_75098_d) {
                        player.field_71075_bZ.field_75101_c = false;
                        player.field_71075_bZ.field_75100_b = false;
                        player.field_71075_bZ.field_75102_a = false;
                    }
                    playersWithFlight.remove(ItemGemChest.playerStr(player));
                }
            } else if (ItemGemChest.shouldPlayerHaveFlight(player)) {
                playersWithFlight.add(ItemGemChest.playerStr(player));
                player.field_71075_bZ.field_75101_c = true;
            }
        }
    }
}

