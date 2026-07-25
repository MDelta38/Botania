/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import thaumcraft.common.Thaumcraft;

public class ItemGolemDecoration
extends Item {
    public IIcon[] icon = new IIcon[8];

    public ItemGolemDecoration() {
        this.func_77625_d(64);
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:golemdecotophat");
        this.icon[1] = ir.func_94245_a("thaumcraft:golemdecoglasses");
        this.icon[2] = ir.func_94245_a("thaumcraft:golemdecobowtie");
        this.icon[3] = ir.func_94245_a("thaumcraft:golemdecofez");
        this.icon[4] = ir.func_94245_a("thaumcraft:golemdecodart");
        this.icon[5] = ir.func_94245_a("thaumcraft:golemdecovisor");
        this.icon[6] = ir.func_94245_a("thaumcraft:golemdecoarmor");
        this.icon[7] = ir.func_94245_a("thaumcraft:golemdecomace");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int d) {
        return this.icon[d];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int a = 0; a <= 7; ++a) {
            par3List.add(new ItemStack((Item)this, 1, a));
        }
    }

    public static String getDecoChar(int md) {
        switch (md) {
            case 0: {
                return "H";
            }
            case 1: {
                return "G";
            }
            case 2: {
                return "B";
            }
            case 3: {
                return "F";
            }
            case 4: {
                return "R";
            }
            case 5: {
                return "V";
            }
            case 6: {
                return "P";
            }
            case 7: {
                return "M";
            }
        }
        return "";
    }

    public String func_77653_i(ItemStack stack) {
        return StatCollector.func_74838_a((String)"item.ItemGolemDecoration.name") + ": " + super.func_77653_i(stack);
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
    }
}

