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

public class ItemGolemUpgrade
extends Item {
    public IIcon[] icon = new IIcon[6];
    public IIcon iconEmpty;

    public ItemGolemUpgrade() {
        this.func_77625_d(64);
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.iconEmpty = ir.func_94245_a("thaumcraft:golem_upgrade_empty");
        this.icon[0] = ir.func_94245_a("thaumcraft:golem_upgrade_air");
        this.icon[1] = ir.func_94245_a("thaumcraft:golem_upgrade_earth");
        this.icon[2] = ir.func_94245_a("thaumcraft:golem_upgrade_fire");
        this.icon[3] = ir.func_94245_a("thaumcraft:golem_upgrade_water");
        this.icon[4] = ir.func_94245_a("thaumcraft:golem_upgrade_order");
        this.icon[5] = ir.func_94245_a("thaumcraft:golem_upgrade_entropy");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int d) {
        return d < 0 ? this.iconEmpty : this.icon[d];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int a = 0; a <= 5; ++a) {
            par3List.add(new ItemStack((Item)this, 1, a));
        }
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
    }

    public void func_77624_a(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        list.add(StatCollector.func_74838_a((String)("item.ItemGolemUpgrade." + stack.func_77960_j() + ".desc")));
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.uncommon;
    }
}

