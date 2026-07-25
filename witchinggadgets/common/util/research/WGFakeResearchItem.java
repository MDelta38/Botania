/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchCategories
 *  thaumcraft.api.research.ResearchCategoryList
 *  thaumcraft.api.research.ResearchItem
 *  thaumcraft.api.research.ResearchPage
 */
package witchinggadgets.common.util.research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class WGFakeResearchItem
extends ResearchItem {
    public ResearchItem original;

    public WGFakeResearchItem(String key, String category, String original, String originalCategory, int displayX, int displayY, ResourceLocation icon) {
        super(key, category, new AspectList(), displayX, displayY, 1, icon);
        this.original = (ResearchItem)((ResearchCategoryList)ResearchCategories.researchCategories.get((Object)originalCategory)).research.get(original);
        this.setupOriginal();
    }

    public WGFakeResearchItem(String key, String category, String original, String originalCategory, int displayX, int displayY, ItemStack icon) {
        super(key, category, new AspectList(), displayX, displayY, 1, icon);
        this.original = (ResearchItem)((ResearchCategoryList)ResearchCategories.researchCategories.get((Object)originalCategory)).research.get(original);
        this.setupOriginal();
    }

    private void setupOriginal() {
        if (this.original.siblings == null) {
            this.original.setSiblings(new String[]{this.key});
        } else {
            String[] newSiblings = new String[this.original.siblings.length + 1];
            System.arraycopy(this.original.siblings, 0, newSiblings, 0, this.original.siblings.length);
            newSiblings[this.original.siblings.length] = this.key;
            this.original.setSiblings(newSiblings);
        }
    }

    public String getName() {
        return this.original.getName();
    }

    public String getText() {
        return this.original.getText();
    }

    public boolean isStub() {
        return true;
    }

    public boolean isHidden() {
        return true;
    }

    public int getComplexity() {
        return 1;
    }

    public ResearchPage[] getPages() {
        return this.original.getPages();
    }
}

