/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Optional$Interface
 *  cpw.mods.fml.common.Optional$InterfaceList
 *  cpw.mods.fml.common.Optional$Method
 *  dan200.computercraft.api.lua.ILuaContext
 *  dan200.computercraft.api.peripheral.IComputerAccess
 *  dan200.computercraft.api.peripheral.IPeripheral
 *  li.cil.oc.api.machine.Arguments
 *  li.cil.oc.api.machine.Callback
 *  li.cil.oc.api.machine.Context
 *  li.cil.oc.api.network.SimpleComponent
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.common.lib.crafting.ThaumcraftCraftingManager
 */
package thaumic.tinkerer.common.block.tile;

import appeng.api.movable.IMovableTile;
import cpw.mods.fml.common.Optional;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import java.util.HashMap;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;

@Optional.InterfaceList(value={@Optional.Interface(iface="li.cil.oc.api.network.SimpleComponent", modid="OpenComputers"), @Optional.Interface(iface="dan200.computercraft.api.peripheral.IPeripheral", modid="ComputerCraft")})
public class TileAspectAnalyzer
extends TileEntity
implements IInventory,
SimpleComponent,
IPeripheral,
IMovableTile {
    ItemStack[] inventorySlots = new ItemStack[1];

    public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
        super.func_145839_a(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.func_150295_c("Items", 10);
        this.inventorySlots = new ItemStack[this.func_70302_i_()];
        for (int var3 = 0; var3 < var2.func_74745_c(); ++var3) {
            NBTTagCompound var4 = var2.func_150305_b(var3);
            byte var5 = var4.func_74771_c("Slot");
            if (var5 < 0 || var5 >= this.inventorySlots.length) continue;
            this.inventorySlots[var5] = ItemStack.func_77949_a((NBTTagCompound)var4);
        }
    }

    public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
        super.func_145841_b(par1NBTTagCompound);
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.inventorySlots.length; ++var3) {
            if (this.inventorySlots[var3] == null) continue;
            NBTTagCompound var4 = new NBTTagCompound();
            var4.func_74774_a("Slot", (byte)var3);
            this.inventorySlots[var3].func_77955_b(var4);
            var2.func_74742_a((NBTBase)var4);
        }
        par1NBTTagCompound.func_74782_a("Items", (NBTBase)var2);
    }

    public int func_70302_i_() {
        return this.inventorySlots.length;
    }

    public ItemStack func_70301_a(int i) {
        return this.inventorySlots[i];
    }

    public ItemStack func_70298_a(int i, int j) {
        if (this.inventorySlots[i] != null) {
            if (this.inventorySlots[i].field_77994_a <= j) {
                ItemStack stackAt = this.inventorySlots[i];
                this.inventorySlots[i] = null;
                return stackAt;
            }
            ItemStack stackAt = this.inventorySlots[i].func_77979_a(j);
            if (this.inventorySlots[i].field_77994_a == 0) {
                this.inventorySlots[i] = null;
            }
            return stackAt;
        }
        return null;
    }

    public ItemStack func_70304_b(int i) {
        return this.func_70301_a(i);
    }

    public void func_70299_a(int i, ItemStack itemstack) {
        this.inventorySlots[i] = itemstack;
    }

    public String func_145825_b() {
        return "aspectAnalyzer";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer entityplayer) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this && entityplayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return true;
    }

    public String getType() {
        return "tt_aspectanalyzer";
    }

    public String[] getMethodNames() {
        return new String[]{"hasItem", "itemHasAspects", "getAspects", "getAspectCount"};
    }

    @Optional.Method(modid="ComputerCraft")
    public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) {
        switch (method) {
            case 0: {
                return this.hasItemMethod();
            }
            case 1: {
                return this.itemHasAspectsMethod();
            }
            case 2: {
                return this.getAspectsMethod();
            }
            case 3: {
                return this.getAspectsAmountsMethod();
            }
        }
        return null;
    }

    public Object[] hasItemMethod() {
        ItemStack stack = this.func_70301_a(0);
        return new Object[]{stack != null};
    }

    public AspectList getAspectList() {
        ItemStack stack = this.func_70301_a(0);
        AspectList aspects = null;
        if (stack != null) {
            aspects = ThaumcraftCraftingManager.getObjectTags((ItemStack)stack);
            aspects = ThaumcraftCraftingManager.getBonusTags((ItemStack)stack, (AspectList)aspects);
        }
        return aspects;
    }

    public Object[] itemHasAspectsMethod() {
        AspectList aspects = this.getAspectList();
        return new Object[]{aspects != null && aspects.size() > 0};
    }

    public Object[] getAspectsMethod() {
        AspectList aspects = this.getAspectList();
        HashMap<Double, String> retVals = new HashMap<Double, String>();
        if (aspects == null) {
            return new Object[]{retVals};
        }
        double i = 1.0;
        for (Aspect aspect : aspects.getAspectsSorted()) {
            double d = i;
            i = d + 1.0;
            retVals.put(d, aspect.getTag());
        }
        return new Object[]{retVals};
    }

    public Object[] getAspectsAmountsMethod() {
        AspectList aspects = this.getAspectList();
        HashMap<String, Double> retVals = new HashMap<String, Double>();
        if (aspects == null) {
            return new Object[]{retVals};
        }
        for (Aspect aspect : aspects.getAspectsSorted()) {
            retVals.put(aspect.getTag(), Double.valueOf(aspects.getAmount(aspect)));
        }
        return new Object[]{retVals};
    }

    @Optional.Method(modid="ComputerCraft")
    public void attach(IComputerAccess computer) {
    }

    @Optional.Method(modid="ComputerCraft")
    public void detach(IComputerAccess computer) {
    }

    @Optional.Method(modid="ComputerCraft")
    public boolean equals(IPeripheral other) {
        return this.equals((Object)other);
    }

    @Override
    public boolean prepareToMove() {
        return true;
    }

    @Override
    public void doneMoving() {
    }

    public String getComponentName() {
        return this.getType();
    }

    @Callback
    @Optional.Method(modid="OpenComputers")
    public Object[] greet(Context context, Arguments args) {
        return new Object[]{String.format("Hello, %s!", args.checkString(0))};
    }

    @Callback(doc="function():boolean -- Whether this inventory contains an item")
    @Optional.Method(modid="OpenComputers")
    public Object[] hasItem(Context context, Arguments args) {
        return this.hasItemMethod();
    }

    @Callback(doc="function():boolean -- Whether the item contains aspects")
    @Optional.Method(modid="OpenComputers")
    public Object[] itemHasAspects(Context context, Arguments args) {
        return this.itemHasAspectsMethod();
    }

    @Callback(doc="function():table -- Returns a list of all available aspects")
    @Optional.Method(modid="OpenComputers")
    public Object[] getAspects(Context context, Arguments args) {
        return this.getAspectsMethod();
    }

    @Callback(doc="function():table -- returns a mapping of all aspect counts")
    @Optional.Method(modid="OpenComputers")
    public Object[] getAspectCount(Context context, Arguments args) {
        return this.getAspectsAmountsMethod();
    }
}

