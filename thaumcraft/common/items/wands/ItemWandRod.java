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

public class ItemWandRod
extends Item {
    public IIcon[] iconWand = new IIcon[8];
    public IIcon[] iconStaff = new IIcon[8];
    public IIcon iconPrimalStaff;

    public ItemWandRod() {
        this.func_77625_d(64);
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.iconWand[0] = ir.func_94245_a("thaumcraft:wand_rod_greatwood");
        this.iconWand[1] = ir.func_94245_a("thaumcraft:wand_rod_obsidian");
        this.iconWand[2] = ir.func_94245_a("thaumcraft:wand_rod_silverwood");
        this.iconWand[3] = ir.func_94245_a("thaumcraft:wand_rod_ice");
        this.iconWand[4] = ir.func_94245_a("thaumcraft:wand_rod_quartz");
        this.iconWand[5] = ir.func_94245_a("thaumcraft:wand_rod_reed");
        this.iconWand[6] = ir.func_94245_a("thaumcraft:wand_rod_blaze");
        this.iconWand[7] = ir.func_94245_a("thaumcraft:wand_rod_bone");
        this.iconStaff[0] = ir.func_94245_a("thaumcraft:staff_rod_greatwood");
        this.iconStaff[1] = ir.func_94245_a("thaumcraft:staff_rod_obsidian");
        this.iconStaff[2] = ir.func_94245_a("thaumcraft:staff_rod_silverwood");
        this.iconStaff[3] = ir.func_94245_a("thaumcraft:staff_rod_ice");
        this.iconStaff[4] = ir.func_94245_a("thaumcraft:staff_rod_quartz");
        this.iconStaff[5] = ir.func_94245_a("thaumcraft:staff_rod_reed");
        this.iconStaff[6] = ir.func_94245_a("thaumcraft:staff_rod_blaze");
        this.iconStaff[7] = ir.func_94245_a("thaumcraft:staff_rod_bone");
        this.iconPrimalStaff = ir.func_94245_a("thaumcraft:staff_rod_primal");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int meta) {
        return meta < 50 ? this.iconWand[meta] : (meta < 100 ? this.iconStaff[meta - 50] : this.iconPrimalStaff);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack((Item)this, 1, 0));
        par3List.add(new ItemStack((Item)this, 1, 1));
        par3List.add(new ItemStack((Item)this, 1, 2));
        par3List.add(new ItemStack((Item)this, 1, 3));
        par3List.add(new ItemStack((Item)this, 1, 4));
        par3List.add(new ItemStack((Item)this, 1, 5));
        par3List.add(new ItemStack((Item)this, 1, 6));
        par3List.add(new ItemStack((Item)this, 1, 7));
        par3List.add(new ItemStack((Item)this, 1, 50));
        par3List.add(new ItemStack((Item)this, 1, 51));
        par3List.add(new ItemStack((Item)this, 1, 52));
        par3List.add(new ItemStack((Item)this, 1, 53));
        par3List.add(new ItemStack((Item)this, 1, 54));
        par3List.add(new ItemStack((Item)this, 1, 55));
        par3List.add(new ItemStack((Item)this, 1, 56));
        par3List.add(new ItemStack((Item)this, 1, 57));
        par3List.add(new ItemStack((Item)this, 1, 100));
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
    }
}

