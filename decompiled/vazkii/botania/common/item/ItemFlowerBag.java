/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.event.entity.player.EntityItemPickupEvent
 */
package vazkii.botania.common.item;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.helper.InventoryHelper;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ItemMod;

public class ItemFlowerBag
extends ItemMod {
    private static final String TAG_ITEMS = "InvItems";
    private static final String TAG_SLOT = "Slot";

    public ItemFlowerBag() {
        this.func_77655_b("flowerBag");
        this.func_77625_d(1);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onPickupItem(EntityItemPickupEvent event) {
        ItemStack stack = event.item.func_92059_d();
        if (stack.func_77973_b() == Item.func_150898_a((Block)ModBlocks.flower) && stack.field_77994_a > 0) {
            int color = stack.func_77960_j();
            if (color > 15) {
                return;
            }
            for (int i = 0; i < event.entityPlayer.field_71071_by.func_70302_i_(); ++i) {
                if (i == event.entityPlayer.field_71071_by.field_70461_c) continue;
                ItemStack invStack = event.entityPlayer.field_71071_by.func_70301_a(i);
                if (invStack != null && invStack.func_77973_b() == this) {
                    ItemStack[] bagInv = ItemFlowerBag.loadStacks(invStack);
                    ItemStack stackAt = bagInv[color];
                    boolean didChange = false;
                    if (stackAt == null) {
                        bagInv[color] = stack.func_77946_l();
                        stack.field_77994_a = 0;
                        didChange = true;
                    } else {
                        int stackAtSize = stackAt.field_77994_a;
                        int spare = 64 - stackAtSize;
                        int stackSize = stack.field_77994_a;
                        int pass = Math.min(spare, stackSize);
                        if (pass > 0) {
                            stackAt.field_77994_a += pass;
                            stack.field_77994_a -= pass;
                            didChange = true;
                        }
                    }
                    if (didChange) {
                        ItemFlowerBag.setStacks(invStack, bagInv);
                    }
                }
                if (stack.field_77994_a != 0) continue;
                return;
            }
        }
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        player.openGui((Object)Botania.instance, 2, world, 0, 0, 0);
        return stack;
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int s, float xs, float ys, float zs) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof IInventory) {
            if (!world.field_72995_K) {
                ForgeDirection side = ForgeDirection.getOrientation((int)s);
                IInventory inv = (IInventory)tile;
                ItemStack[] stacks = ItemFlowerBag.loadStacks(stack);
                ItemStack[] newStacks = new ItemStack[stacks.length];
                boolean putAny = false;
                int i = 0;
                for (ItemStack petal : stacks) {
                    if (petal != null) {
                        int count = InventoryHelper.testInventoryInsertion(inv, petal, side);
                        InventoryHelper.insertItemIntoInventory(inv, petal, side, -1);
                        ItemStack newPetal = petal.func_77946_l();
                        if (newPetal.field_77994_a == 0) {
                            newPetal = null;
                        }
                        newStacks[i] = newPetal;
                        putAny |= count > 0;
                    }
                    ++i;
                }
                ItemFlowerBag.setStacks(stack, newStacks);
                if (putAny && inv instanceof TileEntityChest) {
                    inv = InventoryHelper.getInventory(inv);
                    player.func_71007_a(inv);
                }
            }
            return true;
        }
        return false;
    }

    public static ItemStack[] loadStacks(ItemStack stack) {
        NBTTagList var2 = ItemNBTHelper.getList(stack, TAG_ITEMS, 10, false);
        ItemStack[] inventorySlots = new ItemStack[16];
        for (int var3 = 0; var3 < var2.func_74745_c(); ++var3) {
            NBTTagCompound var4 = var2.func_150305_b(var3);
            byte var5 = var4.func_74771_c(TAG_SLOT);
            if (var5 < 0 || var5 >= inventorySlots.length) continue;
            inventorySlots[var5] = ItemStack.func_77949_a((NBTTagCompound)var4);
        }
        return inventorySlots;
    }

    public static void setStacks(ItemStack stack, ItemStack[] inventorySlots) {
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < inventorySlots.length; ++var3) {
            if (inventorySlots[var3] == null) continue;
            NBTTagCompound var4 = new NBTTagCompound();
            var4.func_74774_a(TAG_SLOT, (byte)var3);
            inventorySlots[var3].func_77955_b(var4);
            var2.func_74742_a((NBTBase)var4);
        }
        ItemNBTHelper.setList(stack, TAG_ITEMS, var2);
    }
}

