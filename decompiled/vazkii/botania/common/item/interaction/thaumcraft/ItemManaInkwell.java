/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Optional$Interface
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  thaumcraft.api.IScribeTools
 */
package vazkii.botania.common.item.interaction.thaumcraft;

import cpw.mods.fml.common.Optional;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.api.IScribeTools;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ItemMod;

@Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.IScribeTools")
public class ItemManaInkwell
extends ItemMod
implements IManaItem,
IScribeTools {
    private static final int COST_PER_USE = 50;
    private static final int USES = 150;
    protected static final int MAX_MANA = 7500;
    private static final String TAG_MANA = "mana";

    public ItemManaInkwell() {
        this.func_77655_b("manaInkwell");
        this.func_77656_e(150);
        this.func_77625_d(1);
        this.setNoRepair();
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(item, 1, 150));
        list.add(new ItemStack(item));
    }

    public int getDamage(ItemStack stack) {
        float mana = this.getMana(stack);
        return 150 - (int)(mana / (float)this.getMaxMana(stack) * 150.0f);
    }

    public void setDamage(ItemStack stack, int damage) {
        int currentDamage = stack.func_77960_j();
        if (damage > currentDamage) {
            int cost = (damage - currentDamage) * 50;
            int mana = this.getMana(stack);
            if (mana >= cost) {
                this.addMana(stack, -cost);
                return;
            }
        }
        super.setDamage(stack, damage);
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
        return 7500;
    }

    @Override
    public void addMana(ItemStack stack, int mana) {
        ItemManaInkwell.setMana(stack, Math.min(this.getMana(stack) + mana, this.getMaxMana(stack)));
        stack.func_77964_b(this.getDamage(stack));
    }

    @Override
    public boolean canReceiveManaFromPool(ItemStack stack, TileEntity pool) {
        return true;
    }

    @Override
    public boolean canReceiveManaFromItem(ItemStack stack, ItemStack otherStack) {
        return false;
    }

    @Override
    public boolean canExportManaToPool(ItemStack stack, TileEntity pool) {
        return false;
    }

    @Override
    public boolean canExportManaToItem(ItemStack stack, ItemStack otherStack) {
        return false;
    }

    @Override
    public boolean isNoExport(ItemStack stack) {
        return true;
    }
}

