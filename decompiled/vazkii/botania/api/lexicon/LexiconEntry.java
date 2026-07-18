/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.api.lexicon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.KnowledgeType;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconPage;

public class LexiconEntry
implements Comparable<LexiconEntry> {
    public final String unlocalizedName;
    public final LexiconCategory category;
    private KnowledgeType type = BotaniaAPI.basicKnowledge;
    public List<LexiconPage> pages = new ArrayList<LexiconPage>();
    private boolean priority = false;
    private ItemStack icon = null;
    private List<ItemStack> extraDisplayedRecipes = new ArrayList<ItemStack>();

    public LexiconEntry(String unlocalizedName, LexiconCategory category) {
        this.unlocalizedName = unlocalizedName;
        this.category = category;
    }

    public LexiconEntry setPriority() {
        this.priority = true;
        return this;
    }

    public LexiconEntry setKnowledgeType(KnowledgeType type) {
        this.type = type;
        return this;
    }

    public KnowledgeType getKnowledgeType() {
        return this.type;
    }

    public void setIcon(ItemStack stack) {
        this.icon = stack;
    }

    public ItemStack getIcon() {
        return this.icon;
    }

    public boolean isPriority() {
        return this.priority;
    }

    public String getUnlocalizedName() {
        return this.unlocalizedName;
    }

    public String getTagline() {
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean isVisible() {
        return true;
    }

    public LexiconEntry setLexiconPages(LexiconPage ... pages) {
        this.pages.addAll(Arrays.asList(pages));
        for (int i = 0; i < this.pages.size(); ++i) {
            LexiconPage page = this.pages.get(i);
            if (page.skipRegistry) continue;
            page.onPageAdded(this, i);
        }
        return this;
    }

    public String getWebLink() {
        return null;
    }

    public void addPage(LexiconPage page) {
        this.pages.add(page);
    }

    public final String getNameForSorting() {
        return (this.priority ? 0 : 1) + StatCollector.func_74838_a((String)this.getUnlocalizedName());
    }

    public List<ItemStack> getDisplayedRecipes() {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        for (LexiconPage page : this.pages) {
            List<ItemStack> l = page.getDisplayedRecipes();
            if (l == null) continue;
            ArrayList<ItemStack> itemsAddedThisPage = new ArrayList<ItemStack>();
            block1: for (ItemStack s : l) {
                for (ItemStack s1 : itemsAddedThisPage) {
                    if (s1.func_77973_b() != s.func_77973_b()) continue;
                    continue block1;
                }
                for (ItemStack s1 : list) {
                    if (!s1.func_77969_a(s) || !ItemStack.func_77970_a((ItemStack)s1, (ItemStack)s)) continue;
                    continue block1;
                }
                itemsAddedThisPage.add(s);
                list.add(s);
            }
        }
        list.addAll(this.extraDisplayedRecipes);
        return list;
    }

    public void addExtraDisplayedRecipe(ItemStack stack) {
        this.extraDisplayedRecipes.add(stack);
    }

    @Override
    public int compareTo(LexiconEntry o) {
        return this.getNameForSorting().compareTo(o.getNameForSorting());
    }
}

