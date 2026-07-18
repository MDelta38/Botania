/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.lexicon;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.lexicon.IRecipeKeyProvider;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.IManaItem;

public final class LexiconRecipeMappings {
    private static Map<String, EntryData> mappings = new HashMap<String, EntryData>();

    public static void map(ItemStack stack, LexiconEntry entry, int page, boolean force) {
        EntryData data = new EntryData(entry, page);
        String str = LexiconRecipeMappings.stackToString(stack);
        if (force || !mappings.containsKey(str)) {
            mappings.put(str, data);
        }
        if (entry.getIcon() == null) {
            entry.setIcon(stack.func_77946_l());
        }
    }

    public static void map(ItemStack stack, LexiconEntry entry, int page) {
        LexiconRecipeMappings.map(stack, entry, page, false);
    }

    public static void remove(ItemStack stack) {
        mappings.remove(LexiconRecipeMappings.stackToString(stack));
    }

    public static EntryData getDataForStack(ItemStack stack) {
        return mappings.get(LexiconRecipeMappings.stackToString(stack));
    }

    public static String stackToString(ItemStack stack) {
        if (stack == null || stack.func_77973_b() == null) {
            return "NULL";
        }
        if (stack.func_77942_o() && stack.func_77973_b() instanceof IRecipeKeyProvider) {
            return ((IRecipeKeyProvider)stack.func_77973_b()).getKey(stack);
        }
        return stack.func_77977_a() + (LexiconRecipeMappings.ignoreMeta(stack) ? "" : "~" + stack.func_77960_j());
    }

    public static boolean ignoreMeta(ItemStack stack) {
        return stack.func_77984_f() || stack.func_77973_b() instanceof IManaItem;
    }

    public static class EntryData {
        public final LexiconEntry entry;
        public final int page;

        public EntryData(LexiconEntry entry, int page) {
            this.entry = entry;
            this.page = page;
        }
    }
}

