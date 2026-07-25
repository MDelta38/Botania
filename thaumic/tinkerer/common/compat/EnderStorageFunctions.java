/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.enderstorage.api.EnderStorageManager
 *  codechicken.enderstorage.storage.item.EnderItemStorage
 *  codechicken.enderstorage.storage.item.TileEnderChest
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package thaumic.tinkerer.common.compat;

import codechicken.enderstorage.api.EnderStorageManager;
import codechicken.enderstorage.storage.item.EnderItemStorage;
import codechicken.enderstorage.storage.item.TileEnderChest;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumic.tinkerer.common.item.foci.ItemFocusEnderChest;

public class EnderStorageFunctions {
    public static ItemStack onFocusRightClick(ItemStack stack, World world, EntityPlayer p, MovingObjectPosition pos) {
        boolean vanilla;
        ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
        ItemStack focus = wand.getFocusItem(stack);
        if (world.field_72995_K) {
            return stack;
        }
        if (!focus.func_77942_o()) {
            focus.func_77982_d(new NBTTagCompound());
        }
        if (pos != null) {
            TileEntity tile = world.func_147438_o(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
            if (tile instanceof TileEnderChest && p.func_70093_af()) {
                TileEnderChest chest = (TileEnderChest)tile;
                focus.func_77978_p().func_74768_a("freq", chest.freq);
                focus.func_77978_p().func_74778_a("owner", chest.owner);
                focus.func_77978_p().func_74757_a("ender", true);
                wand.setFocus(stack, focus);
                return stack;
            }
            if (world.func_147439_a(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d) == Blocks.field_150343_Z && p.func_70093_af()) {
                focus.func_77978_p().func_74768_a("freq", -1);
                focus.func_77978_p().func_74778_a("owner", p.func_146103_bH().getName());
                focus.func_77978_p().func_74757_a("ender", false);
                wand.setFocus(stack, focus);
                return stack;
            }
        }
        boolean bl = vanilla = !focus.func_77978_p().func_74767_n("ender");
        if (wand.consumeAllVis(stack, p, ItemFocusEnderChest.visUsage, true, false)) {
            if (vanilla) {
                p.func_71007_a((IInventory)p.func_71005_bN());
                world.func_72956_a((Entity)p, "mob.endermen.portal", 1.0f, 1.0f);
            } else {
                int freq = focus.func_77978_p().func_74762_e("freq");
                ((EnderItemStorage)EnderStorageManager.instance((boolean)world.field_72995_K).getStorage(EnderStorageFunctions.getOwner(focus), freq & 0xFFF, "item")).openSMPGui(p, focus.func_82833_r());
            }
        }
        return stack;
    }

    private static String getOwner(ItemStack stack) {
        return stack.func_77942_o() ? stack.func_77978_p().func_74779_i("owner") : "global";
    }

    public static void addFocusInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (stack.func_77942_o() && !stack.func_77978_p().func_74779_i("owner").equals("global")) {
            list.add(stack.func_77978_p().func_74779_i("owner"));
        }
    }

    public static String getSortingHelper(ItemStack focus) {
        boolean vanilla;
        String base = "ENDERCHEST";
        if (!focus.func_77942_o()) {
            return base + "-VANILLA";
        }
        boolean bl = vanilla = !focus.func_77978_p().func_74767_n("ender");
        if (vanilla) {
            return base + "-VANILLA";
        }
        int freq = focus.func_77978_p().func_74762_e("freq");
        return base + Integer.toString(freq) + EnderStorageFunctions.getOwner(focus);
    }
}

