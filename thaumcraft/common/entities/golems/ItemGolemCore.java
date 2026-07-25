/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 */
package thaumcraft.common.entities.golems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import thaumcraft.common.Thaumcraft;

public class ItemGolemCore
extends Item {
    public IIcon[] icon = new IIcon[12];
    public IIcon blankIcon;

    public ItemGolemCore() {
        this.func_77625_d(64);
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:golem_core_fill");
        this.icon[1] = ir.func_94245_a("thaumcraft:golem_core_empty");
        this.icon[2] = ir.func_94245_a("thaumcraft:golem_core_gather");
        this.icon[3] = ir.func_94245_a("thaumcraft:golem_core_harvest");
        this.icon[4] = ir.func_94245_a("thaumcraft:golem_core_guard");
        this.icon[5] = ir.func_94245_a("thaumcraft:golem_core_liquid");
        this.icon[6] = ir.func_94245_a("thaumcraft:golem_core_essentia");
        this.icon[7] = ir.func_94245_a("thaumcraft:golem_core_lumber");
        this.icon[8] = ir.func_94245_a("thaumcraft:golem_core_use");
        this.icon[9] = ir.func_94245_a("thaumcraft:golem_core_butcher");
        this.icon[10] = ir.func_94245_a("thaumcraft:golem_core_sorting");
        this.icon[11] = ir.func_94245_a("thaumcraft:golem_core_fish");
        this.blankIcon = ir.func_94245_a("thaumcraft:golem_core_blank");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int d) {
        return d == 100 ? this.blankIcon : this.icon[d];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack((Item)this, 1, 100));
        for (int a = 0; a <= 11; ++a) {
            par3List.add(new ItemStack((Item)this, 1, a));
        }
    }

    public void func_77624_a(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        list.add(StatCollector.func_74838_a((String)("item.ItemGolemCore." + stack.func_77960_j() + ".name")));
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return itemstack.func_77960_j() == 100 ? EnumRarity.common : EnumRarity.uncommon;
    }

    public static boolean hasGUI(int core) {
        switch (core) {
            case 0: 
            case 1: 
            case 2: 
            case 4: 
            case 5: 
            case 8: 
            case 10: {
                return true;
            }
        }
        return false;
    }

    public static boolean canSort(int core) {
        switch (core) {
            case 0: 
            case 1: 
            case 2: 
            case 8: 
            case 10: {
                return true;
            }
        }
        return false;
    }

    public static boolean hasInventory(int core) {
        switch (core) {
            case 0: 
            case 1: 
            case 2: 
            case 5: 
            case 8: {
                return true;
            }
        }
        return false;
    }
}

