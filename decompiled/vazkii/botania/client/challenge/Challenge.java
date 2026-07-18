/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package vazkii.botania.client.challenge;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import vazkii.botania.client.challenge.EnumChallengeLevel;

public class Challenge {
    public final String unlocalizedName;
    public final ItemStack icon;
    public final EnumChallengeLevel level;
    public boolean complete = false;

    public Challenge(String unlocalizedName, ItemStack icon, EnumChallengeLevel level) {
        this.unlocalizedName = unlocalizedName;
        this.icon = icon;
        this.level = level;
    }

    public void writeToNBT(NBTTagCompound cmp) {
        cmp.func_74757_a(this.unlocalizedName, this.complete);
    }

    public void readFromNBT(NBTTagCompound cmp) {
        this.complete = cmp.func_74767_n(this.unlocalizedName);
    }
}

