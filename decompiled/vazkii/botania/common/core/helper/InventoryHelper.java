/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.inventory.InventoryLargeChest
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.core.helper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class InventoryHelper {
    public static void tryInsertStack(IInventory targetInventory, int slot, ItemStack stack, boolean canMerge) {
        if (targetInventory.func_94041_b(slot, stack)) {
            ItemStack targetStack = targetInventory.func_70301_a(slot);
            if (targetStack == null) {
                int space = targetInventory.func_70297_j_();
                int mergeAmount = Math.min(space, stack.field_77994_a);
                ItemStack copy = stack.func_77946_l();
                copy.field_77994_a = mergeAmount;
                targetInventory.func_70299_a(slot, copy);
                stack.field_77994_a -= mergeAmount;
            } else if (canMerge && targetInventory.func_94041_b(slot, stack) && InventoryHelper.areMergeCandidates(stack, targetStack)) {
                int space = Math.min(targetInventory.func_70297_j_(), targetStack.func_77976_d()) - targetStack.field_77994_a;
                int mergeAmount = Math.min(space, stack.field_77994_a);
                ItemStack copy = targetStack.func_77946_l();
                copy.field_77994_a += mergeAmount;
                targetInventory.func_70299_a(slot, copy);
                stack.field_77994_a -= mergeAmount;
            }
        }
    }

    protected static boolean areMergeCandidates(ItemStack source, ItemStack target) {
        return source.func_77969_a(target) && ItemStack.func_77970_a((ItemStack)source, (ItemStack)target) && target.field_77994_a < target.func_77976_d();
    }

    public static void insertItemIntoInventory(IInventory inventory, ItemStack stack) {
        InventoryHelper.insertItemIntoInventory(inventory, stack, ForgeDirection.UNKNOWN, -1);
    }

    public static void insertItemIntoInventory(IInventory inventory, ItemStack stack, ForgeDirection side, int intoSlot) {
        InventoryHelper.insertItemIntoInventory(inventory, stack, side, intoSlot, true);
    }

    public static void insertItemIntoInventory(IInventory inventory, ItemStack stack, ForgeDirection side, int intoSlot, boolean doMove) {
        InventoryHelper.insertItemIntoInventory(inventory, stack, side, intoSlot, doMove, true);
    }

    public static void insertItemIntoInventory(IInventory inventory, ItemStack stack, ForgeDirection side, int intoSlot, boolean doMove, boolean canStack) {
        int[] attemptSlots;
        if (stack == null) {
            return;
        }
        IInventory targetInventory = inventory;
        if (!doMove) {
            targetInventory = new GenericInventory("temporary.inventory", false, targetInventory.func_70302_i_());
            ((GenericInventory)targetInventory).copyFrom(inventory);
        }
        int i = 0;
        if (inventory instanceof ISidedInventory && side != ForgeDirection.UNKNOWN) {
            attemptSlots = ((ISidedInventory)inventory).func_94128_d(side.ordinal());
            if (attemptSlots == null) {
                attemptSlots = new int[]{};
            }
        } else {
            attemptSlots = new int[inventory.func_70302_i_()];
            for (int a = 0; a < inventory.func_70302_i_(); ++a) {
                attemptSlots[a] = a;
            }
        }
        if (intoSlot > -1) {
            HashSet<Integer> x = new HashSet<Integer>();
            for (int attemptedSlot : attemptSlots) {
                x.add(attemptedSlot);
            }
            attemptSlots = x.contains(intoSlot) ? new int[]{intoSlot} : new int[]{};
        }
        while (stack.field_77994_a > 0 && i < attemptSlots.length) {
            if (side != ForgeDirection.UNKNOWN && inventory instanceof ISidedInventory && !((ISidedInventory)inventory).func_102007_a(attemptSlots[i], stack, side.ordinal())) {
                ++i;
                continue;
            }
            InventoryHelper.tryInsertStack(targetInventory, attemptSlots[i], stack, canStack);
            ++i;
        }
    }

    public static int testInventoryInsertion(IInventory inventory, ItemStack item, ForgeDirection side) {
        if (item == null || item.field_77994_a == 0) {
            return 0;
        }
        item = item.func_77946_l();
        if (inventory == null) {
            return 0;
        }
        inventory.func_70302_i_();
        int itemSizeCounter = item.field_77994_a;
        int[] availableSlots = inventory instanceof ISidedInventory ? ((ISidedInventory)inventory).func_94128_d(side.ordinal()) : InventoryHelper.buildSlotsForLinearInventory(inventory);
        for (int i : availableSlots) {
            if (itemSizeCounter <= 0) break;
            if (!inventory.func_94041_b(i, item) || side != ForgeDirection.UNKNOWN && inventory instanceof ISidedInventory && !((ISidedInventory)inventory).func_102007_a(i, item, side.ordinal())) continue;
            ItemStack inventorySlot = inventory.func_70301_a(i);
            if (inventorySlot == null) {
                itemSizeCounter -= Math.min(Math.min(itemSizeCounter, inventory.func_70297_j_()), item.func_77976_d());
                continue;
            }
            if (!InventoryHelper.areMergeCandidates(item, inventorySlot)) continue;
            int space = Math.min(inventory.func_70297_j_(), inventorySlot.func_77976_d()) - inventorySlot.field_77994_a;
            itemSizeCounter -= Math.min(itemSizeCounter, space);
        }
        if (itemSizeCounter != item.field_77994_a) {
            itemSizeCounter = Math.max(itemSizeCounter, 0);
            return item.field_77994_a - itemSizeCounter;
        }
        return 0;
    }

    public static IInventory getInventory(World world, int x, int y, int z) {
        TileEntity tileEntity = world.func_147438_o(x, y, z);
        if (tileEntity instanceof TileEntityChest) {
            Block chestBlock = world.func_147439_a(x, y, z);
            if (world.func_147439_a(x - 1, y, z) == chestBlock) {
                return new InventoryLargeChest("Large chest", (IInventory)world.func_147438_o(x - 1, y, z), (IInventory)tileEntity);
            }
            if (world.func_147439_a(x + 1, y, z) == chestBlock) {
                return new InventoryLargeChest("Large chest", (IInventory)tileEntity, (IInventory)world.func_147438_o(x + 1, y, z));
            }
            if (world.func_147439_a(x, y, z - 1) == chestBlock) {
                return new InventoryLargeChest("Large chest", (IInventory)world.func_147438_o(x, y, z - 1), (IInventory)tileEntity);
            }
            if (world.func_147439_a(x, y, z + 1) == chestBlock) {
                return new InventoryLargeChest("Large chest", (IInventory)tileEntity, (IInventory)world.func_147438_o(x, y, z + 1));
            }
        }
        return tileEntity instanceof IInventory ? (IInventory)tileEntity : null;
    }

    public static IInventory getInventory(World world, int x, int y, int z, ForgeDirection direction) {
        if (direction != null && direction != ForgeDirection.UNKNOWN) {
            x += direction.offsetX;
            y += direction.offsetY;
            z += direction.offsetZ;
        }
        return InventoryHelper.getInventory(world, x, y, z);
    }

    public static IInventory getInventory(IInventory inventory) {
        if (inventory instanceof TileEntityChest) {
            TileEntity te = (TileEntity)inventory;
            return InventoryHelper.getInventory(te.func_145831_w(), te.field_145851_c, te.field_145848_d, te.field_145849_e);
        }
        return inventory;
    }

    public static int[] buildSlotsForLinearInventory(IInventory inv) {
        int[] slots = new int[inv.func_70302_i_()];
        for (int i = 0; i < slots.length; ++i) {
            slots[i] = i;
        }
        return slots;
    }

    public static class GenericInventory
    implements IInventory {
        protected String inventoryTitle;
        protected int slotsCount;
        protected ItemStack[] inventoryContents;
        protected boolean isInvNameLocalized;

        public GenericInventory(String name, boolean isInvNameLocalized, int size) {
            this.isInvNameLocalized = isInvNameLocalized;
            this.slotsCount = size;
            this.inventoryTitle = name;
            this.inventoryContents = new ItemStack[size];
        }

        public ItemStack func_70298_a(int par1, int par2) {
            if (this.inventoryContents[par1] != null) {
                if (this.inventoryContents[par1].field_77994_a <= par2) {
                    ItemStack itemstack = this.inventoryContents[par1];
                    this.inventoryContents[par1] = null;
                    return itemstack;
                }
                ItemStack itemstack = this.inventoryContents[par1].func_77979_a(par2);
                if (this.inventoryContents[par1].field_77994_a == 0) {
                    this.inventoryContents[par1] = null;
                }
                return itemstack;
            }
            return null;
        }

        public int func_70297_j_() {
            return 64;
        }

        public int func_70302_i_() {
            return this.slotsCount;
        }

        public ItemStack func_70301_a(int i) {
            return this.inventoryContents[i];
        }

        public ItemStack getStackInSlot(Enum<?> i) {
            return this.func_70301_a(i.ordinal());
        }

        public ItemStack func_70304_b(int i) {
            if (i >= this.inventoryContents.length) {
                return null;
            }
            if (this.inventoryContents[i] != null) {
                ItemStack itemstack = this.inventoryContents[i];
                this.inventoryContents[i] = null;
                return itemstack;
            }
            return null;
        }

        public boolean isItem(int slot, Item item) {
            return this.inventoryContents[slot] != null && this.inventoryContents[slot].func_77973_b() == item;
        }

        public boolean func_94041_b(int i, ItemStack itemstack) {
            return true;
        }

        public boolean func_70300_a(EntityPlayer entityplayer) {
            return true;
        }

        public void clearAndSetSlotCount(int amount) {
            this.slotsCount = amount;
            this.inventoryContents = new ItemStack[amount];
        }

        public void readFromNBT(NBTTagCompound tag) {
            if (tag.func_74764_b("size")) {
                this.slotsCount = tag.func_74762_e("size");
            }
            NBTTagList nbttaglist = tag.func_150295_c("Items", 10);
            this.inventoryContents = new ItemStack[this.slotsCount];
            for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
                NBTTagCompound stacktag = nbttaglist.func_150305_b(i);
                byte j = stacktag.func_74771_c("Slot");
                if (j < 0 || j >= this.inventoryContents.length) continue;
                this.inventoryContents[j] = ItemStack.func_77949_a((NBTTagCompound)stacktag);
            }
        }

        public void func_70299_a(int i, ItemStack itemstack) {
            this.inventoryContents[i] = itemstack;
            if (itemstack != null && itemstack.field_77994_a > this.func_70297_j_()) {
                itemstack.field_77994_a = this.func_70297_j_();
            }
        }

        public void writeToNBT(NBTTagCompound tag) {
            tag.func_74768_a("size", this.func_70302_i_());
            NBTTagList nbttaglist = new NBTTagList();
            for (int i = 0; i < this.inventoryContents.length; ++i) {
                if (this.inventoryContents[i] == null) continue;
                NBTTagCompound stacktag = new NBTTagCompound();
                stacktag.func_74774_a("Slot", (byte)i);
                this.inventoryContents[i].func_77955_b(stacktag);
                nbttaglist.func_74742_a((NBTBase)stacktag);
            }
            tag.func_74782_a("Items", (NBTBase)nbttaglist);
        }

        public void copyFrom(IInventory inventory) {
            for (int i = 0; i < inventory.func_70302_i_(); ++i) {
                if (i >= this.func_70302_i_()) continue;
                ItemStack stack = inventory.func_70301_a(i);
                if (stack != null) {
                    this.func_70299_a(i, stack.func_77946_l());
                    continue;
                }
                this.func_70299_a(i, null);
            }
        }

        public List<ItemStack> contents() {
            return Arrays.asList(this.inventoryContents);
        }

        public String func_145825_b() {
            return null;
        }

        public boolean func_145818_k_() {
            return false;
        }

        public void func_70296_d() {
        }

        public void func_70295_k_() {
        }

        public void func_70305_f() {
        }
    }
}

