/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package vazkii.botania.common.block.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import vazkii.botania.common.block.tile.TileMod;
import vazkii.botania.common.item.ItemCacophonium;

public class TileCacophonium
extends TileMod {
    private static final String TAG_STACK = "stack";
    public ItemStack stack;

    public void annoyDirewolf() {
        ItemCacophonium.playSound(this.field_145850_b, this.stack, this.field_145851_c, this.field_145848_d, this.field_145849_e, 1.0f);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        super.writeCustomNBT(cmp);
        NBTTagCompound cmp1 = new NBTTagCompound();
        if (this.stack != null) {
            this.stack.func_77955_b(cmp1);
        }
        cmp.func_74782_a(TAG_STACK, (NBTBase)cmp1);
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        super.readCustomNBT(cmp);
        NBTTagCompound cmp1 = cmp.func_74775_l(TAG_STACK);
        this.stack = ItemStack.func_77949_a((NBTTagCompound)cmp1);
    }
}

