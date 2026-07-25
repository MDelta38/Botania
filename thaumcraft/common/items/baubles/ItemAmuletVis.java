/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.api.IBauble
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagInt
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 */
package thaumcraft.common.items.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.text.DecimalFormat;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.tiles.TileVisRelay;

public class ItemAmuletVis
extends Item
implements IBauble,
IRunicArmor {
    public IIcon[] icon = new IIcon[2];
    DecimalFormat myFormatter = new DecimalFormat("#######.##");

    public ItemAmuletVis() {
        this.field_77777_bU = 1;
        this.canRepair = false;
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
        this.func_77627_a(true);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:vis_amulet_lesser");
        this.icon[1] = ir.func_94245_a("thaumcraft:vis_amulet");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return par1 >= this.icon.length ? this.icon[0] : this.icon[par1];
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return itemstack.func_77960_j() == 0 ? EnumRarity.uncommon : EnumRarity.rare;
    }

    public String func_77667_c(ItemStack stack) {
        return super.func_77658_a() + "." + stack.func_77960_j();
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack((Item)this, 1, 0));
        par3List.add(new ItemStack((Item)this, 1, 1));
    }

    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.AMULET;
    }

    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (!player.field_70170_p.field_72995_K && player.field_70173_aa % 5 == 0) {
            if (player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() instanceof ItemWandCasting) {
                ItemWandCasting wand = (ItemWandCasting)player.func_70694_bm().func_77973_b();
                AspectList al = wand.getAspectsWithRoom(player.func_70694_bm());
                for (Aspect aspect : al.getAspects()) {
                    if (aspect == null || this.getVis(itemstack, aspect) <= 0) continue;
                    int amt = Math.min(5, wand.getMaxVis(player.func_70694_bm()) - wand.getVis(player.func_70694_bm(), aspect));
                    amt = Math.min(amt, this.getVis(itemstack, aspect));
                    this.storeVis(itemstack, aspect, this.getVis(itemstack, aspect) - amt);
                    wand.storeVis(player.func_70694_bm(), aspect, this.getVis(player.func_70694_bm(), aspect) + amt);
                }
            }
            if (TileVisRelay.nearbyPlayers.containsKey(player.func_145782_y())) {
                if (TileVisRelay.nearbyPlayers.get(player.func_145782_y()).get() != null && ((TileVisRelay)TileVisRelay.nearbyPlayers.get(player.func_145782_y()).get()).func_145835_a(player.field_70165_t, player.field_70163_u, player.field_70161_v) < 26.0) {
                    AspectList al = this.getAspectsWithRoom(itemstack);
                    for (Aspect aspect : al.getAspects()) {
                        int amt;
                        if (aspect == null || (amt = ((TileVisRelay)TileVisRelay.nearbyPlayers.get(player.func_145782_y()).get()).consumeVis(aspect, Math.min(5, this.getMaxVis(itemstack) - this.getVis(itemstack, aspect)))) <= 0) continue;
                        this.addRealVis(itemstack, aspect, amt, true);
                        ((TileVisRelay)TileVisRelay.nearbyPlayers.get(player.func_145782_y()).get()).triggerConsumeEffect(aspect);
                    }
                } else {
                    TileVisRelay.nearbyPlayers.remove(player.func_145782_y());
                }
            }
        }
    }

    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
    }

    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (stack.func_77960_j() == 0) {
            list.add(EnumChatFormatting.AQUA + StatCollector.func_74838_a((String)"item.ItemAmuletVis.text"));
        }
        list.add(EnumChatFormatting.GOLD + StatCollector.func_74838_a((String)"item.capacity.text") + " " + this.getMaxVis(stack) / 100);
        if (stack.func_77942_o()) {
            for (Aspect aspect : Aspect.getPrimalAspects()) {
                if (!stack.field_77990_d.func_74764_b(aspect.getTag())) continue;
                String amount = this.myFormatter.format((float)stack.field_77990_d.func_74762_e(aspect.getTag()) / 100.0f);
                list.add(" \u00a7" + aspect.getChatcolor() + aspect.getName() + "\u00a7r x " + amount);
            }
        }
    }

    public int getMaxVis(ItemStack stack) {
        return stack.func_77960_j() == 1 ? 25000 : 2500;
    }

    public int getVis(ItemStack is, Aspect aspect) {
        int out = 0;
        if (is.func_77942_o() && is.field_77990_d.func_74764_b(aspect.getTag())) {
            out = is.field_77990_d.func_74762_e(aspect.getTag());
        }
        return out;
    }

    public void storeVis(ItemStack is, Aspect aspect, int amount) {
        is.func_77983_a(aspect.getTag(), (NBTBase)new NBTTagInt(amount));
    }

    public AspectList getAspectsWithRoom(ItemStack wandstack) {
        AspectList out = new AspectList();
        AspectList cur = this.getAllVis(wandstack);
        for (Aspect aspect : cur.getAspects()) {
            if (cur.getAmount(aspect) >= this.getMaxVis(wandstack)) continue;
            out.add(aspect, 1);
        }
        return out;
    }

    public AspectList getAllVis(ItemStack is) {
        AspectList out = new AspectList();
        for (Aspect aspect : Aspect.getPrimalAspects()) {
            if (is.func_77942_o() && is.field_77990_d.func_74764_b(aspect.getTag())) {
                out.merge(aspect, is.field_77990_d.func_74762_e(aspect.getTag()));
                continue;
            }
            out.merge(aspect, 0);
        }
        return out;
    }

    public boolean consumeAllVis(ItemStack is, EntityPlayer player, AspectList aspects, boolean doit, boolean crafting) {
        if (aspects == null || aspects.size() == 0) {
            return false;
        }
        for (Aspect aspect : aspects.getAspects()) {
            if (this.getVis(is, aspect) >= aspects.getAmount(aspect)) continue;
            return false;
        }
        if (doit) {
            for (Aspect aspect : aspects.getAspects()) {
                this.storeVis(is, aspect, this.getVis(is, aspect) - aspects.getAmount(aspect));
            }
        }
        return true;
    }

    public int addVis(ItemStack is, Aspect aspect, int amount, boolean doit) {
        if (!aspect.isPrimal()) {
            return 0;
        }
        int storeAmount = this.getVis(is, aspect) + amount * 100;
        int leftover = Math.max(storeAmount - this.getMaxVis(is), 0);
        if (doit) {
            this.storeVis(is, aspect, Math.min(storeAmount, this.getMaxVis(is)));
        }
        return leftover / 100;
    }

    public int addRealVis(ItemStack is, Aspect aspect, int amount, boolean doit) {
        if (!aspect.isPrimal()) {
            return 0;
        }
        int storeAmount = this.getVis(is, aspect) + amount;
        int leftover = Math.max(storeAmount - this.getMaxVis(is), 0);
        if (doit) {
            this.storeVis(is, aspect, Math.min(storeAmount, this.getMaxVis(is)));
        }
        return leftover;
    }

    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return itemstack.func_77960_j() != 1 ? true : ResearchManager.isResearchComplete(player.func_70005_c_(), "VISAMULET");
    }

    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public int getRunicCharge(ItemStack itemstack) {
        return 0;
    }
}

