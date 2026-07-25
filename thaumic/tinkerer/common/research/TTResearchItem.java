/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchItem
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.api.research.ResearchPage$PageType
 */
package thaumic.tinkerer.common.research;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class TTResearchItem
extends ResearchItem
implements IRegisterableResearch {
    public int warp = 0;

    public TTResearchItem(String par1) {
        super(par1, "TT_CATEGORY");
    }

    public TTResearchItem(String par1, AspectList tags, int par3, int par4, int par5, ItemStack icon, ResearchPage ... pages) {
        super(par1, "TT_CATEGORY", tags, par3, par4, par5, icon);
        this.setPages(pages);
    }

    public TTResearchItem(String par1, AspectList tags, int par3, int par4, int par5, ResourceLocation icon, ResearchPage ... pages) {
        super(par1, "TT_CATEGORY", tags, par3, par4, par5, icon);
        this.setPages(pages);
    }

    public TTResearchItem setWarp(int warp) {
        this.warp = warp;
        return this;
    }

    @SideOnly(value=Side.CLIENT)
    public String getName() {
        return StatCollector.func_74838_a((String)("ttresearch.name." + this.key));
    }

    @SideOnly(value=Side.CLIENT)
    public String getText() {
        return (ConfigHandler.useTootlipIndicators ? StatCollector.func_74838_a((String)this.getPrefix()) : "") + StatCollector.func_74838_a((String)("ttresearch.lore." + this.key));
    }

    String getPrefix() {
        return "ttresearch.prefix";
    }

    public ResearchItem setPages(ResearchPage ... par) {
        for (ResearchPage page : par) {
            if (page.type == ResearchPage.PageType.TEXT) {
                page.text = "ttresearch.page." + this.key + "." + page.text;
            }
            if (!this.checkInfusion() || page.type != ResearchPage.PageType.INFUSION_CRAFTING) continue;
            if (this.parentsHidden == null || this.parentsHidden.length == 0) {
                this.parentsHidden = new String[]{"INFUSION"};
                continue;
            }
            String[] newParents = new String[this.parentsHidden.length + 1];
            newParents[0] = "INFUSION";
            for (int i = 0; i < this.parentsHidden.length; ++i) {
                newParents[i + 1] = this.parentsHidden[i];
            }
            this.parentsHidden = newParents;
        }
        return super.setPages(par);
    }

    boolean checkInfusion() {
        return true;
    }

    @Override
    public void registerResearch() {
        this.registerResearchItem();
        if (this.warp != 0) {
            ThaumcraftApi.addWarpToResearch((String)this.key, (int)this.warp);
        }
    }
}

