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
 */
package thaumcraft.common.items.wands;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;

public class ItemWandCap
extends Item {
    public IIcon[] icon = new IIcon[9];

    public ItemWandCap() {
        this.func_77625_d(64);
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:wand_cap_iron");
        this.icon[1] = ir.func_94245_a("thaumcraft:wand_cap_gold");
        this.icon[2] = ir.func_94245_a("thaumcraft:wand_cap_thaumium");
        this.icon[3] = ir.func_94245_a("thaumcraft:wand_cap_copper");
        this.icon[4] = ir.func_94245_a("thaumcraft:wand_cap_silver");
        this.icon[5] = ir.func_94245_a("thaumcraft:wand_cap_silver_inert");
        this.icon[6] = ir.func_94245_a("thaumcraft:wand_cap_thaumium_inert");
        this.icon[7] = ir.func_94245_a("thaumcraft:wand_cap_void");
        this.icon[8] = ir.func_94245_a("thaumcraft:wand_cap_void_inert");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int meta) {
        return this.icon[meta];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack((Item)this, 1, 0));
        par3List.add(new ItemStack((Item)this, 1, 1));
        if (Config.foundCopperIngot) {
            par3List.add(new ItemStack((Item)this, 1, 3));
        }
        if (Config.foundSilverIngot) {
            par3List.add(new ItemStack((Item)this, 1, 4));
            par3List.add(new ItemStack((Item)this, 1, 5));
        }
        par3List.add(new ItemStack((Item)this, 1, 2));
        par3List.add(new ItemStack((Item)this, 1, 6));
        par3List.add(new ItemStack((Item)this, 1, 7));
        par3List.add(new ItemStack((Item)this, 1, 8));
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
    }
}

