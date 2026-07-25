/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchItem
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.api.research.ResearchPage$PageType
 */
package flaxbeard.thaumicexploration.research;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.ThaumicExploration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class TXResearchItem
extends ResearchItem {
    public TXResearchItem(String par1, String par2) {
        super(par1, par2);
    }

    public TXResearchItem(String par1, String par2, AspectList tags, int par3, int par4, int par5, ItemStack icon) {
        super(par1, par2, tags, par3, par4, par5, icon);
    }

    public TXResearchItem(String par1, String par2, AspectList tags, int par3, int par4, int par5, ResourceLocation icon) {
        super(par1, par2, tags, par3, par4, par5, icon);
    }

    @SideOnly(value=Side.CLIENT)
    public String getName() {
        return StatCollector.func_74838_a((String)("te.name." + this.key));
    }

    @SideOnly(value=Side.CLIENT)
    public String getText() {
        if (ThaumicExploration.prefix) {
            return StatCollector.func_74838_a((String)"te.researchPrefix") + " " + StatCollector.func_74838_a((String)("te.tag." + this.key));
        }
        return StatCollector.func_74838_a((String)("te.tag." + this.key));
    }

    public ResearchItem setPages(ResearchPage ... par) {
        for (ResearchPage page : par) {
            if (page.type == ResearchPage.PageType.TEXT) {
                System.out.println("TEXT IS: " + page.text.split("#")[0].equals("NE") + "'" + page.text.split("#")[0] + "'");
                page.text = "te.text." + this.key + "." + page.text;
            }
            if (page.type != ResearchPage.PageType.INFUSION_CRAFTING) continue;
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
}

