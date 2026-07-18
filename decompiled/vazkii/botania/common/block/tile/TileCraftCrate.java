/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 */
package vazkii.botania.common.block.tile;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.common.block.tile.TileOpenCrate;
import vazkii.botania.common.item.ModItems;

public class TileCraftCrate
extends TileOpenCrate {
    public static final boolean[][] PATTERNS = new boolean[][]{{true, false, false, false, false, false, false, false, false}, {true, true, false, true, true, false, false, false, false}, {true, false, false, true, false, false, false, false, false}, {true, true, false, false, false, false, false, false, false}, {true, false, false, true, false, false, true, false, false}, {true, true, true, false, false, false, false, false, false}, {true, true, false, true, true, false, true, true, false}, {true, true, true, true, true, true, false, false, false}, {true, true, true, true, false, true, true, true, true}};
    private static final String TAG_PATTERN = "pattern";
    public int pattern = -1;
    int signal = 0;

    @Override
    public int func_70302_i_() {
        return 10;
    }

    @Override
    public int func_70297_j_() {
        return 1;
    }

    @Override
    public boolean func_94041_b(int i, ItemStack itemstack) {
        return i != 9 && !this.isLocked(i);
    }

    public boolean isLocked(int slot) {
        return this.pattern != -1 && !PATTERNS[this.pattern][slot];
    }

    @Override
    public void func_145845_h() {
        int newSignal;
        if (!this.field_145850_b.field_72995_K && (this.craft(true) && this.canEject() || this.isFull())) {
            this.ejectAll();
        }
        for (newSignal = 0; newSignal < 9 && (this.isLocked(newSignal) || this.func_70301_a(newSignal) != null); ++newSignal) {
        }
        if (newSignal != this.signal) {
            this.signal = newSignal;
            this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
        }
    }

    boolean craft(boolean fullCheck) {
        if (fullCheck && !this.isFull()) {
            return false;
        }
        InventoryCrafting craft = new InventoryCrafting(new Container(){

            public boolean func_75145_c(EntityPlayer p_75145_1_) {
                return false;
            }
        }, 3, 3);
        for (int i = 0; i < 9; ++i) {
            ItemStack stack = this.func_70301_a(i);
            if (stack == null || this.isLocked(i) || stack.func_77973_b() == ModItems.manaResource && stack.func_77960_j() == 11) continue;
            craft.func_70299_a(i, stack.func_77946_l());
        }
        List recipes = CraftingManager.func_77594_a().func_77592_b();
        for (IRecipe recipe : recipes) {
            if (!recipe.func_77569_a(craft, this.field_145850_b)) continue;
            this.func_70299_a(9, recipe.func_77572_b(craft));
            for (int i = 0; i < 9; ++i) {
                ItemStack stack = this.func_70301_a(i);
                if (stack == null) continue;
                ItemStack container = stack.func_77973_b().getContainerItem(stack);
                this.func_70299_a(i, container);
            }
            return true;
        }
        return false;
    }

    boolean isFull() {
        for (int i = 0; i < 9; ++i) {
            if (this.isLocked(i) || this.func_70301_a(i) != null) continue;
            return false;
        }
        return true;
    }

    void ejectAll() {
        for (int i = 0; i < this.func_70302_i_(); ++i) {
            ItemStack stack = this.func_70301_a(i);
            if (stack != null) {
                this.eject(stack, false);
            }
            this.func_70299_a(i, null);
            this.func_70296_d();
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeCustomNBT(par1nbtTagCompound);
        par1nbtTagCompound.func_74768_a(TAG_PATTERN, this.pattern);
    }

    @Override
    public void readCustomNBT(NBTTagCompound par1nbtTagCompound) {
        super.readCustomNBT(par1nbtTagCompound);
        this.pattern = par1nbtTagCompound.func_74762_e(TAG_PATTERN);
    }

    @Override
    public boolean onWanded(EntityPlayer player, ItemStack stack) {
        this.craft(false);
        this.ejectAll();
        return true;
    }

    public void func_70296_d() {
        super.func_70296_d();
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
    }

    @Override
    public int getSignal() {
        return this.signal;
    }

    @Override
    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        int lastPattern = this.pattern;
        super.onDataPacket(manager, packet);
        if (this.pattern != lastPattern) {
            this.field_145850_b.func_147458_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }
}

