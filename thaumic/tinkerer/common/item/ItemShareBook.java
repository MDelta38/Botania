/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.lib.research.ResearchManager
 */
package thaumic.tinkerer.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.research.ResearchManager;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.core.helper.ItemNBTHelper;
import thaumic.tinkerer.common.registry.ItemBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererCraftingBenchRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class ItemShareBook
extends ItemBase {
    private static final String TAG_PLAYER = "player";
    private static final String NON_ASIGNED = "[none]";

    public ItemShareBook() {
        this.func_77625_d(1);
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        TTResearchItem research = (TTResearchItem)new TTResearchItem("SHARE_TOME", new AspectList(), 0, -1, 0, new ItemStack((Item)this), new ResearchPage[0]).setStub().setAutoUnlock().setRound();
        if (ConfigHandler.enableSurvivalShareTome) {
            research.setPages(new ResearchPage("0"), ResearchHelper.recipePage("SHARE_TOME"));
        } else {
            research.setPages(new ResearchPage("0"));
        }
        return research;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        if (ConfigHandler.enableSurvivalShareTome) {
            return new ThaumicTinkererCraftingBenchRecipe("SHARE_TOME", new ItemStack((Item)this), " S ", "PTP", " P ", Character.valueOf('S'), new ItemStack(ConfigItems.itemInkwell), Character.valueOf('T'), new ItemStack(ConfigItems.itemThaumonomicon), Character.valueOf('P'), new ItemStack(Items.field_151121_aF));
        }
        return null;
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     */
    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        void var5_7;
        String name = this.getPlayerName(par1ItemStack);
        if (name.endsWith(NON_ASIGNED)) {
            this.setPlayerName(par1ItemStack, par3EntityPlayer.func_146103_bH().getName());
            this.setPlayerResearch(par1ItemStack, par3EntityPlayer.func_146103_bH().getName());
            if (par2World.field_72995_K) return par1ItemStack;
            par3EntityPlayer.func_145747_a((IChatComponent)new ChatComponentTranslation("ttmisc.shareTome.write", new Object[0]));
            return par1ItemStack;
        }
        ArrayList arrayList = ResearchManager.getResearchForPlayer((String)name);
        if (arrayList == null) {
            if (!par2World.field_72995_K) {
                par3EntityPlayer.func_145747_a((IChatComponent)new ChatComponentTranslation("ttmisc.shareTome.sync", new Object[0]));
                return par1ItemStack;
            }
            List<String> list = this.getPlayerResearch(par1ItemStack);
        }
        Iterator i$ = var5_7.iterator();
        while (true) {
            if (!i$.hasNext()) {
                if (par2World.field_72995_K) return par1ItemStack;
                par3EntityPlayer.func_145747_a((IChatComponent)new ChatComponentTranslation("ttmisc.shareTome.sync", new Object[0]));
                return par1ItemStack;
            }
            String key = (String)i$.next();
            ThaumicTinkerer.tcProxy.getResearchManager().completeResearch(par3EntityPlayer, key);
        }
    }

    private List<String> getPlayerResearch(ItemStack par1ItemStack) {
        ArrayList<String> retVals = new ArrayList<String>();
        NBTTagCompound cmp = ItemNBTHelper.getNBT(par1ItemStack);
        if (!cmp.func_74764_b("research")) {
            return retVals;
        }
        NBTTagList list = cmp.func_150295_c("research", 8);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            retVals.add(list.func_150307_f(i));
        }
        return retVals;
    }

    @SideOnly(value=Side.CLIENT)
    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.epic;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        String name = this.getPlayerName(par1ItemStack);
        par3List.add(name.equals(NON_ASIGNED) ? StatCollector.func_74838_a((String)"ttmisc.shareTome.noAssign") : String.format(StatCollector.func_74838_a((String)"ttmisc.shareTome.playerName"), name));
    }

    public boolean func_77651_p() {
        return true;
    }

    private String getPlayerName(ItemStack stack) {
        return ItemNBTHelper.getString(stack, TAG_PLAYER, NON_ASIGNED);
    }

    private void setPlayerName(ItemStack stack, String playerName) {
        ItemNBTHelper.setString(stack, TAG_PLAYER, playerName);
    }

    private void setPlayerResearch(ItemStack stack, String playername) {
        ArrayList researchesDone = ResearchManager.getResearchForPlayer((String)playername);
        NBTTagCompound cmp = ItemNBTHelper.getNBT(stack);
        NBTTagList list = new NBTTagList();
        for (String tag : researchesDone) {
            list.func_74742_a((NBTBase)new NBTTagString(tag));
        }
        cmp.func_74782_a("research", (NBTBase)list);
    }

    @Override
    public String getItemName() {
        return "shareBook";
    }
}

