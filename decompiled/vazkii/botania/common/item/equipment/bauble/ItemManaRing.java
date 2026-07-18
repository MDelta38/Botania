/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaTooltipDisplay;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemManaRing
extends ItemBauble
implements IManaItem,
IManaTooltipDisplay {
    protected static final int MAX_MANA = 500000;
    private static final String TAG_MANA = "mana";

    public ItemManaRing() {
        this("manaRing");
        this.func_77656_e(1000);
        this.setNoRepair();
    }

    public ItemManaRing(String name) {
        super(name);
        this.func_77656_e(1000);
    }

    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.RING;
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 10000));
    }

    public int getDamage(ItemStack stack) {
        float mana = this.getMana(stack);
        return 1000 - (int)(mana / (float)this.getMaxMana(stack) * 1000.0f);
    }

    public int getDisplayDamage(ItemStack stack) {
        return this.getDamage(stack);
    }

    public int getEntityLifespan(ItemStack itemStack, World world) {
        return Integer.MAX_VALUE;
    }

    public static void setMana(ItemStack stack, int mana) {
        ItemNBTHelper.setInt(stack, TAG_MANA, mana);
    }

    @Override
    public int getMana(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_MANA, 0);
    }

    @Override
    public int getMaxMana(ItemStack stack) {
        return 500000;
    }

    @Override
    public void addMana(ItemStack stack, int mana) {
        ItemManaRing.setMana(stack, Math.min(this.getMana(stack) + mana, this.getMaxMana(stack)));
        stack.func_77964_b(this.getDamage(stack));
    }

    @Override
    public boolean canReceiveManaFromPool(ItemStack stack, TileEntity pool) {
        return true;
    }

    @Override
    public boolean canReceiveManaFromItem(ItemStack stack, ItemStack otherStack) {
        return true;
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
    public float getManaFractionForDisplay(ItemStack stack) {
        return (float)this.getMana(stack) / (float)this.getMaxMana(stack);
    }
}

