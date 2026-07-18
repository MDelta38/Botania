/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.ILexicon;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.KnowledgeType;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.recipe.IElvenItem;
import vazkii.botania.common.Botania;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.MathHelper;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.item.relic.ItemDice;

public class ItemLexicon
extends ItemMod
implements ILexicon,
IElvenItem {
    private static final String TAG_KNOWLEDGE_PREFIX = "knowledge.";
    private static final String TAG_FORCED_MESSAGE = "forcedMessage";
    private static final String TAG_QUEUE_TICKS = "queueTicks";
    boolean skipSound = false;

    public ItemLexicon() {
        this.func_77625_d(1);
        this.func_77655_b("lexicon");
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        Block block;
        if (par2EntityPlayer.func_70093_af() && (block = par3World.func_147439_a(par4, par5, par6)) != null) {
            if (block instanceof ILexiconable) {
                LexiconEntry entry = ((ILexiconable)block).getEntry(par3World, par4, par5, par6, par2EntityPlayer, par1ItemStack);
                if (entry != null && this.isKnowledgeUnlocked(par1ItemStack, entry.getKnowledgeType())) {
                    Botania.proxy.setEntryToOpen(entry);
                    Botania.proxy.setLexiconStack(par1ItemStack);
                    ItemLexicon.openBook(par2EntityPlayer, par1ItemStack, par3World, false);
                    return true;
                }
            } else if (par3World.field_72995_K) {
                MovingObjectPosition pos = new MovingObjectPosition(par4, par5, par6, par7, Vec3.func_72443_a((double)par8, (double)par9, (double)par10));
                return Botania.proxy.openWikiPage(par3World, block, pos);
            }
        }
        return false;
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(item));
        ItemStack creative = new ItemStack(item);
        for (String s : BotaniaAPI.knowledgeTypes.keySet()) {
            KnowledgeType type = BotaniaAPI.knowledgeTypes.get(s);
            this.unlockKnowledge(creative, type);
        }
        list.add(creative);
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (GuiScreen.func_146272_n()) {
            String edition = EnumChatFormatting.GOLD + String.format(StatCollector.func_74838_a((String)"botaniamisc.edition"), ItemLexicon.getEdition());
            if (!edition.isEmpty()) {
                par3List.add(edition);
            }
            ArrayList<KnowledgeType> typesKnown = new ArrayList<KnowledgeType>();
            for (String s : BotaniaAPI.knowledgeTypes.keySet()) {
                KnowledgeType type = BotaniaAPI.knowledgeTypes.get(s);
                if (!this.isKnowledgeUnlocked(par1ItemStack, type)) continue;
                typesKnown.add(type);
            }
            String format = typesKnown.size() == 1 ? "botaniamisc.knowledgeTypesSingular" : "botaniamisc.knowledgeTypesPlural";
            this.addStringToTooltip(String.format(StatCollector.func_74838_a((String)format), typesKnown.size()), par3List);
            for (KnowledgeType type : typesKnown) {
                this.addStringToTooltip(" \u2022 " + StatCollector.func_74838_a((String)type.getUnlocalizedName()), par3List);
            }
        } else {
            this.addStringToTooltip(StatCollector.func_74838_a((String)"botaniamisc.shiftinfo"), par3List);
        }
    }

    private void addStringToTooltip(String s, List<String> tooltip) {
        tooltip.add(s.replaceAll("&", "\u00a7"));
    }

    public static String getEdition() {
        String version = "249";
        int build = version.contains("GRADLE") ? 0 : Integer.parseInt(version);
        return build == 0 ? StatCollector.func_74838_a((String)"botaniamisc.devEdition") : MathHelper.numberToOrdinal(build);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        String force = ItemLexicon.getForcedPage(par1ItemStack);
        if (force != null && !force.isEmpty()) {
            LexiconEntry entry = ItemLexicon.getEntryFromForce(par1ItemStack);
            if (entry != null) {
                Botania.proxy.setEntryToOpen(entry);
            } else {
                par3EntityPlayer.func_145747_a(new ChatComponentTranslation("botaniamisc.cantOpen", new Object[0]).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.RED)));
            }
            ItemLexicon.setForcedPage(par1ItemStack, "");
        }
        ItemLexicon.openBook(par3EntityPlayer, par1ItemStack, par2World, this.skipSound);
        this.skipSound = false;
        return par1ItemStack;
    }

    public static void openBook(EntityPlayer player, ItemStack stack, World world, boolean skipSound) {
        ILexicon l = (ILexicon)stack.func_77973_b();
        Botania.proxy.setToTutorialIfFirstLaunch();
        if (!l.isKnowledgeUnlocked(stack, BotaniaAPI.relicKnowledge) && l.isKnowledgeUnlocked(stack, BotaniaAPI.elvenKnowledge)) {
            for (ItemStack rstack : ItemDice.relicStacks) {
                Item item = rstack.func_77973_b();
                if (!player.field_71071_by.func_146028_b(item)) continue;
                l.unlockKnowledge(stack, BotaniaAPI.relicKnowledge);
                break;
            }
        }
        Botania.proxy.setLexiconStack(stack);
        player.func_71064_a((StatBase)ModAchievements.lexiconUse, 1);
        player.openGui((Object)Botania.instance, 0, world, 0, 0, 0);
        if (!world.field_72995_K && !skipSound) {
            world.func_72956_a((Entity)player, "botania:lexiconOpen", 0.5f, 1.0f);
        }
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int idk, boolean something) {
        int ticks = ItemLexicon.getQueueTicks(stack);
        if (ticks > 0 && entity instanceof EntityPlayer) {
            boolean bl = this.skipSound = ticks < 5;
            if (ticks == 1) {
                this.func_77659_a(stack, world, (EntityPlayer)entity);
            }
            ItemLexicon.setQueueTicks(stack, ticks - 1);
        }
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.uncommon;
    }

    @Override
    public boolean isKnowledgeUnlocked(ItemStack stack, KnowledgeType knowledge) {
        return knowledge.autoUnlock || ItemNBTHelper.getBoolean(stack, TAG_KNOWLEDGE_PREFIX + knowledge.id, false);
    }

    @Override
    public void unlockKnowledge(ItemStack stack, KnowledgeType knowledge) {
        ItemNBTHelper.setBoolean(stack, TAG_KNOWLEDGE_PREFIX + knowledge.id, true);
    }

    public static void setForcedPage(ItemStack stack, String forced) {
        ItemNBTHelper.setString(stack, TAG_FORCED_MESSAGE, forced);
    }

    public static String getForcedPage(ItemStack stack) {
        return ItemNBTHelper.getString(stack, TAG_FORCED_MESSAGE, "");
    }

    private static LexiconEntry getEntryFromForce(ItemStack stack) {
        String force = ItemLexicon.getForcedPage(stack);
        for (LexiconEntry entry : BotaniaAPI.getAllEntries()) {
            if (!entry.getUnlocalizedName().equals(force) || entry == null || !((ItemLexicon)stack.func_77973_b()).isKnowledgeUnlocked(stack, entry.getKnowledgeType())) continue;
            return entry;
        }
        return null;
    }

    public static int getQueueTicks(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_QUEUE_TICKS, 0);
    }

    public static void setQueueTicks(ItemStack stack, int ticks) {
        ItemNBTHelper.setInt(stack, TAG_QUEUE_TICKS, ticks);
    }

    @Override
    public boolean isElvenItem(ItemStack stack) {
        return this.isKnowledgeUnlocked(stack, BotaniaAPI.elvenKnowledge);
    }
}

