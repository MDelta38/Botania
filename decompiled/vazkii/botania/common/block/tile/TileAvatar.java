/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.tile;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.item.IAvatarTile;
import vazkii.botania.api.item.IAvatarWieldable;
import vazkii.botania.common.block.tile.TileSimpleInventory;

public class TileAvatar
extends TileSimpleInventory
implements IAvatarTile,
ISidedInventory {
    private static final int MAX_MANA = 6400;
    private static final String TAG_ENABLED = "enabled";
    private static final String TAG_TICKS_ELAPSED = "ticksElapsed";
    private static final String TAG_MANA = "ticksElapsed";
    boolean enabled;
    int ticksElapsed;
    int mana;

    public void func_145845_h() {
        ItemStack stack;
        super.func_145845_h();
        this.enabled = true;
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            int redstoneSide = this.field_145850_b.func_72878_l(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ, dir.ordinal());
            if (redstoneSide <= 0) continue;
            this.enabled = false;
            break;
        }
        if ((stack = this.func_70301_a(0)) != null && stack.func_77973_b() instanceof IAvatarWieldable) {
            IAvatarWieldable wieldable = (IAvatarWieldable)stack.func_77973_b();
            wieldable.onAvatarUpdate(this, stack);
        }
        if (this.enabled) {
            ++this.ticksElapsed;
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeCustomNBT(par1nbtTagCompound);
        par1nbtTagCompound.func_74757_a(TAG_ENABLED, this.enabled);
        par1nbtTagCompound.func_74768_a("ticksElapsed", this.ticksElapsed);
        par1nbtTagCompound.func_74768_a("ticksElapsed", this.mana);
    }

    @Override
    public void readCustomNBT(NBTTagCompound par1nbtTagCompound) {
        super.readCustomNBT(par1nbtTagCompound);
        this.enabled = par1nbtTagCompound.func_74767_n(TAG_ENABLED);
        this.ticksElapsed = par1nbtTagCompound.func_74762_e("ticksElapsed");
        this.mana = par1nbtTagCompound.func_74762_e("ticksElapsed");
    }

    public int func_70302_i_() {
        return 1;
    }

    @Override
    public int func_70297_j_() {
        return 1;
    }

    @Override
    public boolean func_94041_b(int i, ItemStack itemstack) {
        return itemstack != null && itemstack.func_77973_b() instanceof IAvatarTile;
    }

    public int[] func_94128_d(int p_94128_1_) {
        return new int[0];
    }

    public boolean func_102007_a(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return false;
    }

    public boolean func_102008_b(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return false;
    }

    public String func_145825_b() {
        return "avatar";
    }

    @Override
    public boolean isFull() {
        return this.mana >= 6400;
    }

    @Override
    public void recieveMana(int mana) {
        this.mana = Math.min(6400, this.mana + mana);
    }

    @Override
    public boolean canRecieveManaFromBursts() {
        return this.func_70301_a(0) != null;
    }

    @Override
    public int getCurrentMana() {
        return this.mana;
    }

    @Override
    public int getElapsedFunctionalTicks() {
        return this.ticksElapsed;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}

