/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  minetweaker.MineTweakerAPI
 *  minetweaker.api.item.IIngredient
 *  minetweaker.api.item.IItemStack
 *  minetweaker.api.item.IngredientStack
 *  minetweaker.api.minecraft.MineTweakerMC
 *  minetweaker.api.oredict.IOreDictEntry
 *  net.minecraft.item.ItemStack
 */
package witchinggadgets.common.minetweaker;

import cpw.mods.fml.relauncher.ReflectionHelper;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.item.IngredientStack;
import minetweaker.api.minecraft.MineTweakerMC;
import minetweaker.api.oredict.IOreDictEntry;
import net.minecraft.item.ItemStack;
import witchinggadgets.common.minetweaker.GemCutting;
import witchinggadgets.common.minetweaker.InfernalBlastfurnace;
import witchinggadgets.common.minetweaker.SpinningWheel;

public class WGMinetweaker {
    public static void init() {
        MineTweakerAPI.registerClass(SpinningWheel.class);
        MineTweakerAPI.registerClass(InfernalBlastfurnace.class);
        MineTweakerAPI.registerClass(GemCutting.class);
    }

    public static ItemStack toStack(IItemStack iStack) {
        return MineTweakerMC.getItemStack((IItemStack)iStack);
    }

    public static Object toObject(IIngredient iStack) {
        if (iStack == null) {
            return null;
        }
        if (iStack instanceof IOreDictEntry) {
            return ((IOreDictEntry)iStack).getName();
        }
        if (iStack instanceof IItemStack) {
            return MineTweakerMC.getItemStack((IItemStack)((IItemStack)iStack));
        }
        if (iStack instanceof IngredientStack) {
            IIngredient ingr = (IIngredient)ReflectionHelper.getPrivateValue(IngredientStack.class, (Object)((IngredientStack)iStack), (String[])new String[]{"ingredient"});
            return WGMinetweaker.toObject(ingr);
        }
        return null;
    }

    public static Object[] toObjects(IIngredient[] iStacks) {
        Object[] oA = new Object[iStacks.length];
        for (int i = 0; i < iStacks.length; ++i) {
            oA[i] = WGMinetweaker.toObject(iStacks[i]);
        }
        return oA;
    }
}

