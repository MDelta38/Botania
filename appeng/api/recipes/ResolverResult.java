/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package appeng.api.recipes;

import net.minecraft.nbt.NBTTagCompound;

public class ResolverResult {
    public final String itemName;
    public final int damageValue;
    public final NBTTagCompound compound;

    public ResolverResult(String name, int damage) {
        this.itemName = name;
        this.damageValue = damage;
        this.compound = null;
    }

    public ResolverResult(String name, int damage, NBTTagCompound data) {
        this.itemName = name;
        this.damageValue = damage;
        this.compound = data;
    }
}

