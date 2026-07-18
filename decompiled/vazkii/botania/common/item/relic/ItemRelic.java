/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.relic;

import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.item.ModItems;

public class ItemRelic
extends ItemMod
implements IRelic {
    private static final String TAG_SOULBIND = "soulbind";
    Achievement achievement;

    public ItemRelic(String name) {
        this.func_77655_b(name);
        this.func_77625_d(1);
    }

    public void func_77663_a(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if (p_77663_3_ instanceof EntityPlayer) {
            ItemRelic.updateRelic(p_77663_1_, (EntityPlayer)p_77663_3_);
        }
    }

    public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        ItemRelic.addBindInfo(p_77624_3_, p_77624_1_, p_77624_2_);
    }

    public static void addBindInfo(List list, ItemStack stack, EntityPlayer player) {
        if (GuiScreen.func_146272_n()) {
            String bind = ItemRelic.getSoulbindUsernameS(stack);
            if (bind.isEmpty()) {
                ItemRelic.addStringToTooltip(StatCollector.func_74838_a((String)"botaniamisc.relicUnbound"), list);
            } else {
                ItemRelic.addStringToTooltip(String.format(StatCollector.func_74838_a((String)"botaniamisc.relicSoulbound"), bind), list);
                if (!ItemRelic.isRightPlayer(player, stack)) {
                    ItemRelic.addStringToTooltip(String.format(StatCollector.func_74838_a((String)"botaniamisc.notYourSagittarius"), bind), list);
                }
            }
            if (stack.func_77973_b() == ModItems.aesirRing) {
                ItemRelic.addStringToTooltip(StatCollector.func_74838_a((String)"botaniamisc.dropIkea"), list);
            }
            if (stack.func_77973_b() == ModItems.dice) {
                ItemRelic.addStringToTooltip("", list);
                String name = stack.func_77977_a() + ".poem";
                for (int i = 0; i < 4; ++i) {
                    ItemRelic.addStringToTooltip(EnumChatFormatting.ITALIC + StatCollector.func_74838_a((String)(name + i)), list);
                }
            }
        } else {
            ItemRelic.addStringToTooltip(StatCollector.func_74838_a((String)"botaniamisc.shiftinfo"), list);
        }
    }

    public boolean shouldDamageWrongPlayer() {
        return true;
    }

    public int getEntityLifespan(ItemStack itemStack, World world) {
        return Integer.MAX_VALUE;
    }

    static void addStringToTooltip(String s, List<String> tooltip) {
        tooltip.add(s.replaceAll("&", "\u00a7"));
    }

    public static String getSoulbindUsernameS(ItemStack stack) {
        return ItemNBTHelper.getString(stack, TAG_SOULBIND, "");
    }

    public static void updateRelic(ItemStack stack, EntityPlayer player) {
        if (stack == null || !(stack.func_77973_b() instanceof IRelic)) {
            return;
        }
        String soulbind = ItemRelic.getSoulbindUsernameS(stack);
        if (soulbind.isEmpty()) {
            player.func_71064_a((StatBase)((IRelic)stack.func_77973_b()).getBindAchievement(), 1);
            ItemRelic.bindToPlayer(player, stack);
            soulbind = ItemRelic.getSoulbindUsernameS(stack);
        }
        if (!(ItemRelic.isRightPlayer(player, stack) || player.field_70173_aa % 10 != 0 || stack.func_77973_b() instanceof ItemRelic && !((ItemRelic)stack.func_77973_b()).shouldDamageWrongPlayer())) {
            player.func_70097_a(ItemRelic.damageSource(), 2.0f);
        }
    }

    public static void bindToPlayer(EntityPlayer player, ItemStack stack) {
        ItemRelic.bindToUsernameS(player.func_70005_c_(), stack);
    }

    public static void bindToUsernameS(String username, ItemStack stack) {
        ItemNBTHelper.setString(stack, TAG_SOULBIND, username);
    }

    public static boolean isRightPlayer(EntityPlayer player, ItemStack stack) {
        return ItemRelic.isRightPlayer(player.func_70005_c_(), stack);
    }

    public static boolean isRightPlayer(String player, ItemStack stack) {
        return ItemRelic.getSoulbindUsernameS(stack).equals(player);
    }

    public static DamageSource damageSource() {
        return new DamageSource("botania-relic");
    }

    @Override
    public void bindToUsername(String playerName, ItemStack stack) {
        ItemRelic.bindToUsernameS(playerName, stack);
    }

    @Override
    public String getSoulbindUsername(ItemStack stack) {
        return ItemRelic.getSoulbindUsernameS(stack);
    }

    @Override
    public Achievement getBindAchievement() {
        return this.achievement;
    }

    @Override
    public void setBindAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    public EnumRarity func_77613_e(ItemStack p_77613_1_) {
        return BotaniaAPI.rarityRelic;
    }
}

