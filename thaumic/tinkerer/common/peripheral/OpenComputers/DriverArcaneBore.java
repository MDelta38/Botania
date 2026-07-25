/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  li.cil.oc.api.machine.Arguments
 *  li.cil.oc.api.machine.Callback
 *  li.cil.oc.api.machine.Context
 *  li.cil.oc.api.network.ManagedEnvironment
 *  li.cil.oc.api.prefab.DriverTileEntity
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  thaumcraft.common.items.equipment.ItemElementalPickaxe
 *  thaumcraft.common.tiles.TileArcaneBore
 */
package thaumic.tinkerer.common.peripheral.OpenComputers;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverTileEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.common.items.equipment.ItemElementalPickaxe;
import thaumcraft.common.tiles.TileArcaneBore;
import thaumic.tinkerer.common.peripheral.OpenComputers.ManagedTileEntityEnvironment;

public class DriverArcaneBore
extends DriverTileEntity {
    public Class<?> getTileEntityClass() {
        return TileArcaneBore.class;
    }

    public ManagedEnvironment createEnvironment(World world, int x, int y, int z) {
        return new Enviroment((TileArcaneBore)world.func_147438_o(x, y, z));
    }

    public static final class Enviroment
    extends ManagedTileEntityEnvironment<TileArcaneBore> {
        public Enviroment(TileArcaneBore tileEntity) {
            super(tileEntity, "arcanebore");
        }

        @Callback(doc="function():boolean -- does the bore have a pickaxe")
        public Object[] hasPickaxe(Context context, Arguments arguments) {
            return new Object[]{((TileArcaneBore)this.tileEntity).hasPickaxe};
        }

        @Callback(doc="function():boolean -- does the bore have a focus")
        public Object[] hasFocus(Context context, Arguments arguments) {
            return new Object[]{((TileArcaneBore)this.tileEntity).hasFocus};
        }

        @Callback(doc="function():boolean -- is the pickaxe near broken?")
        public Object[] isPickaxeBroken(Context context, Arguments arguments) {
            ItemStack pickaxe = ((TileArcaneBore)this.tileEntity).func_70301_a(1);
            boolean nearBroken = pickaxe != null && pickaxe.func_77960_j() + 1 == pickaxe.func_77958_k();
            return new Object[]{nearBroken};
        }

        @Callback(doc="function():boolean -- Is the bore working?")
        public Object[] isWorking(Context context, Arguments arguments) {
            ItemStack pickaxe = ((TileArcaneBore)this.tileEntity).func_70301_a(1);
            boolean nearBroken = pickaxe != null && pickaxe.func_77960_j() + 1 == pickaxe.func_77958_k();
            return new Object[]{((TileArcaneBore)this.tileEntity).gettingPower() && ((TileArcaneBore)this.tileEntity).hasFocus && ((TileArcaneBore)this.tileEntity).hasPickaxe && pickaxe.func_77984_f() && !nearBroken};
        }

        @Callback(doc="function():number -- Gets bore's radius")
        public Object[] getRadius(Context context, Arguments arguments) {
            return new Object[]{1 + (((TileArcaneBore)this.tileEntity).area + ((TileArcaneBore)this.tileEntity).maxRadius) * 2};
        }

        @Callback(doc="function():number -- Gets bore's speed")
        public Object[] getSpeed(Context context, Arguments arguments) {
            return new Object[]{((TileArcaneBore)this.tileEntity).speed};
        }

        @Callback(doc="function():boolean -- Will the bore get native clusters?")
        public Object[] hasNativeClusters(Context context, Arguments arguments) {
            ItemStack pickaxe = ((TileArcaneBore)this.tileEntity).func_70301_a(1);
            return new Object[]{pickaxe != null && pickaxe.func_77973_b() instanceof ItemElementalPickaxe};
        }

        @Callback(doc="function():number -- What level fortune on pick")
        public Object[] getFortune(Context context, Arguments arguments) {
            ItemStack pickaxe = ((TileArcaneBore)this.tileEntity).func_70301_a(1);
            return new Object[]{EnchantmentHelper.func_77506_a((int)Enchantment.field_77346_s.field_77352_x, (ItemStack)pickaxe)};
        }

        @Callback(doc="function():boolean -- Does the pick have silk touch?")
        public Object[] hasSilkTouch(Context context, Arguments arguments) {
            ItemStack pickaxe = ((TileArcaneBore)this.tileEntity).func_70301_a(1);
            return new Object[]{EnchantmentHelper.func_77506_a((int)Enchantment.field_77348_q.field_77352_x, (ItemStack)pickaxe) > 0};
        }
    }
}

