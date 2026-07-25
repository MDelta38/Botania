/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchCategories
 *  thaumcraft.api.research.ResearchCategoryList
 *  thaumcraft.api.research.ResearchItem
 *  thaumcraft.api.research.ResearchPage
 */
package flaxbeard.thaumicexploration.research;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class FauxResearchItem
extends ResearchItem {
    public ResearchItem original;

    public FauxResearchItem(String name, String cat, String origin, String originCategory, int x, int y, ResourceLocation icon) {
        super(name, cat, new AspectList(), x, y, 1, icon);
        this.original = (ResearchItem)((ResearchCategoryList)ResearchCategories.researchCategories.get((Object)originCategory)).research.get(origin);
        this.bindToOriginal();
        this.setStub();
        this.setHidden();
    }

    public FauxResearchItem(String name, String cat, String origin, String originCategory, int x, int y, ItemStack icon) {
        super(name, cat, new AspectList(), x, y, 1, icon);
        this.original = (ResearchItem)((ResearchCategoryList)ResearchCategories.researchCategories.get((Object)originCategory)).research.get(origin);
        this.bindToOriginal();
        this.setStub();
        this.setHidden();
    }

    private void bindToOriginal() {
        if (this.original.siblings == null) {
            this.original.setSiblings(new String[]{this.key});
        } else {
            String[] family = this.original.siblings;
            String[] newFamily = new String[family.length + 1];
            for (int x = 0; x < family.length; ++x) {
                newFamily[x] = family[x];
            }
            newFamily[family.length] = this.key;
            this.original.setSiblings(newFamily);
        }
        if (this.original.isSecondary()) {
            this.setSecondary();
        }
    }

    public ResearchPage[] getPages() {
        return this.original.getPages();
    }

    @SideOnly(value=Side.CLIENT)
    public String getName() {
        return this.original.getName();
    }

    @SideOnly(value=Side.CLIENT)
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
}

