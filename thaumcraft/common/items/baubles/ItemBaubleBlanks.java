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
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 */
package thaumcraft.common.items.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;

public class ItemBaubleBlanks
extends Item
implements IBauble,
IVisDiscountGear,
IRunicArmor {
    public IIcon[] icon = new IIcon[4];

    public ItemBaubleBlanks() {
        this.field_77777_bU = 1;
        this.canRepair = false;
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
        this.func_77627_a(true);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:bauble_amulet");
        this.icon[1] = ir.func_94245_a("thaumcraft:bauble_ring");
        this.icon[2] = ir.func_94245_a("thaumcraft:bauble_belt");
        this.icon[3] = ir.func_94245_a("thaumcraft:bauble_ring_iron");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return par1 <= 2 ? this.icon[par1] : this.icon[3];
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack((Item)this, 1, 0));
        par3List.add(new ItemStack((Item)this, 1, 1));
        par3List.add(new ItemStack((Item)this, 1, 2));
    }

    public BaubleType getBaubleType(ItemStack itemstack) {
        switch (itemstack.func_77960_j()) {
            case 1: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: {
                return BaubleType.RING;
            }
            case 2: {
                return BaubleType.BELT;
            }
        }
        return BaubleType.AMULET;
    }

    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
    }

    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
    }

    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
    }

    @Override
    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        if (stack.func_77960_j() >= 3 && stack.func_77960_j() <= 8 && Aspect.getPrimalAspects().get(stack.func_77960_j() - 3) == aspect) {
            return 1;
        }
        return 0;
    }

    public int func_82790_a(ItemStack stack, int par2) {
        if (stack.func_77960_j() >= 3 && stack.func_77960_j() <= 8) {
            return Aspect.getPrimalAspects().get(stack.func_77960_j() - 3).getColor();
        }
        return super.func_82790_a(stack, par2);
    }

    public String func_77653_i(ItemStack stack) {
        if (stack.func_77960_j() >= 3 && stack.func_77960_j() <= 8) {
            Aspect aspect = Aspect.getPrimalAspects().get(stack.func_77960_j() - 3);
            return StatCollector.func_74838_a((String)"item.ItemBaubleBlanks.3.name").replace("%TYPE", aspect.getName());
        }
        return super.func_77653_i(stack);
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (stack.func_77960_j() >= 3 && stack.func_77960_j() <= 8) {
            Aspect aspect = Aspect.getPrimalAspects().get(stack.func_77960_j() - 3);
            list.add(EnumChatFormatting.DARK_PURPLE + aspect.getName() + " " + StatCollector.func_74838_a((String)"tc.discount") + ": 1%");
        }
    }

    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public int getRunicCharge(ItemStack itemstack) {
        return 0;
    }
}

