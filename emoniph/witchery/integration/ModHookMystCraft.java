/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.event.FMLInterModComms
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package com.emoniph.witchery.integration;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.integration.ModHook;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class ModHookMystCraft
extends ModHook {
    @Override
    public String getModID() {
        return "Mystcraft";
    }

    @Override
    protected void doInit() {
        this.removeMystCraftFluid(Witchery.Fluids.FLOWING_SPIRIT.getName());
        this.removeMystCraftFluid(Witchery.Fluids.HOLLOW_TEARS.getName());
        this.removeMystCraftFluid(Witchery.Fluids.BREW.getName());
        this.removeMystCraftFluid(Witchery.Fluids.BREW_LIQUID.getName());
        this.removeMystCraftFluid(Witchery.Fluids.BREW_GAS.getName());
    }

    private void removeMystCraftFluid(String fluid) {
        NBTTagCompound nbtRoot = new NBTTagCompound();
        nbtRoot.func_74782_a("fluidsymbol", (NBTBase)new NBTTagCompound());
        NBTTagCompound nbtSymbol = nbtRoot.func_74775_l("fluidsymbol");
        nbtSymbol.func_74778_a("fluidname", fluid);
        nbtSymbol.func_74776_a("rarity", 0.0f);
        nbtSymbol.func_74776_a("grammarweight", 0.0f);
        nbtSymbol.func_74776_a("instabilityPerBlock", 10000.0f);
        FMLInterModComms.sendMessage((String)this.getModID(), (String)"fluidsymbol", (NBTTagCompound)nbtRoot);
    }

    @Override
    protected void doPostInit() {
    }

    @Override
    protected void doReduceMagicPower(EntityLivingBase entity, float factor) {
    }
}

