/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.item.ModItems;

public class ItemTemperanceStone
extends ItemMod {
    IIcon enabledIcon;

    public ItemTemperanceStone() {
        this.func_77655_b("temperanceStone");
        this.func_77625_d(1);
        this.func_77627_a(true);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        int dmg = par1ItemStack.func_77960_j();
        par1ItemStack.func_77964_b(~dmg & 1);
        par2World.func_72956_a((Entity)par3EntityPlayer, "random.orb", 0.3f, 0.1f);
        return par1ItemStack;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, (Item)this, 0);
        this.enabledIcon = IconHelper.forItem(par1IconRegister, (Item)this, 1);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return par1 == 1 ? this.enabledIcon : this.field_77791_bV;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (par1ItemStack.func_77960_j() == 1) {
            this.addStringToTooltip(StatCollector.func_74838_a((String)"botaniamisc.active"), par3List);
        } else {
            this.addStringToTooltip(StatCollector.func_74838_a((String)"botaniamisc.inactive"), par3List);
        }
    }

    void addStringToTooltip(String s, List<String> tooltip) {
        tooltip.add(s.replaceAll("&", "\u00a7"));
    }

    public static boolean hasTemperanceActive(EntityPlayer player) {
        InventoryPlayer inv = player.field_71071_by;
        for (int i = 0; i < inv.func_70302_i_(); ++i) {
            ItemStack stack = inv.func_70301_a(i);
            if (stack == null || stack.func_77973_b() != ModItems.temperanceStone || stack.func_77960_j() != 1) continue;
            return true;
        }
        return false;
    }
}

