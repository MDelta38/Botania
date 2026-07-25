/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package thaumcraft.common.items;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.research.ResearchNoteData;

public class ItemResearchNotes
extends Item {
    @SideOnly(value=Side.CLIENT)
    public IIcon iconNote;
    @SideOnly(value=Side.CLIENT)
    public IIcon iconNoteOver;
    @SideOnly(value=Side.CLIENT)
    public IIcon iconDiscovery;
    @SideOnly(value=Side.CLIENT)
    public IIcon iconDiscoveryOver;

    public ItemResearchNotes() {
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77625_d(1);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.iconNote = ir.func_94245_a("thaumcraft:researchnotes");
        this.iconNoteOver = ir.func_94245_a("thaumcraft:researchnotesoverlay");
        this.iconDiscovery = ir.func_94245_a("thaumcraft:discovery");
        this.iconDiscoveryOver = ir.func_94245_a("thaumcraft:discoveryoverlay");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return par1 / 64 == 0 ? this.iconNote : this.iconDiscovery;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77618_c(int par1, int renderPass) {
        return renderPass == 0 ? (par1 / 64 == 0 ? this.iconNote : this.iconDiscovery) : (par1 / 64 == 0 ? this.iconNoteOver : this.iconDiscoveryOver);
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (!world.field_72995_K) {
            if (ResearchManager.getData(stack) != null && ResearchManager.getData(stack).isComplete() && !ResearchManager.isResearchComplete(player.func_70005_c_(), ResearchManager.getData((ItemStack)stack).key)) {
                if (ResearchManager.doesPlayerHaveRequisites(player.func_70005_c_(), ResearchManager.getData((ItemStack)stack).key)) {
                    PacketHandler.INSTANCE.sendTo((IMessage)new PacketResearchComplete(ResearchManager.getData((ItemStack)stack).key), (EntityPlayerMP)player);
                    Thaumcraft.proxy.getResearchManager().completeResearch(player, ResearchManager.getData((ItemStack)stack).key);
                    if (ResearchCategories.getResearch((String)ResearchManager.getData((ItemStack)stack).key).siblings != null) {
                        for (String sibling : ResearchCategories.getResearch((String)ResearchManager.getData((ItemStack)stack).key).siblings) {
                            if (ResearchManager.isResearchComplete(player.func_70005_c_(), sibling) || !ResearchManager.doesPlayerHaveRequisites(player.func_70005_c_(), sibling)) continue;
                            PacketHandler.INSTANCE.sendTo((IMessage)new PacketResearchComplete(sibling), (EntityPlayerMP)player);
                            Thaumcraft.proxy.getResearchManager().completeResearch(player, sibling);
                        }
                    }
                    --stack.field_77994_a;
                    world.func_72956_a((Entity)player, "thaumcraft:learn", 0.75f, 1.0f);
                } else {
                    player.func_145747_a((IChatComponent)new ChatComponentTranslation(StatCollector.func_74838_a((String)"tc.researcherror"), new Object[0]));
                }
            } else if (stack.func_77960_j() == 42 || stack.func_77960_j() == 24) {
                String key = ResearchManager.findHiddenResearch(player);
                if (key.equals("FAIL")) {
                    --stack.field_77994_a;
                    EntityItem entityItem = new EntityItem(world, player.field_70165_t, player.field_70163_u + (double)(player.func_70047_e() / 2.0f), player.field_70161_v, new ItemStack(ConfigItems.itemResource, 7 + world.field_73012_v.nextInt(3), 9));
                    world.func_72838_d((Entity)entityItem);
                    world.func_72956_a((Entity)player, "thaumcraft:erase", 0.75f, 1.0f);
                } else {
                    stack.func_77964_b(0);
                    stack.field_77990_d = ResearchManager.createNote((ItemStack)stack, (String)key, (World)player.field_70170_p).field_77990_d;
                    world.func_72956_a((Entity)player, "thaumcraft:write", 0.75f, 1.0f);
                }
            }
        }
        return stack;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack stack, int par2) {
        if (par2 == 1) {
            int c = 0x999999;
            ResearchNoteData rd = ResearchManager.getData(stack);
            if (rd != null) {
                c = rd.color;
            }
            return c;
        }
        return super.func_82790_a(stack, par2);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_77623_v() {
        return true;
    }

    public boolean func_77651_p() {
        return true;
    }

    public String func_77653_i(ItemStack itemstack) {
        String name = itemstack.func_77960_j() < 64 ? StatCollector.func_74838_a((String)"item.researchnotes.name") : StatCollector.func_74838_a((String)"item.discovery.name");
        return name;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        ResearchNoteData rd;
        if (stack.func_77960_j() == 24 || stack.func_77960_j() == 42) {
            list.add(EnumChatFormatting.GOLD + StatCollector.func_74838_a((String)"item.researchnotes.unknown.1"));
            list.add(EnumChatFormatting.BLUE + StatCollector.func_74838_a((String)"item.researchnotes.unknown.2"));
        }
        if ((rd = ResearchManager.getData(stack)) != null && rd.key != null && ResearchCategories.getResearch(rd.key) != null) {
            list.add("\u00a76" + ResearchCategories.getResearch(rd.key).getName());
            list.add("\u00a7o" + ResearchCategories.getResearch(rd.key).getText());
            int warp = ThaumcraftApi.getWarp(rd.key);
            if (warp > 0) {
                if (warp > 5) {
                    warp = 5;
                }
                String ws = StatCollector.func_74838_a((String)"tc.forbidden");
                String wr = StatCollector.func_74838_a((String)("tc.forbidden.level." + warp));
                String wte = ws.replaceAll("%n", wr);
                list.add(EnumChatFormatting.DARK_PURPLE + wte);
            }
        }
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return itemstack.func_77960_j() < 64 ? EnumRarity.rare : EnumRarity.epic;
    }
}

