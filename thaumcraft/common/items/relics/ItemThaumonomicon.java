/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package thaumcraft.common.items.relics;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collection;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketSyncAspects;
import thaumcraft.common.lib.network.playerdata.PacketSyncResearch;
import thaumcraft.common.lib.research.ResearchManager;

public class ItemThaumonomicon
extends Item {
    @SideOnly(value=Side.CLIENT)
    public IIcon icon;
    @SideOnly(value=Side.CLIENT)
    public IIcon iconCheat;

    public ItemThaumonomicon() {
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77625_d(1);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:thaumonomicon");
        this.iconCheat = ir.func_94245_a("thaumcraft:thaumonomiconcheat");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return par1 != 42 ? this.icon : this.iconCheat;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack((Item)this, 1, 0));
        if (Config.allowCheatSheet) {
            par3List.add(new ItemStack((Item)this, 1, 42));
        }
    }

    public ItemStack func_77659_a(ItemStack stack, World par2World, EntityPlayer player) {
        if (!par2World.field_72995_K) {
            if (Config.allowCheatSheet && stack.func_77960_j() == 42) {
                Collection<ResearchCategoryList> rc = ResearchCategories.researchCategories.values();
                for (ResearchCategoryList cat : rc) {
                    Collection<ResearchItem> rl = cat.research.values();
                    for (ResearchItem ri : rl) {
                        if (ResearchManager.isResearchComplete(player.func_70005_c_(), ri.key)) continue;
                        Thaumcraft.proxy.getResearchManager().completeResearch(player, ri.key);
                    }
                }
                for (Aspect aspect : Aspect.aspects.values()) {
                    if (Thaumcraft.proxy.getPlayerKnowledge().hasDiscoveredAspect(player.func_70005_c_(), aspect)) continue;
                    Thaumcraft.proxy.researchManager.completeAspect(player, aspect, (short)50);
                }
            } else {
                Collection<ResearchCategoryList> rc = ResearchCategories.researchCategories.values();
                for (ResearchCategoryList cat : rc) {
                    Collection<ResearchItem> rl = cat.research.values();
                    for (ResearchItem ri : rl) {
                        if (!ResearchManager.isResearchComplete(player.func_70005_c_(), ri.key) || ri.siblings == null) continue;
                        for (String sib : ri.siblings) {
                            if (ResearchManager.isResearchComplete(player.func_70005_c_(), sib)) continue;
                            Thaumcraft.proxy.getResearchManager().completeResearch(player, sib);
                        }
                    }
                }
            }
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncResearch(player), (EntityPlayerMP)player);
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncAspects(player), (EntityPlayerMP)player);
        } else {
            par2World.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "thaumcraft:page", 1.0f, 1.0f, false);
        }
        player.openGui((Object)Thaumcraft.instance, 12, par2World, 0, 0, 0);
        return stack;
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return itemstack.func_77960_j() != 42 ? EnumRarity.uncommon : EnumRarity.epic;
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (par1ItemStack.func_77960_j() == 42) {
            par3List.add("Cheat Sheet");
        }
        super.func_77624_a(par1ItemStack, par2EntityPlayer, par3List, par4);
    }
}

