/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  dan200.computercraft.api.lua.ILuaContext
 *  dan200.computercraft.api.peripheral.IComputerAccess
 *  dan200.computercraft.api.peripheral.IPeripheral
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.item.ItemStack
 *  thaumcraft.common.items.equipment.ItemElementalPickaxe
 *  thaumcraft.common.tiles.TileArcaneBore
 */
package thaumic.tinkerer.common.peripheral;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import thaumcraft.common.items.equipment.ItemElementalPickaxe;
import thaumcraft.common.tiles.TileArcaneBore;

public class PeripheralArcaneBore
implements IPeripheral {
    TileArcaneBore bore;

    public PeripheralArcaneBore(TileArcaneBore bore) {
        this.bore = bore;
    }

    public String getType() {
        return "tt_arcanebore";
    }

    public String[] getMethodNames() {
        return new String[]{"hasPickaxe", "hasFocus", "isPickaxeBroken", "isWorking", "getRadius", "getSpeed", "hasNativeClusters", "getFortune", "hasSilkTouch"};
    }

    public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) {
        ItemStack pickaxe = this.bore.func_70301_a(1);
        boolean nearBroken = pickaxe != null && pickaxe.func_77960_j() + 1 == pickaxe.func_77958_k();
        switch (method) {
            case 0: {
                return new Object[]{this.bore.hasPickaxe};
            }
            case 1: {
                return new Object[]{this.bore.hasFocus};
            }
            case 2: {
                return new Object[]{nearBroken};
            }
            case 3: {
                return new Object[]{this.bore.gettingPower() && this.bore.hasFocus && this.bore.hasPickaxe && pickaxe.func_77984_f() && !nearBroken};
            }
            case 4: {
                return new Object[]{1 + (this.bore.area + this.bore.maxRadius) * 2};
            }
            case 5: {
                return new Object[]{this.bore.speed};
            }
            case 6: {
                return new Object[]{pickaxe != null && pickaxe.func_77973_b() instanceof ItemElementalPickaxe};
            }
            case 7: {
                return new Object[]{EnchantmentHelper.func_77506_a((int)Enchantment.field_77346_s.field_77352_x, (ItemStack)pickaxe)};
            }
            case 8: {
                return new Object[]{EnchantmentHelper.func_77506_a((int)Enchantment.field_77348_q.field_77352_x, (ItemStack)pickaxe) > 0};
            }
        }
        return null;
    }

    public void attach(IComputerAccess computer) {
    }

    public void detach(IComputerAccess computer) {
    }

    public boolean equals(IPeripheral other) {
        return this.equals((Object)other);
    }
}

