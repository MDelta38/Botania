/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package thaumcraft.api.wands;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;

public abstract class ItemFocusBasic
extends Item {
    public IIcon icon;

    public ItemFocusBasic() {
        this.field_77777_bU = 1;
        this.canRepair = false;
        this.func_77656_e(0);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public boolean func_77645_m() {
        return false;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        AspectList al = this.getVisCost(stack);
        if (al != null && al.size() > 0) {
            list.add(StatCollector.func_74838_a((String)(this.isVisCostPerTick(stack) ? "item.Focus.cost2" : "item.Focus.cost1")));
            for (Aspect aspect : al.getAspectsSorted()) {
                DecimalFormat myFormatter = new DecimalFormat("#####.##");
                String amount = myFormatter.format((float)al.getAmount(aspect) / 100.0f);
                list.add(" \u00a7" + aspect.getChatcolor() + aspect.getName() + "\u00a7r x " + amount);
            }
        }
        this.addFocusInformation(stack, player, list, par4);
    }

    public void addFocusInformation(ItemStack focusstack, EntityPlayer player, List list, boolean par4) {
        LinkedHashMap<Short, Integer> map = new LinkedHashMap<Short, Integer>();
        for (short id : this.getAppliedUpgrades(focusstack)) {
            if (id < 0) continue;
            int amt = 1;
            if (map.containsKey(id)) {
                amt = (Integer)map.get(id) + 1;
            }
            map.put(id, amt);
        }
        Object object = map.keySet().iterator();
        while (object.hasNext()) {
            Short id = (Short)object.next();
            list.add(EnumChatFormatting.DARK_PURPLE + FocusUpgradeType.types[id].getLocalizedName() + ((Integer)map.get(id) > 1 ? " " + StatCollector.func_74838_a((String)("enchantment.level." + map.get(id))) : ""));
        }
    }

    public boolean isVisCostPerTick(ItemStack focusstack) {
        return false;
    }

    public EnumRarity func_77613_e(ItemStack focusstack) {
        return EnumRarity.rare;
    }

    public abstract int getFocusColor(ItemStack var1);

    public IIcon getOrnament(ItemStack focusstack) {
        return null;
    }

    public IIcon getFocusDepthLayerIcon(ItemStack focusstack) {
        return null;
    }

    public WandFocusAnimation getAnimation(ItemStack focusstack) {
        return WandFocusAnimation.WAVE;
    }

    public String getSortingHelper(ItemStack focusstack) {
        String out = "";
        for (short id : this.getAppliedUpgrades(focusstack)) {
            out = out + id;
        }
        return out;
    }

    public abstract AspectList getVisCost(ItemStack var1);

    public int getActivationCooldown(ItemStack focusstack) {
        return 0;
    }

    public int getMaxAreaSize(ItemStack focusstack) {
        return 1;
    }

    public abstract FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack var1, int var2);

    public short[] getAppliedUpgrades(ItemStack focusstack) {
        short[] l = new short[]{-1, -1, -1, -1, -1};
        NBTTagList nbttaglist = this.getFocusUpgradeTagList(focusstack);
        if (nbttaglist == null) {
            return l;
        }
        for (int j = 0; j < nbttaglist.func_74745_c() && j < 5; ++j) {
            l[j] = nbttaglist.func_150305_b(j).func_74765_d("id");
        }
        return l;
    }

    public boolean applyUpgrade(ItemStack focusstack, FocusUpgradeType type, int rank) {
        short[] upgrades = this.getAppliedUpgrades(focusstack);
        if (upgrades[rank - 1] != -1 || rank < 1 || rank > 5) {
            return false;
        }
        upgrades[rank - 1] = type.id;
        this.setFocusUpgradeTagList(focusstack, upgrades);
        return true;
    }

    public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank) {
        return true;
    }

    public boolean isUpgradedWith(ItemStack focusstack, FocusUpgradeType focusUpgradetype) {
        return this.getUpgradeLevel(focusstack, focusUpgradetype) > 0;
    }

    public int getUpgradeLevel(ItemStack focusstack, FocusUpgradeType focusUpgradetype) {
        short[] list = this.getAppliedUpgrades(focusstack);
        int level = 0;
        for (short id : list) {
            if (id != focusUpgradetype.id) continue;
            ++level;
        }
        return level;
    }

    public ItemStack onFocusRightClick(ItemStack wandstack, World world, EntityPlayer player, MovingObjectPosition movingobjectposition) {
        return null;
    }

    public void onUsingFocusTick(ItemStack wandstack, EntityPlayer player, int count) {
    }

    public void onPlayerStoppedUsingFocus(ItemStack wandstack, World world, EntityPlayer player, int count) {
    }

    public boolean onFocusBlockStartBreak(ItemStack wandstack, int x, int y, int z, EntityPlayer player) {
        return false;
    }

    private NBTTagList getFocusUpgradeTagList(ItemStack focusstack) {
        return focusstack.field_77990_d == null ? null : focusstack.field_77990_d.func_150295_c("upgrade", 10);
    }

    private void setFocusUpgradeTagList(ItemStack focusstack, short[] upgrades) {
        if (!focusstack.func_77942_o()) {
            focusstack.func_77982_d(new NBTTagCompound());
        }
        NBTTagCompound nbttagcompound = focusstack.func_77978_p();
        NBTTagList tlist = new NBTTagList();
        nbttagcompound.func_74782_a("upgrade", (NBTBase)tlist);
        for (short id : upgrades) {
            NBTTagCompound f = new NBTTagCompound();
            f.func_74777_a("id", id);
            tlist.func_74742_a((NBTBase)f);
        }
    }

    public static enum WandFocusAnimation {
        WAVE,
        CHARGE;

    }
}

