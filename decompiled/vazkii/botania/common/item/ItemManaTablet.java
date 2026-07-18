/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import java.awt.Color;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.api.mana.ICreativeManaProvider;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaTooltipDisplay;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ItemMod;

public class ItemManaTablet
extends ItemMod
implements IManaItem,
ICreativeManaProvider,
IManaTooltipDisplay {
    IIcon[] icons;
    private static final int MAX_MANA = 500000;
    private static final String TAG_MANA = "mana";
    private static final String TAG_CREATIVE = "creative";
    private static final String TAG_ONE_USE = "oneUse";

    public ItemManaTablet() {
        this.func_77625_d(1);
        this.func_77656_e(1000);
        this.func_77655_b("manaTablet");
        this.setNoRepair();
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1));
        ItemStack fullPower = new ItemStack(par1, 1);
        ItemManaTablet.setMana(fullPower, 500000);
        par3List.add(fullPower);
        ItemStack creative = new ItemStack(par1, 1);
        ItemManaTablet.setMana(creative, 500000);
        ItemManaTablet.setStackCreative(creative);
        par3List.add(creative);
    }

    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        float mana = this.getMana(par1ItemStack);
        return par2 == 1 ? Color.HSBtoRGB(0.528f, mana / 500000.0f, 1.0f) : 0xFFFFFF;
    }

    public int getDamage(ItemStack stack) {
        if (super.getDamage(stack) != 0) {
            super.setDamage(stack, 0);
        }
        return 0;
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[2];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i);
        }
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return this.icons[Math.min(1, pass)];
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (ItemManaTablet.isStackCreative(par1ItemStack)) {
            par3List.add(StatCollector.func_74838_a((String)"botaniamisc.creative"));
        }
    }

    public boolean func_77623_v() {
        return true;
    }

    public int getEntityLifespan(ItemStack itemStack, World world) {
        return Integer.MAX_VALUE;
    }

    public static void setMana(ItemStack stack, int mana) {
        ItemNBTHelper.setInt(stack, TAG_MANA, mana);
    }

    public static void setStackCreative(ItemStack stack) {
        ItemNBTHelper.setBoolean(stack, TAG_CREATIVE, true);
    }

    public static boolean isStackCreative(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, TAG_CREATIVE, false);
    }

    @Override
    public int getMana(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_MANA, 0);
    }

    @Override
    public int getMaxMana(ItemStack stack) {
        return ItemManaTablet.isStackCreative(stack) ? 501000 : 500000;
    }

    @Override
    public void addMana(ItemStack stack, int mana) {
        if (!ItemManaTablet.isStackCreative(stack)) {
            ItemManaTablet.setMana(stack, Math.min(this.getMana(stack) + mana, 500000));
        }
    }

    @Override
    public boolean canReceiveManaFromPool(ItemStack stack, TileEntity pool) {
        return !ItemNBTHelper.getBoolean(stack, TAG_ONE_USE, false);
    }

    @Override
    public boolean canReceiveManaFromItem(ItemStack stack, ItemStack otherStack) {
        return !this.isCreative(stack);
    }

    @Override
    public boolean canExportManaToPool(ItemStack stack, TileEntity pool) {
        return true;
    }

    @Override
    public boolean canExportManaToItem(ItemStack stack, ItemStack otherStack) {
        return true;
    }

    @Override
    public boolean isNoExport(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isCreative(ItemStack stack) {
        return ItemManaTablet.isStackCreative(stack);
    }

    @Override
    public float getManaFractionForDisplay(ItemStack stack) {
        return (float)this.getMana(stack) / (float)this.getMaxMana(stack);
    }

    public boolean showDurabilityBar(ItemStack stack) {
        return !ItemManaTablet.isStackCreative(stack);
    }

    public double getDurabilityForDisplay(ItemStack stack) {
        return 1.0 - (double)this.getManaFractionForDisplay(stack);
    }
}

