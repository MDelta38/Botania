/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package appeng.api.recipes;

import appeng.api.exceptions.MissingIngredientError;
import appeng.api.exceptions.RegistrationError;
import net.minecraft.item.ItemStack;

public interface IIngredient {
    public ItemStack getItemStack() throws RegistrationError, MissingIngredientError;

    public ItemStack[] getItemStackSet() throws RegistrationError, MissingIngredientError;

    public boolean isAir();

    public String getNameSpace();

    public String getItemName();

    public int getDamageValue();

    public int getQty();

    public void bake() throws RegistrationError, MissingIngredientError;
}

